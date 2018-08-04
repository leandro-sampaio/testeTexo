package com.texo.teste.dto;

public class ErroDTO {

    private String code;
    private String message;

    public ErroDTO(int code, String message) {
        this.code = String.valueOf(code);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
