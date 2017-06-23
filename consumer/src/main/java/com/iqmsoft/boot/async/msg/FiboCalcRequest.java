package com.iqmsoft.boot.async.msg;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FiboCalcRequest {

    private int number;

    public FiboCalcRequest(@JsonProperty("number") int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
