package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class FundInfo{

	private String fundcorpno; // 基金公司编号',
	private String fundcode; // 基金编码',
	private String fundname; // 基金名称',
	private String status; // 基金状态：0-可申购赎回，1-发行，4-停止申购赎回，5-停止申购，6-停止赎回，9-基金封闭，a-基金终止\r\n
	private String fundtype; // 基金类型:01-股票型;02-债券型;03-混合型;04-货币型',
	private String fundvol; // 基金总份数',
	private String fundsize; // 基金规模',
	private String date; // 基金净值日',
	private String nav; // 基金单位净值：四位小数',
	private String navadditive; // 累计基金单位净值',
	private String income; // 万份收益：5位小数',
	private String yield; // 7日年化收益率：5位小数',
	private String iscontract; // 是否需要签订电子合同：0 不需要；1 需要',
	private String dividmethod; // 分红方式：0-红利转投，1-现金分红',
	private String shareclass; // 收费方式',
}
