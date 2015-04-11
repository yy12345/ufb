package com.ufufund.ufb.remote.service;

import com.ufufund.ufb.remote.xml.pojo.BalanceQueryRequest;
import com.ufufund.ufb.remote.xml.pojo.BalanceQueryResponse;
import com.ufufund.ufb.remote.xml.pojo.TransQueryRequest;
import com.ufufund.ufb.remote.xml.pojo.TransQueryResponse;

/**
 * 海富通查询相关接口
 * @author ayis
 * 2015年3月22日
 */
public class HftQueryService extends HftBaseService{

	/**
	 * 交易查询
	 * @param request
	 * @return
	 */
	public TransQueryResponse transQuery(TransQueryRequest request){
		return super.send(request, TransQueryResponse.class);
	}
	
	/**
	 * 份额查询
	 * @param request
	 * @return
	 */
	public BalanceQueryResponse balanceQuery(BalanceQueryRequest request){
		return super.send(request, BalanceQueryResponse.class);
	}
	
}
