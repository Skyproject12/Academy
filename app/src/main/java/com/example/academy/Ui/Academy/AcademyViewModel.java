package com.example.academy.Ui.Academy;

import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    // get data use view model
    public List<CourseEntity> getCourse(){
        // get data from DataDummy
        return DataDummy.generateDummy();
    }

}
