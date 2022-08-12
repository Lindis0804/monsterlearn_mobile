package com.ldh.monsterlearn.View.Homepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.monsterlearn.Model.HomepageCourse;
import com.ldh.monsterlearn.R;

import java.util.List;

public class homepage_course_adapter extends RecyclerView.Adapter<homepage_course_adapter.ItemViewHolder>{
    List<HomepageCourse> courses;
    private homepage_course_clickListener itemClickListener;

    public homepage_course_adapter(List<HomepageCourse> courses, homepage_course_clickListener itemClickListener) {
        this.courses = courses;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_course_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
       HomepageCourse course = courses.get(position);
       holder.imageView.setImageResource(course.getHomepage_course_image());
       holder.textView.setText(course.getHomepage_course_name());
       holder.num_of_learner.setText(course.getNumOfLearner()+"");
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
       private TextView textView,num_of_learner;
       private homepage_course_clickListener _itemClickListener;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.homepage_course_item_imageView);
            textView = itemView.findViewById(R.id.homepage_course_item_textView_name);
            num_of_learner = itemView.findViewById(R.id.homepage_course_item_textView_numOfLearner);
            _itemClickListener = itemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_itemClickListener != null){
                        _itemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
