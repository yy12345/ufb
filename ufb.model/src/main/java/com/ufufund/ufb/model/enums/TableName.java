package com.ufufund.ufb.model.enums;


	public enum TableName {

		CUSTINFO("CUSTINFO"),
		BANKCARDINFO("BANKCARDINFO"),
		TRADEACCOINFO("TRADEACCOINFO");
		
		private String value;

		private TableName(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}

	}

