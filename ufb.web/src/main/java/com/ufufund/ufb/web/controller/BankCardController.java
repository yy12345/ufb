package com.ufufund.ufb.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.PicInfo;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class BankCardController {

	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="org/openAccoStep1")
	public String openAccoStep1(BankCardVo bankCardVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			bankCardVo.setCustno(s_custinfo.getCustno());
			bankCardVo.setMobile(s_custinfo.getMobileno());
			bankCardVo.setOrgnm(s_custinfo.getOrgnm()); 
			bankCardVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			
			// for test
			bankCardVo.setOrgprovinceno("001");
			bankCardVo.setOrgcityno("001");
			bankCardVo.setOrgadd("测试幼儿园1地址");
			bankCardVo.setInvnm("测试账号1");
			bankCardVo.setIdno("11010119880808837X");
			bankCardVo.setTelno("12345678");
			bankCardVo.setEmailadd("12345678@qq.com");
			bankCardVo.setRerpnm(bankCardVo.getInvnm());
			bankCardVo.setRerpidno(bankCardVo.getIdno());
			// test end
			
			if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
				bankCardVo.setInvnm(s_custinfo.getInvnm());
				bankCardVo.setIdno(s_custinfo.getIdno());
			}else{
			}
			
			UserHelper.setAddBankCardStatus("N");
			model.addAttribute("BankCardVo", bankCardVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "cust/indexPage";
		}
		return "org/openAccoStep1"; // 企业资料
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="org/openAccoStep2")
	public String openAccoStep2(BankCardVo bankCardVo, Model model){
		try{
			
			if("Y".equals(UserHelper.getAddBankCardStatus())){
				// 此开户流程已结束
				ServletHolder.forward("/cust/session.htm");
				return "cust/indexPage";
			}
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			openAccountAction.setCustno(s_custinfo.getCustno());
			
			/** 机构 **/
			openAccountAction.setOrgbusiness(bankCardVo.getOrgbusiness());
			openAccountAction.setOrgnm(bankCardVo.getOrgnm());
			openAccountAction.setOrgprovinceno(bankCardVo.getOrgprovinceno()); //机构省、直辖市
			openAccountAction.setOrgcityno(bankCardVo.getOrgcityno()); // 机构市
			openAccountAction.setOrgadd(bankCardVo.getOrgadd()); // 机构地址
			/** 经办人 **/
			openAccountAction.setInvnm(bankCardVo.getInvnm());
			openAccountAction.setIdno(bankCardVo.getIdno());
			openAccountAction.setMobile(bankCardVo.getMobile());
			openAccountAction.setTelno(bankCardVo.getTelno());
			openAccountAction.setEmailadd(bankCardVo.getEmailadd());
			/** 法人 **/
			openAccountAction.setRerpidno(bankCardVo.getRerpidno());
			openAccountAction.setRerpnm(bankCardVo.getRerpnm());
			
			if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
				openAccountAction.setOpenaccoflag(true);
			}else{
				openAccountAction.setOpenaccoflag(false);
			}
			
			
			bankCardManager.openAccoStep2(openAccountAction);
			model.addAttribute("BankCardVo", bankCardVo);

			PicInfo picInfo = new PicInfo();
			picInfo.setCustno(s_custinfo.getCustno());
			picInfo = bankCardManager.getPicInfo(picInfo);
			model.addAttribute("PicInfo", picInfo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(ems.equals(BisConst.Register.ORGNM)){
				model.addAttribute("errMsg_orgnm", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.ORGBUSINESS)){
				model.addAttribute("errMsg_orgbusiness", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.CUSTNO)){
				model.addAttribute("errMsg_custno", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.INVNM)){
				model.addAttribute("errMsg_invnm", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.IDNO)){
				model.addAttribute("errMsg_idno", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.RERPNM)){
				model.addAttribute("errMsg_rerpnm", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.RERPIDNO)){
				model.addAttribute("errMsg_rerpidno", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("errMsg", e.getMessage());
			
			model.addAttribute("BankCardVo", bankCardVo);
			return "org/openAccoStep1";
		}
		return "org/openAccoStep2"; // 图
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="org/openAccoStep3")
	public String openAccoStep3(BankCardVo bankCardVo, Model model){
		try{
			
			if("Y".equals(UserHelper.getAddBankCardStatus())){
				// 此开户流程已结束
				ServletHolder.forward("/cust/session.htm");
				return "cust/indexPage";
			}
			
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setCustno(s_custinfo.getCustno());
			
			// for test
			bankCardVo.setBankacnm("xxx幼儿园");
			bankCardVo.setBankno("920");
			bankCardVo.setBankcityno("001");
			bankCardVo.setBankprovinceno("002");
			bankCardVo.setBankadd("支行网点");
			bankCardVo.setBankacco("6225882211122222");
			bankCardVo.setBankacco2("6225882211122222");
			bankCardVo.setTradepwd("123qwe");
			bankCardVo.setTradepwd2("123qwe");
			
			model.addAttribute("BankCardVo", bankCardVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "cust/indexPage";
		}
		return "org/openAccoStep3"; // 银行
	}
	
	
	/**
	 * 绑卡-银行卡绑定(验证) + 开户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="org/openAccoStep4" , method=RequestMethod.POST)
	public String openAccoStep4(BankCardVo bankCardVo, Model model){
		try{
			
			if("Y".equals(UserHelper.getAddBankCardStatus())){
				// 此开户流程已结束
				ServletHolder.forward("/cust/session.htm");
				return "cust/indexPage";
			}
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			openAccountAction.setCustno(s_custinfo.getCustno());
			
			if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
				openAccountAction.setOpenaccoflag(true);
			}else{
				openAccountAction.setOpenaccoflag(false);
			}
			
			openAccountAction.setLevel("1");
			// page1
			openAccountAction.setOrgbusiness(bankCardVo.getOrgbusiness());
			openAccountAction.setOrgnm(bankCardVo.getOrgnm());
			openAccountAction.setOrgprovinceno(bankCardVo.getOrgprovinceno());
			openAccountAction.setOrgcityno(bankCardVo.getOrgcityno());
			openAccountAction.setOrgadd(bankCardVo.getOrgadd());
			openAccountAction.setInvnm(bankCardVo.getInvnm());
			openAccountAction.setIdno(bankCardVo.getIdno());
			openAccountAction.setMobile(bankCardVo.getMobile());
			openAccountAction.setTelno(bankCardVo.getTelno());
			openAccountAction.setEmailadd(bankCardVo.getEmailadd());
			openAccountAction.setRerpidno(bankCardVo.getRerpidno());
			openAccountAction.setRerpnm(bankCardVo.getRerpnm());
			
			// page3
			openAccountAction.setBankacnm(bankCardVo.getBankacnm());
			openAccountAction.setBankno(bankCardVo.getBankno());
			openAccountAction.setBankcityno(bankCardVo.getBankcityno());
			openAccountAction.setBankprovinceno(bankCardVo.getBankprovinceno());
			openAccountAction.setBankadd(bankCardVo.getBankadd());
			openAccountAction.setBankacco(bankCardVo.getBankacco());
			openAccountAction.setBankacco2(bankCardVo.getBankacco2());
			openAccountAction.setTradepwd(bankCardVo.getTradepwd());
			openAccountAction.setTradepwd2(bankCardVo.getTradepwd2());
			
			openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);// 海富通
			
			bankCardManager.openAccountOrg(openAccountAction);
			
			UserHelper.setAddBankCardStatus("Y");
			// 经办人已开户，不要
			if(!openAccountAction.isOpenaccoflag()){
				s_custinfo.setInvnm(bankCardVo.getInvnm());
				s_custinfo.setIdno(bankCardVo.getIdno());
			}
			UserHelper.saveCustinfoVo(s_custinfo);
			
			model.addAttribute("BankCardVo", bankCardVo);
			model.addAttribute("CustinfoVo", s_custinfo);
		}catch (BizException e){
			
			// 获取银行列表
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			if(StringUtils.isBlank(bankCardVo.getBankno())){
				// 默认第一个
				bankCardVo.setBankno(bankBaseList.get(0).getBankno());
			}else{
				// 上下文中的
			}
			model.addAttribute("bankList", bankBaseList);
			
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(ems.equals(BisConst.Register.BANKACNM)){
				model.addAttribute("errMsg_bankacnm", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.BANKNO)){
				model.addAttribute("errMsg_bankno", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.BANKADD)){
				model.addAttribute("errMsg_bankadd", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.BANKACCO)){
				model.addAttribute("errMsg_bankacco", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.BANKACCO2)){
				model.addAttribute("errMsg_bankacco2", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.TRADEPWD)){
				model.addAttribute("errMsg_tradepwd", e.getMessage());
			}else
			if(ems.equals(BisConst.Register.TRADEPWD2)){
				model.addAttribute("errMsg_tradepwd2", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			
			model.addAttribute("BankCardVo", bankCardVo);
			return "org/openAccoStep3";
		}
		return "org/openAccoStep4"; // 成功
	}
	
	
	
	
	/**
	 * 绑卡-Page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCard1")
	public String addBankCard1(BankCardVo bankCardVo, Model model){
		
		try{
			/** 绑卡初始化 **/
			UserHelper.setAddBankCardStatus("N");
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
				// 已开户
				ServletHolder.forward("/bankcard/addBankCard2.htm");
				return "bankcard/addBankCard2";
			}else{
				// 新开户
				bankCardVo.setCustno(s_custinfo.getCustno());
				bankCardVo.setMobile(s_custinfo.getMobileno());
				bankCardVo.setInvtp(s_custinfo.getInvtp());
				bankCardVo.setOrgnm(s_custinfo.getOrgnm());
				bankCardVo.setOrgbusiness(s_custinfo.getOrgbusiness());
				model.addAttribute("BankCardVo", bankCardVo);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "cust/indexPage";
		}
		return "bankcard/addBankCard1";
	}
	
	/**
	 * 绑卡-验证身份
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCard2")
	public String addBankCard2(BankCardVo bankCardVo, Model model){
		
		try{
			if("Y".equals(UserHelper.getAddBankCardStatus())){
				// 此开户流程已结束
				ServletHolder.forward("/cust/session.htm");
				return "cust/indexPage";
			}
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			
			if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
				// 已开户
				/** 开户标志 **/
				bankCardVo.setOpenaccoflag(true);
				bankCardVo.setOrgnm(s_custinfo.getOrgnm());
				bankCardVo.setInvtp(s_custinfo.getInvtp());
				bankCardVo.setLevel(s_custinfo.getLevel());
				bankCardVo.setOrgbusiness(s_custinfo.getOrgbusiness());
				bankCardVo.setCustno(s_custinfo.getCustno());
				bankCardVo.setMobile(s_custinfo.getMobileno());
				bankCardVo.setBankmobile(s_custinfo.getMobileno());
				bankCardVo.setBankacnm(s_custinfo.getInvnm());
				bankCardVo.setBankidtp(s_custinfo.getIdtp());
				bankCardVo.setBankidno(s_custinfo.getIdno());
				bankCardVo.setTradepwd(s_custinfo.getTradepwd());
				bankCardVo.setTradepwd2(s_custinfo.getTradepwd2());
			}else{
				// 新开户
				bankCardVo.setOpenaccoflag(false);
				openAccountAction.setOrgnm(bankCardVo.getOrgnm());
				openAccountAction.setInvtp(bankCardVo.getInvtp());
				openAccountAction.setLevel(bankCardVo.getLevel());
				openAccountAction.setOrgbusiness(bankCardVo.getOrgbusiness());
				openAccountAction.setCustno(s_custinfo.getCustno());
				openAccountAction.setInvnm(bankCardVo.getBankacnm());
				openAccountAction.setBankidtp(bankCardVo.getBankidtp());
				openAccountAction.setBankidno(bankCardVo.getBankidno());
				openAccountAction.setIdno(bankCardVo.getBankidno());
				openAccountAction.setTradepwd(bankCardVo.getTradepwd());
				openAccountAction.setTradepwd2(bankCardVo.getTradepwd2());
				bankCardManager.openAccount1(openAccountAction);
			}
			
			/** 获取银行列表 **/
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			if(StringUtils.isBlank(bankCardVo.getBankno())){
				// 默认第一个
				bankCardVo.setBankno(bankBaseList.get(0).getBankno());
			}else{
				// 上下文中的
			}
			
			/** 页面元素 **/
			model.addAttribute("bankList", bankBaseList);
			model.addAttribute("BankCardVo", bankCardVo);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.BANKIDNO.equals(ems) 
				|| BisConst.Register.IDNO.equals(ems)){
				model.addAttribute("errMsg_bankIdno", e.getMessage());
			}else
			if(BisConst.Register.BANKACNM.equals(ems) 
				|| BisConst.Register.INVNM.equals(ems)){
				model.addAttribute("errMsg_bankAcnm", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD.equals(ems)){
				model.addAttribute("errMsg_tradePwd", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD2.equals(ems)){
				model.addAttribute("errMsg_tradePwd2", e.getMessage());
			}else
			if(BisConst.Register.ORGNM.equals(ems)){
				model.addAttribute("errMsg_organization", e.getMessage());
			}else
			if(BisConst.Register.ORGBUSINESS.equals(ems)){
				model.addAttribute("errMsg_business", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("BankCardVo", bankCardVo);
			return "bankcard/addBankCard1";
		}
		return "bankcard/addBankCard2";
	}
	
	/**
	 * 绑卡-银行卡绑定(验证) + 开户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCard3" , method=RequestMethod.POST)
	public String addBankCard3(BankCardVo bankCardVo, Model model){
		
		bankCardVo.setBankidtp("0"); // 身份证绑卡
		
		try{
			/** 判断开户流程是否已结束 **/
			if("Y".equals(UserHelper.getAddBankCardStatus())){
				// 开户流程已结束，跳转主页
				ServletHolder.forward("/cust/session.htm");
				return "cust/indexPage";
			}
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			OpenAccountAction openAccountAction = new OpenAccountAction();
			/** 开户所属基金单位 **/
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			/** 开户标志 **/
			if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
				openAccountAction.setOpenaccoflag(true);
			}else{
				openAccountAction.setOpenaccoflag(false);
			}
			// 机构
			openAccountAction.setOrgbusiness(s_custinfo.getOrgbusiness());
			openAccountAction.setOrgnm(s_custinfo.getOrgnm());
			// 个人
			openAccountAction.setCustno(s_custinfo.getCustno());
			openAccountAction.setInvnm(bankCardVo.getBankacnm());
			openAccountAction.setIdno(bankCardVo.getBankidno());
			/** 家庭、经办人判断 **/
			String level = "0".equals(s_custinfo.getInvtp())?"0":"2";
			openAccountAction.setLevel(level);
			openAccountAction.setTradepwd(bankCardVo.getTradepwd());
			openAccountAction.setTradepwd2(bankCardVo.getTradepwd2());
			// 银行
			openAccountAction.setBankno(bankCardVo.getBankno());
			openAccountAction.setBankacnm(bankCardVo.getBankacnm());
			openAccountAction.setBankacco(bankCardVo.getBankacco());
			openAccountAction.setBankidtp(bankCardVo.getBankidtp());
			openAccountAction.setBankidno(bankCardVo.getBankidno());
			openAccountAction.setBankmobile(bankCardVo.getBankmobile());
			openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
			openAccountAction.setOtherserial(bankCardVo.getOtherserial());
			/** 需要验证手机验证码标志 **/
			openAccountAction.setCheckautocodeflag(true);
			
			/** 银行手机验证，并带回Serialno、Protocolno的值 **/
			bankCardManager.openAccount3(openAccountAction);
			
			/** 开户 **/
			bankCardManager.openAccountPerson(openAccountAction);
			
			UserHelper.setAddBankCardStatus("Y");
			s_custinfo.setInvnm(bankCardVo.getBankacnm());
			s_custinfo.setIdno(bankCardVo.getBankidno());
			UserHelper.saveCustinfoVo(s_custinfo);
			
			model.addAttribute("BankCardVo", bankCardVo);
		}catch (BizException e){
			
			// 获取银行列表
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			if(StringUtils.isBlank(bankCardVo.getBankno())){
				// 默认第一个
				bankCardVo.setBankno(bankBaseList.get(0).getBankno());
			}else{
				// 上下文中的
			}
			model.addAttribute("bankList", bankBaseList);
			
			//验证码
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			
			if(BisConst.Register.BANKACNM.equals(ems)){
				model.addAttribute("errMsg_bankAcnm", e.getMessage());
			}else 
			if(BisConst.Register.BANKNO.equals(ems)){
				model.addAttribute("errMsg_bankNo", e.getMessage());
			}else
			if(BisConst.Register.BANKIDNO.equals(ems) 
				|| BisConst.Register.IDNO.equals(ems)){
				model.addAttribute("errMsg_bankIdno", e.getMessage());
			}else
			if(BisConst.Register.BANKACCO.equals(ems)){
				model.addAttribute("errMsg_bankAcco", e.getMessage());
			}else
			if(BisConst.Register.MOBILE.equals(ems)
				|| BisConst.Register.BANKMOBILE.equals(ems)){
				model.addAttribute("errMsg_bankMobile", e.getMessage());
			}else
			if(BisConst.Register.BANKMOBILEMSGCODE.equals(ems) 
				|| "对方序列号".equals(ems)){
				if("对方序列号".equals(ems)){
					model.addAttribute("errMsg_msgcode", "手机验证码无效！");
				}else{
					model.addAttribute("errMsg_msgcode", e.getMessage());
				}
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			
			model.addAttribute("BankCardVo", bankCardVo);
			return "bankcard/addBankCard2";
		}

		return "bankcard/addBankCard3";
	}
}
