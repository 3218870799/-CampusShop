package com.xqc.campusshop.exceptions;

public class ShopOperationException extends RuntimeException{
	
	/**
	 * 生成序列化ID
	 */
	private static final long serialVersionUID = 2291116808929801310L;

	public ShopOperationException(String msg){
		super(msg);
		
	}

}
