package com.wolfTungsten.vcampus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
		while(true) {
			// 通过7942端口发送信息
			byte[] buf = "seu-catch-vcampus-server".getBytes();
            try {
				DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("255.255.255.255"), PORT_NUM);
				datagramSocket.send(sendPacket);
				Thread.sleep(1000);	
				System.out.println("正在发送");
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	}

}
