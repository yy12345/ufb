package com.ufufund.ufb.common.utils;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 程序生成流水号工具类
 * 备注：
 * 	1.每秒可并发65535（0xffff）个
 * 	2.在大量集群的环境下，pid和mac组合串不保证绝对的唯一性；
 * @author ayis
 * 2015年4月14日
 */
public final class SequenceUtil {
    private static final Logger LOG = LoggerFactory.getLogger(SequenceUtil.class);

	private static int num = 1;
	private static String pid = null;
    private static String mac = null;

    static {
    	pid = Integer.toHexString((getProcessId() & 0xFF) | 0x100)
        		.substring(1, 3).toUpperCase();
        mac = getMachineID(2);
    }
	
    /**
     * 生成16位长度16进制字符表示的流水号
     * @return
     */
	public static synchronized String getSerial() { 
		StringBuffer buffer = new StringBuffer(16);
		buffer.append(mac);
        buffer.append(pid);
		buffer.append(getCurrentTime(8));
		if (num == 0xffff) num = 1;
		String str = intToHexString((num++), 4);
		buffer.append(str);
        String seq = buffer.toString();
        return seq;
	}
	
	/**
	 * 取当前时间<秒>的16进制表示，（秒数值用int表示，还可用22年）
	 * @param stringLength
	 * @return
	 */
	private static String getCurrentTime(int stringLength) {
		StringBuffer currentTime = new StringBuffer(stringLength);
		currentTime.append(intToHexString((int) (System.currentTimeMillis() / 1000), stringLength));
		return currentTime.toString();
	}

	/**
	 * 取本机ip的16进制表示，ip全值为4个字节，8位16进制长度
	 * @param stringLength
	 * @return
	 */
	private static String getAddress(int stringLength) {
		StringBuffer machineID = new StringBuffer(stringLength);
		byte addr[] = null;
		try {
			addr = InetAddress.getLocalHost().getAddress();
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage(), e);
		}
		machineID.append(intToHexString(byteToInt(addr), stringLength));
		return machineID.toString();
	}

	/**
	 * 取当前机器的mac地址的md5串；若失败，则取ip16进制表示
	 * @param len
	 * @return
	 */
	private static String getMachineID(int len) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			byte[] hardwareAddress = null;
			end: while (interfaces.hasMoreElements()) {
				NetworkInterface inter = interfaces.nextElement();
				hardwareAddress = inter.getHardwareAddress();

				if (hardwareAddress == null || hardwareAddress.length <= 0) {
					continue;
				}

				Enumeration<InetAddress> inetAddressList = inter.getInetAddresses();
				while (inetAddressList.hasMoreElements()) {
					InetAddress inetAddress = inetAddressList.nextElement();
					String hostAddress = inetAddress.getHostAddress();
					if (!hostAddress.equals("127.0.0.1") && !hostAddress.contains(":") && !hostAddress.contains("%")) {
						hardwareAddress = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
						if (hardwareAddress != null && hardwareAddress.length > 0) {
							break end;
						}
					}
				}
			}
			String hex = EncryptUtil.md5(hardwareAddress);
			return hex.substring(0, len);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			// 失败，取本机ip
			return getAddress(len);
		}
	}

	/**
	 * 取当前进程号
	 * @return
	 */
	private static int getProcessId() {
		try {
			String name = ManagementFactory.getRuntimeMXBean().getName();
			int index = name.indexOf("@");
			return Integer.parseInt(name.substring(0, index));
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		}
		return new java.util.Random().nextInt();
	}

	/**
	 * int转换成指定位数16进制字符串
	 * @param value int值
	 * @param stringLength 指定位数
	 * @return 
	 */
	private static String intToHexString(int value, int stringLength) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buffer = new StringBuffer(stringLength);
		int shift = stringLength - 1 << 2;
		for (int i = -1; ++i < stringLength;) {
			buffer.append(hexDigits[value >> shift & 0XF]);
			value <<= 4;
		}
		return buffer.toString();
	}

	/**
	 * byte转换成int
	 * @param bytes
	 * @return 
	 */
	private static int byteToInt(byte bytes[]) {
		int value = 0;
		for (int i = -1; ++i < bytes.length;) {
			value <<= 8;
			int b = bytes[i] & 0XFF;
			value |= b;
		}
		return value;
	}
	
	public static void main(String args[]){
		Set<String> s = new HashSet<String>();
		for(int i = 0 ; i < 10; i++){
			String serial = SequenceUtil.getSerial();
			s.add(serial);
			LOG.debug("i="+i+", serial="+serial);
		}
		LOG.debug("size="+s.size());
	}
}