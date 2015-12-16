package com.ufufund.ufb.biz.manager.validator;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.Cancel;
import com.ufufund.ufb.model.db.Redeem;

@Service
public class TradeManagerValidator {
	
	@Autowired
	private UserModuleValidator userModuleValidator;

	/**
	 * 认购校验
	 * @param vo
	 */
	public void validateSubApply(Apply vo){
		// 参数检查
		paramCheck4Apply(vo);
		/** 业务规则校验 **/ 
		// 验证交易密码
		if(!userModuleValidator.checkTradePwd(vo.getCustno(), vo.getTradePwd())){
			throw new UserException("交易密码错误！");
		}
	}
	
	/**
	 * 申购校验
	 * @param vo
	 */
	public void validateBuyApply(Apply vo){
		// 参数检查
		paramCheck4Apply(vo);
		
		/** 业务规则校验 **/ 
		// 验证交易密码
		if("51".equals(vo.getApkind())){
			
		}else{
			if(!userModuleValidator.checkTradePwd(vo.getCustno(), vo.getTradePwd())){
				throw new UserException("交易密码错误！");
			}
		}
	}
	
	/**
	 * 普通赎回校验
	 * @param vo
	 */
	public void validateRedeem(Redeem vo){
		// 参数检查
		paramCheck4Redeem(vo);
		/** 业务规则校验 **/ 
		// 验证交易密码
		if(!userModuleValidator.checkTradePwd(vo.getCustno(), vo.getTradePwd())){
			throw new UserException("交易密码错误！");
		}
	}
	
	/**
	 * 快速赎回校验
	 * @param vo
	 */
	public void validateRealRedeem(Redeem vo){
		// 参数检查
		paramCheck4Redeem(vo);
		/** 业务规则校验 **/ 
		// 验证交易密码
		if("52".equals(vo.getApkind())){
			
		}else{
			if(!userModuleValidator.checkTradePwd(vo.getCustno(), vo.getTradePwd())){
				throw new UserException("交易密码错误！");
			}
		}
	}
	
	/**
	 * 快速赎回校验
	 * @param vo
	 */
	public void validateCancel(Cancel vo){
		// 参数检查
		paramCheck4Cancel(vo);
		/** 业务规则校验 **/ 
		// 验证交易密码
		if(!userModuleValidator.checkTradePwd(vo.getCustno(), vo.getTradePwd())){
			throw new UserException("交易密码错误！");
		}
	}
	
	private void paramCheck4Apply(Apply vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new UserException("参数[custno]不能为空！");
		}
		if(vo.getTradeacco() == null || vo.getTradeacco().length() == 0){
			throw new UserException("参数[tradeacco]不能为空！");
		}
		if(vo.getAppamt() == null || vo.getAppamt().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[appamt]不能为空！");
		}
	}
	
	private void paramCheck4Redeem(Redeem vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new UserException("参数[custno]不能为空！");
		}
		if(vo.getTradeacco() == null || vo.getTradeacco().length() == 0){
			throw new UserException("参数[tradeacco]不能为空！");
		}
		if(vo.getAppvol() == null || vo.getAppvol().compareTo(new BigDecimal("0")) <= 0){
			throw new UserException("参数[appamt]不能为空！");
		}
	}
	
	private void paramCheck4Cancel(Cancel vo){
		if(StringUtils.isBlank(vo.getCustno())){
			throw new UserException("参数[custno]不能为空！");
		}
		if(vo.getTradeacco() == null || vo.getTradeacco().length() == 0){
			throw new UserException("参数[tradeacco]不能为空！");
		}
	}
}
