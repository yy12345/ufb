package com.ufufund.ufb.model.action;

import java.util.UUID;

//package com.ufufund.ufb.action;
//
public class CommonAction extends PrintableModel {
	
	private String  processId = "["+UUID.randomUUID().toString()+"] ";

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	

}


