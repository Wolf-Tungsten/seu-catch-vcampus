package src.test.test1;

import java.util.HashMap;

public class Response {
	
	private Boolean success;
	private HashMap<String, Object> body;
	public Response() {
		
	}
	public Response(Boolean success, HashMap<String, Object> body)
	{
		super();
		this.success = success;
		this.body = body;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public HashMap<String, Object> getBody() {
		return body;
	}
	public void setBody(HashMap<String, Object> body) {
		this.body = body;
	}
	
	
}
