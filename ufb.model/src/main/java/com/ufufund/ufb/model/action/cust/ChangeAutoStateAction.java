package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;

public class ChangeAutoStateAction extends CommonAction {

	private String autoid;// char(24) default '' comment 'ID',
	private String state;// state  P-暂停 ,C 终止 删除 ,N 恢复
	
	public String getAutoid() {
		return autoid;
	}
	public void setAutoid(String autoid) {
		this.autoid = autoid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	
	
}
