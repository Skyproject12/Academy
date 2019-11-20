package com.example.academy.Ui.Reader.Content;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.R;
import com.example.academy.Ui.Reader.CourseReaderViewModel;

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
            courseReaderViewModel= ViewModelProviders.of(getActivity()).get(CourseReaderViewModel.class);
            // call courseEntity from module entity
            ModuleEntity moduleEntity= courseReaderViewModel.getSlectedModule();
            // set format of webview
            //ContentEntity entity= new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
            // set moduleentity
            populateWebView(moduleEntity);
        }
    }

    // set webview
    private void populateWebView(ModuleEntity moduleEntity){
        // load webview
        webView.loadData(moduleEntity.contentEntity.getmContent(), "text/html", "UTF-8");
    }

}
