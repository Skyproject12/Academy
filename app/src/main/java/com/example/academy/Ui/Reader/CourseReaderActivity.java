package com.example.academy.Ui.Reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.academy.R;
import com.example.academy.Ui.Reader.Content.ModuleContentFragment;
import com.example.academy.Ui.Reader.List.ModuleListFragment;

// bertugas menampung dari beberapa fragment yang nantinya akan di lihat di bagian daftar modul or kontent dari modul
// module content fragment or modul list fragment

public class CourseReaderActivity extends AppCompatActivity implements CourseReaderCallback {

    public static final String EXTRA_COURSE_ID ="extra_course_id" ;
    private CourseReaderViewModel courseReaderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reader);
        Bundle bundle= getIntent().getExtras();
        // set viewmodel
        courseReaderViewModel= ViewModelProviders.of(this).get(CourseReaderViewModel.class);

        if(bundle!=null){
            // menangkap id course yang akan di baca
            String courseId= bundle.getString(EXTRA_COURSE_ID);
            // jika id tidak kosong
            if(courseId!=null){
                // set course id from viewmodel
                courseReaderViewModel.setCourseId(courseId);
                // mengeset fragment module list fragment di activity
                populateFragment();
            }
        }
    }

    // panggil interface moveTo dengan position or moduleId dari fragment ModuleListFragment
    @Override
    public void moveTo(int position, String moduleId) {
        // set module content fragment after interface moveTo click
        Fragment fragment= ModuleContentFragment.newInstance();
        // melakukan move fragment sesuai dengan click
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, ModuleContentFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    // set on back press
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()<=1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }
    private void populateFragment(){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        Fragment fragment= getSupportFragmentManager().findFragmentByTag(ModuleListFragment.TAG);
        // melakukan penambahan awal fragment
        if(fragment==null){
            fragment= ModuleListFragment.newInstance();
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG);
            fragmentTransaction.addToBackStack(null);
        }
        // melakukan commit penambahan fragment ke dalam activity dengan id frame container
        fragmentTransaction.commit();
    }
}
