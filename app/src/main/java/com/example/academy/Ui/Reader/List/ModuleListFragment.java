package com.example.academy.Ui.Reader.List;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.academy.R;
import com.example.academy.Data.ModuleEntity;
import com.example.academy.Ui.Reader.CourseReaderActivity;
import com.example.academy.Ui.Reader.CourseReaderCallback;
import com.example.academy.Ui.Reader.CourseReaderViewModel;
import com.example.academy.Utils.DataDummy;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleListFragment extends Fragment implements MyAdapterClickListener {

    public static final String TAG = "ModuleListFragment";
    private ModuleListAdapter moduleListAdapter;
    private CourseReaderCallback courseReaderCallback;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CourseReaderViewModel courseReaderViewModel;

    public static ModuleListFragment newInstance(){
        return new ModuleListFragment();
    }

    public ModuleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.rv_module);
        progressBar= view.findViewById(R.id.progress_bar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            // initial viewmodel
            courseReaderViewModel= ViewModelProviders.of(getActivity()).get(CourseReaderViewModel.class);
            moduleListAdapter= new ModuleListAdapter(this);
            // set module data from viewmodel
            populateRecyclerView(courseReaderViewModel.getModules());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        courseReaderCallback= ((CourseReaderActivity)context);
    }

    @Override
    public void onItemCliked(int position, String moduleId) {
        // set position or moduleId if recyclerview click
        courseReaderCallback.moveTo(position, moduleId);
        // if click recycler view can run interface move to or send position into activity parent
        // mengeset data berdasarkan modul yang di klik
        courseReaderViewModel.setSelectedModule(moduleId);
    }

    // set module list
    private void populateRecyclerView(List<ModuleEntity> moduleEntities){
        progressBar.setVisibility(View.GONE);
        moduleListAdapter.setModuleEntities(moduleEntities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moduleListAdapter);
        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
