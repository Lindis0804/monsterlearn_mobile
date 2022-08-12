package com.ldh.monsterlearn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {
    @SerializedName("Course")
    private String course;
    @SerializedName("Question")
    private String question;
    @SerializedName("A")
    private String A;
    @SerializedName("B")
    private String B;
    @SerializedName("C")
    private String C;
    @SerializedName("D")
    private String D;
    @SerializedName("E")
    private String E;
    @SerializedName("Answer")
    private String answer;
    @SerializedName("Type")
    private int type;

    public Question(String course, String question, String a, String b, String c, String d, String e) {
        this.course = course;
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
    }

    public Question(String course, String question, String a, String b, String c, String d,String answer,int type) {
        this.course = course;
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        this.answer = answer;
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getAnswer() {
        return answer;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "course='" + course + '\'' +
                ", question='" + question + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", D='" + D + '\'' +
                ", E='" + E + '\'' +
                ", answer='" + answer + '\'' +
                ", type=" + type +
                '}';
    }
}
