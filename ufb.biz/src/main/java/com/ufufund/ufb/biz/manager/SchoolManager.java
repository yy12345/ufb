package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;

public interface SchoolManager {
	
	/**
	 * 获取机构用户自定义班级类型
	 * @param orgid
	 * @return
	 */
	List<ClazzType> getClazzTypeList(String orgid);
	
	/**
	 * 添加机构自定义班级类型
	 * @param ct
	 * @return
	 */
	int addClazzType(ClazzType ct);
	
	/**
	 * 删除机构自定义班级类型
	 * @param id
	 * @return
	 */
	int removeClazzType(String id);
	
	/**
	 * 获取机构下的所有班级
	 * @param orgid
	 * @return
	 */
	List<Clazz> getClazzList(String orgid);
	
	/**
	 * 添加班级
	 * @param clazz
	 * @return
	 */
	int addClazz(Clazz clazz);
	
	/**
	 * 删除班级
	 * @param cid
	 * @return
	 */
	int removeClazz(String cid);
	
}
