package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.ClazzType;

public interface ClazzTypeMapper {

	ClazzType get(String id);
	
	int add(ClazzType ct);
	
	int remove(String id);
	
	List<ClazzType> getList(String orgid);
}
