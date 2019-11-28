package com.example.academy.Ui.Reader.Content;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.R;
import com.example.academy.Ui.Reader.CourseReaderViewModel;
import com.example.academy.viewmodel.viewModelVactory;

public class ModuleContentFragment extends Fragment {

    public static final String TAG = "ModuleContentFragment";
    private WebView webView;
    private ProgressBar progressBar;
    private CourseReaderViewModel courseReaderViewModel;
    private Button btnNext;
    private Button btnPrev;

    public static ModuleContentFragment newInstance() {
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
        webView = view.findViewById(R.id.web_view);
        progressBar = view.findViewById(R.id.progress_bar);
        btnNext= view.findViewById(R.id.btn_next);
        btnPrev= view.findViewById(R.id.btn_prev);

    }

    // set content into modul use object ContentEntity
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // if this activity not null
        if (getActivity() != null) {
            // initial viewmodel
            courseReaderViewModel = obtainViewModel(getActivity());
            // call courseEntity from module entity
            progressBar.setVisibility(View.VISIBLE);
            // set modul entity
            courseReaderViewModel.selectedContent.observe(this, moduleEntity->{
                if(moduleEntity!=null){
                    switch (moduleEntity.status){
                        case Loading:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if(moduleEntity.data!=null){
                                // set enabliting button
                                setButtonNextPrevState(moduleEntity.data);
                                progressBar.setVisibility(View.GONE);
                                if(!moduleEntity.data.isRead()){
                                    courseReaderViewModel.readContent(moduleEntity.data);
                                }
                                if(moduleEntity.data.contentEntity!=null){
                                    populateWebView(moduleEntity.data.contentEntity);
                                }
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });
            // set onclick next page from viewmodel
            btnNext.setOnClickListener(v-> courseReaderViewModel.setNextPage());
            btnPrev.setOnClickListener(v-> courseReaderViewModel.setPrevPage());
        }
    }

    // set webview
    private void populateWebView(ContentEntity content) {
        // load webview
        webView.loadData(content.getContent(), "text/html", "UTF-8");
    }

    private void setButtonNextPrevState(ModuleEntity module){
        // mengecek actity sebelumnya null atau tidak
        if(getActivity()!=null){
            btnPrev.setEnabled(false);
            btnNext.setEnabled(true);

        }
        // mengecek apakah size -1
        else if(module.getPosition()== courseReaderViewModel.getModuleSize()-1){
            btnPrev.setEnabled(true);
            btnNext.setEnabled(false);

        }
        // selain dari itu maka button true terus
        else{
            btnPrev.setEnabled(true);
            btnNext.setEnabled(true);

        }
    }

    private static CourseReaderViewModel obtainViewModel(FragmentActivity activity) {
        viewModelVactory factory = viewModelVactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel.class);

    }

}
