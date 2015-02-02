package com.ufufund.trade.ufb.file.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

public class SFTPTools {

	/**
	* 上传文件
	* @param directory 上传的目录
	* @param uploadFile 要上传的文件
	* @param sftp
	*/
	public static void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file=new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* 下载文件
	* @param directory 下载目录
	* @param downloadFile 下载的文件
	* @param saveFile 存在本地的路径
	* @param sftp
	*/
	public static void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file=new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* 删除文件
	* @param directory 要删除文件所在目录
	* @param deleteFile 要删除的文件
	* @param sftp
	*/
	public static void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* 列出目录下的文件
	* @param directory 要列出的目录
	* @param sftp
	* @return
	* @throws SftpException
	*/
	public static Vector listFiles(String directory, ChannelSftp sftp) throws SftpException{
		return sftp.ls(directory);
	}
}
