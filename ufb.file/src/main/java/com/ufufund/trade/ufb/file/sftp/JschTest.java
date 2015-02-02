package com.ufufund.trade.ufb.file.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;

public class JschTest {
	
	public static void main(String args[]){
		
		JschTest jt = new JschTest();
		jt.testDownload();
	}

	public void testDownload(){
		ChannelSftp sftp = null;
		int timeout = 5000;
		try {
			sftp = SFTPChannel.getChannel(timeout);
			SFTPTools.download("/data", "datafile.txt", "datafile.txt", sftp);
		} catch (JSchException e) {
			e.printStackTrace();
		}finally{
			try {
				SFTPChannel.closeChannel();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void testUpload(){
		ChannelSftp sftp = null;
		int timeout = 5000;
		try {
			sftp = SFTPChannel.getChannel(timeout);
			SFTPTools.upload("/data", "datafile.txt", sftp);
		} catch (JSchException e) {
			e.printStackTrace();
		}finally{
			try {
				SFTPChannel.closeChannel();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

