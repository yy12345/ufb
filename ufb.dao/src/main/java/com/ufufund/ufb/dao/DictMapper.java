package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.Dictionary;



public interface  DictMapper{

	/**
	 * 所有参数表数据
	 * @return
	 */
	public List<Dictionary> getDictionary();
	
}
