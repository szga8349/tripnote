package com.lenovo.exception;

/**
 * 网络异常
 * @author：涂有
 * @date 2017年12月13日 下午3:40:52
 */
public class NetException extends CustomException{

	private static final long serialVersionUID = 5555175022295279032L;
	
	public NetException() {
        super();
    }
	
	public NetException(String message) {
        super(message);
    }
	
	public NetException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public NetException(Throwable cause) {
        super(cause);
    }
}
