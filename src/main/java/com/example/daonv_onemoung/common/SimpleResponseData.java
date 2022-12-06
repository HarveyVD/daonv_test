package com.example.daonv_onemoung.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleResponseData<T> extends SimpleResponseNoData {
    @JsonProperty("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
