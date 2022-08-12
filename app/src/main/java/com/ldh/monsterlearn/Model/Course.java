package com.ldh.monsterlearn.Model;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("Name")
    private String name;
    @SerializedName("NumOfQuestion")
    private int numOfQuestion;
    @SerializedName("Rate")
    private int rate;
    @SerializedName("NumberOfLearner")
    private int numOfLearner;

    public Course(String name, int numOfQuestion, int rate, int numOfLearner) {
        this.name = name;
        this.numOfQuestion = numOfQuestion;
        this.rate = rate;
        this.numOfLearner = numOfLearner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getNumOfLearner() {
        return numOfLearner;
    }

    public void setNumOfLearner(int numOfLearner) {
        this.numOfLearner = numOfLearner;
    }
}
