package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.Student;

public interface StudentMapper {

	Student get(String sid);

	int bindStudent(Student s);
	
	OrgQuery getClassNmBySid(String sid);
	
}
