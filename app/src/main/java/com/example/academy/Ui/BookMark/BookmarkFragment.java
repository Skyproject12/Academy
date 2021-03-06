package com.example.academy.Ui.BookMark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.Data.CourseEntity;
import com.example.academy.Utils.DataDummy;

import java.util.ArrayList;
import java.util.List;


public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback {

    View view;
    private BookmarkAdapter adapter;
    private RecyclerView rvBookmark;
    private ProgressBar progressBar;

    private BookmarkViewModel bookmarkViewModel;
    private ArrayList<CourseEntity> course;

    public BookmarkFragment(){
        // create costructor
    }

    public static Fragment newInstance(){
        return new BookmarkFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_bookmark, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBookmark= view.findViewById(R.id.rv_bookmark);
        progressBar= view.findViewById(R.id.progress_bar);

    }

    @Override
    // call interface to share button to another application
    public void onShareClick(CourseEntity courseEntity) {
        if(getActivity()!= null){
            String mimeType="text/plain";
            // make share button
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang")
                    .setText(String.format("Segera daftar kelas %s di dicoding.com", courseEntity.getTitle()))
                    .startChooser();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            // call viewmodel
            bookmarkViewModel= ViewModelProviders.of(this).get(BookmarkViewModel.class);
            // set data array list from viewmodel
            course= bookmarkViewModel.getBookmarks();

            // set recyclervview
            adapter= new BookmarkAdapter(getActivity(), this);
            // set bootmark course use class Datadummy
            adapter.setCourse(course);
            rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            rvBookmark.setHasFixedSize(true);
            // set adapter
            rvBookmark.setAdapter(adapter);
        }
    }
}
