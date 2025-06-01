package ru.webrise.error.exception;

import lombok.Getter;

public class CommonException extends RuntimeException {

    @Getter
    private String code;
    @Getter
    private Object[] params = new Object[0];

    public CommonException(String code) {
        super(code);
        this.code = code;
    }

    public CommonException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
    }

    public CommonException withParameters(Object... params) {
        this.params = params;
        return this;
    }

}
