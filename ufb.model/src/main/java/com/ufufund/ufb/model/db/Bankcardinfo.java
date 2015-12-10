package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Bankcardinfo{
	private String serialid;   // 银行卡编号（流水号）
	private String custno;     // 用户编号
	private String bankno;     // 银行编码
	private String bankacco;   // 银行卡号
	private String certtype;   // 银行证件类型
	private String certno;     // 银行证件号码
	private String state;      // Y 正常；N 已解绑
	private String province;   // 开户省份
	private String city;       // 开户城市
	private String subbank;    // 开户网点
	private String createtime; // 创建时间
	private String updatetime; // 更新时间	
	private String banknm;     // 银行名
	private String level;      // 银行卡级别:1-幼富宝卡；2-普通卡
	
	private String mobileautocode; // 手机验证码
	private String mobile;
}
