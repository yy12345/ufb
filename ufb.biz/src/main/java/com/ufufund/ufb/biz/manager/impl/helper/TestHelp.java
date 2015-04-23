package com.ufufund.ufb.biz.manager.impl.helper;

import com.ufufund.ufb.biz.manager.DictManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.db.Dictionary;

public class TestHelp {
	protected static boolean isTest = getTest();
	
	private static boolean getTest(){
		Dictionary dictionary = DictManager.getDict(Constant.DICTIONARY$SYS, Constant.TEST);
		if(dictionary!=null&&"NO".equals(dictionary.getPmnm())){
			return false;
		}
		return true;
	}
}
