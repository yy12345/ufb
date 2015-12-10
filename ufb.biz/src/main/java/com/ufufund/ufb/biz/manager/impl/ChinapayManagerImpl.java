package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.ChinapayManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.chinapay.Response;
import com.ufufund.ufb.model.chinapay.TransDetail;
import com.ufufund.ufb.remote.chinapay.ChinapayService;

import lombok.extern.slf4j.Slf4j;

/**
 * 银联交易处理层
 * @author ayis
 * 2015年11月3日
 */
@Service
@Slf4j
public class ChinapayManagerImpl implements ChinapayManager{

	@Autowired
	private ChinapayService chinapayService;
	
	
	@Override
	public void checkAccount(OpenAccountAction openAccountAction) {
		
		TransDetail tDetail = new TransDetail();
		tDetail.setBANK_CODE("105");
		tDetail.setACCOUNT_NO("6227001823260036733");
		tDetail.setACCOUNT_NAME("吴小龄");
		tDetail.setID_TYPE("0");
		tDetail.setID("441509876512014787");
		tDetail.setTEL("13602459062");
		
		// 本地交易流水
		String serialno = SequenceUtil.getSerial();
		// code later...
		
		// 银联交易
		Response response = chinapayService
				.checkAccount(serialno, tDetail);
		
		// 响应处理
		if("0000".equals(response.getINFO().getRET_CODE())){
			if("0000".equals(response.getBODY().getRET_DETAIL().get(0).getRET_CODE())){
				log.info("认证成功：bankno="+openAccountAction.getBankno()
					+",bankacco="+openAccountAction.getBankacco()+",name="+openAccountAction.getBanknm());
			}else{
				throw new UserException(response.getBODY().getRET_DETAIL().get(0).getRET_CODE(),
						response.getBODY().getRET_DETAIL().get(0).getERR_MSG());
			}
		}else{
			throw new UserException("系统出现异常！");
		}
	}

}
