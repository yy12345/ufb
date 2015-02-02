package com.ufufund.trade.ufb.file.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskJob{
	 
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskJob.class);
 
    public void run() {
 
        if (LOG.isInfoEnabled()) {
            LOG.info("数据转换任务线程开始执行");
        }
        
        LOG.debug("xxxxxxxxxxxxxxxxxxxx------------");
    }
}
