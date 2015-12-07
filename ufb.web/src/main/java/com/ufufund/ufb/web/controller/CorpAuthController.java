package com.ufufund.ufb.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.CityManager;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.FileUtils;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.City;
import com.ufufund.ufb.model.db.OrgCodes;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.OrgUserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，机构认证业务模块
 * @author ayis
 * 2015年11月24日
 */
@Controller
@RequestMapping(value="corp/auth")
@Slf4j
public class CorpAuthController {
	
	private static final String AUTH_INDEX = "corp/auth/auth_base.htm";
	private static final String AUTH_NAME = "账户认证";
	private static final String ACCOUNT = "corp/uft/uft_index.htm";
	private static final String ACCOUNT_NAME = "我的账户";
	private static final String AUTH_ORGINFO="auth_orginfo";
	private static final String AUTH_PICINFO="auth_picinfo";
	
	@Value("${ufb.corp.infopic}")
	private String infopic; 
	@Value("${ufb.corp.infopic_web}")
	private String infopic_web;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private OrganManager organManager;
	@Autowired
	private CityManager cityManager;
	/**
	 * 认证页，首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_index")
	public String authIndex(Model model){
		return "organ/auth/auth_index";
	}
	
	/**
	 * 认证页，基本资料填写
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_base", method=RequestMethod.GET)
	public String authBase(Model model){
		String orgid = OrgUserHelper.getOrgid();
		try {
			Orginfo orginfo = new Orginfo();
			orginfo.setOrgid(orgid);
			orginfo=organManager.getOrginfo(orginfo);
			
			// 对公银行账户验证
			if("3".equals(orginfo.getState())){
				return "redirect:/corp/auth/code_index.htm";
			}
			
			// 获得城市列表
			List<City> provinceList = cityManager.getAllProvince();
			model.addAttribute("provinceList", provinceList);
			
			model.addAttribute("mobile", orginfo.getOperator_mobile());
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", AUTH_INDEX);
			model.addAttribute("back_module", AUTH_NAME);
			return "error/error";
		}
		return "organ/auth/auth_base";
	}
	
	/**
	 * 认证页，资质照片上传
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_certpic")
	public String authCertpic(Orginfo orginfo,Model model){
		Orginfo s_orginfo=OrgUserHelper.getOrginfo();
		try{
			// 校验身份证是否已注册
 			boolean result = organManager.isCertnoRegister(orginfo.getOperator_certno());
 			if(result){
 				throw new UserException("系统异常！");
 			}
			
			// 将机构信息存入session
			orginfo.setOrgid(s_orginfo.getOrgid());
			OrgUserHelper.setSessionAttr(AUTH_ORGINFO, orginfo);
			OrgUserHelper.setSessionAttr(AUTH_PICINFO, null);
			
			model.addAttribute("Orginfo", orginfo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", AUTH_INDEX);
			model.addAttribute("back_module", AUTH_NAME);
			return "error/error";
		}
		return "organ/auth/auth_certpic";
	}
	
	/**
	 * 上传机构证件照
	 * @param file
	 * @param pictype
	 * @return
	 */
	@RequestMapping(value="upload_infopic")
	@ResponseBody
	public Map<String,String> uploadInfopic(@RequestParam("file") MultipartFile file,String pictype){
		Map<String,String> resultMap = new HashMap<String, String>();
		String orgid=OrgUserHelper.getOrgid();
		try {
			String oldfilename=null;
			String filename=file.getOriginalFilename();
			filename =pictype+"_"+SequenceUtil.getSerial()+filename.substring(filename.lastIndexOf("."));
			
			// 判断session数据
			Picinfo picinfo=(Picinfo)OrgUserHelper.getSessionAttr(AUTH_PICINFO);
			if(picinfo==null){
				picinfo = new Picinfo();
				FileUtils.ensureDirEmpty(infopic+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid);
				FileUtils.ensureDirEmpty(infopic_web+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid);
			}
			if("01".equals(pictype)){
				oldfilename = picinfo.getImg1();
				picinfo.setImg1(filename);
			}
			if("02".equals(pictype)){
				oldfilename = picinfo.getImg2();
				picinfo.setImg2(filename);
			}
			if("03".equals(pictype)){
				oldfilename = picinfo.getImg3();
				picinfo.setImg3(filename);
			}
			if("04".equals(pictype)){
				oldfilename = picinfo.getImg4();
				picinfo.setImg4(filename);
			}
			if("05".equals(pictype)){
				oldfilename = picinfo.getImg5();
				picinfo.setImg5(filename);
			}
			if("06".equals(pictype)){
				oldfilename = picinfo.getImg6();
				picinfo.setImg6(filename);
			}
			if("07".equals(pictype)){
				oldfilename = picinfo.getImg7();
				picinfo.setImg7(filename);
			}
			if("08".equals(pictype)){
				oldfilename = picinfo.getImg8();
				picinfo.setImg8(filename);
			}
			if("09".equals(pictype)){
				oldfilename = picinfo.getImg9();
				picinfo.setImg9(filename);
			}
			if("10".equals(pictype)){
				oldfilename = picinfo.getImg10();
				picinfo.setImg10(filename);
			}
			
			// 删除旧图片
			if(!StringUtils.isBlank(oldfilename)){
				File oldfile = new File(infopic+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid+"/",oldfilename);
				File oldfile_web = new File(infopic_web+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid+"/",oldfilename);
				oldfile.delete();
				oldfile_web.delete();
			}
			
			// 添加新图片
	        File targetFile = new File(infopic+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid+"/", filename);  
	        File targetFile_web = new File(infopic_web+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid+"/", filename);  
	        file.transferTo(targetFile);
	        FileUtils.copyFile(targetFile.getAbsolutePath(), targetFile_web.getAbsolutePath());
	        
	        
	        // 保存session
			OrgUserHelper.setSessionAttr(AUTH_PICINFO, picinfo);
			
			// 生成图片url
	        String picUrl = "infopic/"+DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1)+"/"+orgid+"/" + filename;
	        resultMap.put("errCode", "0000");
	        resultMap.put("picUrl", picUrl);
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
	
	@RequestMapping(value="download")
	@ResponseBody
	public Map<String,Object> download(String url,String title){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			if(url.indexOf("../")<0){
				String[] path = url.split("\\.");
				String hz = "";
				if (path.length >= 1)
				{
					hz = "." + path[1];
				}
				BufferedInputStream bufIn = null;
				BufferedOutputStream bufOut = null;
				ServletHolder.getResponse().reset();// 清空输出流  
				try
				{
					ServletHolder.getResponse().setContentType("application/x-msdownload");
					ServletHolder.getResponse().setHeader("Content-disposition", "attachment; filename=" + new String(title.getBytes("gb2312"), "iso8859-1"));
					bufIn = new BufferedInputStream(new FileInputStream(ServletHolder.getSession().getServletContext().getRealPath(url)));
					bufOut = new BufferedOutputStream(ServletHolder.getResponse().getOutputStream());
					byte[] buff = new byte[2048];
					int bytesRead;
					while (-1 != (bytesRead = bufIn.read(buff, 0, buff.length)))
					{
						bufOut.write(buff, 0, bytesRead);
					}
					bufOut.flush();
				}
				catch (IOException ex)
				{
					log.error("", ex);
				}
				finally
			   {
					if (bufIn != null)
						bufIn.close();
					if (bufOut != null)
						bufOut.close();
				}
				return null;
			}else{
				throw new UserException("");
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
	 * 认证页，银行卡资料填写
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_card")
	public String auth_card(Model model){
		try{
			Orginfo orginfo=(Orginfo)OrgUserHelper.getSessionAttr(AUTH_ORGINFO);
			Picinfo picinfo=(Picinfo)OrgUserHelper.getSessionAttr(AUTH_PICINFO);
			if(orginfo==null||picinfo==null){
				throw new UserException("系统异常！");
			}else{
				// 获得银行列表
				List<BankBaseInfo> bankBase=bankBaseManager.getBankBaseInfoList(null);
				
				// 获得城市列表
				List<City> provinceList = cityManager.getAllProvince();
				
				model.addAttribute("bankBase", bankBase);
				model.addAttribute("provinceList", provinceList);
				model.addAttribute("operatorName", orginfo.getOrgname());
			}
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", AUTH_INDEX);
			model.addAttribute("back_module", AUTH_NAME);
			return "error/error";
		}
		
		return "organ/auth/auth_card";
	}
	
	/**
	 * 认证页，银行卡资料填写
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_result")
	public String authResult(Bankcardinfo bankCardInfo,String tradepwd,Model model){
		Orginfo orginfo = OrgUserHelper.getOrginfo();
		try {
			Orginfo organ=(Orginfo)OrgUserHelper.getSessionAttr(AUTH_ORGINFO);
			Picinfo picinfo=(Picinfo)OrgUserHelper.getSessionAttr(AUTH_PICINFO);
			if(organ==null||picinfo==null){
				throw new UserException("系统异常！");
			}else{
				if(StringUtils.isBlank(tradepwd)||StringUtils.isBlank(bankCardInfo.getBankacco())||StringUtils.isBlank(bankCardInfo.getBankno())
						||StringUtils.isBlank(bankCardInfo.getProvince())||StringUtils.isBlank(bankCardInfo.getCity())||StringUtils.isBlank(bankCardInfo.getSubbank())){
					throw new UserException("系统异常！");
				}
				organ.setTradepwd(EncryptUtil.md5(tradepwd));
				
				// 判断交易密码是否与登录密码相同
				orginfo=organManager.getOrginfo(organ);
				if(orginfo.getPasswd().equals(organ.getTradepwd())){
					throw new UserException("交易密码与登录密码相同！");
				}
				
				// 修改机构基本信息、添加机构证件信息、添加机构银行信息
				if("true".equals(organ.getEqaddress())){
					organ.setLicense_address(organ.getAddress());
				}
				
				// 更新机构信息
				organManager.bindOrgan(organ, picinfo, bankCardInfo);
				
				// 发送1元随机金额   later   remove....
				organManager.sendAmt(organ.getOrgid());
			}
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", AUTH_INDEX);
			model.addAttribute("back_module", AUTH_NAME);
			return "error/error";
		}
		return "organ/auth/auth_result";
	}
	
    /**
     * 账户金额验证页
     * @param model
     * @return
     */
	@RequestMapping(value="code_index")
	public String codeIndex(Model model){
		String orgid = OrgUserHelper.getOrgid();
		try {
			
			// 验证金额
			OrgCodes orgCode = new OrgCodes();
			orgCode.setOrgid(orgid);
			boolean result = organManager.getAmtInvalid(orgCode);
			
			// 如果金额验证时间失效或发送失败(短信通知)，则重新账户验证    later...
			if(!result){
				// 删除银行卡、证件照信息
				organManager.remove(orgid);
				
				//修改用户状态
				organManager.updateState("2", orgid);
				return "redirect:/corp/auth/auth_index.htm";
			}
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", ACCOUNT);
			model.addAttribute("back_module", ACCOUNT_NAME);
			return "error/error";
		}
		return "organ/auth/code_index";
	}
	
	/**
	 * 账户金额验证
	 * @param orgCodes
	 * @return
	 */
	@RequestMapping(value="checkCode")
	@ResponseBody
	public Map<String,Object> checkCode(OrgCodes orgCodes){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Orginfo orginfo = OrgUserHelper.getOrginfo();
		try {
			if(StringUtils.isBlank(orgCodes.getAmt_code())||StringUtils.isBlank(orginfo.getOrgid())){
				throw new UserException("系统异常！");
			}
			
			orgCodes.setOrgid(orginfo.getOrgid());
			boolean result = organManager.getOrgCodes(orgCodes);
			
			// 更新用户的状态
			if(result){
				organManager.updateState("4", orginfo.getOrgid());
			}
			
			resultMap.put("errCode","0000");
			resultMap.put("errMsg",result);
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
}
