package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.BankCardManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.BankCardManagerValidator;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.biz.util.HftResponseUtil;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.dao.BankCardInfoMapper;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.chinapay.Response;
import com.ufufund.ufb.model.chinapay.TransDetail;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.hftfund.BankAuthRequest;
import com.ufufund.ufb.model.hftfund.BankAuthResponse;
import com.ufufund.ufb.model.hftfund.BankVeriRequest;
import com.ufufund.ufb.model.hftfund.BankVeriResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountResponse;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.remote.chinapay.ChinapayService;
import com.ufufund.ufb.remote.hftfund.HftCustService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankCardManagerImpl implements BankCardManager{

	@Autowired
	private BankCardManagerHelper bankCardManagerHelper;
	@Autowired
	private HftCustService hftCustService;
	@Autowired
	private CustinfoMapper custinfoMapper;
	@Autowired
	private BankCardInfoMapper bankCardInfoMapper;
	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private ChinapayService chinapayService;
	@Autowired
	private BankCardManagerValidator bankCardManagerValidator;
	@Autowired
	private CustManagerValidator custManagerValidator;
	
	
	@Override
	public List<Bankcardinfo> getBankcardinfoList(String custno){
		
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(custno);
		
		return bankCardInfoMapper.getBankcardinfo(bankcardinfo);
	}
	
	/**
	 * @param OpenAccountAction
	 * @return
	 */
	public OpenAccountAction openAccoStep1(OpenAccountAction openAccountAction)  {
		
		return openAccountAction;
	}
	
	/**
	 * @param OpenAccountAction
	 * @return
	 */
	public OpenAccountAction openAccoStep2(OpenAccountAction openAccountAction)  {
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		bankCardManagerValidator.validator(openAccountAction, "OrgBase");
		
		return openAccountAction;
	}
	
	/**
	 * @param OpenAccountAction
	 * @return
	 */
	public OpenAccountAction openAccoStep3(OpenAccountAction openAccountAction)  {
		
		return openAccountAction;
	}
	
	/**
	 * @param openAccountAction
	 * @return
	 */
	public void openAccountOrg(OpenAccountAction openAccountAction)  {
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		bankCardManagerValidator.validator(openAccountAction, "OrgBase");
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "OrgBankBase");
		
		// 是否已绑卡
		openAccountAction.setBankacco(openAccountAction.getBankacco().trim());
		if(0 != tradeAccoinfoMapper.isTradeaccoinfoBind(openAccountAction)){
			throw new UserException("系统异常！");
		}
		
		// 执行开户交易
		openAccountAction.setSerialno(SequenceUtil.getSerial());
		OpenAccountOrgRequest request = bankCardManagerHelper.toOpenAccountOrgRequest(openAccountAction);
		OpenAccountOrgResponse response = hftCustService.openAccountOrg(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		// *** 开户成功，更新custinfo表的交易帐号、投资人姓名、证件类型、证件号、开户状态、交易密码
		openAccountAction.setTransactionaccountid(response.getTransactionAccountID());
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		
		custinfoMapper.updateCustinfo(custinfo);
		Changerecordinfo changerecordinfo2 = new Changerecordinfo();
		changerecordinfo2.setCustno(custinfo.getCustno());
		changerecordinfo2.setRecordafter(custinfo.toString());
		changerecordinfo2.setTablename(TableName.CUSTINFO.value());
		changerecordinfo2.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo2.setRefserialno(openAccountAction.getSerialno());
		// **** 变更表
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo2);	
		
		// 检查此银行卡是否已有记录
		Bankcardinfo bankcardinfodef = null;
		Bankcardinfo bankcardinfoqey = new Bankcardinfo();
		bankcardinfoqey.setCustno(custinfo.getCustno());
		List<Bankcardinfo> bankList = bankCardInfoMapper.getBankcardinfo(bankcardinfoqey);
		for(Bankcardinfo bankcardinfo : bankList){
			if(bankcardinfo.getBankno()!=null && 
			   bankcardinfo.getBankno().equals(openAccountAction.getBankno())&&
			   bankcardinfo.getBankacco().equals(openAccountAction.getBankacco())){
			   bankcardinfodef = bankcardinfo;
			   break;
			}
		}
		// 如果此银行卡没有记录，添加银行卡
		if(bankcardinfodef==null){
			bankcardinfodef = bankCardManagerHelper.toBankcardinfo(openAccountAction);
			String bankSeq = SequenceUtil.getSerial();
			bankcardinfodef.setSerialid(bankSeq);
			bankcardinfodef.setState("Y");
			// **** 添加银行卡
			bankCardInfoMapper.insterBankcardinfo(bankcardinfodef);
			Changerecordinfo changerecordinfo1 = bankCardManagerHelper.toBankcardinfo(bankcardinfodef);
			changerecordinfo1.setApkind(Apkind.OPEN_ACCOUNT.getValue());
			changerecordinfo1.setRefserialno(openAccountAction.getSerialno());
			// **** 变更表
			tradeNotesMapper.insterChangerecordinfo(changerecordinfo1);
		}		
		// 添加tradeacco表
		Today today = workDayManager.getSysDayInfo();
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setCustno(openAccountAction.getCustno()); 
		tradeaccoinfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno); 
		tradeaccoinfo.setLevel(openAccountAction.getLevel());
		tradeaccoinfo.setBankserialid(bankcardinfodef.getSerialid()); 
		tradeaccoinfo.setTradeacco(openAccountAction.getTransactionaccountid()); 
		tradeaccoinfo.setOpendt(today.getWorkday());
		tradeAccoinfoMapper.insterTradeaccoinfo(tradeaccoinfo);

		// *** 插入流水表
		Fdacfinalresult fdacfinalresult = new  Fdacfinalresult(); 
		fdacfinalresult.setCustno(custinfo.getCustno());
		fdacfinalresult.setTobankserialid(bankcardinfodef.getSerialid());
		fdacfinalresult.setTotradeacco(openAccountAction.getTransactionaccountid());
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(openAccountAction.getSerialno());
		fdacfinalresult.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		fdacfinalresult.setTofundcorpno(Constant.HftSysConfig.MerchantId);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		Changerecordinfo changerecordinfo3 = bankCardManagerHelper.toTradeaccoinfo(tradeaccoinfo);
		changerecordinfo3.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo3.setRefserialno(openAccountAction.getSerialno());
		// **** 变更表
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo3);
	}
	
	/**
	 *  1 验证身份
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction)  {
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		bankCardManagerValidator.validator(openAccountAction, "UserBase");
		// 用户注册、冻结、已开户验证
		custManagerValidator.validator(openAccountAction, "UserBase");
		
		if(openAccountAction.getHftorganizationtradeaccoct() == 0){
			// 校验是否与登录密码一致（已绑卡开户的用户不需要再次验证密码）
			Custinfo custinfo = new Custinfo();
			custinfo.setCustno(openAccountAction.getCustno());
			custinfo = custinfoMapper.getCustinfo(custinfo);
			String md5 = EncryptUtil.md5(openAccountAction.getTradepwd());
			if(md5.equals(custinfo.getPasswd())){
				// 交易密码不能和登录密码相同
				throw new UserException("系统异常！");
			}
		}
		
		return openAccountAction;
	}
	
	/**
	 *  2 银行快捷鉴权
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction)  {
		// 银行基本信息验证
		//bankCardManagerValidator.validator(openAccountAction, "UserBankBase");
		
		// 生成流水号
		openAccountAction.setSerialno(SequenceUtil.getSerial());
		openAccountAction.setAccoreqserial(SequenceUtil.getSerial());
		
		// 执行鉴权交易
		BankAuthRequest request = bankCardManagerHelper.toBankAuthRequest(openAccountAction);
		BankAuthResponse response = hftCustService.bankAuth(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		return openAccountAction;
	}

	/**
	 *  3 银行手机验证
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction)  {
		// 银行基本信息验证
//		bankCardManagerValidator.validator(openAccountAction, "UserBankBase");
		
		// 执行银行验证交易
		openAccountAction.setSerialno(SequenceUtil.getSerial());
		BankVeriRequest request = bankCardManagerHelper.toBankVeriRequest(openAccountAction);
		BankVeriResponse response = hftCustService.bankVeri(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		openAccountAction.setProtocolno(response.getProtocolNo());
		return openAccountAction;
	}
	
	/**
	 *  3 银行手机验证
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount4(OpenAccountAction openAccountAction)  {
		// 银行基本信息验证
//		bankCardManagerValidator.validator(openAccountAction, "UserBankBase");
		
		// 执行开户交易
		openAccountAction.setSerialno(SequenceUtil.getSerial());
		OpenAccountRequest request = bankCardManagerHelper.toOpenAccountRequest(openAccountAction);
		OpenAccountResponse response = hftCustService.openAccount(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		openAccountAction.setTransactionaccountid(response.getTransactionAccountID());
		return openAccountAction;
	}
	
	/**
	 * 添加银行卡
	 * @param openAccountAction
	 */
	public String addBankCardinfo(OpenAccountAction openAccountAction){
		Bankcardinfo bankcardinfo = bankCardManagerHelper.toBankcardinfo(openAccountAction);
		bankcardinfo.setSerialid(SequenceUtil.getSerial());
		bankcardinfo.setState("Y");
		bankCardInfoMapper.insterBankcardinfo(bankcardinfo);
		
		return bankcardinfo.getSerialid();
	}
	
	public void addTradeaccoinfo(OpenAccountAction openAccountAction, String bankSerialid){
		
		Today today = workDayManager.getSysDayInfo();
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setCustno(openAccountAction.getCustno());
		tradeaccoinfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeaccoinfo.setLevel(openAccountAction.getLevel());
		tradeaccoinfo.setBankserialid(bankSerialid);
		tradeaccoinfo.setTradeacco(openAccountAction.getTransactionaccountid());
		tradeaccoinfo.setOpendt(today.getWorkday());
		tradeAccoinfoMapper.insterTradeaccoinfo(tradeaccoinfo);
	}
	
	/**
	 * 银联验证
	 */
	@Override
	public void checkAccount(OpenAccountAction openAccountAction){
		try{
		TransDetail tDetail = new TransDetail();
//		tDetail.setBANK_CODE(openAccountAction.getBankno());
//		tDetail.setACCOUNT_NO(openAccountAction.getBankacco());
//		tDetail.setACCOUNT_NAME(openAccountAction.getBankacnm());
//		tDetail.setID_TYPE(openAccountAction.getIdtp());
//		tDetail.setID(openAccountAction.getBankidno());
//		tDetail.setTEL(openAccountAction.getBankmobile());
		tDetail.setBANK_CODE("105");
		tDetail.setACCOUNT_NO("6227001823260036733");
		tDetail.setACCOUNT_NAME("吴小龄");
		tDetail.setID_TYPE("0");
		tDetail.setID("441509876512014787");
		tDetail.setTEL("13602459062");
		Response response = chinapayService
				.checkAccount(String.valueOf(System.currentTimeMillis()), tDetail);
		
		if("0000".equals(response.getINFO().getRET_CODE())){
			if("0000".equals(response.getBODY().getRET_DETAIL().get(0).getRET_CODE())){
				log.info("认证成功！");
			}else{
				log.info("认证失败：code="+response.getBODY().getRET_DETAIL().get(0).getRET_CODE()
						+", msg="+response.getBODY().getRET_DETAIL().get(0).getERR_MSG());
				throw new UserException("银联认证失败");
			}
		}else{
			log.error("系统异常：code="+response.getINFO().getRET_CODE()
					+", msg="+response.getINFO().getERR_MSG());
			throw new UserException("系统异常");
		}
		}catch(UserException e){
			throw new UserException("系统异常");
		}
	}

	@Override
	@Transactional
	public void updateCard(OpenAccountAction openAccountAction) {
		bankCardInfoMapper.removeCard(openAccountAction.getCustno());
		// 添加银行卡及基金交易账户
		openAccountAction.setCustno(openAccountAction.getCustno());
		String bankSerialid = addBankCardinfo(openAccountAction);
		addTradeaccoinfo(openAccountAction, bankSerialid);
	}

	@Override
	public Bankcardinfo getBankcardinfo(String custno) {
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(custno);
		List<Bankcardinfo> list=bankCardInfoMapper.getBankcardinfo(bankcardinfo);
		if(list.size()>0&&null!=list){
			return (Bankcardinfo)list.get(0);
		}
		return null;
	}

	@Override
	public void deleteBankCard(String serialid) {
		bankCardInfoMapper.deleteBankCard(serialid);
	}

}
