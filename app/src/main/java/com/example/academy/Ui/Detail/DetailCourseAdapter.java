package com.example.academy.Ui.Detail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.Data.source.local.entity.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ViewHolder> {
    private List<ModuleEntity> mModule= new ArrayList<>();

    public void setModules(List<ModuleEntity> mModul) {
        if(mModul==null)return;
        mModule.addAll(mModul);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mModule.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mModule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle= itemView.findViewById(R.id.text_module_title);
        }

        void bind(String title){
            textTitle.setText(title);
        }

    }
}
