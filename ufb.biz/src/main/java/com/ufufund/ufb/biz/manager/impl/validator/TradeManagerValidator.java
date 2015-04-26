package com.ufufund.ufb.biz.manager.impl.validator;


import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;

@Service
public class TradeManagerValidator {

	/**
	 * 认购校验
	 * @param vo
	 */
	public void validateSubApply(ApplyVo vo){
		// 参数检查
		paramCheck4Apply(vo);
	}
	
	/**
	 * 申购校验
	 * @param vo
	 */
	public void validateBuyApply(ApplyVo vo){
		// 参数检查
		paramCheck4Apply(vo);
	}
	
	/**
	 * 普通赎回校验
	 * @param vo
	 */
	public void validateRedeem(RedeemVo vo){
		// 参数检查
		paramCheck4Redeem(vo);
	}
	
	/**
	 * 快速赎回校验
	 * @param vo
	 */
	public void validateRealRedeem(RedeemVo vo){
		// 参数检查
		paramCheck4Redeem(vo);
	}
	
	private void paramCheck4Apply(ApplyVo vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new UserException("参数[custno]不能为空！");
		}
		if(StringUtils.isBlank(vo.getTradeacco())){
			throw new UserException("参数[tradeacco]不能为空！");
		}
		if(StringUtils.isBlank(vo.getFundcode())){
			throw new UserException("参数[fundcode]不能为空！");
		}
		if(vo.getAppamt() == null || vo.getAppamt().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[appamt]不能为空！");
		}
		if(vo.getAppvol() == null || vo.getAppvol().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[appvol]不能为空！");
		}
		if(vo.getFee() == null || vo.getFee().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[fee]不能为空！");
		}
	}
	
	private void paramCheck4Redeem(RedeemVo vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new UserException("参数[custno]不能为空！");
		}
		if(StringUtils.isBlank(vo.getTradeacco())){
			throw new UserException("参数[tradeacco]不能为空！");
		}
		if(StringUtils.isBlank(vo.getFundcode())){
			throw new UserException("参数[fundcode]不能为空！");
		}
		if(vo.getAppamt() == null || vo.getAppamt().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[appamt]不能为空！");
		}
		if(vo.getAppvol() == null || vo.getAppvol().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[appvol]不能为空！");
		}
		if(vo.getFee() == null || vo.getFee().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[fee]不能为空！");
		}
	}
}
