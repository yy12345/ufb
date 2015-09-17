package com.ufufund.ufb.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.SchoolManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;

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
	
	@Autowired
	private SchoolManager schoolManager;
	
	
	@RequestMapping(value="class_setting", method=RequestMethod.GET)
	public String classSetting(String orgid, Model model){
		
		orgid = "1";
		
		try{
			List<ClazzType> otherTypes = schoolManager.getClazzTypeList(orgid);
			List<Clazz> allList = schoolManager.getClazzList(orgid);
			
			// 普通班级：托、小、中、大班
			List<Clazz> list_1 = filterClazz(allList, Constant.ClazzTypeNormal.type_1);
			List<Clazz> list_2 = filterClazz(allList, Constant.ClazzTypeNormal.type_2);
			List<Clazz> list_3 = filterClazz(allList, Constant.ClazzTypeNormal.type_3);
			List<Clazz> list_4 = filterClazz(allList, Constant.ClazzTypeNormal.type_4);
			
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
		
		String orgid = "1";
		
		Map<String,String> resultMap = new HashMap<String,String>();
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
		
		String orgid = "1";
		
		Map<String,String> resultMap = new HashMap<String,String>();
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
