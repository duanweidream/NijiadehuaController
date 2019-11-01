package com.nijiadehua.api.exception;

public class ServiceException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static long errorCode;
	private static String errorDescription;
	
	
	
	public static long getErrorCode() {
		return errorCode;
	}

	public static void setErrorCode(long errorCode) {
		ServiceException.errorCode = errorCode;
	}

	public static String getErrorDescription() {
		return errorDescription;
	}

	public static void setErrorDescription(String errorDescription) {
		ServiceException.errorDescription = errorDescription;
	}

	public ServiceException() {
		super();
	};

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable t) {
		super(t);
	}
	
	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public ServiceException(long errorCode,String errorDescription) {
		super(errorDescription);
	}
	
	
	

}
