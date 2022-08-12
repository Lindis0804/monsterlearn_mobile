package com.ldh.monsterlearn.View.Homepage;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.HomepageCourse;
import com.ldh.monsterlearn.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homepage_fragment_learn<courses> extends Fragment implements homepage_course_clickListener {
    private ArrayList<HomepageCourse> courses = new ArrayList<>();
    RecyclerView recyclerView;
    //tạo interface gửi dữ liệu đến homepage_fragment_course_detail
    private SendDataListener sendDataListener;
    private Homepage homepage = (Homepage) getActivity();
    public interface SendDataListener{
          //gửi dữ liệu đến homepage_fragment_course_detail
         void sendData(HomepageCourse course);
    }
    //====================================================================
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment_learn,container,false);
        recyclerView = view.findViewById(R.id.homepage_fragment_learn_recyclerView_course_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);
        //phải đặt cái này dưới cùng
        getCourse();
        return view;
    }
    public void getCourse(){
        ApiService.apiService.getListCourse().enqueue(new Callback<ArrayList<HomepageCourse>>() {
            @Override
            public void onResponse(Call<ArrayList<HomepageCourse>> call, Response<ArrayList<HomepageCourse>> response) {
                ArrayList<HomepageCourse> courseList;
//                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), response.body()+"", Toast.LENGTH_SHORT).show();
                courses = response.body();
                courses.get(0).setHomepage_course_image(R.drawable.homepage_raw_android);
                courses.get(1).setHomepage_course_image(R.drawable.homepage_raw_computer_network);
                courses.get(2).setHomepage_course_image(R.drawable.homepage_raw_distributed_system);
                homepage_course_adapter adapter = new homepage_course_adapter(courses,homepage_fragment_learn.this);
                recyclerView.setAdapter(adapter);
//               setCourses(courseList);
            }

            @Override
            public void onFailure(Call<ArrayList<HomepageCourse>> call, Throwable t) {
                Log.v("TAG","Fragment learn"+t.getMessage());
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
     //   Toast.makeText(getContext(), courses.get(position).toString(), Toast.LENGTH_SHORT).show();
        homepage_fragment_course_detail fragment = new homepage_fragment_course_detail();
        Bundle bundle = new Bundle();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.homepage_frameLayout,fragment);
        fragmentTransaction.commit();
        sendDataToHomepageFragmentCourseDetail(courses.get(position));
    }
    public void sendDataToHomepageFragmentCourseDetail(HomepageCourse course){
          sendDataListener.sendData(course);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendDataListener = (SendDataListener) getActivity();
    }
}
