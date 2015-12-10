package com.ufufund.ufb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ufufund.ufb.model.db.Autotrade;


public interface AutotradeMapper{
	
	public int insertAutotrade(Autotrade autotrade);
	
	public int updateAutotrade(Autotrade autotrade);
	
	public List<Autotrade> getAutotradeList(Autotrade autotrade);
	
	public List<Autotrade> getAutotradeCList(Autotrade autotrade);

	public Autotrade getAutotrade(@Param("autoid")String autoid);
	
	public void deleteAutotrade(@Param("custno")String custno, @Param("frombankserialid")String frombankserialid, @Param("autoid")String autoid);
	
	
}
