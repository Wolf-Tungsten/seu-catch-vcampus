package com.wolfTungsten.vcampus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class VCampusUdpServer implements Runnable{
	
    private final int MAX_LENGTH = 1024; // 最大接收字节长度
    private final int PORT_NUM   = 7942;   // port号
    private DatagramSocket datagramSocket;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			datagramSocket = new DatagramSocket(PORT_NUM);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InetAddress inetAddress = null;
		try {
			inetAddress = this.getLocalHostLANAddress();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (inetAddress != null) {
			// 检测到处于校园网环境
			String udpAddress = inetAddress.getHostAddress().split("\\.")[0] +"."+ inetAddress.getHostAddress().split("\\.")[1] + ".255.255";
			while(true) {
				// 通过7942端口发送信息
				byte[] buf = "seu-catch-vcampus-server".getBytes();
				try {

					DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName(udpAddress), PORT_NUM);
					datagramSocket.send(sendPacket);
					Thread.sleep(1000);	

				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	    
	}
	
	public InetAddress getLocalHostLANAddress() throws Exception {
	    try {
	        InetAddress candidateAddress = null;
	        // 遍历所有的网络接口
	        for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	            // 在所有的接口下再遍历IP
	            for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                if (inetAddr.getHostAddress().startsWith("223")) {
	                	return inetAddr;
	                }
	            }
	        }
	        // 如果没有发现 non-loopback地址.只能用最次选的方案
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        return jdkSuppliedAddress;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
