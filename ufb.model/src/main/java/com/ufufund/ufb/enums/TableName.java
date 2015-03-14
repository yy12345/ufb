package com.ufufund.ufb.enums;


	public enum TableName {

		CUSTINFO("CUSTINFO");
		
		
		private String value;

		private TableName(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}

	}

