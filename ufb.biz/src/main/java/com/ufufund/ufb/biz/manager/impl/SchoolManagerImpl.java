package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.SchoolManager;
import com.ufufund.ufb.biz.manager.impl.validator.SchoolManagerValidator;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.dao.ClazzMapper;
import com.ufufund.ufb.dao.ClazzTypeMapper;
import com.ufufund.ufb.dao.StudentMapper;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;

@Service
public class SchoolManagerImpl implements SchoolManager{
	
	@Autowired
	private ClazzTypeMapper clazzTypeMapper;
	
	@Autowired
	private ClazzMapper clazzMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private SchoolManagerValidator smValidator;
	
	
	/**
	 * 获取机构下的所有班级
	 * @param orgid
	 * @return
	 */
	public List<Clazz> getClazzList(Clazz clazz){
		return clazzMapper.getList(clazz);
	}

	/**
	 * 添加班级
	 * @param clazz
	 * @return
	 */
	public int addClazz(Clazz clazz){
		smValidator.validateAddClazz(clazz);
		return clazzMapper.add(clazz);
	}
	
	/**
	 * 删除班级
	 * @param cid
	 * @return
	 */
	public int removeClazz(String cid){
		
		int n = studentMapper.getCountByClazz(cid);
		if(n > 0){
			throw new UserException("班级下有学生档案记录，不能删除！");
		}
		return clazzMapper.remove(cid);
	}
	
	/**
	 * 获取机构用户自定义班级类型
	 * @param orgid
	 * @return
	 */
	public List<ClazzType> getClazzTypeList(String orgid){
		return clazzTypeMapper.getList(orgid);
	}
	
	/**
	 * 添加机构自定义班级类型
	 * @param ct
	 * @return
	 */
	public int addClazzType(ClazzType ct){
		smValidator.validateAddClazzType(ct);
		return clazzTypeMapper.add(ct);
	}
	
	/**
	 * 删除机构自定义班级类型
	 * @param id
	 * @return
	 */
	public int removeClazzType(String id){
		Clazz c = new Clazz();
		c.setTypeid(id);
		if(clazzMapper.getCount(c) > 0){
			throw new UserException("有子班级，不能删除！");
		}
		return clazzTypeMapper.remove(id);
	}
}
