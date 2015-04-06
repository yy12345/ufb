package com.ufufund.ufb.model.action;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 可以打印的业务模型 
 * 
 * 
 *
 */
public abstract class PrintableModel {

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
         
    	//return "";
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}