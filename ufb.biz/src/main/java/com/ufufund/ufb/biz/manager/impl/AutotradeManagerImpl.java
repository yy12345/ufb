package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.SequenceManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.AutotradeManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.AutoTradeManagerValidator;
import com.ufufund.ufb.biz.manager.impl.validator.UserModuleValidator;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.AutotradeMapper;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.vo.Today;


@Service
public class AutotradeManagerImpl extends ImplCommon implements AutotradeManager {

	
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private AutoTradeManagerValidator autoTradeManagerValidator;
	@Autowired
	private UserModuleValidator userModuleValidator;
	@Autowired
	private AutotradeMapper autotradeMapper;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	@Autowired
	private SequenceManager sequenceManager;
	
	
	@Override
	public void addAutotrade(AddAutotradeAction action) throws BizException {
		this.getProcessId(action);
		
		/** 业务规则校验 **/ 
		autoTradeManagerValidator.validator(action);
		
		/** 验证交易密码 **/
		if(!userModuleValidator.checkTradePwd(action.getCustno(), action.getTradepwd())){
			throw new UserException("交易密码错误！");
		}
		
		/** 序列号 **/
		String seq = sequenceManager.getAutotradeSequence();
		if(RegexUtil.isNull(action.getAutoname())){
			action.setAutoname(seq);
		}
		
		/** 数据包装 **/
		Autotrade autotrade = AutotradeManagerHelper.toAutotrade(action);
		autotrade.setState(Constant.Autotrade.STATE$N); // 状态
		autotrade.setAutoid(seq); // 序列号
		autotrade.setNextdate(this.getNextdate(autotrade.getCycle(), autotrade.getDat())); // 下一扣款日
		autotrade = this.getOtherInfo(action.getTradetype(), autotrade); // 根据业务获取冗余字段（交易账号、银行卡号）
		
		/** 插入 **/
		int n = autotradeMapper.insertAutotrade(autotrade);
		if(n!=1){
			if(autotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL.value())){
				
				throw new UserException("您可通过自动取现计划列表确认！");
			}else if(autotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE.value())){
				
				throw new UserException("您可通过自动充值计划列表确认！");
			}
			//throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		
		/** 记录交易流水 **/
		this.insertFdacfinalresult(autotrade,autotrade.getTradetype() + "0");
		
	}
	
	@Override
	public void modifyAutotrade(ModifyAutotradeAction action) throws BizException {
		this.getProcessId(action);
		
		/** 业务验证 **/
		autoTradeManagerValidator.validatorModify(action);
		autoTradeManagerValidator.validator(action);
		
		/** 验证交易密码 **/
		if(!userModuleValidator.checkTradePwd(action.getCustno(), action.getTradepwd())){
			throw new UserException("交易密码错误！");
		}
		
		/** 验证暂停/终止/交易状态、扣款日当日不能修改 **/
		Autotrade dbautotrade = new Autotrade();
		dbautotrade.setAutoid(action.getAutoid());
		List<Autotrade> list = autotradeMapper.getAutotradeList(dbautotrade);
		if(list.isEmpty()||list.size()!=1){
			if(action.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
				
				throw new UserException("您可通过自动充值计划列表确认！");
			}
			else if(action.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
				throw new UserException("您可通过自动取现计划列表确认！");
				
			}
			//throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		dbautotrade = list.get(0);
		// 暂停/终止/交易状态的交易不能修改，请先恢复
		if(!Constant.Autotrade.STATE$N.equals(dbautotrade.getState())){
            if(action.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
				
            	throw new UserException("（暂停/终止）状态的自动充值计划不能修改！");
			}
			else if(action.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
				throw new UserException("（暂停/终止）状态的自动取现计划不能修改！");
				
			}
		
			//throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
		}
		// 扣款日当日不能修改
		String workdate = workDayManager.getCurrentWorkDay();
		if(dbautotrade.getNextdate().equals(workdate)){
			   if(action.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
				   throw new UserException("当前工作日的自动充值计划不能修改！");
				}
				else if(action.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
					throw new UserException("当前工作日的自动取现计划不能修改！");
				}
			
			//throw new BizException(processId, ErrorInfo.AUTO_NEXTDAY_ISWORKDAY); 
		}
		
		/*
		 * 新增当前工作日的扣款也可以，该工作不发起当天新增的交易
		 * nextdate = nextdate +1个周期
		 */
		
		/** 数据包装 **/
		Autotrade autotrade = AutotradeManagerHelper.toAutotrade(action);
		autotrade.setAutoid(action.getAutoid());
		autotrade = this.getOtherInfo(action.getTradetype(), autotrade); // 根据业务获取冗余字段（交易账号、银行卡号）
		autotrade.setNextdate(this.getNextdate(action.getCycle(), action.getDat())); // 下一扣款日
		
		/** 更新 **/
		int n = autotradeMapper.updateAutotrade(autotrade);
		if(n!=1){
			   if(action.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
					throw new UserException("您可通过自动充值计划列表确认！");
				}
				else if(action.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
					throw new UserException("您可通过自动取现计划列表确认！");
				}
			
		
			//throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		
		/** 记录交易流水 **/
		this.insertFdacfinalresult(autotrade,autotrade.getTradetype()+"1");
		
	}
	
	@Override
	public void changestatus(ChangeAutoStateAction action) throws BizException {
		this.getProcessId(action);
		
		/** 业务验证 **/
		autoTradeManagerValidator.validator(action);
		
		/** 验证交易密码 **/
		if(!userModuleValidator.checkTradePwd(action.getCustno(), action.getTradepwd())){
			throw new UserException("交易密码错误！");
		}
		
		/** 验证暂停/终止/交易状态、扣款日当日不能修改 **/
		Autotrade dbautotrade = new Autotrade();
		dbautotrade.setAutoid(action.getAutoid());
		List<Autotrade> list = autotradeMapper.getAutotradeList(dbautotrade);
		if(list.isEmpty()||list.size()!=1){
			throw new UserException("您可通过自动充值计划列表确认！");
			//throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		//state  P-暂停 ,C 终止 删除 ,N 恢复
		String apkind = "";
		dbautotrade = list.get(0);
		if(Constant.Autotrade.STATE$N.equals(dbautotrade.getState())){
			// N状态-〉P暂停、C终止
			if(!Constant.Autotrade.STATE$P.equals(action.getState())
					&& !Constant.Autotrade.STATE$C.equals(action.getState()) ){
				//throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
				 if(dbautotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
					 throw new UserException("您的自动充值计划修改失败，非正常业务范围！");
					}
					else if(dbautotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
						throw new UserException("您的自动取现计划修改失败，非正常业务范围！");
					}
				
			}
			apkind = dbautotrade.getTradetype()+"2";
		}else if(Constant.Autotrade.STATE$P.equals(dbautotrade.getState())){
			// P暂停-> N状态、C终止
			if(!Constant.Autotrade.STATE$N.equals(action.getState())
					&& !Constant.Autotrade.STATE$C.equals(action.getState())){
				//throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
				if(dbautotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
					throw new UserException("您的自动充值计划修改失败，非正常业务范围！");
					}
					else if(dbautotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
						throw new UserException("您的自动取现计划修改失败，非正常业务范围！");
					}
				
			}
			apkind = dbautotrade.getTradetype()+"3";
		}else if(Constant.Autotrade.STATE$C.equals(dbautotrade.getState())){
			// C终止-〉
			if(dbautotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
				throw new UserException("您的自动充值计划修改失败，非正常业务范围！");
				}
				else if(dbautotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
					throw new UserException("您的自动取现计划修改失败，非正常业务范围！");
				}
			//throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
		}else{
			apkind = dbautotrade.getTradetype()+"4";
		}
		// 扣款日当日不能修改
		String workdate = workDayManager.getCurrentWorkDay();
		if(dbautotrade.getNextdate().equals(workdate)){
			//throw new BizException(processId, ErrorInfo.AUTO_NEXTDAY_ISWORKDAY); 
			if(dbautotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
				throw new UserException("当前工作日的自动充值计划不能修改！");
				}
				else if(dbautotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
					throw new UserException("当前工作日的自动取现计划不能修改！");
				}
			
		}
		
		/** 数据包装 **/
		String nextdate = this.getNextdate(dbautotrade.getCycle(), dbautotrade.getDat());// 下一扣款日
		dbautotrade.setNextdate(nextdate);
		dbautotrade.setState(action.getState());
		int n = autotradeMapper.updateAutotrade(dbautotrade);
		if(n!=1){
			if(dbautotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE)){
				throw new UserException("您可通过自动充值计划列表确认！");
				}
				else if(dbautotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)){
					throw new UserException("您可通过自动取现计划列表确认！");
				}
			
			
			//throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		this.insertFdacfinalresult(dbautotrade,apkind);
	}
	
	@Override
	public String getNextdate(String cycle, String dat) throws BizException {
		String processId = this.getProcessId("");
		if(dat==null || "".equals(dat)){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR); 
		}
		String systime = workDayManager.getSysTime();
		String nextdate = "";
		if(Constant.Autotrade.CYCLE$MM.equals(cycle)){
			 nextdate = DateUtil.getDateByMM(systime, dat);		 
		}else if(Constant.Autotrade.CYCLE$WW.equals(cycle)){
			 nextdate = DateUtil.getDateByWW(systime, dat);		 
		}else if(Constant.Autotrade.CYCLE$DD.equals(cycle)){
			nextdate = DateUtil.getDateByDD(systime, dat);		
		}else{
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR); 
		}
		if("".equals(nextdate)){
			return nextdate;
		}
		if(workDayManager.isWorkDay(nextdate)){
			 return nextdate;
		}else{
			 /* 当前日期的 下个工作日  */
			 return workDayManager.getNextWorkDay(nextdate, 1);
		}
	}
	
	@Override
	public List<Autotrade> getAutotradeList(String custno) throws BizException {
		this.getProcessId(custno);
		Autotrade autotrade = new Autotrade();
		autotrade.setCustno(custno);
		autotrade.setTradetype(AutoTradeType.AUTORECHARGE.value());
		return autotradeMapper.getAutotradeList(autotrade);
	}
	@Override
	public List<Autotrade> getAutotradeCList(String custno) throws BizException {
		this.getProcessId(custno);
		Autotrade autotrade = new Autotrade();
		autotrade.setCustno(custno);
		autotrade.setTradetype(AutoTradeType.AUTORECHARGE.value());
		return autotradeMapper.getAutotradeCList(autotrade);
	}
	@Override
	public Autotrade getAutotrade(String autoid) throws BizException {
		this.getProcessId(autoid);
		return autotradeMapper.getAutotrade(autoid);
	}

	private void insertFdacfinalresult(Autotrade autotrade,String apkind) {
		Fdacfinalresult fdacfinalresult = AutotradeManagerHelper.toFdacfinalresult(autotrade);
		fdacfinalresult.setSerialno(sequenceManager.getFdacfinalresultSeq());
		fdacfinalresult.setApkind(apkind);
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
	}
	
	private Autotrade getOtherInfo(AutoTradeType tradeType,Autotrade autotrade) {
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		tradeaccoinfo.setCustno(autotrade.getCustno());
		if(tradeType.equals(AutoTradeType.AUTORECHARGE)){
			tradeaccoinfo.setBankserialid(autotrade.getFrombankserialid());
			tradeaccoinfo.setFundcorpno(autotrade.getTofundcorpno());
			tradeaccoinfo = tradeAccoinfoMapper.getTradeaccoinfo(tradeaccoinfo);
			// 交易账号
			autotrade.setToaccoid(tradeaccoinfo.getAccoid());
			autotrade.setTotradeacco(tradeaccoinfo.getTradeacco());		
			
			bankcardinfo.setSerialid(autotrade.getFrombankserialid());
			List<Bankcardinfo> list = bankMapper.getBankcardinfo(bankcardinfo);
			String bankacco = null;
			if(null != list && list.size() > 0){
				bankacco = list.get(0).getBankacco();
			}
			// 银行卡号
			autotrade.setFrombankacco(bankacco);
			
		}else if(tradeType.equals(AutoTradeType.AUTOWITHDRAWAL)){
			tradeaccoinfo.setBankserialid(autotrade.getTobankserialid());
			tradeaccoinfo.setFundcorpno(autotrade.getFromfundcorpno());
			tradeaccoinfo = tradeAccoinfoMapper.getTradeaccoinfo(tradeaccoinfo);
			autotrade.setFromaccoid(tradeaccoinfo.getAccoid());
			autotrade.setFromtradeacco(tradeaccoinfo.getTradeacco());
			//
			bankcardinfo.setSerialid(autotrade.getTobankserialid());
			List<Bankcardinfo> list = bankMapper.getBankcardinfo(bankcardinfo);
			String bankacco = null;
			if(null != list && list.size() > 0){
				bankacco = list.get(0).getBankacco();
			}
			// 银行卡号
			autotrade.setTobankacco(bankacco);
		}
		return autotrade;
	}
/**
 * 获得取现的数据
 */
	@Override
	public List<Autotrade> getCashtradeList(String custno) throws BizException {
		this.getProcessId(custno);
		Autotrade autotrade = new Autotrade();
		autotrade.setCustno(custno);
		autotrade.setTradetype(AutoTradeType.AUTOWITHDRAWAL.value());
		return autotradeMapper.getAutotradeList(autotrade);
	}
/**
 * 删除自动充值业务
 */
@Override
public void deleteAutotrade(String custno, String frombankserialid, String autoid) throws BizException {
	autotradeMapper.deleteAutotrade(custno, frombankserialid, autoid);
}
	
}
