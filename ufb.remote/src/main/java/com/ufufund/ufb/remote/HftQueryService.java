package com.ufufund.ufb.remote;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.hft.BalanceQueryRequest;
import com.ufufund.ufb.model.hft.BalanceQueryResponse;
import com.ufufund.ufb.model.hft.TransQueryRequest;
import com.ufufund.ufb.model.hft.TransQueryResponse;

/**
 * 海富通查询相关接口
 * @author ayis
 * 2015年3月22日
 */
@Service
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
