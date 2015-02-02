package com.ufufund.trade.ufb.file.sftp;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPChannel {
	private static Session session = null;
	private static Channel channel = null;

    private static final Logger log = LoggerFactory.getLogger(SFTPChannel.class);

    public static ChannelSftp getChannel(int timeout) throws JSchException {

    	// 创建session
    	JSch jsch = new JSch(); 
        session = jsch.getSession(SFTPConstants.user, SFTPConstants.host, SFTPConstants.port);
        if (SFTPConstants.pwd != null) {
        	session.setPassword(SFTPConstants.pwd); 
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); 
        session.setTimeout(timeout);
        session.connect();
        
        log.info("Session connected...");

        // 打开SFTP通道
        channel = session.openChannel("sftp"); 
        channel.connect();
        log.debug("Connected successfully to ftpHost = " + SFTPConstants.host + ",as ftpUserName = " + SFTPConstants.user
                + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

    public static void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
