package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TradeAcco{

	private BigDecimal total = new BigDecimal("0.00");
	private BigDecimal available = new BigDecimal("0.00");
	private BigDecimal realavailable = new BigDecimal("0.00");
	private BigDecimal frozen = new BigDecimal("0.00");
	
	private BigDecimal funddayincome = new BigDecimal("0.00");
	private BigDecimal totalincome = new BigDecimal("0.00");
}
