package com.ufufund.ufb.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
	
	/**
	 * 确保为空文件夹
	 * @param dir
	 */
	public static void ensureDirEmpty(String dir){
		File directory = new File(dir);
		if(!directory.exists()){
			directory.mkdirs();
		}else{
			File[] files = directory.listFiles();
			for(File f : files){
				f.delete();
			}
		}
	}

	public static void copyFile(String sourceFile, String destFile){
		FileInputStream input=null;
		FileOutputStream output=null;
		try {
			  input = new FileInputStream(new File(sourceFile));
			  output = new FileOutputStream(new File(destFile));
			
	          IOUtils.copy(input, output); 
		}catch (IOException e) {
			log.error("copyFile error:sourceFile="+sourceFile+",destFile="+destFile, e);
		}
		finally{
			try {
				if(input!=null){
					input.close();
				}
				if(output!=null){
					output.flush();
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
