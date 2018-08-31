package src.test.test1;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJson
{
	public static void main(String[] args) {
		Response response = new Response();
		response.setSuccess(true);
		Gson gson = new Gson();
		
		HashMap map = new HashMap<String , String>(); 
		map.put("num","123456");
		response.setBody(map);
		String responseStr = gson.toJson(response);
		System.out.println(responseStr);
		
	}
}
