package com.ufufund.ufb.biz.manager.impl.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.ClazzMapper;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.AdjustStudentVo;
import com.ufufund.ufb.model.vo.StudentVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchoolManagerValidator {

	@Autowired
	private ClazzMapper clazzMapper;
	
	public void validateAddClazz(Clazz clazz){
		
		if(clazz == null || StringUtils.isBlank(clazz.getCid())
				|| StringUtils.isBlank(clazz.getOrgid())
				|| StringUtils.isBlank(clazz.getTypeid())
				|| StringUtils.isBlank(clazz.getName())){
			throw new UserException("输入参数为空！");
		}
		
		Clazz c = new Clazz();
		c.setOrgid(clazz.getOrgid());
		c.setTypeid(clazz.getTypeid());
		if(clazzMapper.getCount(c) >= 5){
			throw new UserException("只能添加5个子班级！");
		}
	}
	
	public void validateAddClazzType(ClazzType ct){
		
		if(ct == null || StringUtils.isBlank(ct.getId())
				|| StringUtils.isBlank(ct.getOrgid())
				|| StringUtils.isBlank(ct.getName())){
			throw new UserException("输入参数为空！");
		}
	}
	
	public void validateRemoveStudentByClazz(String clazzId, List<Student> students){
		// 1.检测clazzId，班级是否能全量删除
		// 2.检测students数据是否为空
		// code ...
	}
	
	public void validateAdjustStudent(AdjustStudentVo vo){
		if(vo == null || StringUtils.isBlank(vo.getToCid())){
			throw new UserException("输入参数为空！");
		}
		if(clazzMapper.get(vo.getToCid()) == null){
			throw new UserException("找不到调入班级！");
		}
	}
	
	public void validateUpdateStudent(Student s){
		if(s == null || StringUtils.isBlank(s.getSid()) 
				|| StringUtils.isBlank(s.getName()) || StringUtils.isBlank(s.getSex()) 
				|| StringUtils.isBlank(s.getBirthday()) || StringUtils.isBlank(s.getState())
				|| StringUtils.isBlank(s.getP1_name()) || StringUtils.isBlank(s.getP1_mobile())
				|| StringUtils.isBlank(s.getP1_mail())){
			log.warn("输入参数为空："+s.toString());
			throw new UserException("输入参数为空！");
		}
	}
	
	public void validateAddStudent(Student s){
		if(s == null || StringUtils.isBlank(s.getSid()) 
				|| StringUtils.isBlank(s.getCid()) || StringUtils.isBlank(s.getCname())
				|| StringUtils.isBlank(s.getName()) || StringUtils.isBlank(s.getSex()) 
				|| StringUtils.isBlank(s.getBirthday()) || StringUtils.isBlank(s.getState())
				|| StringUtils.isBlank(s.getP1_name()) || StringUtils.isBlank(s.getP1_mobile())
				|| StringUtils.isBlank(s.getP1_mail())){
			log.warn("输入参数为空："+s.toString());
			throw new UserException("输入参数为空！");
		}
	}
	
	public void validateRemoveStudent(StudentVo s){
		// code later...
	}
}
