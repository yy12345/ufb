package com.ufufund.test.test4;






import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.common.SysoutCommon;
import com.ufufund.daointerface.CustinfoMapper;
import com.ufufund.dataobject.CustinfoDO;
import com.ufufund.ufb.dao.AreaMapper;
import com.ufufund.ufb.model.Area;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-dao.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CustinfoMapperTest {

	@Autowired
	private CustinfoMapper custinfoMapper;

	@Test
	public void testAddArea(){
		CustinfoDO custinfoDO = new CustinfoDO();
		CustinfoDO s = custinfoMapper.queryCustinfoDO(custinfoDO);
		//SysoutCommon.SystemObject(s);
		System.out.println("ssssss"+s.getCustid());
	}
	

	
}
