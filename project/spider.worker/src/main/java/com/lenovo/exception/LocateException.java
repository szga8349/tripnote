package com.lenovo.exception;

/**
 * 定位异常，比如用于selenium定位
 * @author：涂有
 * @date 2017年12月13日 下午3:40:19
 */
public class LocateException extends CustomException{

	private static final long serialVersionUID = 5555175022295279032L;
	
	public LocateException() {
        super();
    }
	
	public LocateException(String message) {
        super(message);
    }
	
	public LocateException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public LocateException(Throwable cause) {
        super(cause);
    }
}
