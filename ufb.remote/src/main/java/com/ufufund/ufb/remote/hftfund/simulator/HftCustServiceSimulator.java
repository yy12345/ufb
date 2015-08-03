package com.ufufund.ufb.remote.hftfund.simulator;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.model.hftfund.BankAuthRequest;
import com.ufufund.ufb.model.hftfund.BankAuthResponse;
import com.ufufund.ufb.model.hftfund.BankVeriRequest;
import com.ufufund.ufb.model.hftfund.BankVeriResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountResponse;
import com.ufufund.ufb.remote.hftfund.HftCustService;

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
	@Override
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
	@Override
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
	@Override
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
	
	/**
	 * 基金账户开户接口:机构
	 * @param request
	 * @return
	 */
	public OpenAccountOrgResponse openAccountOrg(OpenAccountOrgRequest request){
		OpenAccountOrgResponse response = new OpenAccountOrgResponse();
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
