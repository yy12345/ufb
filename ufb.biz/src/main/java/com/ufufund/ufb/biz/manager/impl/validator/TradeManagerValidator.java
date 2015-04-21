package com.ufufund.ufb.biz.manager.impl.validator;


import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;

@Service
public class TradeManagerValidator {

	private void paramCheck4Apply(ApplyVo vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new BizException("参数[custno]不能为空！");
		}
		if(StringUtils.isBlank(vo.getTradeacco())){
			throw new BizException("参数[tradeacco]不能为空！");
		}
		if(StringUtils.isBlank(vo.getFundcode())){
			throw new BizException("参数[fundcode]不能为空！");
		}
		if(vo.getAppamt() == null || vo.getAppamt().compareTo(new BigDecimal("0")) <= 0){
			throw new BizException("参数[appamt]不能为空！");
		}
		if(vo.getAppvol() == null || vo.getAppvol().compareTo(new BigDecimal("0")) <= 0){
			throw new BizException("参数[appvol]不能为空！");
		}
		if(vo.getFee() == null || vo.getFee().compareTo(new BigDecimal("0")) <= 0){
			throw new BizException("参数[fee]不能为空！");
		}
	}
	
	public void validateSubApply(ApplyVo vo){
		
		// 输入参数检查
		paramCheck4Apply(vo);
		// 业务规则检查
		// code...
	}
	
	public void validateBuyApply(ApplyVo vo){
		
		// 输入参数检查
		paramCheck4Apply(vo);
		// 业务规则检查
		// code...
	}
	
	private void paramCheck4Redeem(RedeemVo vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new BizException("参数[custno]不能为空！");
		}
		if(StringUtils.isBlank(vo.getTradeacco())){
			throw new BizException("参数[tradeacco]不能为空！");
		}
		if(StringUtils.isBlank(vo.getFundcode())){
			throw new BizException("参数[fundcode]不能为空！");
		}
		if(vo.getAppamt() == null || vo.getAppamt().compareTo(new BigDecimal("0")) <= 0){
			throw new BizException("参数[appamt]不能为空！");
		}
		if(vo.getAppvol() == null || vo.getAppvol().compareTo(new BigDecimal("0")) <= 0){
			throw new BizException("参数[appvol]不能为空！");
		}
		if(vo.getFee() == null || vo.getFee().compareTo(new BigDecimal("0")) <= 0){
			throw new BizException("参数[fee]不能为空！");
		}
	}
	
	public void validateRedeem(RedeemVo vo){
		
		// 输入参数检查
		paramCheck4Redeem(vo);
		// 业务规则检查
		// code...
	}
	
	public void validateRealRedeem(RedeemVo vo){
		
		// 输入参数检查
		paramCheck4Redeem(vo);
		// 业务规则检查
		// code...
	}
}
