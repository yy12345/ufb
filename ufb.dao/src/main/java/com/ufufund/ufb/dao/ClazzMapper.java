package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.Clazz;

/**
 * 班级mapper类
 * @author ayis
 * 2015年9月10日
 */
public interface ClazzMapper {

	Clazz get(String cid);
	
	int add(Clazz clazz);
	
	int remove(String cid);
	
	int update(Clazz clazz);
	
	List<Clazz> getList(String cid);
	
	int getCount(Clazz clazz);
}
