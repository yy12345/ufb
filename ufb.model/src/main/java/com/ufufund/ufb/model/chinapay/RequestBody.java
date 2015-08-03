package com.ufufund.ufb.model.chinapay;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RequestBody {

	private TransSum TRANS_SUM;
	@XmlElementWrapper(name="TRANS_DETAILS")
	private List<TransDetail> TRANS_DETAIL;
	
	// 查询时用到，非查询时不设置此字段
	private QueryTrans QUERY_TRANS;
}
