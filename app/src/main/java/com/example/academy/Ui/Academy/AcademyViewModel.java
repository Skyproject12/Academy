package com.example.academy.Ui.Academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    private AcademyRepository academyRepository;

    // this constructor for viewmodelvactory
    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository= mAcademyRepository;
    }

    // get data use view model
    public LiveData<List<CourseEntity>> getCourse(){
        // get data from DataDummy
        return academyRepository.getAllCourse();
    }

}
