package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.Orgplandetail;

public interface PlanDetailMapper {

	void updateDetail(Orgplandetail orgplandetail);
	String selectPayDate(String detailid);
}
