package com.ufufund.ufb.common.constant;

public interface BisConst {
	
	/**
	 * 海富通业务类型（接口调用类型）
	 * @author ayis
	 * 2015年4月9日
	 */
	public static interface Register{
		
		public static final String CUSTNO = "用户编号";
		public static final String LOGINCODE = "登录账号";
		
		public static final String LOGINPWD0 = "原登录密码";
		public static final String LOGINPWD = "登录密码";
		public static final String LOGINPWD2 = "确认密码";
		public static final String MOBILE = "手机号";
		public static final String VERIFYCODE = "验证码";
		public static final String MSGCODE = "手机验证码";

		public static final String BANKNO = "银行编码";
		public static final String BANKACCO = "银行卡号";
		public static final String BANKACCO2 = "确认银行卡号";
		public static final String BANKIDTP = "银行证件类型";
		public static final String BANKIDNO = "银行证件号码";
		public static final String BANKACNM = "银行开户名";
		public static final String BANKADD = "支行网点";
		public static final String BANKMOBILE = "银行开户手机号";	
		public static final String BANKMOBILEMSGCODE = "手机验证码";
		
		public static final String INVNM = "用户姓名";
		public static final String IDNO = "证件号码";
		
		public static final String RERPIDNO = "法人证件号码";
		public static final String RERPNM = "法人姓名";

		public static final String TRADEPWD0 = "原交易密码";
		public static final String TRADEPWD = "交易密码";
		public static final String TRADEPWD2 = "交易确认密码";
		
		public static final String ORGNM = "机构名称";
		public static final String ORGBUSINESS = "营业执照";
	}
	
	
	public static interface AutoTrade{
		
		public static final String AUTOID = "用户编号";
		public static final String CUSTNO = "用户编号";
		public static final String APKIND = "用户编号";
		public static final String TYPE = "用户编号";
		public static final String CYCLE = "用户编号";
		public static final String DAT = "用户编号";
		public static final String FROMBANKSERIALID = "用户编号";
		public static final String TOFUNDCODE = "用户编号";
		public static final String TOFUNDCORPNO = "用户编号";
		public static final String TOCHARGETYPE = "用户编号";
		
		public static final String TOBANKSERIALID = "用户编号";
		public static final String FROMFUNDCODE = "用户编号";
		public static final String FROMFUNDCORPNO = "用户编号";
		public static final String FROMCHARGETYPE = "用户编号";
		
		public static final String AUTOAMT = "用户编号";
		public static final String AUTOVOL = "用户编号";
		public static final String STATE = "用户编号";
	}
	
	
	public static interface Orggrade{
		
		public static final String ORGID = "机构ID";
		public static final String GRADE_NAME = "学年名";
		public static final String START_DATE = "开始日期";
		public static final String END_DATE = "结束日期";
		public static final String TERM_ID = "学期号码";

		  
		
		public static final String CHARGE_TYPE = "计费类型";
		public static final String CHARGE_NAME = "计费名称";
		public static final String CHARGE_AMOUNT = "默认金额";
		public static final String CYCLE = "计费周期";
		public static final String CREATE_NO = "操作者";
		public static final String CHARGE_ID = "费用主键";
		
		public static final String GRADE_ID = "学年ID";
		public static final String PLAN_NAME = "计划名";
		public static final String PLAN_TYPE = "费用类型";
		public static final String CYCLE_TYPE = "类型";
		public static final String TYPE = "其他类型";
		
		public static final String ACKDAT = "确认日";
		public static final String DAT = "扣款日";
		public static final String REPLAN_ID = "退费原计划号";
		
		public static final String STUDENT_ID = "学生ID";
		public static final String PLAN_ID = "计划ID";
		public static final String PAY_DATE = "默认扣款日";
		
//		public static final String CUSTNO = "用户编号";
//		public static final String APKIND = "用户编号";
//		public static final String TYPE = "用户编号";
//		public static final String CYCLE = "用户编号";
//		public static final String DAT = "用户编号";
//		public static final String FROMBANKSERIALID = "用户编号";
//		public static final String TOFUNDCODE = "用户编号";
//		public static final String TOFUNDCORPNO = "用户编号";
//		public static final String TOCHARGETYPE = "用户编号";
//		
//		public static final String TOBANKSERIALID = "用户编号";
//		public static final String FROMFUNDCODE = "用户编号";
//		public static final String FROMFUNDCORPNO = "用户编号";
//		public static final String FROMCHARGETYPE = "用户编号";
//		
//		public static final String AUTOAMT = "用户编号";
//		public static final String AUTOVOL = "用户编号";
//		public static final String STATE = "用户编号";
//	
		}
}
