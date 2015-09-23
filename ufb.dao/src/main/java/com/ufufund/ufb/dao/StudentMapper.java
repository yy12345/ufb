package com.ufufund.ufb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.Student;

public interface StudentMapper {

	Student get(String sid);
	
	int add(Student student);
	
	int update(Student student);
	
	int remove(String sid);
	
	int removeByClazz(String cid);
	
	int addBatch(List<Student> studentList);
	
	int adjustClazz(@Param("c")Clazz newClazz, @Param("sidList")List<String> sidList);
	
	int getCountByClazz(String cid);
	
	List<Student> getListByClazz(String cid);
}
