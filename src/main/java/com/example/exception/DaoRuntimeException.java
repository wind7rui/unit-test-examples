package com.example.exception;

public class DaoRuntimeException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public DaoRuntimeException(Throwable cause){
        super(cause);
    }

    public DaoRuntimeException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DaoRuntimeException(String errorCode, String errorMsg, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
