package com.example.academy.Ui.Detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Utils.DataDummy;

import java.util.List;

public class DetailCourseViewModel extends ViewModel {

    private AcademyRepository academyRepository;
     private CourseEntity mCourse;
     private String courseId;

    // this constructor for viewmodelvactory
    public DetailCourseViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository= mAcademyRepository;

    }

    public LiveData<CourseEntity> getCourse(){
        return academyRepository.getCourseWithModules(courseId);

    }


     // getModule
     public LiveData<List<ModuleEntity>> getModules(){
        return academyRepository.getAllModuleByCourse(courseId);
     }

     public String getCourseId() {
        return courseId;
     }

     public void setCourseId(String courseId) {
       this.courseId= courseId;

     }
}
