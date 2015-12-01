package com.ufufund.ufb.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.CityManager;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.OrgCodesMapper;
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.model.db.City;
import com.ufufund.ufb.model.db.OrgCodes;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.MsgCodeUtils.MsgCode;
import com.ufufund.ufb.web.util.OrgUserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;

import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping(value="corp/common")
@Slf4j
public class CorpCommonController {

	@Autowired
	private OrganManager organManager;
	@Autowired
	private OrgCodesMapper orgCodesMapper;
	@Autowired
	private BankBaseManager bankBaseManager ;
	@Autowired
	private CityManager cityManager;
	/**
	 * 检验图形验证码是否正确
	 * @param verifycode
	 * @return
	 */
	@RequestMapping(value = "verifycode_check")
	@ResponseBody
	public Map<String,Object> verifycodeCheck(String verifycode) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			VerifyCodeUtils.check(verifycode);
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
	 * 校验手机号是否注册
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="isRegister")
	@ResponseBody
	public Map<String,Object> isRegister(String mobile){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			Orginfo orginfo = new Orginfo();
			orginfo.setOperator_mobile(mobile);
			boolean result=organManager.isMobileRegister(orginfo);
			resultMap.put("errCode", "0000");
			resultMap.put("isRegister", result);
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
	 * 发送手机验证码
	 * @param mobileno
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "msgcode_send")
	@ResponseBody
	public Map<String,Object> msgcodeSend(String mobile, String code) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(StringUtils.isBlank(mobile)||StringUtils.isBlank(code)){
				throw new BizException("参数为空！");
			} 
			
			OrgCodes orgCodes = new OrgCodes();
			orgCodes.setCode(code);
			List<OrgCodes> orgcodeList = orgCodesMapper.getOrgCodeList(orgCodes);
			if(orgcodeList.size()==0){
				resultMap.put("errCode", "0001");
				resultMap.put("errMsg", "邀请码不正确！");
				return resultMap;
			}else{
				OrgCodes codes = (OrgCodes)orgcodeList.get(0);
				if(!StringUtils.isBlank(codes.getOrgid())){
					resultMap.put("errCode", "0002");
					resultMap.put("errMsg", "邀请码已被使用！");
					return resultMap;
				}
			}
			
			MsgCodeUtils.sendMsg(mobile, "0J001");
			resultMap.put("errCode", "0000");
			
			// this is test code ... removed later...
			MsgCode msgcode = (MsgCode)ServletHolder.getSession().getAttribute("MSGCODE");
			resultMap.put("msgcode", msgcode.getMsgCode());
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
	 * 检验短信验证码是否正确
	 * @param msgcode
	 * @return
	 */
	@RequestMapping(value = "msgcode_check")
	@ResponseBody
	public Map<String,Object> msgcodeCheck(String msgcode, String mobileno) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			MsgCodeUtils.check(msgcode, mobileno);
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
	 * 根据bin编码读取银行卡bin
	 * @param bin
	 * @return
	 */
	@RequestMapping(value="cardbin_read")
	@ResponseBody
	public Map<String,Object> cardbinRead(String bin){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			BankCardbin cardbin = bankBaseManager.getBankCardbin(bin);
			
			resultMap.put("errCode", "0000");
			resultMap.put("cardbin", cardbin);
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
	 * 检验交易密码是否与登录密码相同
	 * @param tradwpwd
	 * @return
	 */
	@RequestMapping(value="isTradePwdSame")
	@ResponseBody
	public Map<String,Object> isTradePwdSame(String tradwpwd){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String orgid = OrgUserHelper.getOrgid();
		try{
			if(StringUtils.isBlank(orgid)){
				throw new UserException("系统异常：未登录！");
			}else{
				Orginfo orginfo = new Orginfo();
				orginfo.setOrgid(orgid);
				orginfo= organManager.getOrginfo(orginfo);
				
				boolean isSame = EncryptUtil.md5(tradwpwd).equals(orginfo.getPasswd());
				resultMap.put("errCode","0000");
				resultMap.put("isSame", isSame);	
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
	 * 根据省市获得城市
	 * @param province
	 * @return
	 */
	@RequestMapping(value="getCity")
	@ResponseBody
	public Map<String,Object> getCity(City city){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			List<City> cityList = cityManager.getCityByProvince(city);
			String  cityArr = "";
			for(City citys:cityList){
				cityArr=cityArr+(citys.getCity())+",";
			}
			resultMap.put("errCode", "0000");
			resultMap.put("cityData", cityArr);
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
