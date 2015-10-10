package com.ufufund.ufb.model.vo;

import com.ufufund.ufb.model.db.Student;

import lombok.Data;

@Data
public class StudentVo extends Student{

	private String orgid;
//	private String name;
//	private String sex;
//	private String birthday;
//	private String p1name;
//	private String p1mobile;
	private String organization;
//	private String orgid;
}
