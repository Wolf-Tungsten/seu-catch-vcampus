package com.wolfTungsten.vcampus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;
import com.wolfTungsten.vcampus.controller.BaseController;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class VCampusServerThread implements Runnable {

	private Socket client = null;  
	private VCampusServer server;
	
    public VCampusServerThread(Socket client, VCampusServer server){  
        this.client = client;  
        this.server = server;
    }  

    @Override  
    public void run() {  
        try{  
            //获取Socket的输出流，用来向客户端发送数据  
            PrintStream out = new PrintStream(client.getOutputStream());  
            //获取Socket的输入流，用来接收从客户端发送过来的数据  
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String requestRaw = "";
            boolean flag =true;  
            while(flag){  
                //接收从客户端发送过来的数据  
                String str =  buf.readLine();  
                if(str == null || "".equals(str)){  
                    flag = false;  
                }else{  
                    if("bye".equals(str)){  
                        flag = false;  
                    }else{  
                    	requestRaw += str;
                    }  
                }  
            }
            // 从Server实例中获取PathMap
            HashMap<String, BaseController> pathMap = server.getPathMap();
            Gson gson = new Gson();
            Request request = gson.fromJson(requestRaw, Request.class);
            System.out.println(requestRaw);
            for(String path:pathMap.keySet()) {
            	if (request.getPath().startsWith(path)) {
            		Response response = pathMap.get(path).handle(request);
            		String responseRaw = gson.toJson(response);
            		out.println(responseRaw);
            		out.flush();
            	}
            }
            out.close();  
            client.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}
