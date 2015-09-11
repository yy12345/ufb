package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Orggplandetailcharge extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String detailid;// char(24) DEFAULT NULL,
	private String planid;// char(24) DEFAULT NULL COMMENT '计划ID',
	private String studentid;// char(24) DEFAULT NULL COMMENT '学生ID',
	private String payappamount;// decimal(16,2) DEFAULT NULL COMMENT '应缴金额',
	private String paydiscount;// decimal(16,2) DEFAULT NULL COMMENT '折扣金额',
	private String payackamount;// decimal(16,2) DEFAULT NULL COMMENT '实缴金额'

	public String getDetailid() {
		return detailid;
	}

	public String getPlanid() {
		return planid;
	}

	public String getStudentid() {
		return studentid;
	}

	public String getPayappamount() {
		return payappamount;
	}

	public String getPaydiscount() {
		return paydiscount;
	}

	public String getPayackamount() {
		return payackamount;
	}

}
