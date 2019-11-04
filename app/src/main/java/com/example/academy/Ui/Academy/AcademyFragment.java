package com.example.academy.Ui.Academy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.academy.R;
import com.example.academy.Utils.DataDummy;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcademyFragment extends Fragment {

    private View view;
    private RecyclerView rvCouse;
    private ProgressBar progressBar;
    private AcademyAdapter academyAdapter;


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

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            academyAdapter= new AcademyAdapter(getActivity());
            // set value from generatorDummy in Datadummy
            academyAdapter.setListCourse(DataDummy.generateDummy());
            rvCouse.setLayoutManager(new LinearLayoutManager(getContext()));
            rvCouse.setHasFixedSize(true);
            // sen adapter
            rvCouse.setAdapter(academyAdapter);
        }
    }
}
