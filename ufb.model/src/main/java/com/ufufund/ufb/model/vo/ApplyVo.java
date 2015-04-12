package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.db.TradeRequest;

public class ApplyVo extends TradeRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "ApplyVo [toString()=" + super.toString() + "]";
	}
	
}
