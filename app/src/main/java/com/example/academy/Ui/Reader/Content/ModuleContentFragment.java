package com.example.academy.Ui.Reader.Content;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.R;
import com.example.academy.Ui.Reader.CourseReaderViewModel;
import com.example.academy.viewmodel.viewModelVactory;

public class ModuleContentFragment extends Fragment {

    public  static final String TAG = "ModuleContentFragment";
    private WebView webView;
    private ProgressBar progressBar;
    private CourseReaderViewModel courseReaderViewModel;

    public static ModuleContentFragment newInstance(){
        return new ModuleContentFragment();
    }

    public ModuleContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView= view.findViewById(R.id.web_view);
        progressBar= view.findViewById(R.id.progress_bar);
    }

    // set content into modul use object ContentEntity
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // if this activity not null
        if(getActivity()!=null){
            // initial viewmodel
            courseReaderViewModel= obtainViewModel(getActivity());
            // call courseEntity from module entity
           progressBar.setVisibility(View.VISIBLE);
           // set modul entity
           courseReaderViewModel.getSlectedModule().observe(this, moduleEntity -> {
               if(moduleEntity!=null){
                   // set data from viewmodel
                   progressBar.setVisibility(View.GONE);
                   populateWebView(moduleEntity);
               }
           });
            // set format of webview
        }
    }

    // set webview
    private void populateWebView(ModuleEntity moduleEntity){
        // load webview
        webView.loadData(moduleEntity.contentEntity.getmContent(), "text/html", "UTF-8");
    }
    private static CourseReaderViewModel obtainViewModel (FragmentActivity activity){
        viewModelVactory factory= viewModelVactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel.class);

    }

}
