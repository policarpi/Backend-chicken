package com.chicken.rest.exception;

import lombok.Getter;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private final List<String> erros;

    public ApiErrors(List<String> erros){
        this.erros = erros;
    }

    public ApiErrors(String message){
        this.erros = Arrays.asList(message);
    }

}