package com.brandfactory.Exception;

public class BusinessException extends Exception {

	private String errorCode;
	private String errorMsg;
	

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public BusinessException()
	{
		
	}
	
	BusinessException(String errorMsg)
	{
		super(errorMsg);
	}
	
	
	
	
	
}
