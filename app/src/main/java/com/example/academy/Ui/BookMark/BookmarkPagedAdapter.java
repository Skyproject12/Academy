package com.example.academy.Ui.BookMark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.R;
import com.example.academy.Ui.Detail.DetailCourseActivity;

public class BookmarkPagedAdapter extends PagedListAdapter<CourseEntity, BookmarkPagedAdapter.ViewHolder> {
    private BookmarkFragmentCallback callback;

    BookmarkPagedAdapter(BookmarkFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // menampung hasil data dari recyclerview tanpa menggunakan arraylist
        final CourseEntity bookmark = getItem(position);
        if (bookmark != null) {
            holder.tvTitle.setText(bookmark.getTitle());
            holder.tvDate.setText(String.format("Deadline %s", bookmark.getDeadline()));
            holder.tvDescription.setText(bookmark.getDeskription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, DetailCourseActivity.class);
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, bookmark.getCourseId());

                    context.startActivity(intent);

                }
            });
            holder.imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CourseEntity courseEntity = new CourseEntity(
                            bookmark.getCourseId(),
                            bookmark.getTitle(),
                            bookmark.getDeskription(),
                            bookmark.getDeadline(),
                            false,
                            bookmark.getImagePath()
                    );
                    callback.onShareClick(courseEntity);
                }
            });
            Glide.with(holder.itemView.getContext())
                    .load(bookmark.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                    .into(holder.imgPoster);

        }
    }

    // melakukan pengecekan data baru atau data lama
    private static DiffUtil.ItemCallback<CourseEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<CourseEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull CourseEntity oldItem, @NonNull CourseEntity newItem) {
            return oldItem.getCourseId().equals(newItem.getCourseId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CourseEntity oldItem, @NonNull CourseEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    CourseEntity getItemById(int swipePosition) {
        return getItem(swipePosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
            imgPoster = itemView.findViewById(R.id.gambar_bookmark);

        }
    }
}
