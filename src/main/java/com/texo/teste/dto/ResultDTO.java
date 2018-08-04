package com.texo.teste.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDTO {

    private Object sucessDTO;
    private ErroDTO erroDTO;

    public ResultDTO() {
    }

    public ResultDTO(Object sucessDTO) {
        this.sucessDTO = sucessDTO;
    }

    public ResultDTO(ErroDTO erroDTO) {
        this.erroDTO = erroDTO;
    }

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("success")
    public Object getSucessDTO() {
        return sucessDTO;
    }

    public void setSucessDTO(Object sucessDTO) {
        this.sucessDTO = sucessDTO;
    }

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    public ErroDTO getErroDTO() {
        return erroDTO;
    }

    public void setErroDTO(ErroDTO erroDTO) {
        this.erroDTO = erroDTO;
    }

    @JsonProperty("hasError")
    public boolean hasError(){
        return  erroDTO != null;
    }
}
