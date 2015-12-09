package com.ufufund.ufb.model.db;

import java.util.List;

import lombok.Data;

@Data
public class OrgPlanDetail {

	// 学费通缴费详单
	private String id           ;     //  交易流水号
	private String orgid        ;     //  机构id
	private String parentid     ;     //  家长id
	private String sid          ;     //  学生id
	private String plan_id      ;     //  计划id
	private String amount       ;     //  实收金额
	private String discount     ;     //  折扣金额
	private String state        ;     //  订单状态:0-等待缴费；1-等待扣款；2-待补扣；3-扣款失败；4-扣款成功；5-手工补缴
	private String confirm_date ;     //  家长确认日
	private String pay_date     ;     //  首次扣款日
	private String payday;            //每月扣款日
	private String repay_date   ;     //  再次扣款日
	private String charge_type  ;  //收费方式：01-月代扣；02-单笔按月；03-单笔学期；04-单笔学年；05-临时性
	private String type;
	private String sname;// 学生姓名
	private String cname;// 班级姓名
	private String code;
	private String planname;// 计划名称
	private String orgname;// 机构名称
	private String deadline;//确认截止日
	private String bill_month;//记账月份
	private List<String> ispaylist;
	
	private OrgQuery orgQuery;
}
