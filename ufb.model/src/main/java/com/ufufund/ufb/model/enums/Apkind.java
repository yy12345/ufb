package com.ufufund.ufb.model.enums;

public enum Apkind {

	/** 用户账户及开户类 **/
	LOGININ("011"), //登录
	REGISTER("012"),//注册
	CHANGE_PASSWORD("013"),//修改密码
	OPEN_ACCOUNT("014"),//开户 
	
	
	SWIFTAUTH("901"),	// 银行快捷鉴权
	SWIFTVERIFY("902"),	// 银行快捷验证
	
	/** 交易类  **/
	SUBAPPLY("021"),    // 认购
	BUYAPPLY("022"),   	// 申购
	REDEEM("023"),		// 普通赎回
	REALREDEEM("024"),  // 快速赎回
	CANCEL("025"),		// 撤单
	BUYNOTIFY("026"),	// 认申购支付通知
	
	FROZEN("031"),		// 冻结
	UNFROZEN("032"),	// 解冻
	TRANSFER("033"),	// 过户
	
	/** 查询类  **/
	TRANSRESULTQUERY("041"),   // 交易结果查询
	STATICSHAREQUERY("042"),   // 份额查询
	
	/*  0 新增 1修改 2恢复 3暂停 4删除
	 * 51 自动充值
	 * 52 自动取现
	 */
	ADD_AUTORECHARGE("510"),     //新增自动充值
	MODIFY_AUTORECHARGE("511"),  //修改自动充值
	N_AUTORECHARGE("512"),  	 //恢复自动充值
	P_AUTORECHARGE("513"),  	 //暂停自动充值
	C_AUTORECHARGE("514"),  	 //删除自动充值
	S_AUTORECHARGE("515"),       //自动充值
	
	ADD_AUTOWITHDRAWAL("520"), 	  //新增自动取现
	MODIFY_AUTOWITHDRAWAL("521"), //修改自动取现
	N_AUTOWITHDRAWAL("522"), 	  //恢复自动取现
	P_AUTOWITHDRAWAL("523"), 	  //暂停自动取现
	C_AUTOWITHDRAWAL("524"), 	  //删除自动取现
	S_AUTOWITHDRAWAL("525")       //自动取现
	
	;
	private String value;

	private Apkind(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}


