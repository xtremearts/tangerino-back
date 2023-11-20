package br.com.tangerino.tangerino.configurations;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }


}
