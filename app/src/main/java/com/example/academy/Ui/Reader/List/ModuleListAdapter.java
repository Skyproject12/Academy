package com.example.academy.Ui.Reader.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.R;

import java.util.ArrayList;
import java.util.List;

// make custom recyclerview where holder is MovieViewHolderHide, MovieViewHolder

public class ModuleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final MyAdapterClickListener listner;
    private List<ModuleEntity> moduleEntities = new ArrayList<>();

    public ModuleListAdapter(MyAdapterClickListener listner) {
        this.listner = listner;
    }

    public void setModuleEntities(List<ModuleEntity> moduleEntities) {
        if (moduleEntities == null) return;
        this.moduleEntities.clear();
        this.moduleEntities.addAll(moduleEntities);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ModuleViewHolderHide(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list_custom_disable, parent, false));
        } else {
            return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list_custom, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModuleEntity moduleEntity = moduleEntities.get(position);
        if (holder.getItemViewType() == 0) {
            //custom Viewholder
            ModuleViewHolderHide moduleViewHolderHide = (ModuleViewHolderHide) holder;
            moduleViewHolderHide.bind(moduleEntity.getTitle());

        } else {
            // make viewholder
            ModuleViewHolder moduleViewHolder = (ModuleViewHolder) holder;
            moduleViewHolder.bind(moduleEntity.getTitle());
            moduleViewHolder.itemView.setOnClickListener(v -> {
                // set onclick in itemview
                listner.onItemCliked(holder.getAdapterPosition(), moduleEntities.get(holder.getAdapterPosition()).getModuleId());
            });
        }
    }

    @Override
    public int getItemCount() {
        return moduleEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        int modulePosition = moduleEntities.get(position).getPosition();
        if (modulePosition == 0) return 1;
        else if (moduleEntities.get(modulePosition - 1).isRead()) return 1;
        else return 0;

    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder {
        final TextView textTitle;
        final TextView textLastSeen;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);
            textLastSeen = itemView.findViewById(R.id.text_last_seen);
        }

        void bind(String title) {
            textTitle.setText(title);
        }
    }

    class ModuleViewHolderHide extends RecyclerView.ViewHolder {
        final TextView textTitle;

        ModuleViewHolderHide(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);

        }

        void bind(String title) {
            textTitle.setText(title);

        }
    }
}

interface MyAdapterClickListener {
    void onItemCliked(int position, String moduleId);
}
