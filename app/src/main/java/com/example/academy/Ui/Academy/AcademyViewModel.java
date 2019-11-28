package com.example.academy.Ui.Academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Utils.DataDummy;
import com.example.academy.ValueObject.Resource;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    private AcademyRepository academyRepository;
    // set mutablelive data
    private MutableLiveData<String> mLogin= new MutableLiveData<>();

    public AcademyViewModel(AcademyRepository academyRepository){
        this.academyRepository= academyRepository;

    }

    // get Course
    LiveData<Resource<List<CourseEntity>>> course= Transformations.switchMap(mLogin, data->academyRepository.getAllCourse());

    // set username
    void setUsername(String username){
        mLogin.setValue(username);
    }

}
