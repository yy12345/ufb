package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class BankCardbin {
	
	private String bin   ;   
	private String bankno;   
	private String banknm;   
	private String type  ;   
	private String updatetime;
}
