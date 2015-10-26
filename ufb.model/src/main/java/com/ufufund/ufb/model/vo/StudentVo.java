package com.ufufund.ufb.model.vo;

import com.ufufund.ufb.model.db.Student;

import lombok.Data;

@Data
public class StudentVo extends Student{

	private String orgid;
	private String organization;
	
	private String sexDisplay; 
	private String birthdayDisplay;
	private String f_typeDisplay;
}
