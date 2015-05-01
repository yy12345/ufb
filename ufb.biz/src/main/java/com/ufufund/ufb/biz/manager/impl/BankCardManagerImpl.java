package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.BankCardManagerHelper;
import com.ufufund.ufb.biz.manager.impl.helper.CustManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.BankCardManagerValidator;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.biz.util.HftResponseUtil;
import com.ufufund.ufb.dao.BankBaseMapper;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriResponse;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountResponse;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.remote.HftCustService;

@Service
public class BankCardManagerImpl extends ImplCommon implements BankCardManager{

	@Autowired
	private CustManagerValidator custManagerValidator;
	@Autowired
	private BankCardManagerValidator bankCardManagerValidator;
	@Autowired
	private BankCardManagerHelper bankCardManagerHelper;
	@Autowired
	private CustManagerHelper custManagerHelper;
	@Autowired
	private HftCustService hftCustService;
	@Autowired
	private CustinfoMapper custinfoMapper;
	@Autowired
	private BankBaseMapper bankBaseMapper;
	@Autowired
	private BankMapper bankCardMapper;
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	@Autowired
	private WorkDayManager workDayManager;
	
	@Override
	public List<Bankcardinfo> getBankcardinfoList(String custno){
		
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(custno);
		
		return bankCardMapper.getBankcardinfo(bankcardinfo);
	}
	
	/**
	 *  1 验证身份
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction) throws BizException {
		this.getProcessId(openAccountAction);
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		bankCardManagerValidator.validator(openAccountAction, "User_Base");
		// 用户注册、冻结、已开户验证
		custManagerValidator.validator(openAccountAction, "User_Business");
		
		return openAccountAction;
	}
	
	/**
	 *  2 银行快捷鉴权
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction) throws BizException {
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "Bank_Base");
		
		// 生成流水号
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		openAccountAction.setAccoreqSerial(tradeNotesMapper.getAccoreqSerialSeq());
		
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
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction) throws BizException {
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "Bank_Base");
		
		// 执行银行验证交易
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		BankVeriRequest request = bankCardManagerHelper.toBankVeriRequest(openAccountAction);
		BankVeriResponse response = hftCustService.bankVeri(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		openAccountAction.setProtocolno(response.getProtocolNo());
		return openAccountAction;
	}

	/**
	 *  4 开户
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public void openAccount4(OpenAccountAction openAccountAction) throws BizException {
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		bankCardManagerValidator.validator(openAccountAction, "User_Base");
		// 用户注册、冻结、已开户验证
		custManagerValidator.validator(openAccountAction, "User_Business");
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "Bank_Base");
		
		// 执行开户交易
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		OpenAccountRequest request = bankCardManagerHelper.toOpenAccountRequest(openAccountAction);
		OpenAccountResponse response = hftCustService.openAccount(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		// *** 开户成功，更新custinfo表的交易帐号、投资人姓名、证件类型、证件号、开户状态、交易密码
		openAccountAction.setTransactionAccountID(response.getTransactionAccountID());
		Custinfo custinfo = custManagerHelper.toOpenAccountAction(openAccountAction);
		custinfoMapper.updateCustinfo(custinfo);
		
		Bankcardinfo bankcardinfodef = null;
		Bankcardinfo bankcardinfoqey = new Bankcardinfo();
		bankcardinfoqey.setCustno(custinfo.getCustno());
		List<Bankcardinfo> bankList = bankCardMapper.getBankcardinfo(bankcardinfoqey);
		for(Bankcardinfo bankcardinfo : bankList){
			if(bankcardinfo.getBankno()!=null && bankcardinfo.getBankno().equals(openAccountAction.getBankno())){
				bankcardinfodef = bankcardinfo;
				break;
			}
		}
		if(bankcardinfodef==null){
			bankcardinfodef = bankCardManagerHelper.toBankcardinfo(openAccountAction);
			String bankSeq = bankCardMapper.getBankcardinfoSequence();
			bankcardinfodef.setSerialid(bankSeq);
			bankcardinfodef.setState("Y");
			bankCardMapper.insterBankcardinfo(bankcardinfodef);
			Changerecordinfo changerecordinfo1 = bankCardManagerHelper.toBankcardinfo(bankcardinfodef);
			changerecordinfo1.setApkind(Apkind.OPEN_ACCOUNT.getValue());
			changerecordinfo1.setRefserialno(openAccountAction.getSerialno());
			// **** 变更表
			tradeNotesMapper.insterChangerecordinfo(changerecordinfo1);
		}		
		
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setCustno(openAccountAction.getCustno());// char(10) not null comment '客户编号',
		tradeaccoinfo.setFundcorpno(openAccountAction.getMerchant().Value());// char(2) not null default '' comment '交易账号类型：归属基金公司',
		tradeaccoinfo.setBankserialid(bankcardinfodef.getSerialid());// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
		tradeaccoinfo.setTradeacco(openAccountAction.getTransactionAccountID());// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
		bankCardMapper.insterTradeaccoinfo(tradeaccoinfo);

		// *** 插入流水表
		Fdacfinalresult fdacfinalresult = new  Fdacfinalresult();//helper.toFdacfinalresult(custinfo);
		fdacfinalresult.setCustno(custinfo.getCustno());
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setBankserialid(bankcardinfodef.getSerialid());
		fdacfinalresult.setTradeaccoid(openAccountAction.getTransactionAccountID());
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(openAccountAction.getSerialno());
		fdacfinalresult.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		Changerecordinfo changerecordinfo2 = new Changerecordinfo();
		changerecordinfo2.setCustno(custinfo.getCustno());
		changerecordinfo2.setRecordafter(custinfo.toString());
		changerecordinfo2.setTablename(TableName.CUSTINFO.value());
		changerecordinfo2.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo2.setRefserialno(openAccountAction.getSerialno());
		// **** 变更表
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo2);	
		
		Changerecordinfo changerecordinfo3 = bankCardManagerHelper.toTradeaccoinfo(tradeaccoinfo);
		changerecordinfo3.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo3.setRefserialno(openAccountAction.getSerialno());
		// **** 变更表
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo3);
	}
}