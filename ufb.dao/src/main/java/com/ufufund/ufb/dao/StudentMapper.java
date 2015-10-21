package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.AdjustStudentVo;
import com.ufufund.ufb.model.vo.StudentVo;

public interface StudentMapper {

	Student get(String sid);

	List<Student> getList(Student s);
	
	List<Student> getListWithOrg(StudentVo vo);
	
	int add(Student s);
	
	int update(Student s);
	
	int remove(StudentVo s);
	
	int addBatch(List<Student> studentList);
	
	int adjustStudent(@Param("vo")AdjustStudentVo vo, @Param("cidList")List<String> cidList,
			@Param("sidList")List<String> sidList);
	
	int getCountByClazz(String cid);
	
}
