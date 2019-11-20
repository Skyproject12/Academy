package com.example.academy.Ui.Detail;

import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Utils.DataDummy;

import java.util.List;

public class DetailCourseViewModel extends ViewModel {

     private CourseEntity mCourse;
     private String courseId;

     public CourseEntity getCourse(){
         for (int i=0; i< DataDummy.generateDummy().size(); i++){
             // melakukan perulangan untuk mengambil nilai dari datadummy course
             CourseEntity courseEntity= DataDummy.generateDummy().get(i);
             if(courseEntity.getCourseId().equals(courseId)){
                 mCourse = courseEntity;
             };
         }
         return mCourse;
     }

     // getModule
     public List<ModuleEntity> getModules(){
         // return data from datadummy modules
         return DataDummy.generateDummyModules(getCourseId());
     }

     public String getCourseId() {
        return courseId;
     }

     public void setCourseId(String courseId) {
        this.courseId = courseId;
     }
}
