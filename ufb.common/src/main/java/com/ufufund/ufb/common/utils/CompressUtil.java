package com.ufufund.ufb.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

import com.ufufund.ufb.common.exception.SysException;

import lombok.extern.slf4j.Slf4j;

/**
 * zip文件解压缩工具类
 * @author ayis
 * 2015-02-16
 */
@Slf4j
public class CompressUtil {

	/**
	 *  解压zip文件
	 * @param srcArchive 源文件
	 * @param destDir 目标目录
	 */
    public static void unzip(String srcArchive, String destDir) {  
        File sourceFile = new File(srcArchive);  
        try {  
            //获得输出流  
            BufferedInputStream bufferedInputStream = new BufferedInputStream(  
                    new FileInputStream(sourceFile));  
            ArchiveInputStream in = new ArchiveStreamFactory()  
                    .createArchiveInputStream(ArchiveStreamFactory.ZIP,  
                            bufferedInputStream);  
            ZipArchiveEntry entry = null;  
            //循环遍历解压  
            while ((entry = (ZipArchiveEntry) in.getNextEntry()) != null) {  
                if (entry.isDirectory()) {  
                    new File(destDir, entry.getName()).mkdir();  
                } else {  
                    OutputStream out = FileUtils.openOutputStream(new File(  
                    		destDir, entry.getName()));  
                    IOUtils.copy(in, out);  
                    out.close();  
                }  
            }  
            in.close();  
        } catch (Exception e) {  
            log.error("解压文件失败："+srcArchive, e);
            throw new SysException("解压文件失败！"+srcArchive);
        } 
    }  
    
    /** 
     *  创建压缩的zip文件
     * @param destArchive 压缩后的目标文件名
     * @param sourceDir 备压缩的源文件目录（包含子目录）
     */
    public static void zip(String destArchive, String sourceDir) { 
    	File outFile = new File(destArchive);  
        try {  
            outFile.createNewFile();  
            //创建文件  
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(  
                    new FileOutputStream(outFile));  
            ArchiveOutputStream out = new ArchiveStreamFactory()  
                    .createArchiveOutputStream(ArchiveStreamFactory.ZIP,  
                            bufferedOutputStream);  
            
            // 处理windows目录分隔符及最后一级目录
            sourceDir = sourceDir.replace('/', File.separatorChar);
            if (sourceDir.charAt(sourceDir.length() - 1) != File.separatorChar) {  
            	sourceDir += File.separatorChar;  
            }  
  
            Iterator<File> files = FileUtils.iterateFiles(new File(sourceDir),  
                    null, true);  
            while (files.hasNext()) {  
                File file = files.next();  
                String entryName = file.getPath().substring(sourceDir.length());
                
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, entryName);  
                out.putArchiveEntry(zipArchiveEntry);  
                FileInputStream in = new FileInputStream(file);
                IOUtils.copy(in, out);  
                out.closeArchiveEntry(); 
                in.close();
            }  
            out.finish();  
            out.close();  
        } catch (Exception e) {  
            log.error("创建压缩文件失败："+sourceDir, e);
            throw new SysException("创建压缩文件失败！"+sourceDir);
        } 
    }  
 
}
