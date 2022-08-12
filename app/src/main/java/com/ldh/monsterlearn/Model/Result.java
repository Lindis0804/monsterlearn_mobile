package com.ldh.monsterlearn.Model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("result")
    private String result;

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
