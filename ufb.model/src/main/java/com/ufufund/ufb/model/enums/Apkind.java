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
	
	AUTORECHARGE("051"),  //自动充值
	AUTOWITHDRAWAL("052") //自动取现
	//AUTOAPPLY("042") 
	;
	private String value;

	private Apkind(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}


