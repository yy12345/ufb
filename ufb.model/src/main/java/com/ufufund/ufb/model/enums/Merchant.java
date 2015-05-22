package com.ufufund.ufb.model.enums;

import com.ufufund.ufb.common.constant.Constant;

public enum Merchant {

	HFT_FUND(Constant.HftSysConfig.MerchantId);// 海富通

	private String value;

	private Merchant(String value) {
		this.value = value;
	}

	public String Value() {
		return value;
	}

}