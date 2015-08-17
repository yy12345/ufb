package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class MsgRecord {

	private String serailno  ;
	private String custno    ;
	private String mobileno  ;
	private String content   ;
	private String status    ;   // 上送状态：0-上送成功；
	private String updatetime;
}
