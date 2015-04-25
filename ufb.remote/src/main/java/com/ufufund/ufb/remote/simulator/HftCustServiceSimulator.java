package com.ufufund.ufb.remote.simulator;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriResponse;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountResponse;
import com.ufufund.ufb.remote.HftCustService;

/**
 * 海富通银行鉴权、开户相关接口
 * @author ayis
 * 2015年3月22日
 */
@Service
public class HftCustServiceSimulator extends HftCustService{
	private static final Logger LOG = LoggerFactory.getLogger(HftCustServiceSimulator.class);
	
	/**
	 * 银行鉴权接口（快捷）
	 * @param request
	 * @return
	 */
	public BankAuthResponse bankAuth(BankAuthRequest request){
		
		BankAuthResponse response = new BankAuthResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 银行鉴权验证接口（快捷）
	 * @param request
	 * @return
	 */
	public BankVeriResponse bankVeri(BankVeriRequest request){
		
		BankVeriResponse response = new BankVeriResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		response.setValidateState("1");
		response.setProtocolNo("Protocol"+DateUtil.format(new Date(), DateUtil.FULL_PATTERN_1));
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 基金账户开户接口
	 * @param request
	 * @return
	 */
	public OpenAccountResponse openAccount(OpenAccountRequest request){
		
		OpenAccountResponse response = new OpenAccountResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		response.setTransactionAccountID("Tan"+DateUtil.format(new Date(), DateUtil.FULL_PATTERN_1));
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
}
