package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.MsgRecord;

public interface MsgRecordMapper {

	public int add(MsgRecord msgRecord);
	
	public int update(MsgRecord msgRecord);
}
