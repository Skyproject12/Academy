package com.example.academy.Ui.Academy;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.academy.R;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Ui.Detail.DetailCourseActivity;

import java.util.ArrayList;
import java.util.List;

public class AcademyAdapter extends RecyclerView.Adapter<AcademyAdapter.ViewHolder> {

    final Activity activity;
    private List<CourseEntity> mCourse= new ArrayList<>();

    public AcademyAdapter(Activity activity) {
        this.activity = activity;
        this.mCourse = mCourse;
    }

    public List<CourseEntity> getmCourse() {
        return mCourse;
    }
    public static final String EXTRA_COURCE ="extra_course" ;

    public void setListCourse(List<CourseEntity> mCourse) {
        if(mCourse==null)return;
        this.mCourse.clear();
        this.mCourse.addAll(mCourse);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_academy, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public int getItemCount() {
        return getmCourse().size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(getmCourse().get(position).getTitle());
        holder.tvDescription.setText(getmCourse().get(position).getDeskription());
        holder.tvDate.setText(String.format("Deadline %s", getmCourse().get(position).getDeadline()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity, DetailCourseActivity.class);
                // put extra for id course
                intent.putExtra(EXTRA_COURCE, getmCourse().get(position).getCourseId());
                activity.startActivity(intent);

            }
        });

        Glide.with(holder.itemView.getContext())
                .load(getmCourse().get(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView  tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle= itemView.findViewById(R.id.tv_item_title);
            tvDescription= itemView.findViewById(R.id.tv_item_description);
            imgPoster= itemView.findViewById(R.id.image_poster);
            tvDate= itemView.findViewById(R.id.tv_item_date);

        }
    }
}
