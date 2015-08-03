package com.ufufund.ufb.remote.hftfund;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.hftfund.NotifyPaidRequest;
import com.ufufund.ufb.model.hftfund.NotifyPaidResponse;
import com.ufufund.ufb.model.hftfund.NotifyUploadedRequest;
import com.ufufund.ufb.model.hftfund.NotifyUploadedResponse;

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
