package com.ufufund.ufb.model.chinapay;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ResponseBody {

	@XmlElementWrapper(name="RET_DETAILS")
	private List<ReturnDetail> RET_DETAIL;
	
	// 查询时用到，非查询时此字段无数据
	private QueryTrans QUERY_TRANS;
}
