package com.ldh.monsterlearn.Model;

import com.google.gson.annotations.SerializedName;

public class CourseUpdate {
    @SerializedName("Rate")
    private float rate;
    @SerializedName("NumberOfRate")
    private int numberOfRate;

    public CourseUpdate(float rate, int numberOfRate) {
        this.rate = rate;
        this.numberOfRate = numberOfRate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getNumberOfRate() {
        return numberOfRate;
    }

    public void setNumberOfRate(int numberOfRate) {
        this.numberOfRate = numberOfRate;
    }
}
