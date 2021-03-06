package com.iqmsoft.boot.async.msg;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FiboCalcResponse {

    private int number;

    private long fibo;

    public FiboCalcResponse(@JsonProperty("number") int number, @JsonProperty("fibo") long fibo) {
        this.number = number;
        this.fibo = fibo;
    }

    @Override
    public String toString() {
        return "FiboCalcResponse{" +
                "number=" + number +
                ", fibo=" + fibo +
                '}';
    }
}
