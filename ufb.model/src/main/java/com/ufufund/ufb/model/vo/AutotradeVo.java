package com.ufufund.ufb.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class AutotradeVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String autoid;
	
	private String custno;
	private BigDecimal autoamt;
	private String summary;
	private String apkind;
	private String cycle;
	private String dat;
	private String fromfundcode;   // 源基金代码
	private String fromfundcorpno; // 源归属基金公司
	private String frombankserialid;
	private String frombankacco;
	private String fromtradeacco;
	private String fromchargetype;
	private String frombankno;
	private String frombanktail;
	private String tradepwd;
	

	private String tofundcode;   // 目标基金代码
	private String tofundcorpno; // 目标归属基金公司
	private String tobankserialid;
	private String tobankacco;
	private String totradeacco;
	private String tochargetype;
	 
	private String tobankno;
	private String tobanktail;
	private BigDecimal autovol; // 份额
	
	private String lastdate;   // 最近扣款日期
	private String nextdate;   // 下一扣款日期
	
	private String step;
	
}
