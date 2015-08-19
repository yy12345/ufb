package com.ufufund.ufb.common.constant;

import java.util.HashMap;
import java.util.Map;

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
		public final static String STATE$C = "C";
		
		public final static String CYCLE$MM = "MM";
		public final static String CYCLE$WW = "WW";
		public final static String CYCLE$DD = "DD";
		// public final static String DICTIONARY$SYS = "SYS";
		// public final static String DICTIONARY$HTFERROR = "HFTERROR";
		// public final static String DICTIONARY$HFTBANKNO = "HFTBANKNO";
		// public final static String DICTIONARY$HFTIDTP = "HFTIDTP";
		// public final static String DICTIONARY$HFTAPKIND = "HFTAPKIND";
	}

	public static interface Orggrade {

		public final static String ISOPEN$N = "N";
		public final static String ISOPEN$Y= "Y";
		public final static String ISOPEN$C = "C";
		
		public final static String ISDELETE$Y= "Y";
	}
	/*
	 * 客户
	 */
	public static interface Jobcontral {
		//任务状态 N-初始化 I-处理中 P-停止 X-结束
		public final static String STATUS$N = "N";
		public final static String STATUS$I = "I";
		public final static String STATUS$P = "P";
		public final static String STATUS$X = "X";
		
	}
	
	public static final String RES_CODE_SUCCESS = "0000";
	public static final String RES_CODE_FAIL = "9999";

	public interface HftSysConfig {
		// 直销平台销售代码
		public static final String DistributorCode = "223";
		// 直销平台商户号
		public static final String MerchantId = "00000001";
		// 海富通接口版本号
		public static final String Version = "1.0.0";
		// 直销平台对海富通基金公司的编码
		public static final String HftFundCorpno = "01";
	}

	/**
	 * 海富通业务代码定义
	 */
	public static interface HftBusiType {
		public static final String BankAuth = "bankSwiftAuth";
		public static final String BankVeri = "bankSwiftVerify";
		public static final String OpenAccount = "openAccount";
		public static final String OpenAccountOrg = "openAccountOrg";
		
		public static final String SubApply = "subscribeApply";
		public static final String SubApplyOrg = "subscribeApplyOrg";
		public static final String BuyApply = "buyApply";
		public static final String BuyApplyOrg = "buyApplyOrg";
		public static final String Redeem = "redeemApply";
		public static final String RedeemOrg = "redeemApplyOrg";
		public static final String RealRedeem = "realTimeRedeemApply";
		public static final String RealRedeemOrg = "realTimeRedeemApplyOrg";
		
		public static final String Frozen = "frozenShare";
		public static final String FrozenOrg = "frozenShareOrg";
		public static final String Unfrozen = "unfrozenShare";
		public static final String UnfrozenOrg = "unfrozenShareOrg";
		public static final String Transfer = "swiftTransfer";
		public static final String TransferOrg = "swiftTransferOrg";
		
		public static final String Cancel = "cancelApply";
		public static final String BuyNotify = "buyApplyNotify";
		public static final String TransQuery = "transResultQuery";
		public static final String BalanceQuery = "staticShareQuery";
		public static final String uploadedNotify = "docUploadedNotify";
	}
	
	public static class MsgTemplate{
		public static Map<String,String> templateMap = new HashMap<String,String>();
		static{
			templateMap.put("0J001", "亲爱的家长，您的验证码为：%s，5分钟内有效。");
			templateMap.put("0Y001", "亲爱的园长，您的验证码为：%s，5分钟内有效。");
			// 暂时测试编码2个，其它见文档。
		}
	}
	
	

}
