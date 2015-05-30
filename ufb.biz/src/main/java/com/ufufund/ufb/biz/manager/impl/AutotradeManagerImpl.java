package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.AutotradeManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.AutoTradeManagerValidator;
import com.ufufund.ufb.common.constant.Constant;
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
	private AutotradeMapper autotradeMapper;
	@Autowired
	private AutoTradeManagerValidator autoTradeManagerValidator;
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	
//	
	@Override
	public void addAutotrade(AddAutotradeAction action) throws BizException {
		String processId = this.getProcessId(action);
		autoTradeManagerValidator.validator(action);
		// 序列号
		String seq = autotradeMapper.getAutotradeSequence();
		if(RegexUtil.isNull(action.getAutoname())){
			action.setAutoname(seq);
		}
		// 数据包装
		Autotrade autotrade = AutotradeManagerHelper.toAutotrade(action);
		// 状态
		autotrade.setState(Constant.Autotrade.STATE$N);
		// 序列号
		autotrade.setAutoid(seq);
		/*
		 * 根据业务获取冗余字段
		 */
		autotrade = this.getOtherInfo(action.getTradetype(), autotrade);
		autotrade.setNextdate(this.getNextdate(autotrade.getCycle(), autotrade.getDat()));
		int n = autotradeMapper.insertAutotrade(autotrade);
		if(n!=1){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		/*
		 * 记录交易流水
		 */
		this.insertFdacfinalresult(autotrade,autotrade.getTradetype() + "0");
		
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
		return autotradeMapper.getAutotradeList(autotrade);
	}
	
	@Override
	public Autotrade getAutotrade(String autoid) throws BizException {
		this.getProcessId(autoid);
		return autotradeMapper.getAutotrade(autoid);
	}

	@Override
	public void modifyAutotradeAction(ModifyAutotradeAction action) throws BizException {
		String processId = this.getProcessId(action);
		autoTradeManagerValidator.validatorModify(action);
		Autotrade dbautotrade = new Autotrade();
		dbautotrade.setAutoid(action.getAutoid());
		List<Autotrade> list = autotradeMapper.getAutotradeList(dbautotrade);
		if(list.isEmpty()||list.size()!=1){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		dbautotrade = list.get(0);
		/*
		 * 判断状态  暂停或者交易状态的交易不能修改，请先恢复
		 */
		if(!Constant.Autotrade.STATE$N.equals(dbautotrade.getState())){
			throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
		}
		/*
		 * 新增当前工作日的扣款也可以，该工作不发起当天新增的交易
		 * nextdate = nextdate +1个周期
		 */
		autoTradeManagerValidator.validator(action);
		if(RegexUtil.isNull(action.getAutoname())){
			action.setAutoname(action.getAutoid());
		}
		Autotrade autotrade = AutotradeManagerHelper.toAutotrade(action);
		/*
		 * 根据业务获取冗余字段
		 */
		autotrade = this.getOtherInfo(action.getTradetype(), autotrade);
		String nextdate = this.getNextdate(action.getCycle(), action.getDat());
		autotrade.setNextdate(nextdate);
		int n = autotradeMapper.updateAutotrade(autotrade);
		if(n!=1){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		/*
		 * 记录交易流水
		 */
		this.insertFdacfinalresult(autotrade,autotrade.getTradetype()+"1");
		
	}
	
	private void insertFdacfinalresult(Autotrade autotrade,String apkind) {
		Fdacfinalresult fdacfinalresult = AutotradeManagerHelper.toFdacfinalresult(autotrade);
		fdacfinalresult.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
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
			autotrade.setToaccoid(tradeaccoinfo.getAccoid());
			autotrade.setTotradeacco(tradeaccoinfo.getTradeacco());		
			
			bankcardinfo.setSerialid(autotrade.getFrombankserialid());
			List<Bankcardinfo> list = bankMapper.getBankcardinfo(bankcardinfo);
			String bankacco = null;
			if(null != list && list.size() > 0){
				bankacco = list.get(0).getBankacco();
			}
			autotrade.setFrombankacco(bankacco);
			
		}else if(tradeType.equals(AutoTradeType.AUTOWITHDRAWAL)){
			tradeaccoinfo.setBankserialid(autotrade.getTobankserialid());
			tradeaccoinfo.setFundcorpno(autotrade.getFromfundcorpno());
			tradeaccoinfo = tradeAccoinfoMapper.getTradeaccoinfo(tradeaccoinfo);
			autotrade.setFromaccoid(tradeaccoinfo.getAccoid());
			autotrade.setFromtradeacco(tradeaccoinfo.getTradeacco());				
		}
		return autotrade;
	}

	@Override
	public void changestatus(ChangeAutoStateAction action) throws BizException {
		String processId = this.getProcessId(action);
		autoTradeManagerValidator.validator(action);
		Autotrade dbautotrade = new Autotrade();
		dbautotrade.setAutoid(action.getAutoid());
		List<Autotrade> list = autotradeMapper.getAutotradeList(dbautotrade);
		if(list.isEmpty()||list.size()!=1){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		//state  P-暂停 ,C 终止 删除 ,N 恢复
		String apkind = "";
		dbautotrade = list.get(0);
		if(Constant.Autotrade.STATE$N.equals(action.getState())){
			if(!Constant.Autotrade.STATE$P.equals(action.getState())){
				throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
			}
			apkind = dbautotrade.getTradetype()+"2";
		}else if(Constant.Autotrade.STATE$P.equals(action.getState())){
			if(!Constant.Autotrade.STATE$N.equals(action.getState())){
				throw new BizException(processId, ErrorInfo.AUTO_STATE_ERROR); 
			}
			apkind = dbautotrade.getTradetype()+"3";
		}else{
			apkind = dbautotrade.getTradetype()+"4";
		}
		String workdate = workDayManager.getCurrentWorkDay();
		if(dbautotrade.getNextdate().equals(workdate)){
			throw new BizException(processId, ErrorInfo.AUTO_NEXTDAY_ISWORKDAY); 
		}
		String nextdate = this.getNextdate(dbautotrade.getCycle(), dbautotrade.getDat());
		dbautotrade.setNextdate(nextdate);
		dbautotrade.setState(action.getState());
		int n = autotradeMapper.updateAutotrade(dbautotrade);
		if(n!=1){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		this.insertFdacfinalresult(dbautotrade,apkind);
	}
	
}
