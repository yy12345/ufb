package com.ufufund.ufb.biz.manager.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.convert.BankConvert;
import com.ufufund.ufb.biz.convert.CustConvert;
import com.ufufund.ufb.biz.exception.AppException;
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.validator.CustManagerValidator;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.LoginAction;
import com.ufufund.ufb.model.action.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.DateInfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.Invtp;

@Service
public class CustManagerImpl implements CustManager {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private String processId = "["+UUID.randomUUID().toString()+"] ";

	@Autowired
	private CustinfoMapper custinfoMapper;

	@Autowired
	private CustManagerValidator custManagerValidator = new CustManagerValidator(processId);

	@Autowired
	private BankManager bankManager;

	@Autowired
	private TradeNotesMapper tradeNotesMapper;

	/**
	 * 查询手机号是否注册
	 * 
	 * @param String
	 *            mobile
	 * @return
	 */
	public boolean isMobileRegister(String mobile) throws BizException {
		boolean res = false;
		log.debug(processId+ "查询手机号是否注册  mobile :" + mobile);
		custManagerValidator.isMobile(mobile);
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(mobile);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}

	/**
	 * 查询该身份证是否注册
	 * 
	 * @param idCardNo
	 * @return
	 */
	public boolean isIdCardNoRegister(String idCardNo) throws BizException {
		log.debug(processId+ "查询该身份证是否注册  idCardNo :" + idCardNo);
		boolean res = false;
		custManagerValidator.isIdCardNo(idCardNo);
		Custinfo custinfo = new Custinfo();
		custinfo.setIdno(idCardNo);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}

	/**
	 * 注册
	 * 
	 * @param LoginAction
	 * @return
	 */
	public void register(LoginAction loginAction) throws BizException {
		log.debug(processId+ " 注册 :" + loginAction.toString());
		/*
		 * 先验证验证码
		 */
		loginAction.setLoginType(Apkind.REGISTER);
		custManagerValidator.validator(loginAction);
		
		/*
		 * 插入客户信息表
		 */
		Custinfo custinfo = CustConvert.convertCustinfo(loginAction);
		custinfo.setCustno(custinfoMapper.getCustinfoSequence());
		custinfoMapper.insterCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.REGISTER.getValue());
	}

	/**
	 * 登录
	 * 
	 * @param LoginAction
	 * @return
	 */
	@Override
	public void loginIn(LoginAction loginAction) throws BizException {
		log.debug(processId+ " 登录 :" + loginAction.toString());
		// TODO Auto-generated method stub
		/*
		 * 先验证验证码
		 */
		loginAction.setLoginType(Apkind.LOGININ);
		custManagerValidator.validator(loginAction);
		/*
		 * 加载信息
		 */
		Custinfo custinfo = new Custinfo();
		if (RegexUtil.isMobile(loginAction.getLoginCode())) {
			custinfo.setMobileno(loginAction.getLoginCode());
		} else if (RegexUtil.isIdCardNo(loginAction.getLoginCode())) {
			custinfo.setIdno(loginAction.getLoginCode());
		} else {
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE.value());
		}
		custinfo.setInvtp(loginAction.getInvtp().getValue() + "");
		// custinfoAction.setPasswd(loginAction.getLoginPassword());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null || "".equals(custinfo.getCustno())) {
			throw new BizException(processId, ErrorInfo.NO_IDCARDNO.value());
		}
		custinfo.setLastlogintime("");// 当前时间
		if (!loginAction.getLoginPassword().equals(custinfo.getPasswd())) {
			if (custinfo.getPasswderr() == 4) {
				//custinfo.setCustst(Constant.CUSTST$N);
			}
			custinfo.setPasswderr(custinfo.getPasswderr() + 1);
			custinfoMapper.updateCustinfo(custinfo);
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD.value());
		}
		/*
		 * 登录
		 */
		custinfoMapper.updateCustinfo(custinfo);

	}

	/**
	 * 根据缓存获取custno 获取客户信息 判断是否具有身份证 没有 必须完善个人信息绑卡
	 * 
	 * @param custno
	 * @return
	 */
	public Custinfo getCustinfo(String custno) throws BizException {
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		return custinfo;
	}

	

	/**
	 * 有身份证信息的绑卡 开户绑卡
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public void openAccount(OpenAccountAction openAccountAction) throws BizException {
		Custinfo custinfo = this.getCustinfo(openAccountAction.getCustno());
		if(custinfo!=null && Constant.CUSTST$N.equals(custinfo.getCustst())){
			custinfo.setInvnm(openAccountAction.getInvnm());
			custinfo.setIdno(openAccountAction.getIdno());
			custinfo.setTradepwd(openAccountAction.getTradepwd());
			custinfo.setInvtp(Invtp.PERSONAL.getValue()+"");
			custinfo.setIdtp(Constant.IDTP$0);
			custManagerValidator.validator(custinfo);
			if (this.isIdCardNoRegister(openAccountAction.getIdno())) {
				throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD.value());
			}
		}
		custManagerValidator.validator(openAccountAction);
		/*
		 * 进行XML接口开户鉴权验证
		 */
		Bankcardinfo bankcardinfo = BankConvert.converBankcardinfo(openAccountAction);
		bankManager.insterBankcardinfo(bankcardinfo);
		Tradeaccoinfo tradeaccoinfo = BankConvert.converTradeaccoinfo(openAccountAction);
		bankManager.insterTradeaccoinfo(tradeaccoinfo);
		/*
		 * 插入流水表
		 */
		Fdacfinalresult fdacfinalresult = new  Fdacfinalresult();//CustConvert.convertFdacfinalresult(custinfo);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		if(custinfo!=null && Constant.CUSTST$N.equals(custinfo.getCustst())){
			this.updateCustinfo(custinfo);
		}
	}
	
	
	


	public void updateCustinfo(Custinfo custinfo) throws BizException {
		// TODO Auto-generated method stub
		custinfoMapper.updateCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.CHANGE_PASSWORD.getValue());
	}
	
	private void insterSerialno(Custinfo custinfo,String apkind) throws BizException {
		/*
		 * 插入流水表
		 */
		String seq = tradeNotesMapper.getFdacfinalresultSeq();
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		fdacfinalresult.setCustno(custinfo.getCustno());
		DateInfo dateInfo = tradeNotesMapper.getDateInfo();
		fdacfinalresult.setWorkdate(dateInfo.getWorkdate());
		fdacfinalresult.setApdt(dateInfo.getApdt());
		fdacfinalresult.setAptm(dateInfo.getAptm());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(apkind);
		/*
		 * 
		 * 插入变动记录表
		 */
		Changerecordinfo changerecordinfo = CustConvert.convertChangerecordinfo(custinfo);
		changerecordinfo.setApkind(apkind);
		changerecordinfo.setRefserialno(seq);
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
	}

}
