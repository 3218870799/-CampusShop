package com.xqc.campusshop.dto;

/**
 * 封装json对象，返回结果都使用它！！
 * @author A Cang（xqc）
 *
 */
public class Result<T> {
	
	//是否成功的标识
	private boolean success;
	
	//成功时返回的数据
	private T data;

	//错误信息
	private String errorMsg;
	
	//状态码
	private int errorCode;
	
	//成功时的构造器
	public Result(boolean success,T data){
		this.success = success;
		this.data = data;
	}
	
	//错误时的构造器
	public Result(boolean success,int errorCode,String errorMsg){
		this.success = success;
		this.errorMsg=errorMsg;
		this.errorCode=errorCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	
}
