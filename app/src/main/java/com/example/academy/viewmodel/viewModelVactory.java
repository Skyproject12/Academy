package com.example.academy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Ui.Academy.AcademyViewModel;
import com.example.academy.Ui.BookMark.BookmarkViewModel;
import com.example.academy.Ui.Detail.DetailCourseViewModel;
import com.example.academy.Ui.Reader.CourseReaderViewModel;
import com.example.academy.di.Injection;

public class viewModelVactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile viewModelVactory INSTANCE;
    AcademyRepository  mAcademyRepository;

    // viewmodelvactory defnition academyrepository
    private viewModelVactory(AcademyRepository academyRepository){
        mAcademyRepository= academyRepository;
    }
    public static viewModelVactory getInstance(Application application){
        if(INSTANCE==null){
            // definition injection in viewmodelfactory
            synchronized (viewModelVactory.class){
                if(INSTANCE==null){
                    INSTANCE= new viewModelVactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // mengeset repository kepada setiap viewmodel
        if(modelClass.isAssignableFrom(AcademyViewModel.class)){
            //noinspection unchecked
            return (T) new AcademyViewModel(mAcademyRepository);
        }
        else if(modelClass.isAssignableFrom(DetailCourseViewModel.class)){
            //noinspection unchecke
            return (T) new DetailCourseViewModel(mAcademyRepository);
        }
        else if(modelClass.isAssignableFrom(BookmarkViewModel.class)){
            //noinspection unchecke
            return (T) new BookmarkViewModel(mAcademyRepository);
        }
        else if(modelClass.isAssignableFrom(CourseReaderViewModel.class)){
            return (T) new CourseReaderViewModel(mAcademyRepository);
        }
        throw  new IllegalArgumentException("Unknown ViewModel class:"+modelClass.getName());
    }
}
