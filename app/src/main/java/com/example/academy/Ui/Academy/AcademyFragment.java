package com.example.academy.Ui.Academy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.R;
import com.example.academy.viewmodel.viewModelVactory;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcademyFragment extends Fragment {

    private View view;
    private RecyclerView rvCouse;
    private ProgressBar progressBar;
    private AcademyAdapter academyAdapter;
    private AcademyViewModel academyViewModel;
    private List<CourseEntity> course;


    public AcademyFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        return new AcademyFragment();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_academy, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCouse= view.findViewById(R.id.rv_academy);
        progressBar= view.findViewById(R.id.progress_bar);
        ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            academyViewModel= obtainViewModel(getActivity());
            // initial about viewmodel
            academyViewModel.setUsername("Dicoding");
            // defination viewmodel
            academyViewModel.course.observe(this, course->{
                if(course!=null){
                    switch (course.status){
                        case Loading:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            academyAdapter= new AcademyAdapter(getActivity());
                            progressBar.setVisibility(View.GONE);
                            ArrayList<CourseEntity> list= new ArrayList<>();
                            academyAdapter.setListCourse(course.data);
                            // set adapter terus berubah sesuai dengan request data
                            academyAdapter.notifyDataSetChanged();
                            rvCouse.setLayoutManager(new LinearLayoutManager(getContext()));
                            rvCouse.setHasFixedSize(true);
                            // set adapter
                            rvCouse.setAdapter(academyAdapter);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });
        }
    }

    @NonNull
    private static AcademyViewModel obtainViewModel(FragmentActivity activity){
        // call viewmodel factory
        viewModelVactory factory= viewModelVactory.getInstance(activity.getApplication());
        // call academy view model use viewmodel factory
        return ViewModelProviders.of(activity, factory).get(AcademyViewModel.class);

    }
}
