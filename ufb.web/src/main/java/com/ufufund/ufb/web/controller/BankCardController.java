package com.ufufund.ufb.web.controller;

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
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
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
	 * 绑卡-Page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCard")
	public String addBankCard(BankCardVo bankCardVo, Model model){
		
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			
			if(null != s_custinfo){
				List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(s_custinfo.getCustno());
				if(null != tradeAccoList && tradeAccoList.size() > 0){
				//if("Y".equals(s_custinfo.getOpenaccount())){
					//bankCardVo.setTradePwd("YYY***");
					//bankCardVo.setTradePwd2("YYY***");
					//直接跳转
					UserHelper.setAddBankCardStatus("N");
					ServletHolder.forward("/bankcard/addBankCardInit.htm");
					return "bankcard/addBankCardAuthPage";
				}
				bankCardVo.setCustNo(s_custinfo.getCustno());
				bankCardVo.setBankMobile(s_custinfo.getMobileno());
				bankCardVo.setInvtp(s_custinfo.getInvtp());
				bankCardVo.setLevel(s_custinfo.getLevel());
				bankCardVo.setOpenaccount(s_custinfo.getOpenaccount());
				bankCardVo.setOrganization(s_custinfo.getOrganization());
				bankCardVo.setBusiness(s_custinfo.getBusiness());
				if(null == bankCardVo.getBankAcnm() || bankCardVo.getBankAcnm().trim().length() == 0){
					bankCardVo.setBankAcnm(s_custinfo.getInvnm());
				}
				if(null == bankCardVo.getBankIdno() || bankCardVo.getBankIdno().trim().length() == 0){
					bankCardVo.setBankIdno(s_custinfo.getIdno());
				}
				
			}
			UserHelper.setAddBankCardStatus("N");
			model.addAttribute("BankCardVo", bankCardVo);
			model.addAttribute("CustinfoVo", s_custinfo);
		}catch (BizException e){
			// TODO 
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "cust/indexPage";
		}
		return "bankcard/addBankCardPage";
	}
	
	/**
	 * 绑卡-验证身份
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCardInit")
	public String addBankCardInit(BankCardVo bankCardVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		
		try{
			String openaccount = null;
			List<TradeAccoinfoOfMore> tradeAccoList = null;
			if(null != s_custinfo){
				openaccount = s_custinfo.getOpenaccount();
				tradeAccoList = tradeAccoManager.getTradeAccoList(s_custinfo.getCustno());
			}
			OpenAccountAction openAccountAction = new OpenAccountAction();
			if(null != tradeAccoList && tradeAccoList.size() > 0){
			//if("Y".equals(openaccount)){
				bankCardVo.setOpenaccount(openaccount);
				bankCardVo.setOrganization(s_custinfo.getOrganization());
				bankCardVo.setInvtp(s_custinfo.getInvtp());
				bankCardVo.setLevel(s_custinfo.getLevel());
				bankCardVo.setBusiness(s_custinfo.getBusiness());
				bankCardVo.setCustNo(s_custinfo.getCustno());
				bankCardVo.setBankAcnm(s_custinfo.getInvnm());
				bankCardVo.setBankIdtp(s_custinfo.getIdtp());
				bankCardVo.setBankIdno(s_custinfo.getIdno());
				bankCardVo.setTradePwd(s_custinfo.getTradepwd());
				bankCardVo.setTradePwd2(s_custinfo.getTradepwd2());
			}else{
			}
			
			openAccountAction.setOpenaccount(openaccount);
			openAccountAction.setOrganization(bankCardVo.getOrganization());
			openAccountAction.setInvtp(bankCardVo.getInvtp());
			openAccountAction.setLevel(bankCardVo.getLevel());
			openAccountAction.setBusiness(bankCardVo.getBusiness());
			openAccountAction.setCustno(bankCardVo.getCustNo());
			openAccountAction.setInvnm(bankCardVo.getBankAcnm());
			openAccountAction.setBankidtp(bankCardVo.getBankIdtp());
			openAccountAction.setBankidno(bankCardVo.getBankIdno());
			openAccountAction.setIdno(bankCardVo.getBankIdno());
			openAccountAction.setTradepwd(bankCardVo.getTradePwd());
			openAccountAction.setTradepwd2(bankCardVo.getTradePwd2());
			
			bankCardManager.openAccount1(openAccountAction);
			
			// 获取银行列表
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			if(StringUtils.isBlank(bankCardVo.getBankNo())){
				// 默认第一个
				bankCardVo.setBankNo(bankBaseList.get(0).getBankno());
			}else{
				// 上下文中的
			}
			model.addAttribute("bankList", bankBaseList);
			model.addAttribute("BankCardVo", bankCardVo);
			model.addAttribute("CustinfoVo", s_custinfo);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.BANKIDNO.equals(ems) 
				|| BisConst.Register.IDNO.equals(ems) 
				|| BisConst.Register.IDCARDNO.equals(ems)){
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
			if(BisConst.Register.ORGANIZATION.equals(ems)){
				model.addAttribute("errMsg_organization", e.getMessage());
			}else
			if(BisConst.Register.BUSINESS.equals(ems)){
				model.addAttribute("errMsg_business", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("BankCardVo", bankCardVo);
			return "bankcard/addBankCardPage";
		}
		return "bankcard/addBankCardAuthPage";
	}
	
	/**
	 * 绑卡-银行卡绑定(验证) + 开户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCardChk" , method=RequestMethod.POST)
	public String addBankCardChk(BankCardVo bankCardVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		
		try{
			if("Y".equals(UserHelper.getAddBankCardStatus())){
				// 此开户流程已结束
				ServletHolder.forward("/cust/session.htm");
				return "cust/indexPage";
			}
			
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setOpenaccount(s_custinfo.getOpenaccount());
			openAccountAction.setReqSeq("3"); // 第三步，需要验证手机验证码
			openAccountAction.setBankno(bankCardVo.getBankNo());
			openAccountAction.setBankacnm(bankCardVo.getBankAcnm());
			openAccountAction.setBankacco(bankCardVo.getBankAcco());
			bankCardVo.setBankIdtp("0"); // 身份证绑卡
			openAccountAction.setBankidtp(bankCardVo.getBankIdtp());
			openAccountAction.setBankidno(bankCardVo.getBankIdno());
			openAccountAction.setBankmobile(bankCardVo.getBankMobile());
			openAccountAction.setMobileAutoCode(bankCardVo.getMsgcode());
			openAccountAction.setOtherserial(bankCardVo.getOtherserial());
			
			// 3 银行手机验证，并带回Serialno、Protocolno的值 
			bankCardManager.openAccount3(openAccountAction);
			
			// 4 开户
			openAccountAction.setCustno(bankCardVo.getCustNo());
			openAccountAction.setInvnm(bankCardVo.getBankAcnm());
			openAccountAction.setIdno(bankCardVo.getBankIdno());
			openAccountAction.setTradepwd(bankCardVo.getTradePwd());
			openAccountAction.setTradepwd2(bankCardVo.getTradePwd2());
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);// 海富通
			bankCardManager.openAccount4(openAccountAction);
			
			UserHelper.setAddBankCardStatus("Y");
			s_custinfo.setInvnm(bankCardVo.getBankAcnm());
			s_custinfo.setIdno(bankCardVo.getBankIdno());
			s_custinfo.setOpenaccount("Y");
			
			UserHelper.saveCustinfoVo(s_custinfo);
			
			model.addAttribute("BankCardVo", bankCardVo);
			model.addAttribute("CustinfoVo", s_custinfo);
		}catch (BizException e){
			
			// 获取银行列表
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			if(StringUtils.isBlank(bankCardVo.getBankNo())){
				// 默认第一个
				bankCardVo.setBankNo(bankBaseList.get(0).getBankno());
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
				|| BisConst.Register.IDNO.equals(ems) 
				|| BisConst.Register.IDCARDNO.equals(ems)){
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
			return "bankcard/addBankCardAuthPage";
		}

		return "bankcard/addBankCardSuccessPage";
	}
}
