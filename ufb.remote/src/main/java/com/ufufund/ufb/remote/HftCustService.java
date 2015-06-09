package com.ufufund.ufb.remote;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.hft.BankAuthRequest;
import com.ufufund.ufb.model.hft.BankAuthResponse;
import com.ufufund.ufb.model.hft.BankVeriRequest;
import com.ufufund.ufb.model.hft.BankVeriResponse;
import com.ufufund.ufb.model.hft.OpenAccountOrgRequest;
import com.ufufund.ufb.model.hft.OpenAccountOrgResponse;
import com.ufufund.ufb.model.hft.OpenAccountRequest;
import com.ufufund.ufb.model.hft.OpenAccountResponse;

/**
 * 海富通银行鉴权、开户相关接口
 * @author ayis
 * 2015年3月22日
 */
@Service
public class HftCustService extends HftBaseService{
	
	
	/**
	 * 银行鉴权接口（快捷）
	 * @param request
	 * @return
	 */
	public BankAuthResponse bankAuth(BankAuthRequest request){
		return super.send(request, BankAuthResponse.class);
	}
	
	/**
	 * 银行鉴权验证接口（快捷）
	 * @param request
	 * @return
	 */
	public BankVeriResponse bankVeri(BankVeriRequest request){
		return super.send(request, BankVeriResponse.class);
	}
	
	/**
	 * 基金账户开户接口:个人
	 * @param request
	 * @return
	 */
	public OpenAccountResponse openAccount(OpenAccountRequest request){
		return super.send(request, OpenAccountResponse.class);
	}
	
	/**
	 * 基金账户开户接口:机构
	 * @param request
	 * @return
	 */
	public OpenAccountOrgResponse openAccountOrg(OpenAccountOrgRequest request){
		return super.send(request, OpenAccountOrgResponse.class);
	}
	
}
