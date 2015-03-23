package com.ufufund.ufb.common.utils;

public class SequenceUtil {

	/**
	 * 生成xml报文中，message节点的id序列号
	 * @return
	 */
	public static String getSerialId4Message(){
		// 暂时如此实现
		return String.valueOf(System.currentTimeMillis());
	}
}
