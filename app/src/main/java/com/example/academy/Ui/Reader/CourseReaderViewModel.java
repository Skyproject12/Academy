package com.example.academy.Ui.Reader;

import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Utils.DataDummy;

import java.util.ArrayList;
import java.util.List;

public class CourseReaderViewModel extends ViewModel {

    private AcademyRepository academyRepository;
    private String courseId;
    private String moduleId;

    // this constructor for viewmodelvactory
    public CourseReaderViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository= mAcademyRepository;

    }

    // set module
    public List<ModuleEntity> getModules(){
        return academyRepository.getAllModuleByCourse(courseId);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    // if click detail modul
    public ModuleEntity getSlectedModule(){
       return academyRepository.getContent(courseId, moduleId);

    }

    public void setSelectedModule(String moduleId){
        this.moduleId= moduleId;
    }

}
