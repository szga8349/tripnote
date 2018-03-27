package com.lenovo.exception;

public class Exceptions {

    /**
     * 忽略当前循环
     */
    public static class IgnoreCurrentLoopException extends IllegalStateException {

        public IgnoreCurrentLoopException(String message) {
            super(message);
        }
    }

    public static void throwIgnoreCurrentLoopException(String message) throws IgnoreCurrentLoopException {
        throw new IgnoreCurrentLoopException(message);
    }

}
