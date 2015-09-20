package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;

public interface SchoolManager {
	
	/**
	 * 获取班级类型名字
	 * 1.typeid=0，返回名称 <code>全部</code>
	 * 2.typeid为<code>NormalClazzType</code>中定义的普通班级，则返回其对应名称
	 * 3.typeid为其它，返回clazztype表中对应的机构自定义班级类型的名称
	 * @param typeid
	 * @return
	 */
	String getClazzTypeName(String typeid);
	
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
	List<Clazz> getClazzList(Clazz clazz);
	
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
	
	/**
	 * 生成学生档案模板
	 * @param orgid 机构id
	 * @param typeid 班级类型id
	 * @return
	 */
	String genStudentTemplate(String orgid, String typeid);
	
	/**
	 * 导入学生档案数据
	 * @param filePath
	 */
	void importStudentExcel(String filePath);
}
