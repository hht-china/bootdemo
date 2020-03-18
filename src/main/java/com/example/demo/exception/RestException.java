package com.example.demo.exception;

import com.example.demo.common.Msg;

/**
 * 对外开放的异常
 * @author hongtao.hao
 * @date 2019/9/18
 */
public class RestException extends BusinessException{
    private Msg msg;

    private Object data;

    public RestException(Msg msg) {
        super(msg.getTip());
        this.msg = msg;
    }

    public RestException(Msg msg, Object data) {
        super(msg.getTip());
        this.msg = msg;
        this.data = data;
    }

    public RestException(Msg msg, Throwable cause) {
        super(msg.getTip(), cause);
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public Msg getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
