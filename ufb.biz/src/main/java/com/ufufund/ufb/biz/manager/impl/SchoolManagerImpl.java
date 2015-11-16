package com.ufufund.ufb.biz.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.SchoolManager;
import com.ufufund.ufb.biz.manager.impl.validator.SchoolManagerValidator;
import com.ufufund.ufb.biz.util.StudentExcelUtil;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.dao.ClazzMapper;
import com.ufufund.ufb.dao.ClazzTypeMapper;
import com.ufufund.ufb.dao.StudentMapper;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.enums.NormalClazzType;
import com.ufufund.ufb.model.vo.AdjustStudentVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.StudentVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchoolManagerImpl implements SchoolManager{
	
	@Autowired
	private ClazzTypeMapper clazzTypeMapper;	
	@Autowired
	private ClazzMapper clazzMapper;	
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private SchoolManagerValidator smValidator;
	@Autowired
	private CustManager custManager;
	
	/**
	 * 获取班级类型名字
	 * 1.typeid=0，返回名称 <code>全部</code>
	 * 2.typeid为<code>NormalClazzType</code>中定义的普通班级，则返回其对应名称
	 * 3.typeid为其它，返回clazztype表中对应的机构自定义班级类型的名称
	 * @param typeid
	 * @return
	 */
	public String getClazzTypeName(String typeid){
		
		String typeName = null;
		if(StringUtils.isBlank(typeid) || "0".equals(typeid)){
			typeName = "全部";
		}else if(NormalClazzType.TB.getId().equals(typeid)){
			typeName = NormalClazzType.TB.getName();
		}else if(NormalClazzType.XB.getId().equals(typeid)){
			typeName = NormalClazzType.XB.getName();
		}else if(NormalClazzType.ZB.getId().equals(typeid)){
			typeName = NormalClazzType.ZB.getName();
		}else if(NormalClazzType.DB.getId().equals(typeid)){
			typeName = NormalClazzType.DB.getName();
		}else{
			// 机构自定义班级
			ClazzType clazzType = clazzTypeMapper.get(typeid);
			typeName = clazzType.getName();
		}
		
		return typeName;
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
	 * 获取指定班级的学生数量
	 * @param clazzList 要查询的班级列表
	 * @return
	 */
	public void getClazzSize(List<Clazz> clazzList){
		int size = 0;
		for(Clazz c : clazzList){
			size = studentMapper.getCountByClazz(c.getCid());
			c.setSize(size);
		}
	}
	
	
	/**
	 * 生成学生档案模板
	 * @param orgid 机构id
	 * @param gradeid 年级id
	 * @param cid 班级id
	 * @return
	 */
	public String genStudentTemplate(String orgid, String gradeid, String cid){
		
		String downFilename = null;
		
		// 查询待生成导入模板的班级
		Clazz clazz = new Clazz();
		clazz.setOrgid(orgid);
		if(!"0".equals(gradeid)){
			clazz.setTypeid(gradeid);
		}
		if(!"0".equals(cid)){
			clazz.setCid(cid);
		}
		List<Clazz> clazzList = clazzMapper.getList(clazz);
		
		// 逐班生成模板
		Custinfo custinfo = custManager.getCustinfo(orgid);
		String dirName = StudentExcelUtil.getDirName(custinfo.getOrgnm());
		StudentExcelUtil.ensureDirEmpty(dirName);
		for(Clazz c : clazzList){
			String gradeName = getClazzTypeName(c.getTypeid());
			String excelFile = StudentExcelUtil.getTemplateName(custinfo.getOrgnm(), gradeName, c.getName());
			String savePath = dirName + excelFile;
			StudentExcelUtil.createFromTemplate(c, savePath);
		}
		downFilename = StudentExcelUtil.zipExcels(dirName.substring(0, dirName.length() -1), dirName);
		
		return downFilename;
	}
	
	/**
	 * 导入学生档案数据
	 * @param orgid
	 * @param filePath
	 * @return 导入班级的cid；全部班级，则为<code>0</code>
	 */
	public String importStudentExcel(String orgid, String filePath){
		
		StudentVo vo = new StudentVo();
		vo.setOrgid(orgid);
		Map<String, List<Student>> resultMap = StudentExcelUtil.readFromExcel(filePath);
		for(Entry<String, List<Student>> entry : resultMap.entrySet()){
			String clazzId = entry.getKey();
			List<Student> students = entry.getValue();
			// 导入学生档案数据
			vo.setCid(clazzId);
			smValidator.validateRemoveStudentByClazz(clazzId, students);
			studentMapper.remove(vo);
			studentMapper.addBatch(students);
		}
		
		return resultMap.size() > 0? resultMap.keySet().iterator().next():"0";
	}
	
	
	/**
	 * 查询班级下的学生列表
	 * @param clazzId
	 * @return
	 */
	public List<Student> getStudentList(String cid){
		Student s = new Student();
		s.setCid(cid);
		return studentMapper.getList(s);
	}
	
	/**
	 * 根据条件搜索学生记录
	 * @param vo
	 * @return
	 */
	public List<Student> searchStudentList(StudentVo vo){
		return studentMapper.getListWithOrg(vo);
	}
	
	/**
	 * 学生调班
	 * @param vo
	 * @return
	 */
	public int adjustStudent(AdjustStudentVo vo){
		smValidator.validateAdjustStudent(vo);
		
		List<String> cidList = null;
		List<String> sidList = null;
		if(!StringUtils.isBlank(vo.getCidStr())){
			cidList = Arrays.asList(vo.getCidStr().split(","));
		}
		if(!StringUtils.isBlank(vo.getSidStr())){
			sidList = Arrays.asList(vo.getSidStr().split(","));
		}
		return studentMapper.adjustStudent(vo, cidList, sidList);
	}
	
	/**
	 * 查询学生详细
	 * @param sid
	 * @return
	 */
	public Student queryStudent(String sid){
		return studentMapper.get(sid);
	}
	
	/**
	 * 更新学生档案信息
	 * @param s
	 * @return
	 */
	public int updateStudent(Student s){
		smValidator.validateUpdateStudent(s);
		return studentMapper.update(s);
	}
	
	/**
	 * 新增学生档案信息
	 * @param s
	 * @return
	 */
	public int addStudent(Student s){
		smValidator.validateAddStudent(s);
		return studentMapper.add(s);
	}
	
	/**
	 * 删除学生档案信息
	 * @param s
	 * @return
	 */
	public int removeStudent(StudentVo s){
		smValidator.validateRemoveStudent(s);
		return studentMapper.remove(s);
	}
	
	/**
	 * 根据学生id查询班级名、识别码
	 */
	public QueryCustplandetail getClassName(String studentid) {
		return studentMapper.getClassNmBySid(studentid);
	}
	
}
