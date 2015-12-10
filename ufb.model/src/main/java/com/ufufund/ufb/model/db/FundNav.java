package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class FundNav{

	private String fundcorpno; // 基金公司编号',
	private String fundcode; // 基金编码',
	private String fundname; // 基金名称',
	private String date; // 基金净值日',
	private String nav; // 基金单位净值：四位小数',
	private String navadditive; // 累计基金单位净值',
	private String income; // 万份收益：5位小数',
	private String yield; // 7日年化收益率：5位小数',
}
