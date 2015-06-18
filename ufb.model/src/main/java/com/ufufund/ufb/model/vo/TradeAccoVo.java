package com.ufufund.ufb.model.vo;

import java.math.BigDecimal;

import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;

public class TradeAccoVo extends TradeAccoinfoOfMore {
	private static final long serialVersionUID = 1L;

	private BigDecimal total = new BigDecimal("0.00");
	private BigDecimal available = new BigDecimal("0.00");
	private BigDecimal realavailable = new BigDecimal("0.00");
	private BigDecimal frozen = new BigDecimal("0.00");
	
	private BigDecimal funddayincome = new BigDecimal("0.00");
	private BigDecimal totalincome = new BigDecimal("0.00");

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getRealavailable() {
		return realavailable;
	}

	public void setRealavailable(BigDecimal realavailable) {
		this.realavailable = realavailable;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public BigDecimal getFunddayincome() {
		return funddayincome;
	}

	public void setFunddayincome(BigDecimal funddayincome) {
		this.funddayincome = funddayincome;
	}

	public BigDecimal getTotalincome() {
		return totalincome;
	}

	public void setTotalincome(BigDecimal totalincome) {
		this.totalincome = totalincome;
	}
	
}
