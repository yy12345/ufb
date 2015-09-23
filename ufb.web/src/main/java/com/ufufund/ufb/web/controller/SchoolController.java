package com.ufufund.ufb.web.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ufufund.ufb.biz.manager.SchoolManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.enums.NormalClazzType;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 班级管理、学生档案管理等
 * @author ayis
 * 2015年9月8日
 */
@Controller
@RequestMapping(value="school")
@Slf4j
public class SchoolController {
	
	private static final String CLASS_INDEX = "";
	private static final String STUDENT_INDEX = "";
	
	@Value("${ufb.school.excel_download_dir}")
	private String excel_download_dir;
	@Value("${ufb.school.excel_upload_dir}")
	private String excel_upload_dir;
	@Autowired
	private SchoolManager schoolManager;
	
	/**
	 * 异步查询机构班级类型的列表（同步方式，可直接调用对应manager层）
	 * 1.mode参数不传，查询所有班级类型
	 * 2.mode=1，只查询自定义班级类型
	 * 备注：使用get方式请求
	 * @return
	 */
	@RequestMapping(value="classtype_list", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> clazztypeList(String mode){
		
		String orgid = UserHelper.getCustno();
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			// 机构下，所有班级类型
			List<ClazzType> allTypes = new ArrayList<ClazzType>();
			// 普通班级
			List<ClazzType> normalTypes = NormalClazzType.getList(orgid);
			// 机构自定义班级类型
			List<ClazzType> otherTypes = schoolManager.getClazzTypeList(orgid);
			
			allTypes.addAll(normalTypes);
			allTypes.addAll(otherTypes);
			
			resultMap.put("errCode", "0000");
			if("1".equals(mode)){  // 只查询自定义班级类型
				resultMap.put("otherTypes", otherTypes);
			}else{  // 默认，查询所有班级类型
				resultMap.put("allTypes", allTypes);
			}
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 异步获取班级列表：（同步方式，可直接调用对应manager层）
	 * 1.typeid参数不传或typeid=0，获取机构下的所有班级列表
	 * 2.typeid传入，获取机构的对应typeid下的班级列表
	 * 备注：使用get方式请求
	 * @param typeid
	 * @return
	 */
	@RequestMapping(value="class_list", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> clazzList(String typeid){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String orgid = UserHelper.getCustno();
		try{
			Clazz clazz = new Clazz();
			clazz.setOrgid(orgid);
			// 是否根据typeid查询
			if(!StringUtils.isBlank(typeid) && !"0".equals(typeid)){
				clazz.setTypeid(typeid);
			}
			List<Clazz> clazzList = schoolManager.getClazzList(clazz);
			
			resultMap.put("errCode", "0000");
			resultMap.put("clazzList", clazzList);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="class_setting", method=RequestMethod.GET)
	public String classSetting(Model model){
		
		String orgid = UserHelper.getCustno();
		try{
			List<ClazzType> otherTypes = schoolManager.getClazzTypeList(orgid);
			Clazz clazz = new Clazz();
			clazz.setOrgid(orgid);
			List<Clazz> allList = schoolManager.getClazzList(clazz);
			
			// 普通班级：托、小、中、大班
			List<Clazz> list_1 = filterClazz(allList, NormalClazzType.TB.getId());
			List<Clazz> list_2 = filterClazz(allList, NormalClazzType.XB.getId());
			List<Clazz> list_3 = filterClazz(allList, NormalClazzType.ZB.getId());
			List<Clazz> list_4 = filterClazz(allList, NormalClazzType.DB.getId());
			
			// 机构自定义班级
			for(ClazzType ct : otherTypes){
				ct.setClazzList(filterClazz(allList, ct.getId()));
			}
			model.addAttribute("otherTypes", otherTypes);
			
			model.addAttribute("list_1", list_1);
			model.addAttribute("list_2", list_2);
			model.addAttribute("list_3", list_3);
			model.addAttribute("list_4", list_4);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "班级管理");
			model.addAttribute("message_url", CLASS_INDEX);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		
		return "school/class_setting";
	}
	
	@RequestMapping(value="class_add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addClazz(Clazz clazz){
		Map<String,String> resultMap = new HashMap<String,String>();
		
		String orgid = UserHelper.getCustno();
		try{
			clazz.setCid(SequenceUtil.getSerial());
			clazz.setOrgid(orgid);
			schoolManager.addClazz(clazz);
			resultMap.put("errCode", "0000");
			resultMap.put("cid", clazz.getCid());
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	@RequestMapping(value="class_remove", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> removeClazz(String cid){
		
		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			schoolManager.removeClazz(cid);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	@RequestMapping(value="classtype_add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addClazzType(ClazzType ct){
		Map<String,String> resultMap = new HashMap<String,String>();
		
		String orgid = UserHelper.getCustno();
		try{
			ct.setId(SequenceUtil.getSerial());
			ct.setOrgid(orgid);
			schoolManager.addClazzType(ct);
			resultMap.put("errCode", "0000");
			resultMap.put("id", ct.getId());
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	@RequestMapping(value="classtype_remove", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> removeClazzType(String id){
		
		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			schoolManager.removeClazzType(id);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	@RequestMapping(value="student_manager", method=RequestMethod.GET)
	public String studentManager(String typeid, Model model){
		
		String orgid = UserHelper.getCustno();
		if(StringUtils.isBlank(typeid)){
			typeid = "0";  // 0-全部类型
		}
		try{
			// 机构下，所有班级类型
			List<ClazzType> allTypes = new ArrayList<ClazzType>();
			List<ClazzType> normalTypes = NormalClazzType.getList(orgid);
			List<ClazzType> otherTypes = schoolManager.getClazzTypeList(orgid);
			allTypes.addAll(normalTypes);
			allTypes.addAll(otherTypes);
			
			// 机构下，所有班级列表
			Clazz clazz = new Clazz();
			clazz.setOrgid(orgid);
			// 是否根据typeid查询
			if(!"0".equals(typeid)){
				clazz.setTypeid(typeid);
			}
			List<Clazz> clazzList = schoolManager.getClazzList(clazz);
			// 获取班级学生数量
			schoolManager.getClazzSize(clazzList);
			
			model.addAttribute("allTypes", allTypes);
			model.addAttribute("clazzList", clazzList);
			model.addAttribute("typeid", typeid);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "学生档案管理");
			model.addAttribute("message_url", STUDENT_INDEX);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		
		return "school/student_manager";
	}
	
	/**
	 * 查询班级的学生列表
	 * @param cid 班级id
	 * @return
	 */
	@RequestMapping(value="student_list", method=RequestMethod.GET)
	public String studentList(String cid, Model model){
		try{
			List<Student> students = schoolManager.getStudentList(cid);
			model.addAttribute("students", students);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "班级管理");
			model.addAttribute("message_url", CLASS_INDEX);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		
		return "school/student_list";
	}
	
	@RequestMapping(value="student_downTemplate", method=RequestMethod.GET)
	public String studentDownTemplate(String typeid, Model model)
			throws UnsupportedEncodingException{
		
		String downFilename = "";
		String orgid = UserHelper.getCustno();
		try{
			downFilename = schoolManager.genStudentTemplate(orgid, typeid);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "学生档案管理");
			model.addAttribute("message_url", STUDENT_INDEX);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		
		downFilename = URLEncoder.encode(downFilename, "utf-8");
		return "redirect:/excel/"+downFilename;
	}
	
	@RequestMapping(value = "student_upload", method=RequestMethod.POST) 
	@ResponseBody
    public Map<String,String> studentUpload(@RequestParam("file") MultipartFile file) {  
		Map<String,String> resultMap = new HashMap<String,String>();
		
		String orgid = UserHelper.getCustno();
		try{
			
			String fileName = DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1) + "-" 
					+ orgid + "-" + String.valueOf(System.currentTimeMillis()).substring(8);
	        File targetFile = new File(excel_upload_dir, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        // 保存  
	        file.transferTo(targetFile);
	        // 解析数据
	        String typeid = schoolManager.importStudentExcel(targetFile.getAbsolutePath());
	        
	        resultMap.put("errCode", "0000");
	        resultMap.put("typeid", typeid);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
    }
	
	
	/**
	 * 根据类型过滤出不同类型的班级
	 * @param allList 所有班级
	 * @param type 
	 * @return
	 */
	private List<Clazz> filterClazz(List<Clazz> allList, String type){
		List<Clazz> list = new ArrayList<Clazz>();
		for(Clazz c : allList){
			if(c.getTypeid().equals(type)){
				list.add(c);
			}
		}
		return list;
	}
	
}
