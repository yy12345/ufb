package com.ufufund.ufb.web.filter;

import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 检查重复提交工具类
 * @author ayis
 * 2015年4月25日
 */
public class RefreshHolder {
	private static Logger LOG = LoggerFactory.getLogger(RefreshHolder.class);

	// 保存随机序列的map
	private static final ConcurrentMap<String,Long> holder = new ConcurrentHashMap<String,Long>(5000);
	// 生成input项的表单元素名
	public static final String INPUT_NAME = "refresh_holer_item";
	// holder中存储序列的数量的预警值
	private static final int WARN_NUM = 3000;
	// 序列值失效的时间，单位：分钟
	private static final int INVALID_TIME = 10;
	// 预警线程检查频率，单位：秒
	private static final int CHECK_SECOND = 5;
	
	/**
	 * 初始化时，启动预警维护线程
	 */
	static{
		new Thread(new HolderCleaner()).start();
	}
	
	/**
	 * 生成带随机序列值的input元素
	 * @return
	 */
	public static String genInputHtml(){
		String rand = UUID.randomUUID().toString();
		holder.put(rand, System.currentTimeMillis());
		return "<input name=\""+INPUT_NAME+"\" value=\""+rand+"\" type=\"hidden\">";
	}
	
	/**
	 * 检查是否为刷新提交
	 * @param key
	 * @return
	 */
	public static boolean isRefresh(String key){
		Object v = holder.remove(key);
		return v == null?true:false;
	}
	
	/**
	 * 预警维护线程
	 */
	static final class HolderCleaner implements Runnable{
		@Override
		public void run() {
			while(true){
				// 达到预警存储数量时，清除过时元素
				if(holder.size() > WARN_NUM){
					long now = System.currentTimeMillis();
					for(Entry<String,Long> entry : holder.entrySet()){
						if(now - entry.getValue() > INVALID_TIME*60*1000){
							holder.remove(entry.getKey());
						}
					}
				}
				// 线程休眠
				try {
					Thread.sleep(CHECK_SECOND*1000);
				} catch (InterruptedException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}
}
