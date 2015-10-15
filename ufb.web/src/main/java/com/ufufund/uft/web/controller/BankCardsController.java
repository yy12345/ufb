package com.ufufund.uft.web.controller;

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
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class BankCardsController {

	private static final Logger LOG = LoggerFactory.getLogger(BankCardsController.class);
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
	@RequestMapping(value="family/addBankCard")
	public String addBankCard(BankCardVo bankCardVo, Model model){
		
		try{
			 CustinfoVo custinfoVo=UserHelper.getCustinfoVo();
			 String ivnname=custinfoVo.getInvnm();
			 String idno=custinfoVo.getIdno();
			 //用户信息
			 model.addAttribute("ivnname", ivnname);
			 model.addAttribute("idno", idno);
			 //获得所有的银行卡
				List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
				//支持幼富通的银行===20151013
				List<BankBaseInfo> yftBankList= new ArrayList<BankBaseInfo>();
				//其它的银行
				List<BankBaseInfo> qtBankList= new ArrayList<BankBaseInfo>();
				for(BankBaseInfo bankinfo:bankBaseList){
					if("1".equals(bankinfo.getLevel())){
						qtBankList.add(bankinfo);
					}
					else if("2".equals(bankinfo.getLevel())){
						yftBankList.add(bankinfo);
					}
				}
				
				if(StringUtils.isBlank(bankCardVo.getBankno())){
					// 默认第一个
					bankCardVo.setBankno(yftBankList.get(0).getBankno());
				} 
				model.addAttribute("bankList", yftBankList);
				model.addAttribute("qtBankList", qtBankList);
				//===20151013
				model.addAttribute("BankCardVo", bankCardVo);
				
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			ServletHolder.forward("/family/settingCard.htm");
			return "family/ufb/settingCard";
		}
		return "family/ufb/addBankCard";
	}
	@RequestMapping(value="family/addBankCard_result")
	public String addBankCard_result(BankCardVo bankCardVo, Model model){
		
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		bankCardVo.setBankidtp("0"); // 身份证绑卡
		try{		 
					//验证手机验证码
					OpenAccountAction openAccountAction = new OpenAccountAction();
					/** 开户所属基金单位 **/
					openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
					/** 开户标志 **/
					if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
						openAccountAction.setOpenaccoflag(true);
					}else{
						openAccountAction.setOpenaccoflag(false);
					}
					// 个人
					openAccountAction.setCustno(s_custinfo.getCustno());
					openAccountAction.setInvnm(bankCardVo.getBankacnm());
					openAccountAction.setIdno(bankCardVo.getBankidno());
					/** 家庭**/
					openAccountAction.setLevel(Invtp.PERSONAL.getValue());
					openAccountAction.setTradepwd(s_custinfo.getTradepwd());
					openAccountAction.setTradepwd2(s_custinfo.getTradepwd2());
					// 银行
					openAccountAction.setBankno(bankCardVo.getBankno());
					openAccountAction.setBankacnm(bankCardVo.getBankacnm());
					openAccountAction.setBankacco(bankCardVo.getBankacco());
					openAccountAction.setBankidtp(bankCardVo.getBankidtp());
					openAccountAction.setBankidno(bankCardVo.getBankidno());
					openAccountAction.setBankmobile(bankCardVo.getBankmobile());
					openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
					openAccountAction.setOtherserial(bankCardVo.getOtherserial());
					openAccountAction.setBankcitynm(bankCardVo.getBankcitynm());
					openAccountAction.setBankprovincenm(bankCardVo.getBankprovincenm());
					openAccountAction.setBankadd(bankCardVo.getBankadd());
					openAccountAction.setOtherserial(bankCardVo.getOtherserial());
					
					/** 需要验证手机验证码标志 **/
					openAccountAction.setCheckautocodeflag(true);
					
					bankCardManager.openAccount3(openAccountAction);
					/** 开户 **/
					bankCardManager.openAccountPerson(openAccountAction);
				
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			ServletHolder.forward("/family/addBankCard.htm");
			return "family/ufb/addBankCard";
		}
		ServletHolder.forward("/family/settingCard.htm");
		return "family/ufb/settingCard";
	}
	
	 
}
