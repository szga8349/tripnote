package com.lenovo.exception;

public class CustomException extends Exception{

	private static final long serialVersionUID = 5555175022295279032L;
	
	public CustomException() {
        super();
    }
	
	public CustomException(String message) {
        super(message);
    }
	
	public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public CustomException(Throwable cause) {
        super(cause);
    }
}
