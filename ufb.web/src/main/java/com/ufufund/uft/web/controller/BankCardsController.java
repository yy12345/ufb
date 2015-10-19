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
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.model.db.PicInfo;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
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
	public String addBankCard(BankCardVo bankCardVo,String bankacco, String serialid,Model model){
		
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
					if("2".equals(bankinfo.getLevel())){
						qtBankList.add(bankinfo);
					}
					else if("1".equals(bankinfo.getLevel())){
						yftBankList.add(bankinfo);
					}
				}
				String banknbinno="";
				 if(null!=bankacco&&!"".equals(bankacco)){//重新绑定的交易账号
					 bankCardVo.setBankacco(bankacco);
					BankCardbin banknbin=bankBaseManager.getBankCardbin(bankacco.substring(0, 6));
					if(null!=banknbin){
						banknbinno=banknbin.getBankno();
					}
				 }
				 if(""!=banknbinno&&null!=banknbinno){
					 bankCardVo.setBankno(banknbinno);
				 }else if(StringUtils.isBlank(bankCardVo.getBankno())){
						 // 默认第一个
						 bankCardVo.setBankno(yftBankList.get(0).getBankno());
					 } 
				 
				model.addAttribute("bankList", yftBankList);
				model.addAttribute("qtBankList", qtBankList);
				//获得用户是否有幼富通卡
				List<String> tradeaccosts = new ArrayList<String>();
				tradeaccosts.add("Y"); // 
				tradeaccosts.add("N"); // 
				
				List<String> levels = new ArrayList<String>();
				levels.add("0"); 
				List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(
						custinfoVo.getCustno(),
						null,//Constant.HftSysConfig.HftFundCorpno, 
						levels,
						tradeaccosts);
				String isufbCard="N";
				if(null!=hft_family_trade&& hft_family_trade.size() > 0){
					for(TradeAccoinfoOfMore tradeacco:hft_family_trade){
						if(tradeacco.getFundcorpno().equals("01")){
							isufbCard="Y";
							break;
						}
					}
				}
				else{
					isufbCard="Y";
				}
				 model.addAttribute("hasTadeacco", isufbCard);
				 if(""!=serialid&&null!=serialid){
					 
					 model.addAttribute("serialid", serialid);
				 }
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
	public String addBankCard_result(BankCardVo bankCardVo, String serialid,Model model){
		
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		bankCardVo.setBankidtp("0"); // 身份证绑卡
		try{		
			
			          if(""!=serialid&&null!=serialid){
						bankCardManager.unbindBankCard(
								s_custinfo.getCustno(), 
								ServletHolder.getRequest().getParameter("serialid"), 
								"Y");
		             }else{
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
					openAccountAction.setBankcitynm(StringUtils.isNotBlank(bankCardVo.getBankcitynm())?bankCardVo.getBankcitynm():null);
					openAccountAction.setBankprovincenm(StringUtils.isNotBlank(bankCardVo.getBankprovincenm())?bankCardVo.getBankprovincenm():null);
					openAccountAction.setBankadd(StringUtils.isNotBlank(bankCardVo.getBankadd())?bankCardVo.getBankadd():null);
					openAccountAction.setOtherserial(bankCardVo.getOtherserial());
					//String banklevel=bankCardManager.getLevelByBankno(bankCardVo.getBankno());//add
					 
						/** 需要验证手机验证码标志 **/
						openAccountAction.setCheckautocodeflag(true);
						
						bankCardManager.openAccount3(openAccountAction);
						//其他的银行卡的银联验证
						String banklevel=bankCardManager.getLevelByBankno(openAccountAction.getBankno());
						if("2".equals(banklevel)){
							bankCardManager.checkYinLian(openAccountAction);  	
						}
						/** 开户 **/
						bankCardManager.openAccountPerson(openAccountAction);
						
		             } 
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
