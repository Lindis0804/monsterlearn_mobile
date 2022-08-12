package com.ldh.monsterlearn.Model;

import java.io.Serializable;

public class UserCourse implements Serializable {
    private  String course;
    private int numOfQuestion;

    public UserCourse(String course, int numOfQuestion) {
        this.course = course;
        this.numOfQuestion = numOfQuestion;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }
}
