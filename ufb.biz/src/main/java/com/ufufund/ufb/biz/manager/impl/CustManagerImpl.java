package com.ufufund.ufb.biz.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.biz.convert.BankConvert;
import com.ufufund.ufb.biz.convert.CustConvert;
import com.ufufund.ufb.biz.manager.BankManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.validator.CustManagerValidator;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.enums.Invtp;
import com.ufufund.ufb.model.model.Bankcardinfo;
import com.ufufund.ufb.model.model.Changerecordinfo;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.model.model.DateInfo;
import com.ufufund.ufb.model.model.Fdacfinalresult;
import com.ufufund.ufb.model.model.Tradeaccoinfo;

@Service
public class CustManagerImpl implements CustManager {

	private static final Logger log = LoggerFactory.getLogger(CustManagerImpl.class);

	@Autowired
	private CustinfoMapper custinfoMapper;

	@Autowired
	private CustManagerValidator custManagerValidator;

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
	public boolean isMobileRegister(String mobile) throws Exception {
		boolean res = false;
		if (!RegexUtil.isMobile(mobile)) {
			throw new Exception();
		}
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
	public boolean isIdCardNoRegister(String idCardNo) throws Exception {
		boolean res = false;
		if (!RegexUtil.isIdCardNo(idCardNo)) {
			throw new Exception();
		}
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
	public void register(LoginAction loginAction) throws Exception {
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
	public void loginIn(LoginAction loginAction) throws Exception {
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
			throw new Exception();
		}
		custinfo.setInvtp(loginAction.getInvtp().getValue() + "");
		// custinfoAction.setPasswd(loginAction.getLoginPassword());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null || "".equals(custinfo.getCustno())) {
			throw new Exception();
		}
		custinfo.setLastlogintime("");// 当前时间
		if (!loginAction.getLoginPassword().equals(custinfo.getPasswd())) {
			if (custinfo.getPasswderr() == 4) {
				//custinfo.setCustst(Constant.CUSTST$N);
			}
			custinfo.setPasswderr(custinfo.getPasswderr() + 1);
			custinfoMapper.updateCustinfo(custinfo);
			throw new Exception();
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
	public Custinfo getCustinfo(String custno) throws Exception {
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
	public void openAccount(OpenAccountAction openAccountAction) throws Exception {
		Custinfo custinfo = this.getCustinfo(openAccountAction.getCustno());
		if(custinfo!=null && Constant.CUSTST$N.equals(custinfo.getCustst())){
			custinfo.setInvnm(openAccountAction.getInvnm());
			custinfo.setIdno(openAccountAction.getIdno());
			custinfo.setTradepwd(openAccountAction.getTradepwd());
			custinfo.setInvtp(Invtp.PERSONAL.getValue()+"");
			custinfo.setIdtp(Constant.IDTP$0);
			custManagerValidator.validator(custinfo);
			if (this.isIdCardNoRegister(openAccountAction.getIdno())) {
				throw new Exception();
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
	
	
	


	public void updateCustinfo(Custinfo custinfo) throws Exception {
		// TODO Auto-generated method stub
		custinfoMapper.updateCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.CHANGE_PASSWORD.getValue());
	}
	
	private void insterSerialno(Custinfo custinfo,String apkind) throws Exception {
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
