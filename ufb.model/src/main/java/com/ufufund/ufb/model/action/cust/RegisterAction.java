package com.ufufund.ufb.model.action.cust;

import lombok.Data;

@Data
public class RegisterAction{

	private String custno;
	private String logincode;
	private String passwd;
	private String tradepwd;
	private String state;
	private String orgnm;// 机构名称
	private String orgbusiness;// 营业执照
	private String idno;

}
