package com.example.academy.Ui.Reader.List;

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

public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ViewHolder> {

    private final MyAdapterClickListener listner;
    private List<ModuleEntity> moduleEntities= new ArrayList<>();

    public ModuleListAdapter(MyAdapterClickListener listner) {
        this.listner = listner;
    }

    public void setModuleEntities(List<ModuleEntity> moduleEntities) {
        if (moduleEntities==null) return;
        this.moduleEntities.clear();
        this.moduleEntities.addAll(moduleEntities);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list_custom, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModuleEntity moduleEntity= moduleEntities.get(position);
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.bind(moduleEntity.getmTitle());
        viewHolder.itemView.setOnClickListener(v-> {
            // set onclick in itemview
            listner.onItemCliked(viewHolder.getAdapterPosition(), moduleEntities.get(viewHolder.getAdapterPosition()).getmModuleId());
        });
    }

    @Override
    public int getItemCount() {
        return moduleEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textTitle;
        final TextView textLastSeen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle= itemView.findViewById(R.id.text_module_title);
            textLastSeen= itemView.findViewById(R.id.text_last_seen);
        }
        void bind(String title){
            textTitle.setText(title);
        }
    }
}

interface  MyAdapterClickListener {
    void onItemCliked(int position, String moduleId);
}
