package com.ufufund.ufb.common.constant;

import java.util.Map;

public interface Constant {
	
	public final static String CUSTST$P = "P";
	public final static String OPENACCOUNT$Y = "Y";
	public final static String IDTP$0= "0";
	
	public final static String CACHE$DICTIONARY = "DICTIONARY";
	public final static String DICTIONARY$ERROR = "ERROR";
	public final static boolean TEST = true;
	
	
	public interface HftSysConfig{
		public static final String DistributorCode = "223";
		public static final String MerchantId = "00000001";
		public static final String Version = "1.0.0";
	}
	
	/**
	 * 海富通业务类型（接口调用类型）
	 * @author ayis
	 * 2015年4月9日
	 */
	public static interface HftBusiType{
		public static final String BankAuth = "bankSwiftAuth";
		public static final String BankVeri = "bankSwiftVerify";
		public static final String OpenAccount = "openAccount";
		public static final String SubApply = "subsribeApply";
		public static final String BuyApply = "buyApply";
		public static final String Cancel = "cancelApplyQuery";
		public static final String Redeem = "redeemApply";
		public static final String RealRedeem = "realTimeRedeemApply";
		public static final String PayNotify = "buyApplyNotify";
		public static final String Unfrozen = "unfrozenShare";
		public static final String SwiftTransfer = "swiftTransfer";
		public static final String TransResultQuery = "transResultQuery";
		public static final String StaticShareQuery= "staitcshareQuery";
	}
	
}
