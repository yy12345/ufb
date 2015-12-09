package com.ufufund.ufb.model.db;


import lombok.Data;

/**
 * 用户信息表dto
 * 
 * @author ayis
 * 
 */
@Data
public class Custinfo{

	private String custno;// 用户id
	private String name;// 用户姓名
	private String sex;// 性别:0男 1女
	private String idtp;// 0-身份证 1-护照 2-军官证 3-士兵证 4-港澳 5-户口本 6-外国护照 7-其它 8-文职证 9-警官证 a-台胞证'
	private String idno;// 证件号码
	private String mobileno;// 手机号
	private String email;   // 邮箱
	private String state;  // 账户状态：1 正常；5-冻结；6-销户
	private String passwd;
	private String lastlogintime;// 上次登录时间
	private int passwderr;// 密码错误次数
	private String tradepwd;// 交易密码，md5密文
	
	private String verifycode;
	private String Msgcode;

}
