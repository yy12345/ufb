package com.ufufund.ufb.model.enums;

public enum Apkind {

	/** 用户账户及开户类 **/
	LOGININ("011"), //登录
	REGISTER("012"),//注册
	CHANGE_PASSWORD("013"),//修改密码
	OPEN_ACCOUNT("014"),//开户 
	
	/** 交易类  **/
	SUBAPPLY("021"),    // 认购
	BUYAPPLY("022"),   	// 申购
	REDEEM("023"),		// 普通赎回
	REALREDEEM("023"),  // 快速赎回
	CANCEL("025"),		// 撤单
	PAYNOTIFY("026"),	// 认申购支付通知
	UNFROZEN("027"),	// 解冻
	
	/** 查询类  **/
	TRANSRESULTQUERY("031"),   // 交易结果查询
	STATICSHAREQUERY("032");	   // 份额查询
	
	private String value;

	private Apkind(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
