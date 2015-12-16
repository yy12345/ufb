package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.helper.BankCardManagerHelper;
import com.ufufund.ufb.biz.manager.validator.BankCardManagerValidator;
import com.ufufund.ufb.biz.util.HftResponseUtil;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.dao.BankCardInfoMapper;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.chinapay.Response;
import com.ufufund.ufb.model.chinapay.TransDetail;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Today;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.hftfund.BankAuthRequest;
import com.ufufund.ufb.model.hftfund.BankAuthResponse;
import com.ufufund.ufb.model.hftfund.BankVeriRequest;
import com.ufufund.ufb.model.hftfund.BankVeriResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountResponse;
import com.ufufund.ufb.remote.chinapay.ChinapayService;
import com.ufufund.ufb.remote.hftfund.HftCustService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankCardManager{

	@Autowired
	private BankCardManagerHelper bankCardManagerHelper;
	@Autowired
	private HftCustService hftCustService;
	@Autowired
	private BankCardInfoMapper bankCardInfoMapper;
	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private ChinapayService chinapayService;
	@Autowired
	private BankCardManagerValidator bankCardManagerValidator;
	
	
	/**
	 * 获取用户绑定的银行卡列表
	 * @param custno
	 * @return
	 */
	public List<Bankcardinfo> getBankcardinfoList(String custno){
		
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(custno);
		
		return bankCardInfoMapper.getBankcardinfo(bankcardinfo);
	}
	
	/**
	 * 获取用户银行卡列表
	 * @param custno
	 * @return
	 */
	public Bankcardinfo getBankcardinfo(String custno) {
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(custno);
		List<Bankcardinfo> list=bankCardInfoMapper.getBankcardinfo(bankcardinfo);
		if(list.size()>0&&null!=list){
			return (Bankcardinfo)list.get(0);
		}
		return null;
	}
	
	/**
	 *  
	 * 开户绑卡:银行快捷鉴权
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction)  {
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "UserBankBase");
		
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
	 *  
	 * 开户绑卡:银行手机验证
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction)  {
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "UserBankBase");
		
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
	 *  
	 * 开户绑卡:开户
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount4(OpenAccountAction openAccountAction)  {
		// 银行基本信息验证
		bankCardManagerValidator.validator(openAccountAction, "UserBankBase");
		
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
	 *  
	 * 添加银行卡信息，返回上serialid
	 * @param OpenAccountAction
	 * @return 
	 */
	public String addBankCardinfo(OpenAccountAction openAccountAction){
		Bankcardinfo bankcardinfo = bankCardManagerHelper.toBankcardinfo(openAccountAction);
		bankcardinfo.setSerialid(SequenceUtil.getSerial());
		bankcardinfo.setState("Y");
		bankCardInfoMapper.insterBankcardinfo(bankcardinfo);
		
		return bankcardinfo.getSerialid();
	}
	
	/**
	 *  
	 * 添加交易账户信息
	 * @param OpenAccountAction
	 * @return 
	 */
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
	 * 银联账户验证
	 * @param openAccountAction
	 */
	public void checkAccount(OpenAccountAction openAccountAction){
		try{
		TransDetail tDetail = new TransDetail();
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

	/**
	 * 升级银行卡
	 * @param openAccountAction
	 */
	@Transactional
	public void updateCard(OpenAccountAction openAccountAction) {
		bankCardInfoMapper.removeCard(openAccountAction.getCustno());
		// 添加银行卡及基金交易账户
		openAccountAction.setCustno(openAccountAction.getCustno());
		String bankSerialid = addBankCardinfo(openAccountAction);
		addTradeaccoinfo(openAccountAction, bankSerialid);
	}

	/**
	 * 删除银行卡信息
	 * @param serialid
	 */
	public void deleteBankCard(String serialid) {
		bankCardInfoMapper.deleteBankCard(serialid);
	}


}
