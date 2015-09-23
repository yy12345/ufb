package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;
import com.ufufund.ufb.model.db.Student;

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
	 * 获取指定班级的学生数量
	 * @param clazzList 要查询的班级列表
	 * @return
	 */
	void getClazzSize(List<Clazz> clazzList);
	
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
	 * @return 导入班级的typeid；全部班级，则为<code>0</code>
	 */
	String importStudentExcel(String filePath);
	
	/**
	 * 查询班级下的学生列表
	 * @param clazzId
	 * @return
	 */
	List<Student> getStudentList(String cid);
}
