package com.ufufund.ufb.remote;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriResponse;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountResponse;

/**
 * 海富通银行鉴权、开户相关接口
 * @author ayis
 * 2015年3月22日
 */
//@Service
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
	 * 基金账户开户接口
	 * @param request
	 * @return
	 */
	public OpenAccountResponse openAccount(OpenAccountRequest request){
		return super.send(request, OpenAccountResponse.class);
	}
	
}
