package com.ufufund.ufb.model.action.cust;

import lombok.Data;
@Data
public class OpenAccountAction{
	
	private String fundcorpno;
	private String custno;
	private String invtp;
	private String level;
	private String tradepwd; // 交易密码，md5密文',
	private String tradepwd2; // 交易密码，md5密文',
	private String mobileautocode; // 手机验证码
	
	/** 接口必须 **/
	private String serialno; // 流水号
	private String accoreqserial; // 请求序列号
	private String transactionaccountid;
	private String otherserial; // 对方序列号
	private String protocolno; // 银行协议编号
	
	/** 个人 **/
	private String name; // 用户姓名',
	private String idno; // 证件号码',
	private String idtp;
	private String mobile;
	private String telno;
	private String email;
	
	/** 机构信息 **/
	private String orgnm; // 幼教机构名称
	private String orgbusiness; // 营业执照注册号
	private String orgprovinceno; // 省份
	private String orgprovincenm;
	private String orgcityno; // 城市
	private String orgcitynm;
	private String orgadd; // 实际办学经营地址
	
	/** 银行 **/
	// 1
	private String bankno; // 银行编码
	private String certtype; // 银行证件类型
	private String certno;   // 银行证件号码
	private String bankacco; // 银行卡号
	private String province; // 省份
	private String city;     // 城市
	private String subbank; // 支行网点
	private String banknm;
	
	/** 法人信息 **/
	private String rerpidtp; // 法人证件类型
	private String rerpidno; // 法人证件号码
	private String rerpvalidt; // 法人证件有效日期
	private String rerpnm; // 法人姓名

	private int hftfamilytradeaccoct;
	private int hftoperatortradeaccoct;
	private int hftorganizationtradeaccoct;
	private int cpfamilytradeaccoct;
	private int cporganizationtradeaccoct;
	
	private boolean openaccoflag = false; // 开户标志，不需要验证身份证、交易密码
	private boolean checkautocodeflag = false;
	
}
