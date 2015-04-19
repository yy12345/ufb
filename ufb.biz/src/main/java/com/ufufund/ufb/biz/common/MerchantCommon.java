package com.ufufund.ufb.biz.common;

import com.ufufund.ufb.biz.Merchant.MerchantFund;
import com.ufufund.ufb.biz.Merchant.htf.HtfFund;
import com.ufufund.ufb.model.enums.Merchant;

public class MerchantCommon {
	
	public static  MerchantFund getMerchant(Merchant merchant){
		MerchantFund fund = null;
		if(merchant.equals(Merchant.HFT_FUND)){
			fund = new HtfFund();
		}
		return fund;
	}
}
