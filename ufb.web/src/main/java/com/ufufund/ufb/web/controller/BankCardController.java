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
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Merchant;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
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
				bankCardVo.setCustNo(s_custinfo.getCustno());
				bankCardVo.setBankMobile(s_custinfo.getMobileno());
				bankCardVo.setInvtp(s_custinfo.getInvtp());
				bankCardVo.setLevel(s_custinfo.getLevel());
				bankCardVo.setOrganization(s_custinfo.getOrganization());
				bankCardVo.setBusiness(s_custinfo.getBusiness());
			}
			model.addAttribute("BankCardVo", bankCardVo);
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
	@RequestMapping(value="bankcard/addBankCardInit" , method=RequestMethod.POST)
	public String addBankCardInit(BankCardVo bankCardVo, Model model){
		
		try{
			OpenAccountAction openAccountAction = new OpenAccountAction();
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
			
		}catch (BizException e){
			// TODO
			LOG.error(e.getErrmsg(), e);
			
			if("用户证件号".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankIdno", e.getMessage());
			}else
			if("用户姓名".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankAcnm", e.getMessage());
			}else
			if("证件号码".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankIdno", e.getMessage());
			}else
			if("交易密码".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_tradePwd", e.getMessage());
			}else
			if("确认密码".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_tradePwd2", e.getMessage());
			}else
			if("身份证".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankIdno", e.getMessage());
			}else
			if("开户机构".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_organization", e.getMessage());
			}else
			if("营业执照".equals(e.getOtherInfo())){
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
		try{
			// 防止开户后重复刷新
			Custinfo custinfo = custManager.getCustinfo(bankCardVo.getCustNo());
			if(Constant.OPENACCOUNT$Y.equals(custinfo.getOpenaccount())){
				// 已开户
				return "bankcard/addBankCardSuccessPage";
			}
			
			OpenAccountAction openAccountAction = new OpenAccountAction();
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
			openAccountAction.setMerchant(Merchant.HFT_FUND); // 海富通
			bankCardManager.openAccount4(openAccountAction);
			
			model.addAttribute("BankCardVo", bankCardVo);
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
			
			if("银行开户户名".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankAcnm", e.getMessage());
			}else
			if("银行证件号码".equals(e.getOtherInfo())||"用户证件号".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankIdno", e.getMessage());
			}else
			if("银行卡号".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankAcco", e.getMessage());
			}else
			if("银行开户手机号".equals(e.getOtherInfo()) || "手机号".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_bankMobile", e.getMessage());
			}else
			if("验证码".equals(e.getOtherInfo()) || "对方序列号".equals(e.getOtherInfo())){
				if("对方序列号".equals(e.getOtherInfo())){
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
