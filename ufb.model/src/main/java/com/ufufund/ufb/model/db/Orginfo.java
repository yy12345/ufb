package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Orginfo {
	private String orgid              ;
	private String orgname            ;
	private String type               ;
	private String license            ;
	private String province           ;
	private String city               ;
	private String address            ;
	private String license_address    ;
	private String operator_name      ;
	private String operator_certno    ;
	private String operator_mobile    ;
	private String operator_tel       ;
	private String operator_mail      ;
	private String legal_name         ;
	private String legal_certno       ;
	private String state              ;
	private String passwd             ;
	private String passwd_err         ;
	private String tradepwd           ;
	private String last_logintime     ;
	private String createtime         ;
	private String updatetime         ;

}
