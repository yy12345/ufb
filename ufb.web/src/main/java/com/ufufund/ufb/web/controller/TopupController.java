package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.BankVo;
import com.ufufund.ufb.model.vo.TopUpComfVo;
import com.ufufund.ufb.model.vo.TopUpVo;
//import com.ufufund.ufb.model.Area;


@Controller
public class TopupController {
	private static final Logger LOG = LoggerFactory.getLogger(TopupController.class);
	
//	@Autowired
	private CustManager custManager;
	
//	@Autowired
	private BankManager bankManager;
	
	/**
	 * 充值首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "topup/index", method = RequestMethod.GET)
	public String getPage(Model model){
		
		TopUpVo topupVO = new TopUpVo();
		try {
			String custNo = "";//UserHelper.getCurrentCustno();
			String fundCorpNo = "";//PartnerHelper.getBranchCode();
			String fundID = "";
			
			// 货基余额
			BigDecimal vcashAmt = new BigDecimal(0);//ctsService.queryVaccoBalance(custNo, fundCorpNo, fundID);
			topupVO.setVcashAmt("");//NumberUtils.DEF_CASH_COMMON.format(vcashAmt)
			topupVO.setVcashAmtDisplay("");//NumberUtils.NF_2FRACTION.format(vcashAmt)
			
			// 下一工作日
//			topupVO.setNextWorkDay(
//					DateUtil.format(
//							"",//ctsService.queryNextWorkDay(), 
//							DateUtil.DATE_PATTERN_1, 
//							DateUtil.DATE_PATTERN_2));
			
			// 基金配置（是否可认购，及最小、最大购买份额）
//			TopupConfig topupConfig = fundService.queryTopupConfig(custNo);
//			topupVO.setCanBid(topupConfig.isCanBid());
//			topupVO.setMaxBid(NumberUtils.DEF_CASH_COMMON.format(topupConfig.getMaxBid()));
//			topupVO.setMinBid(NumberUtils.DEF_CASH_COMMON.format(topupConfig.getMinBid()));
//			topupVO.setDayLimitBid(NumberUtils.DEF_CASH_COMMON.format(topupConfig.getDayLimitBid()));
//			topupVO.setDayUsedBid(NumberUtils.DEF_CASH_COMMON.format(ctsService.queryTopupTotalToday(custNo, PartnerHelper.getBranchCode())));
			
			// 是否需要验证码
//			topupVO.setNeedVerify(topupManager.isCheckVeriCode(custNo));
			
			// 充值银行卡列表
			List<BankCardVo> bankCardVoList = new ArrayList<BankCardVo>();
			List<BankVo> bankList = new ArrayList<BankVo>();//cifService.queryBnkCardList(custNo);
			for(BankVo bank:bankList){
				BankCardVo vo = new BankCardVo();
				vo.setBankSerialId(bank.getBankSerialId());
				vo.setBankNo(bank.getBankNo());
				vo.setBankAcco(bank.getBankAcco());
				vo.setSumaryCardName(
						//cifService.getBankName(bank.getBankNo()) +
						"BankName" + 
						"[尾号" + 
						StringUtils.format(
								bank.getBankAccoDisplay(), 
								"CardDisplay") + 
						"]");
				
				// 银行卡属性 B2C、限额
//				BankCardConfig bankCardConfig = 
//						beService.queryBankCardConfig(
//								custNo,
//								bank.getBankNo(),
//								bank.getBankSerialId());
//				BankCardConfig bankCardConfig = new BankCardConfig();
//				vo.setB2c(bankCardConfig.isB2c());
//				vo.setCardLevel(bankCardConfig.getRecharge());
//				vo.setPerLimit(NumberUtils.DEF_CASH_COMMON.format(bankCardConfig.getPerLimit()));
//				vo.setLimitDesc(bankCardConfig.getLimitDesc());
				bankCardVoList.add(vo);
			}
			topupVO.setBankCardVoList(bankCardVoList);
			
			model.addAttribute("topup", topupVO);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "../test/index.htm");
			return "error/error";
		}
		
		return "topup/topup_index";
	}
	
	/**
	 * 充值下单:b2b
	 * @param previewVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="topup/pay", method = RequestMethod.POST)
	public String b2bAdd(TopUpComfVo topUpComfVo, Model model){
		
		try {
			String custNo = ""; //UserHelper.getCurrentCustno();
			topUpComfVo.setCustNo(custNo);
			topUpComfVo.setAcceptMode(""); //PartnerHelper.getAcceptMode()
			topUpComfVo.setSubApkind(""); //SubApkindUtil.getSubApkind(SubApkindUtil.MAC_APKIND_0101)
			topUpComfVo.setFundCorpNo(""); //(PartnerHelper.getBranchCode());
//			topUpComfVo.setB2c(false);
			
			// 充值流水
			String serial = ""; // Sequences.getPK();
			topUpComfVo.setSerialNo(serial);
//			HttpSession session = ServletContextHolder.getHttpServletRequest().getSession();
//			session.setAttribute(TOPUP_SERIAL, serial);
			
			// 下单
			//VaccoSubscribeMacResult result = topupManager.recharge(previewVO);
			// cts的2011错误码透传
//			if("2011".equals(result.getErrCode())){
//				throw new BizException(result.getErrMsg());
//			}
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "TOPUP_INDEX");
			return "error/error1";
		}
		return ""; //"redirect:" + TOPUP_RESULT + PartnerHelper.getRedirectPartner();
	}
	
	
}
