package com.example.academy.Ui.BookMark;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private final Activity activity;
    // call interface to set onclick
    private final BookmarkFragmentCallback callback;
    private ArrayList<CourseEntity> course = new ArrayList<>();

    public BookmarkAdapter(Activity activity, BookmarkFragmentCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public void setCourse(ArrayList<CourseEntity> course) {
        if(course==null) return;
        this.course.clear();
        this.course.addAll(course);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseEntity courseEntity= course.get(position);
        holder.tvTitle.setText(courseEntity.getTitle());
        holder.tvDate.setText(String.format("Deadline %s", courseEntity.getDeadline()));
        holder.tvDescription.setText(courseEntity.getDeskription());
        // set click itemview intent to detail course
        holder.itemView.setOnClickListener(v->{
            Intent intent= new Intent(activity, DetailCourseActivity.class);
            // putExtra with courseId
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, courseEntity.getCourseId());
            activity.startActivity(intent);

        });
        // set click share to call interface BookmarkFragmnetCallback
        holder.imgShare.setOnClickListener(v->callback.onShareClick(course.get(holder.getAdapterPosition())));
        Glide.with(holder.itemView.getContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                .into(holder.imgPoster);

    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_item_title);
            tvDate= itemView.findViewById(R.id.tv_item_date);
            tvDescription= itemView.findViewById(R.id.tv_item_description);
            imgShare= itemView.findViewById(R.id.img_share);
            imgPoster= itemView.findViewById(R.id.gambar_bookmark);

        }
    }
}
