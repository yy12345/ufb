package com.ufufund.ufb.model.vo;

import java.math.BigDecimal;

import com.ufufund.ufb.model.db.BankCardWithTradeAcco;

public class TradeAccoVo extends BankCardWithTradeAcco {
	private static final long serialVersionUID = 1L;

	private BigDecimal total = new BigDecimal("0.00");
	private BigDecimal available = new BigDecimal("0.00");
	private BigDecimal realavailable = new BigDecimal("0.00");
	private BigDecimal frozen = new BigDecimal("0.00");

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
}