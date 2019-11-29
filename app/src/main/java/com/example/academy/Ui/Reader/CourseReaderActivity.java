package com.example.academy.Ui.Reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.R;
import com.example.academy.Ui.Reader.Content.ModuleContentFragment;
import com.example.academy.Ui.Reader.List.ModuleListFragment;
import com.example.academy.ValueObject.Resource;
import com.example.academy.viewmodel.viewModelVactory;

import java.util.List;

// bertugas menampung dari beberapa fragment yang nantinya akan di lihat di bagian daftar modul or kontent dari modul
// module content fragment or modul list fragment

public class CourseReaderActivity extends AppCompatActivity implements CourseReaderCallback {

    public static final String EXTRA_COURSE_ID ="extra_course_id" ;
    private CourseReaderViewModel courseReaderViewModel;
    private boolean isLarge= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reader);
        Bundle bundle= getIntent().getExtras();
//      // check apakah terdapat id frame list
        if(findViewById(R.id.frame_list)!=null){
            // jika ada maka set large true
            isLarge=true;
        }
        courseReaderViewModel= obtainViewModel(this);
        // make two pane layout
        courseReaderViewModel.modules.observe(this, initObserver);
        // set viewmodel

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
        if(!isLarge) {
            // set module content fragment after interface moveTo click
            Fragment fragment = ModuleContentFragment.newInstance();
            // melakukan move fragment sesuai dengan click
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, ModuleContentFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }
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

    // to remove observer in two pane layout
    void removeObserver(){
        courseReaderViewModel.modules.removeObserver(initObserver);

    }
    private void populateFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // checking in two pane layout
        if(!isLarge) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(ModuleListFragment.TAG);
            // melakukan penambahan awal fragment
            if (fragment == null) {
                fragment = ModuleListFragment.newInstance();
                fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG);
                fragmentTransaction.addToBackStack(null);
            }
            // melakukan commit penambahan fragment ke dalam activity dengan id frame container
            fragmentTransaction.commit();
        }
        else{
            // mengecek apakah fragment berasal dari modulelist
            Fragment fragmentList= getSupportFragmentManager().findFragmentByTag(ModuleListFragment.TAG);
            // jika listfragment kosong maka akan di isi dengan fragmentlist
            if(fragmentList==null){
                fragmentList= ModuleListFragment.newInstance();
                fragmentTransaction.add(R.id.frame_list, fragmentList, ModuleListFragment.TAG);

            }
            // mengecek apakah fragment berasal dari moduleContent
            Fragment fragmentContent= getSupportFragmentManager().findFragmentByTag(ModuleContentFragment.TAG);
            if(fragmentContent==null){
                fragmentContent= ModuleContentFragment.newInstance();
                fragmentTransaction.add(R.id.frame_content, fragmentContent, ModuleContentFragment.TAG);

            }
            fragmentTransaction.commit();

        }
    }

    private String getLastReadFromModules(List<ModuleEntity> moduleEntitises){
        String lastReadModule= null;
        for(ModuleEntity moduleEntity: moduleEntitises){
            if(moduleEntity.isRead()){
                lastReadModule= moduleEntity.getModuleId();
                continue;
            }
            break;
        }
        return lastReadModule;
    }

    private final Observer<Resource<List<ModuleEntity>>> initObserver = modules -> {
        if(modules!=null){
            switch (modules.status){
                case Loading:
                    break;
                case SUCCESS:
                    if(modules.data!=null && modules.data.size()>0){
                        ModuleEntity firstModule= modules.data.get(0);
                        boolean isFirstModuleRead= firstModule.isRead();
                        if(!isFirstModuleRead){
                            String firstModuleId= firstModule.getModuleId();
                            courseReaderViewModel.setSelectedModule(firstModuleId);
                        }
                        else{
                            String lastReadModuleId= getLastReadFromModules(modules.data);
                            if(lastReadModuleId!=null){
                                courseReaderViewModel.setSelectedModule(lastReadModuleId);
                            }
                        }
                    }
                    removeObserver();
                    break;
                case ERROR:
                    removeObserver();
                    default:
                        break;
            }
        }
    };

    private static CourseReaderViewModel obtainViewModel(FragmentActivity activity){

        viewModelVactory factory= viewModelVactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel.class);

    }
}
