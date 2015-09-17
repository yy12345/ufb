package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.Student;

public interface StudentMapper {

	Student get(String sid);
	
	int add(Student student);
	
	int update(Student student);
	
	int remove(String sid);
	
	int addBatch(List<Student> studentList);
	
	int adjustClazz(@Param("oldCid")String oldCid, @Param("newCid")String newCid, 
			@Param("sidList")List<String> sidList);
	
	int removeByClazz(String cid);
	
	int getCountByClazz(String cid);
}
