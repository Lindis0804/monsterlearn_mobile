package com.ldh.monsterlearn.Model;

import com.google.gson.annotations.SerializedName;
import com.ldh.monsterlearn.R;

public class HomepageCourse {

    @SerializedName("Name")
    private String homepage_course_name ="";
    @SerializedName("NumOfQuestion")
    private int numOfQuestion = 0;
    @SerializedName("Rate")
    private float rate = 0.0F;
    @SerializedName("NumberOfLearner")
    private int numOfLearner = 0;
    @SerializedName("NumberOfRate")
    private int numOfRate = 0;
    private int homepage_course_image = R.drawable.homepage_raw_android;

    public HomepageCourse(String homepage_course_name, int numOfQuestion, float rate, int numOfLearner, int numOfRate) {
        this.homepage_course_name = homepage_course_name;
        this.numOfQuestion = numOfQuestion;
        this.rate = rate;
        this.numOfLearner = numOfLearner;
        this.numOfRate = numOfRate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getNumOfRate() {
        return numOfRate;
    }

    public void setNumOfRate(int numOfRate) {
        this.numOfRate = numOfRate;
    }

    public int getHomepage_course_image() {
        return homepage_course_image;
    }

    public void setHomepage_course_image(int homepage_course_image) {
        this.homepage_course_image = homepage_course_image;
    }

    public String getHomepage_course_name() {
        return homepage_course_name;
    }

    public void setHomepage_course_name(String homepage_course_name) {
        this.homepage_course_name = homepage_course_name;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public float getRate() {
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

    @Override
    public String toString() {
        return "HomepageCourse{" +
                "homepage_course_name='" + homepage_course_name + '\'' +
                ", numOfQuestion=" + numOfQuestion +
                ", rate=" + rate +
                ", numOfLearner=" + numOfLearner +
                ", homepage_course_image=" + homepage_course_image +
                '}';
    }
}
