package com.ufufund.ufb.biz.manager.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.AutotradeManagerHelper;
import com.ufufund.ufb.biz.manager.op.AutotradeManagerOP;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.SysoutCommon;
import com.ufufund.ufb.dao.AutotradeMapper;
import com.ufufund.ufb.dao.JobcontralMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Jobcontral;
import com.ufufund.ufb.model.db.Redeem;
import com.ufufund.ufb.model.db.Today;
import com.ufufund.ufb.model.enums.AutoTradeType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AutotradeManagerOPImpl implements AutotradeManagerOP {

	@Autowired
	private AutotradeMapper autotradeMapper;

	@Autowired
	private TradeNotesMapper tradeNotesMapper;

	@Autowired
	private WorkDayManager workDayManager;

	@Autowired
	private TradeManager tradeManager;

	@Autowired
	private AutotradeManager autotradeManager;

	@Autowired
	private JobcontralMapper jobcontralMapper;
	
	@Override
	public void startAutotrade(AutoTradeType tradetype, String workDate){
		/*
		 * 判断任务状态 如果状态为处理中或者已完成 直接退出
		 */
		log.debug(" 自动任务 " + tradetype.toString() +" 开始执行 " + workDate);
		Jobcontral newjobcontral = new Jobcontral();
		newjobcontral.setWorkdate(workDate);
		newjobcontral.setJobname(tradetype.toString());
		Jobcontral jobcontral = jobcontralMapper.getJobcontral(newjobcontral);
		int n = 0;
		if (jobcontral != null) {
			n = 1;
			log.debug(" 任务状态记录  : "  + jobcontral.toString());
			if (jobcontral.getJobstatus().equals(Constant.Jobcontral.STATUS$I)) {
				throw new UserException("系统异常！");
			} else if (jobcontral.getJobstatus().equals(Constant.Jobcontral.STATUS$X)) {
				throw new  UserException("系统异常！");
			} 
		}else{
			jobcontral = newjobcontral;
		}
		
		jobcontral.setJobstatus(Constant.Jobcontral.STATUS$I);
		
		if (jobcontral.getStarttime() == null || "".equals(jobcontral.getStarttime())) {
			jobcontral.setStarttime(workDayManager.getSysTime());
		}
		
		if(n==0){
			n = jobcontralMapper.insertJobcontral(jobcontral);	
		}else{
			n = jobcontralMapper.updateJobcontral(jobcontral);
		}
		SysoutCommon.JOBSTATUS_MAP.put(tradetype.toString(), Constant.Jobcontral.STATUS$N);
		/*
		 * 轮训开始执行任务
		 */
		Autotrade autotrade = new Autotrade();
		autotrade.setTradetype(tradetype.value());
		autotrade.setNextdate(workDate);
		List<Autotrade> list = autotradeMapper.getAutotradeList(autotrade);
		log.debug(" 轮训 可执行任务记录  : "  + list.size());
		/*
		 * 任务发起，可重构，太多次调用连接池
		 */
		String nextdate = "";
		int count = 0;
		for (Autotrade listAutotrade : list) {
			/*
			 * 获取状态 break 如果状态为已经停止，退出任务
			 */
			if (SysoutCommon.JOBSTATUS_MAP.get(tradetype.toString()).equals(Constant.Jobcontral.STATUS$P)) {
				log.debug(" 状态被修改，停止任务  : "  + workDayManager.getSysTime());
				break;
			}
			/*
			 * 判断该计划当前工作日有没有发起过
			 */
			n = jobcontralMapper.getFdacfinalresult(listAutotrade.getAutoid(), workDate);
			if (n == 0) {
				/*
				 * 写交易流水
				 */
				try {
					if (listAutotrade.getTradetype().equals(AutoTradeType.AUTORECHARGE.value())) {
						Apply vo = AutotradeManagerHelper.toApplyVo(listAutotrade);
						tradeManager.buyApply(vo);
						n = 1;
					} else if (listAutotrade.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL.value())) {
						Redeem vo = AutotradeManagerHelper.toRedeemVo(listAutotrade);
						tradeManager.realRedeem(vo);
						n = 1;
					}
				} catch (Exception e) {
					log.debug(listAutotrade.getAutoid() + "自动任务失败 !");
					log.error(listAutotrade.getAutoid() + "自动任务失败 !",e);
				}
				
				/*
				 * 插入流水
				 */
				if(n == 1){
					count ++;
					// 根据返回更新流水状态
					nextdate = autotradeManager.getNextdate(Constant.Autotrade.CYCLE$MM, listAutotrade.getDat());
					listAutotrade.setLastdate(workDate);
					listAutotrade.setNextdate(nextdate);
					autotradeMapper.updateAutotrade(listAutotrade);
					
					this.insertFdacfinalresult(listAutotrade, autotrade.getTradetype() + "5");
				}
			}
		}
		/*
		 * 更新任务状态为已完成
		 */
		log.debug(" 自动任务 [" + tradetype.toString() +"] 结束 " + workDate + " 执行计划 " + count + " 条");
		if(!SysoutCommon.JOBSTATUS_MAP.get(tradetype.toString()).equals(Constant.Jobcontral.STATUS$P)){
			jobcontral.setEndtime(workDayManager.getSysTime());
			jobcontral.setJobstatus(Constant.Jobcontral.STATUS$X);
			n = jobcontralMapper.updateJobcontral(jobcontral);
		}
	}

	@Override
	public void stopAutotrade(AutoTradeType tradetype, String workDate){
		log.debug(" 自动任务 " + tradetype.toString() +" 开始结束 " + workDate);
		Jobcontral jobcontral = new Jobcontral();
		jobcontral.setWorkdate(workDate);
		jobcontral.setJobname(tradetype.toString());
		jobcontral = jobcontralMapper.getJobcontral(jobcontral);
		if (jobcontral == null) {
			throw new  UserException("系统异常！");
		}else{
			log.debug(" 任务状态记录  : "  + jobcontral.toString());
			if (!jobcontral.getJobstatus().equals(Constant.Jobcontral.STATUS$I)) {
				throw new  UserException("系统异常！");
			}
		}
		jobcontral.setJobstatus(Constant.Jobcontral.STATUS$P);
		SysoutCommon.JOBSTATUS_MAP.put(tradetype.toString(), Constant.Jobcontral.STATUS$P);
//		SysoutCommon.JOB_STATUS = Constant.Jobcontral.STATUS$P;
		int n = jobcontralMapper.updateJobcontral(jobcontral);
		log.debug(" 自动任务结束成功 "  + n);
	}

	private void insertFdacfinalresult(Autotrade autotrade, String apkind) {
		Fdacfinalresult fdacfinalresult = AutotradeManagerHelper.toFdacfinalresult(autotrade);
		fdacfinalresult.setSerialno(SequenceUtil.getSerial());
		fdacfinalresult.setApkind(apkind);
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);

	}
}
