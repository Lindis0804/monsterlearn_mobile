package com.ldh.monsterlearn.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ldh.monsterlearn.Model.CourseUpdate;
import com.ldh.monsterlearn.Model.HomepageCourse;
import com.ldh.monsterlearn.Model.Question;
import com.ldh.monsterlearn.Model.Result;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.Model.UserVerify;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    //link api:
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
   String address = "192.168.71.2";
   //String address = "169.254.53.148";
  //  String address = "192.168.1.67";
    ApiService apiService = new Retrofit.Builder().baseUrl("http://"+address+":3000/").addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService.class);

    //log in
    @POST("user/signIn")
    Call<User> getLogInResult(@Body User user);
    @GET("user")
    Call<User> getUser();
    @POST("user/update")
    Call<User> updateInformation(@Body User user);
    @GET("user/{Username}")
    Call<User> getUserInformation(@Path("Username") String Username);
    //signUp
    @POST("user/signUp")
    Call<Result> getSignUpResult(@Body User user);
    @POST("user/signUp/verify")
    Call<Result> getVerifyResult(@Body UserVerify userVerify);
    // lay thong tin course
    @GET("course")
    Call<ArrayList<HomepageCourse>> getListCourse();
    @PUT("course/{course}")
    Call<Result> updateCourse(@Path("course") String course, @Body CourseUpdate courses);
    //lấy thông tin question
    @GET("question/{question}/null")
    Call<ArrayList<Question>> getQuestionList(@Path("question") String Question);

}
