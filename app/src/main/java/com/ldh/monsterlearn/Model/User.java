package com.ldh.monsterlearn.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{
    @SerializedName("Name")
    private String name = "";
    @SerializedName("Address")
    private String address ="";
    @SerializedName("Username")
    private String username ="";
    @SerializedName("Password")
    private String password ="";
    @SerializedName("Phone")
    private String phone ="";
    @SerializedName("Coins")
    private int coins = 0;
    @SerializedName("Course")
    private ArrayList<UserCourse> courseList = new ArrayList<>();
    @SerializedName("Followers")
    private int followers = 0;
    @SerializedName("WorkPlace")
    private String workPlace = "";
    @SerializedName("Birthday")
    private String birthday = "";
    @SerializedName("About")
    private String about = "";
    @SerializedName("Specialization")
    private String specialization ="";
    @SerializedName("Gmail")
    private String gmail = "";
    private Map<String, Integer> courseMap;

    public User(String name, String address, String username, String password, String phone, int coins, ArrayList<UserCourse> courseList) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.coins = coins;
        this.courseList = courseList;
    }

    public User(String name, String address, String username, String password, String phone, int coins, ArrayList<UserCourse> courseList, int followers, String workPlace, String birthday, String about, String specialization, Map<String, Integer> courseMap,String gmail) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.coins = coins;
        this.courseList = courseList;
        this.followers = followers;
        this.workPlace = workPlace;
        this.birthday = birthday;
        this.about = about;
        this.specialization = specialization;
        this.courseMap = courseMap;
        this.gmail = gmail;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, String username, String password, String phone, String gmail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gmail = gmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public ArrayList<UserCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<UserCourse> courseList) {
        this.courseList = courseList;
    }
    public void setCourseMap(){
        courseMap = new HashMap<>();
        if (courseList.size()>0) {
            for (int i = 0; i < courseList.size(); i++) {
                courseMap.put(courseList.get(i).getCourse(), courseList.get(i).getNumOfQuestion());
            }
        }
    }

    public Map<String, Integer> getCourseMap() {
        return courseMap;
    }
    public int getNumOfPassedChallenge(String name){
        for (UserCourse x:courseList){
            if (x.getCourse().equals(name)) return x.getNumOfQuestion();
        }
        return 0;
    }
    public void setNumOfPassedChallenged(String name,int t){
        for (UserCourse x:courseList) {
            if (x.getCourse().equals(name)) {
                x.setNumOfQuestion(t);
                return;
            }
        }
            UserCourse course = new UserCourse(name,t);
            this.courseList.add(course);

    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setCourseMap(Map<String, Integer> courseMap) {
        this.courseMap = courseMap;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", coins=" + coins +
                ", courseList=" + courseList +
                ", courseMap=" + courseMap +
                '}';
    }
}
