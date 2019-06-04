package com.mbs.response.model;

public class Response {
	private String message;
    private int code;
    private String moreInfo;

    
    //Constructors
    public Response() {
    	
    }
    
	public Response(String message, int code, String moreInfo) {
		this.message = message;
		this.code = code;
		this.moreInfo = moreInfo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

    
    
}
