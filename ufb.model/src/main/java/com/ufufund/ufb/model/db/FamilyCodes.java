package com.ufufund.ufb.model.db;

import lombok.Data;

/**
 * 家庭绑定学生的识别码对象
 * @author ayis
 * 2015年11月8日
 */
@Data
public class FamilyCodes {

	private String code         ;
	private String sid          ;
	private String parent_name  ;
	private String state        ;
	private String createtime   ;
}
