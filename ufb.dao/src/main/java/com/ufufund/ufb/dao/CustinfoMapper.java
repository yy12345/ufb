package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.Student;


public interface CustinfoMapper  {
	
	public Custinfo getCustinfo(Custinfo custinfo);
	
	public void insertCustinfo(Custinfo custinfo);
	
	public void updateCustinfo(Custinfo custinfo);
	
	public List<Student> queryStudentsByCustno(String custno);
	
	public Student  queryOrgsByCid(String cid);
	
	public OrgQuery  queryOrgBankInfo(String custno);
}
