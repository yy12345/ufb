package com.ufufund.ufb.remote;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.hft.NotifyPaidRequest;
import com.ufufund.ufb.model.hft.NotifyPaidResponse;
import com.ufufund.ufb.model.hft.NotifyUploadedRequest;
import com.ufufund.ufb.model.hft.NotifyUploadedResponse;

@Service
public class HftNotifyService extends HftBaseService{

	/**
	 * 支付通知：直销平台扣款后，通知基金公司
	 * @param request
	 * @return
	 */
	public NotifyPaidResponse paid(NotifyPaidRequest request){
		return super.send(request, NotifyPaidResponse.class);
	}
	
	/**
	 * 机构开户影印资料上传完成通知
	 * @param request
	 * @return
	 */
	public NotifyUploadedResponse uploaded(NotifyUploadedRequest request){
		return super.send(request, NotifyUploadedResponse.class);
	}
}
