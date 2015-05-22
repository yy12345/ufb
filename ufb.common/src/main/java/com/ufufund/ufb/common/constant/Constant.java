package com.ufufund.ufb.common.constant;

public interface Constant {

	/*
	 * 缓存
	 */
	public static interface Cache {
		public final static String CACHE$DICTIONARY = "DICTIONARY";
	}

	/*
	 * 客户
	 */
	public static interface Custinfo {
		public final static String CUSTST$P = "P";
		public final static String OPENACCOUNT$Y = "Y";
	}

	// public final static String IDTP$0= "0";

	/*
	 * 字典
	 */
	public static interface Dictionary {
		public final static String DICTIONARY$ERROR = "ERROR";
		// public final static String DICTIONARY$SYS = "SYS";
		// public final static String DICTIONARY$HTFERROR = "HFTERROR";
		// public final static String DICTIONARY$HFTBANKNO = "HFTBANKNO";
		// public final static String DICTIONARY$HFTIDTP = "HFTIDTP";
		// public final static String DICTIONARY$HFTAPKIND = "HFTAPKIND";
	}
	
	public static interface Autotrade {
		public final static String STATE$N = "N";
		public final static String STATE$P = "P";
		// public final static String DICTIONARY$SYS = "SYS";
		// public final static String DICTIONARY$HTFERROR = "HFTERROR";
		// public final static String DICTIONARY$HFTBANKNO = "HFTBANKNO";
		// public final static String DICTIONARY$HFTIDTP = "HFTIDTP";
		// public final static String DICTIONARY$HFTAPKIND = "HFTAPKIND";
	}

	public static final String RES_CODE_SUCCESS = "0000";
	public static final String RES_CODE_FAIL = "9999";

	public interface HftSysConfig {
		/** 海富通分配给直销平台的销售代码及商户编号 **/
		public static final String DistributorCode = "223";
		public static final String MerchantId = "00000001";
		// 海富通接口版本号
		public static final String Version = "1.0.0";
		// 直销平台对海富通基金公司的编码
		public static final String HftFundCorpno = "01";
	}

	/**
	 * 海富通业务类型（接口调用类型）
	 * 
	 * @author ayis 2015年4月9日
	 */
	public static interface HftBusiType {
		public static final String BankAuth = "bankSwiftAuth";
		public static final String BankVeri = "bankSwiftVerify";
		public static final String OpenAccount = "openAccount";
		public static final String SubApply = "subscribeApply";
		public static final String BuyApply = "buyApply";
		public static final String Cancel = "cancelApply";
		public static final String Redeem = "redeemApply";
		public static final String RealRedeem = "realTimeRedeemApply";
		public static final String BuyNotify = "buyApplyNotify";
		public static final String Frozen = "frozenShare";
		public static final String Unfrozen = "unfrozenShare";
		public static final String Transfer = "swiftTransfer";
		public static final String TransQuery = "transResultQuery";
		public static final String BalanceQuery = "staticShareQuery";
	}

//	public static interface FundCode {
//		public static final String YFB = "225010";
//		public static final String YFT_1 = "001002";
//	}

}
