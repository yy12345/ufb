package com.ufufund.ufb.remote.service;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.remote.xml.pojo.BankAuthRequest;
import com.ufufund.ufb.remote.xml.pojo.BankAuthResponse;
import com.ufufund.ufb.remote.xml.pojo.BankVeriRequest;
import com.ufufund.ufb.remote.xml.pojo.BankVeriResponse;
import com.ufufund.ufb.remote.xml.pojo.OpenAccountRequest;
import com.ufufund.ufb.remote.xml.pojo.OpenAccountResponse;

/**
 * 银行鉴权、开户等相关接口（海富通）
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
	 * 基金账户开户接口
	 * @param request
	 * @return
	 */
	public OpenAccountResponse openAccount(OpenAccountRequest request){
		return super.send(request, OpenAccountResponse.class);
	}
	
}
