package com.wolfTungsten.vcampus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.wolfTungsten.vcampus.controller.BaseController;
import com.wolfTungsten.vcampus.controller.LibraryController;
import com.wolfTungsten.vcampus.controller.UserController;
import com.wolfTungsten.vcampus.controller.BankController;

public class VCampusServer {
	
	private HashMap<String, BaseController> pathMap;
	
	public VCampusServer() {
		
		pathMap = new HashMap<>();
		pathMap.put("user", new UserController());
		pathMap.put("bank", new BankController());
		pathMap.put("book", new LibraryController());

	}
	
	public HashMap<String, BaseController> getPathMap() {
		return this.pathMap;
	}
	
	public static void main(String[] args) {
		// 服务端在20006端口监听客户端请求的TCP连接
		ServerSocket serverSocket = null;
		VCampusServer server = new VCampusServer();
		try {
			serverSocket = new ServerSocket(20006);
			Socket client = null;
			boolean f = true;
			while (f) {
				client = serverSocket.accept();
				System.out.println("与客户端连接成功！");
				// 为每个客户端连接开启一个线程
				new Thread(new VCampusServerThread(client, server)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
