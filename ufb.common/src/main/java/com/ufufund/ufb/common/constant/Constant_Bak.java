package com.ufufund.ufb.common.constant;


import java.math.BigDecimal;



public interface Constant_Bak {
	

	public final static String CUSTST$N = "N";
	public final static String IDTP$0= "0";
	public final static String CACHE$DICTIONARY = "DICTIONARY";
	public final static String DICTIONARY$ERROR = "ERROR";
//	public final static String RESP_CODE_FAIL = "9999";
//	public final static String RESP_CODE_TRADEPWD_ERROR = "FTSU004";
//	public final static String RESP_CODE_GETLASTSERIALNO_ERROR = "FTS-W101";
//	public final static String RESP_CANCEL_CHECK_ERROR = "FTS-W100";
//	public final static String RESP_REDEEM_CHECK_ERROR = "FTS-R100";
//	public static final String ACCPTMD_INTERNET = "2";
//	
//	/*现金宝积分卡充值*/
//	public static final String SUBAPKIND_010100 = "010100";
//
//	public static final String BANK_STATUS_1 = "Y";
//	
//	// 申请金额最大值 100000000
//	public static final int max_number = 100000000;
//
//	// 申请金额最大位数 11
//	public static final int max_length = 11;
//	
//	// 交易密码长度 6 
//	public static final int length_cardpwd = 6;
//	
//	/** 显示记录数 */
//	public static final int PAGE_SIZE = 10;
//	
//	/** 现金宝业务码 */
//	public final static String BRANCH_CODE = "247";
//	
//	/** 现金宝业务码 */
//	public final static String FUNDID_000330 = "000330";
//	
//	/** 老现金宝业务码 */
//	public final static String FUNDID_519518 = "519518";
//	
//	/** 添富货币B */
//	public final static String FUNDID_519517 = "519517";
//	
//	/**
//	 *  银行卡 00-不支持充值,01-支持充值,02-非强制升级，03-强制升级
//	 */
//	public static String TOPUP_NOT_SUPPORT = "00";
//	public static String TOPUP_SUPPORT = "01";
//	public static String TOPUP_UPDATE_NOT_FORCE = "02";
//	public static String TOPUP_UPDATE_FORCE = "03";
//	
//	/**
//	 * LCFLAG 理财标志: 0 - 理财预约, 1 - 理财
//	 */
//	public static String LCFLAG_0 = "0";
//	public static String LCFLAG_1 = "1";
//	
//	
//	/* 账户等级 */
//	public enum StringFormat {
//		/* -1异常 */
//		LogFormat("LogFormat"),
//		PwdFormat("PwdFormat"),
//		CardDisplay("CardDisplay")
//		;
//		
//
//		private String value;
//
//		StringFormat(String value) {
//			this.value = value;
//		}
//
//		public String getValue() {
//			return this.value;
//		}
//	}
//	
//public enum RespCode{
//	    
//	    /***********************************
//	     * 内部交易码
//	     ***********************************/
//	    //ADD BY LSS 2014-08-25
//	    FTS_SYSEXCEPTION("I","9999","系统异常"),
//
//	    FTS_SUCCESS("Y","0000","申请成功"),
//	    
//	    // 用户业务类错误
//	    USER_INPUT_ERROR("F", "FTSU001", "输入的参数为空！"),
//	    USER_VERICODE_EMPTY("F", "FTSU002", "验证码为空！"),
//	    USER_VERICODE_ERROR("F", "FTSU003", "验证码错误！"),
//	    USER_TRADEPWD_ERROR("F", "FTSU004", "交易密码错误！"),
//	    
//	    // 基金购买返回错误码
//	    PURCHASE_INPUTPARAM_EMPTY_OBJ("F", "FTS-P001", "购买输入参数判空校验失败[业务对象]"),
//	    PURCHASE_INPUTPARAM_EMPTY_SEQ("F", "FTS-P002", "购买输入参数判空校验失败[流水号]"),
//	    PURCHASE_INPUTPARAM_EMPTY_CUSTNO("F", "FTS-P003", "购买输入参数判空校验失败[客户编号]！"),
//	    PURCHASE_INPUTPARAM_EMPTY_TRADEACCO("F", "FTS-P004", "购买输入参数判空校验失败[交易账号]"),
//	    PURCHASE_INPUTPARAM_EMPTY_VACCONO("F", "FTS-P005", "购买输入参数判空校验失败[资金账号]"),
//	    PURCHASE_INPUTPARAM_EMPTY_BSACCONO("F", "FTS-P006", "购买输入参数判空校验失败[业务账号]"),
//	    PURCHASE_INPUTPARAM_EMPTY_FUNDID("F", "FTS-P007", "购买输入参数判空校验失败[基金代码]"),
//	    PURCHASE_INPUTPARAM_EMPTY_PURAMT("F", "FTS-P008", "购买输入参数判空校验失败[购买金额]"),
//	    PURCHASE_INPUTPARAM_EMPTY_PURTYPE("F", "FTS-P009", "购买输入参数判空校验失败[购买类型]"),
//	    PURCHASE_INPUTPARAM_EMPTY_CHARGETP("F", "FTS-P010", "购买输入参数判空校验失败[收费方式]"),
//	    PURCHASE_INPUTPARAM_EMPTY_SUBAPKIND("F", "FTS-P011", "购买输入参数判空校验失败[子交易码]"),
//	    PURCHASE_INPUTPARAM_EMPTY_ACCPTMD("F", "FTS-P012", "购买输入参数判空校验失败[委托方式]"),
//	    PURCHASE_INPUTPARAM_EMPTY_BALANCEFROM("F", "FTS-P013", "购买输入参数判空校验失败[份额来源]"),
//	    
//	    PURCHASE_INPUTPARAM_BUSIZ_SHARECLASS("F","FTS-P101","购买基金业务校验失败[基金等级]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_LARGEPURCHASESGL("F","FTS-P102","购买基金业务校验失败[单笔大额申购]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_LARGEPURCHASESUM("F","FTS-P103","购买基金业务校验失败[累计大额申购]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_MINSUBAMT("F","FTS-P104","购买基金业务校验失败[最低申购金额]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_MAXSUBAMT("F","FTS-P105","购买基金业务校验失败[最高申购金额]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_MINBIDAMT("F","FTS-P106","购买基金业务校验失败[最低申购金额]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_MAXBIDAMT("F","FTS-P107","购买基金业务校验失败[最高申购金额]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_FUNDST("F","FTS-P108","购买基金业务校验失败[基金状态]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_BUSSACCO("F","FTS-P109","购买基金业务校验失败[业务子账号]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_BALANCEFROM("F","FTS-P110","购买基金业务校验失败[份额来源]"),
//	    PURCHASE_INPUTPARAM_BUSIZ_VBALANCE("F","FTS-P111","购买基金业务校验失败[现金宝余额不足]"),
//	    
//	    // 基金赎回返回错误码
//	    REDEEM_INPUTPARAM_EMPTY_OBJ("F", "FTS-R001", "赎回输入参数判空校验失败[对象为空]"),
//	    REDEEM_INPUTPARAM_EMPTY_CUSTNO("F", "FTS-R002", "赎回输入参数判空校验失败[客户编号]！"),
//	    REDEEM_INPUTPARAM_EMPTY_TRADEACCO("F", "FTS-R003", "赎回输入参数判空校验失败[交易账号]"),
//	    REDEEM_INPUTPARAM_EMPTY_BSACCONO("F", "FTS-R004", "赎回输入参数判空校验失败[业务账号]"),
//	    REDEEM_INPUTPARAM_EMPTY_FUNDID("F", "FTS-R005", "赎回输入参数判空校验失败[基金代码]"),
//	    REDEEM_INPUTPARAM_EMPTY_PURAMT("F", "FTS-R006", "赎回输入参数判空校验失败[赎回份额]"),
//	    REDEEM_INPUTPARAM_EMPTY_LARGEFLG("F", "FTS-R007", "赎回输入参数判空校验失败[大额赎回标志]"),
//	    REDEEM_INPUTPARAM_EMPTY_SHARETYPE("F", "FTS-R008", "赎回输入参数判空校验失败[收费方式]"),
//	    REDEEM_INPUTPARAM_EMPTY_APKIND("F", "FTS-R009", "赎回输入参数判空校验失败[交易代码]"),
//	    REDEEM_INPUTPARAM_EMPTY_SUBAPKIND("F", "FTS-R010", "赎回输入参数判空校验失败[子交易码]"),
//	    REDEEM_INPUTPARAM_EMPTY_ACCPTMD("F", "FTS-R011", "赎回输入参数判空校验失败[委托方式]"),
//	    REDEEM_INPUTPARAM_EMPTY_EXPIREDATE("F", "FTS-R011", "赎回输入参数判空校验失败[到期日期]"),
//	    
//	    REDEEM_INPUTPARAM_BUSIZ_EXPIRE_NOT_REDEEM("F","FTS-R101","赎回基金业务校验失败[投资到期日不能预约赎回]"),
//	    REDEEM_INPUTPARAM_BUSIZ_EXPIRE_WRONG_FUNDID("F","FTS-R102","赎回基金业务校验失败[基金代码非法]"),
//	    REDEEM_INPUTPARAM_BUSIZ_EXPIRE_NOT_EQUAL_STATIC_WITH_ODS("F","FTS-R103","赎回基金业务校验失败[检查静态份额与ODS份额明细不一致]"),
//	    
//	    
//	    // 基金撤单返回错误码
//	    CANCEL_INPUTPARAM_EMPTY_OBJ("F", "FTS-W001", "购买输入参数判空校验失败[对象为空]"),
//	    
//	    CANCEL_LASTSERIALNO_ERROR("F","FTS-W101","撤单业务校验失败[根据理财产品业务规则,请先撤该笔申请!]"),
//	    
//	    CANCEL_INPUTPARAM_BUSIZ_NOT_RIGHT_ORIGN_ORDER("F","FTS-W101","撤单业务校验失败[原交易申请流水号不存在!]"),
//	    
//	    CANCEL_INPUTPARAM_BUSIZ_NOT_RIGHT__ORDER("F","FTS-W103","撤单业务校验失败[不能撤销已受理或已撤销的单!]"),     
//
//	    CANCEL_INPUTPARAM_BUSIZ_NOT_RIGHT__TRADE_ACCO("F","FTSP104","撤单业务校验失败[交易账号与原始申请交易账号不一致!]"),
//	    
//	    CANCEL_INPUTPARAM_BUSIZ_NOT_RIGHT__TRADE_052("F","FTSP105","撤单业务校验失败[撤单业务不能取消!]"),
//	    
//	    CANCEL_INPUTPARAM_BUSIZ_NOT_RIGHT__WORKDAY("F","FTSP105","撤单业务校验失败[交易当日工作日才能撤单!]"),
//	    
//	    CANCEL_INPUTPARAM_BUSIZ_NOT_RIGHT__TRADE_920("F","FTSP106","撤单业务校验失败[认购业务不能取消!]"),
//	    
//	    // 基金转换返回错误码
//	    CONVERT_OSHARETYPE_NOT_A("F","FTS-CO101","基金转换失败[转出基金的收费方式必须为前端!]"),
//	    CONVERT_SHARETYPE_NOT_A("F","FTS-CO102","基金转换失败[转入基金的收费方式必须为前端!]"),
//	    // 设置分红
//	    MELON_FUNDTP_WRONG("F","FTS-ME101","设置分红失败[货币型基金必然是红利转投的,不能设置分红方式!]"),
//	    MELON_TA_WRONG("F","FTS-ME102","设置分红失败[取得TA代码错误!]"),
//	    // 定投
//	    MIP_BANK_NO_NULL("F","FTS-MIP101","定投失败[银行卡错误,调用CIF返回银行卡信息为空!]"),
//	    MIP_BANK_INFO_NULL("F","FTS-MIP102","定投失败[银行卡错误,根据定投银行卡ID取配置信息错误!]"),
//	    MIP_BANK_CAN_NOT_MIP("F","FTS-MIP103","定投失败[银行卡错误,定投银行卡不支持定投!]"),
//	    MIP_MIPSTYLE_WRONG("F","FTS-MIP104","定投失败[定投类型错误!]"),
//	    MIP_BANK_WRONG("F","FTS-MIP105","定投失败[银行卡信息错误!]"),
//	    MIP_FUND_ID_WRONG("F","FTS-MIP106","定投失败[不能定投货币基金!]"),
//	    MIP_FUND_RT_WRONG("F","FTS-MIP107","定投失败[不能定投RT基金!]"),
//	    MIP_CONTRACTNO_WRONG("F","FTS-MIP108","定投失败[定投协议号不存在!]"),
//	    MIP_CONTRACTNO_WRONG_2("F","FTS-MIP109","定投失败[定投协议号状态错误！]"),
//	    MIP_STATUS_WRONG("F","FTS-MIP110","定投失败[申请重复或申请状态不一致!]"),
//	    MIP_STATUS_P_WRONG("F","FTS-MIP111","定投失败[不能变更暂停等非正常状态的计划!]"),
//	    MIP_CIF_WRONG("F","FTS-MIP112","定投失败[调用CIF获取工作日错误!]"),
//	    MIP_MIPDAT_WRONG_1("F","FTS-MIP113","定投失败[扣款日期错误!]"),
//	    MIP_MIPDAT_WRONG_2("F","FTS-MIP114","定投失败[不能发起或变更当前工作日扣款的定投计划!]"),
//	    MIP_MIPDAT_WRONG_3("F","FTS-MIP115","定投失败[不能变更或终止当日扣款的定投计划！]"),
//	    MIP_MIPDAT_WRONG_4("F","FTS-MIP116","定投失败[不能暂停或重新启用扣款日为当前工作日的定投计划！]"),
//	    
//	    
//	    COM_APKIND_NULL("F","FTS-COM101","交易失败[判断额度错误 ：Apkind为空!]"),
//	    COM_QTY_NULL("F","FTS-COM109","交易失败[判断额度错误 ：QTY为空!]"),
//	    COM_QTY_FORMAT_ERROR("F","FTS-COM110","交易失败[判断额度错误 ：QTY格式不对!]"),
//	    COM_QTY_WRONG_1("F","FTS-COM102","认购失败[认购额度错误 ：小于认购最小份额或大于认购最大份额!]"),
//	    COM_QTY_WRONG_2("F","FTS-COM103","申购失败[申购额度错误 ：小于认购最小份额或大于认购最大份额!]"),
//	    COM_QTY_WRONG_3("F","FTS-COM104","交易失败[交易额度错误 ：赎回份额大于该可用余额！]"),
//	    COM_QTY_WRONG_4("F","FTS-COM105","赎回失败[赎回额度错误 ：小于最低赎回份额！]"),
//	    COM_QTY_WRONG_5("F","FTS-COM106","赎回失败[赎回额度错误 ：取现份额＋最低保留份额不能大于可用份额！]"),
//	    COM_QTY_WRONG_6("F","FTS-COM107","转换失败[转换额度错误 ：小于最低转换份额！]"),
//	    COM_QTY_WRONG_7("F","FTS-COM108","转换失败[转换额度错误 ：转换份额＋转出基金最低保留份额不能大于转出基金可用份额！]"),
//	    COM_QTY_WRONG_8("F","FTS-COM109","定投失败[定投额度错误 ：定投金额小于最低定投金额！]"),
//	    COM_QTY_WRONG_9("F","FTS-COM110","定投失败[定投额度错误 ：定期不定额设置扣款金额小于最低定投金额！]"),
//	    // 查询基金交易费率
//	    QRY_FUNDTRADERATIO_BUSIZ_RATIO("F","FTSQ001","获取基金交易费率异常")
//	    
//        ;
//	    RespCode(String status,String code, String msg) {
//	        this.code = code;
//	        this.message = msg;
//	        this.status = status;
//	    }
//
//	    private String code;
//
//	    private String message;
//	    
//	    private String status;
//
//	    public String getCode() {
//	        return code;
//	    }
//
//	    public void setCode(String code) {
//	        this.code = code;
//	    }
//
//	    public String getMessage() {
//	        return message;
//	    }
//
//	    public void setMessage(String message) {
//	        this.message = message;
//	    }
//	    
//	    
//
//	    public String getStatus() {
//			return status;
//		}
//		public void setStatus(String status) {
//			this.status = status;
//		}
//		public static RespCode getByCode(String code) {
//	        if(code == null || code.isEmpty()) {
//	            return null;
//	        }
//
//	        RespCode[] codes = RespCode.values();
//	        for(RespCode respCode : codes) {
//	            if(respCode.getCode().equals(code)) {
//	                return respCode;
//	            }
//	        }
//
//	        return null;
//	    }
//	}
//	
//
//	/**
//	 * 业务模块的常量 <br>
//	 * 创建日期：2014-2-10 <br>
//	 * <b>Copyright 2014　汇添富基金有限公司　All Rights Reserved</b>
//	 * 
//	 * @author 周靖
//	 * @since
//	 * @version 1.0
//	 */
//	public interface BizConstantDemo {
//		/* 账户等级 */
//		public enum AccRateEnum {
//			/* -1异常 */
//			AccRateErr(-1),
//			/* 1低 */
//			AccRate1(1),
//			/* 2中 */
//			AccRate2(2),
//			/* 3较高 */
//			AccRate3(3),
//			/* 4高 */
//			AccRate4(4);
//
//			private int value;
//
//			AccRateEnum(int value) {
//				this.value = value;
//			}
//
//			public int getValue() {
//				return this.value;
//			}
//		}
//		
//		/*老客户默认额度*/
//		public final String OLDMAXLIMIT  = "1000000";
//		
//	}
//	/**
//	 * web 模块常量
//	 * <br>创建日期：2014年6月12日
//	 * @author 张蒙
//	 * @since
//	 * @version 1.0
//	 */
//	public interface FTSContent {
//		// 银行代码
//		public static final String BnkNo = "997";
//		
//		// 银行名称
//		public static String BnkNm = "预付费卡";
//		
//		//商户备注
////		MerchantRemarks =
//
//		// 取明细账存放目录
////		AccountListFileDirPath = D\:\\ECDomain\\applications\\ECC\\TempFile\\PRECARD\\
//
//		//预付费卡接口地址
//		public static final String WebServiceUrl =  "http://172.16.8.166:8080/HSAWSserver/services/wsServer";
//		//预付费卡接口用户名
//		public static final String WebServiceUser = "htf";
//		//预付费卡接口密码
//		public static final String WebservicePassword = "abc=123";
//
//		//业务类型 0001：支付 0002：转出
//		public static final String bizType = "0001";
//
//		//卡信息查询业务类型 0001：有密查询 0002：无密查询
//		public static final String bizTypeWithPwd = "0001";
//		public static final String bizTypeWithoutPwd = "0002";
//
//		//卡信息查询交易代码
//		public static final String tranCodeCardInfoQuery = "3011";
//
//		//接口方提供的加密key 数据库存储卡密用到的加解密使用的key
//		public static final String ThreeDESKey = "HTF987654321123456789ACQ";
//
//		//接口方管理平台设置merchantID
//		public static final String merchantID = "100000000000004";
//
//		// 认/申购接收银行交易服务器通知返回URL 
//		public static final String PayResultNotifyURL = "https://open.99fund.com/payment/ab/ab_srv_resp.jsp";
//
//		// 认/申购商户端接受应答URL 
//		public static final String PayResultMerchantURL = "https://open.99fund.com/payment/ab/abPayGetResponsion.jsp";
//
//		// 支付接口延迟时间 
//		public static final String WaitTimeForPay = "20";
//		
//		// 赛维卡号长度 
//		public static final int length_cardno = 19;
//		
//		/**********************************
//		 * 申请返回状态
//		 * Y-成功;F-失败;I-处理中
//		 * ********************************/
//		public static final String FTS_APPLYST_Y = "Y";
//		public static final String FTS_APPLYST_F = "F";
//		public static final String FTS_APPLYST_I = "I";
//		
//		/***********************************
//		 * 交易委托模式常量
//		 * 0-柜台;2-网上交易;6-开放平台;M-手机
//		 ***********************************/
//		public static final String FTS_ACCPTMD_0 = "0";
//		public static final String FTS_ACCPTMD_2 = "2";
//		public static final String FTS_ACCPTMD_6 = "6";
//		public static final String FTS_ACCPTMD_M = "M";
//		
//		/**********************************
//		 * 基金交易码常量
//		 * 020-基金认购;022-基金申购;024-基金赎回;
//		 * 036-基金转换;920-现金宝认购;950-现金宝申购;
//		 * ********************************/
//		public static final String FTS_APKIND_020 = "020";
//		public static final String FTS_APKIND_022 = "022";
//		public static final String FTS_APKIND_024 = "024";
//		public static final String FTS_APKIND_029 = "029";
//		public static final String FTS_APKIND_036 = "036";
//		public static final String FTS_APKIND_920 = "920";
//		public static final String FTS_APKIND_950 = "950";
//		public static final String FTS_APKIND_951 = "951";
//		public static final String FTS_APKIND_052 = "052";
//		public static final String FTS_APKIND_062 = "062";
//		
//		/**********************************
//		 * 基金子交易码常量
//		 * 002201-现金宝认购;002202-现金宝申购;002301-银行卡认购;002302-银行卡申购;002104-撤单
//		 * ********************************/
//		public static final String FTS_SUBAPKIND_DEFAULT = "999999";
//		public static final String FTS_SUBAPKIND_002103 = "002103";
//		public static final String FTS_SUBAPKIND_002201 = "002201";
//		public static final String FTS_SUBAPKIND_002202 = "002202";
//		public static final String FTS_SUBAPKIND_002301 = "002301";
//		public static final String FTS_SUBAPKIND_002302 = "002302";
//		public static final String FTS_SUBAPKIND_002104 = "002104";
//		
//		/**********************************
//		 * 基金渠道来源常量
//		 * 247-直销;
//		 * ********************************/
//		public static final String FTS_BRANCHCODE_247 = "247";
//		
//		/**********************************
//		 * 基金收费模式常量
//		 * A-前端;B-后端
//		 * ********************************/
//		public static final String FTS_CHARGETYPE_A = "A";
//		public static final String FTS_CHARGETYPE_B = "B";
//		
//		/**********************************
//		 * 基金购买模式常量
//		 * R-认购;B-申购
//		 * ********************************/
//		public static final String FTS_PURTYPE_R = "R";
//		public static final String FTS_PURTYPE_B = "B";
//		
//		/**********************************
//		 * 基金等级
//		 * A;B;C
//		 * ********************************/
//		public static final String FTS_SHARECLASS_A = "A";
//		public static final String FTS_SHARECLASS_B = "B";
//		public static final String FTS_SHARECLASS_C = "C";
//		
//		/**********************************
//		 * 基金类别
//		 * 0-股票型;1-货币型;2-债券型;3-平衡型;4-偏股型;6-专户;8-理财;9-信托
//		 * ********************************/
//		public static final String FTS_FUNDTYPE_0 = "0";
//		public static final String FTS_FUNDTYPE_1 = "1";
//		public static final String FTS_FUNDTYPE_2 = "2";
//		public static final String FTS_FUNDTYPE_3 = "3";
//		public static final String FTS_FUNDTYPE_4 = "4";
//		public static final String FTS_FUNDTYPE_6 = "6";
//		public static final String FTS_FUNDTYPE_8 = "8";
//		public static final String FTS_FUNDTYPE_9 = "9";
//		
//		/**********************************
//		 * 收费方式
//		 * A-前端;B-后端
//		 * ********************************/
//		public static final String FTS_SHARETYPE_A = "A";
//		public static final String FTS_SHARETYPE_B = "B";
//		
//		/**********************************
//		 * 基金状态
//		 * 0-正常 ;1-发行中;2-暂停定投;4-暂停交易;5-暂停申购;6-暂停赎回 ;7-募集期;8-募集结束;9-基金封闭
//		 * ********************************/
//		public static final String FTS_FUNDSTATUS_0 = "0";
//		public static final String FTS_FUNDSTATUS_1 = "1";
//		public static final String FTS_FUNDSTATUS_2 = "2";
//		public static final String FTS_FUNDSTATUS_3 = "3";
//		public static final String FTS_FUNDSTATUS_4 = "4";
//		public static final String FTS_FUNDSTATUS_5 = "5";
//		public static final String FTS_FUNDSTATUS_6 = "6";
//		public static final String FTS_FUNDSTATUS_7 = "7";
//		public static final String FTS_FUNDSTATUS_8 = "8";
//		public static final String FTS_FUNDSTATUS_9 = "9";
//		
//		/**********************************
//		 * 分红方式
//		 * 0-红利再投;1-现金分红
//		 * ********************************/
//		public static final String FTS_MELONMD_0 = "0";
//		public static final String FTS_MELONMD_1 = "1";
//		
//		/**********************************
//		 * 份额来源
//		 * 0-现金宝;2-银行卡
//		 * ********************************/
//		public static final String FTS_BALANCEFROM_0 = "0";
//		public static final String FTS_BALANCEFROM_2 = "2";
//		
//		/**********************************
//		 * 银行引擎需要
//		 * 00-固定
//		 * ********************************/
//		public static final String FTS_MERTRANCODE_00 = "00";
//		
//		/**********************************
//		 * 基金申购时类型判断
//		 * 00-不支持充值,01-支持充值,02-非强制升级，03-强制升级
//		 * ********************************/
//		public static String FTS_TOPUP_NOT_SUPPORT = "00";
//		public static String FTS_TOPUP_SUPPORT = "01";
//		public static String FTS_TOPUP_UPDATE_NOT_FORCE = "02";
//		public static String FTS_TOPUP_UPDATE_FORCE = "03";
//		
//		/**********************************
//		 * 基金申购是否需要验证码
//		 * Y-需求 N-不需要
//		 * ********************************/
//		public static final String FTS_PURCHKVCODE_Y = "Y";
//		public static final String FTS_PURCHKVCODE_N = "N";
//		
//		/**********************************
//		 * 基金申购费率是否有折扣
//		 * N-有;Y-没有(默认值)
//		 * ********************************/
//		public static final String FTS_DISCOUNT_Y = "Y";
//		public static final String FTS_DISCOUNT_N = "N";
//		
//		/**********************************
//		 * 基金默认折扣,现金宝折扣4折
//		 * ********************************/
//		public static final BigDecimal FTS_DEFAULTDISCOUNT_4OFF = new BigDecimal("0.4");
//		
//		/**********************************
//		 * 基金默认费率
//		 * ********************************/
//		public static final BigDecimal FTS_DEFAULTRATIO_0 = new BigDecimal(0);
//		
//		/**********************************
//		 * 基金费率是否为固定金额
//		 * 0-否;1-是
//		 * ********************************/
//		public static final String FTS_ISFIXRATIO_0 = "0";
//		public static final String FTS_ISFIXRATIO_1 = "1";
//		
//		/**********************************
//		 * 是否为基金活动折扣
//		 * 0-否;1-是
//		 * ********************************/
//		public static final String FTS_ISFIXDISCOUNT_0 = "0";
//		public static final String FTS_ISFIXDISCOUNT_1 = "1";
//		
//		/**********************************
//		 * 定投计划处理类型
//		 * ADDF-新增定期定额;ADDU-新增定期不定额;CHGF-修改定期定额;CHGU-修改定期不定额;
//		 * DEL-删除定投计划;STP-暂停定投计划;REC-恢复定投计划
//		 * ********************************/
//		public static final String FTS_MIP_ADDF = "ADDF";
//		public static final String FTS_MIP_ADDU = "ADDU";
//		public static final String FTS_MIP_CHGF = "CHGF";
//		public static final String FTS_MIP_CHGU = "CHGU";
//		public static final String FTS_MIP_DEL = "DEL";
//		public static final String FTS_MIP_STP = "STP";
//		public static final String FTS_MIP_REC = "REC";
//		
//		/**********************************
//		 * 现金宝购买V
//		 * ********************************/
//		public static final String FTS_BIDFROM_V = "V";
//		
//		/**********************************
//		 * 基金业务代码
//		 * 01-基金  02-理财 03-专户
//		 * ********************************/
//		public static final String FTS_BUSSACCOTP_01 = "01";
//		public static final String FTS_BUSSACCOTP_02 = "02";
//		public static final String FTS_BUSSACCOTP_03 = "03";
//		
//		/**********************************
//		 * 基金费率默认下限 0.6
//		 * ********************************/
//		public static final String FTS_RATIO_BASE = "0.6";
//		
//		/**********************************
//		 * 需要特殊处理的基金编号
//		 * ********************************/
//		public static final String FTS_FUNDID_164705 = "164705";//添富恒生指数分级
//	}
//
//	/**
//	 * 交易查询代码转换
//	 * <br>创建日期：2014年9月2日
//	 * @author 张蒙
//	 * @since
//	 * @version 1.0
//	 */
//	public enum TradeContent{
//	    
//	    DTTYPE_1("1","最近一周"),
//	    DTTYPE_2("2","最近一月"),
//	    DTTYPE_3("3","最近三月"),
//	   
//	    DTTYPE_4("4","最近半年"),
//	    DTTYPE_5("5","最近一年"),
//	    DTTYPE_6("6","任意时间"),
//
//	    STATUS_A("A","所有状态"),
//	    STATUS_S("S","成功交易"),
//	    STATUS_F("F","失败交易"),
//	    STATUS_N("N","已处理");
//	    
//	    TradeContent(String code, String msg) {
//	        this.code = code;
//	        this.message = msg;
//	    }
//
//	    private String code;
//
//	    private String message;
//	    
//	    public String getCode() {
//	        return code;
//	    }
//
//	    public void setCode(String code) {
//	        this.code = code;
//	    }
//
//	    public String getMessage() {
//	        return message;
//	    }
//
//	    public void setMessage(String message) {
//	        this.message = message;
//	    }
//	    
//	    
//
//		public static TradeContent getByCode(String code) {
//	        if(code == null || code.isEmpty()) {
//	            return null;
//	        }
//
//	        TradeContent[] codes = TradeContent.values();
//	        for(TradeContent respCode : codes) {
//	            if(respCode.getCode().equals(code)) {
//	                return respCode;
//	            }
//	        }
//
//	        return null;
//	    }
//	}
//	
//	/**
//	 * 交易状态枚举
//	 * <br>创建日期：2014-7-14
//	 * <br><b>Copyright 2014　汇添富基金有限公司　All Rights Reserved</b>
//	 * @author 周靖
//	 * @since
//	 * @version 1.0
//	 */
//	public enum STATUS{
//		SUCESS("Y", "处理成功"),
//		FAIL("F", "处理失败"),
//		REVERSE("R", "已冲正"),
//		PENDING("I", "处理中");
//		STATUS(String code, String msg) {
//	        this.code = code;
//	        this.message = msg;
//	    }
//
//	    private String code;
//
//	    private String message;
//
//	    public String getCode() {
//	        return code;
//	    }
//
//	    public void setCode(String code) {
//	        this.code = code;
//	    }
//
//	    public String getMessage() {
//	        return message;
//	    }
//
//	    public void setMessage(String message) {
//	        this.message = message;
//	    }
//	}
}
