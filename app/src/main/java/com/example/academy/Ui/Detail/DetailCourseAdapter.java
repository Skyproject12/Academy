package com.example.academy.Ui.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.Data.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ViewHolder> {
    private List<ModuleEntity> mModule= new ArrayList<>();

    public DetailCourseAdapter(List<ModuleEntity> mModule) {
        this.mModule = mModule;
    }

    public void setmModule(List<ModuleEntity> mModule) {
        if(mModule==null)return;
        mModule.clear();
        mModule.addAll(mModule);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mModule.get(position).getmTitle());
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
