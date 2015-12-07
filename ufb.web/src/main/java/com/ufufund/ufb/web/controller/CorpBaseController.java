package com.ufufund.ufb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.OrgCodesMapper;
import com.ufufund.ufb.model.db.OrgCodes;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.MsgCodeUtils.MsgCode;
import com.ufufund.ufb.web.util.OrgUserHelper;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，账户相关业务模块
 * @author ayis
 * 2015年11月24日
 */
@Controller
@RequestMapping(value="corp")
@Slf4j
public class CorpBaseController {
	private static final String REGISTER_INDEX = "corp/register.htm";
	private static final String REGISTER_NAME = "注册";
	
	@Autowired
	private OrganManager organManager;
	@Autowired
	private OrgCodesMapper orgCodesMapper;

	/**
	 * 首页，登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model){
		return "organ/index";
	}
	
	/**
	 * 机构版：退出登录
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(){
		ServletHolder.getSession().invalidate();
		return "redirect:/corp/index.htm";
	}
	
	/**
	 * 首页，登录
	 * @param orginfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login")
	@ResponseBody
	public Map<String,Object> login(Orginfo orginfo, Model model) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			Orginfo organ=organManager.login(orginfo);
			if(organ!=null){
				OrgUserHelper.saveOrginfo(organ);
				resultMap.put("errCode", "0000");
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
	 * 注册页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register")
	public String register(Model model){
		return "organ/register";
	}
	
	/**
	 * 注册页，成功
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register_success", method=RequestMethod.POST)
	@Transactional
	public String registerSuccess(Orginfo orginfo,Model model){
		try{
			// 检验参数
			if(StringUtils.isBlank(orginfo.getCode())||StringUtils.isBlank(orginfo.getOperator_mobile())||StringUtils.isBlank(orginfo.getPasswd())){
				throw new UserException("参数为空！");
			}
			
			// 校验邀请码
			OrgCodes orgCodes = new OrgCodes();
			orgCodes.setCode(orginfo.getCode());
			List<OrgCodes> orgcodeList = orgCodesMapper.getOrgCodeList(orgCodes);
			if(orgcodeList.size()==0){
				throw new UserException("邀请码不正确！");
			}else{
				OrgCodes codes = (OrgCodes)orgcodeList.get(0);
				if(!StringUtils.isBlank(codes.getOrgid())){
					throw new UserException("邀请码已被使用！");
				}
			}
			
			// 添加机构信息
			orginfo.setOrgid(SequenceUtil.getSerial());
			orginfo.setPasswd(EncryptUtil.md5(orginfo.getPasswd()));
			Orginfo corp=organManager.addOrginfo(orginfo);
			
			// 更新邀请码数据
			orgCodes.setOrgid(corp.getOrgid());
			orgCodesMapper.bindOrgid(orgCodes);
			
			// 用户信息保存session
			OrgUserHelper.saveOrginfo(corp);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "注册失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", REGISTER_INDEX);
			model.addAttribute("back_module", REGISTER_NAME);
			return "error/error";
		}
		return "redirect:/corp/auth/auth_index.htm";
	}
	
}
