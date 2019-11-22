package com.example.academy.Data.source;

import androidx.lifecycle.LiveData;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;

import java.util.List;

public interface AcademyDataSource {

    // bungkus semua data use livedata

    LiveData<List<CourseEntity>> getAllCourse();
    // get corse with id (this interface to menyambungkan repository yang dibuat)
    LiveData<CourseEntity> getCourseWithModules(String courseId);
    // get all module (interface untuk menyambungkan repository yang di buat )
    LiveData<List<ModuleEntity>> getAllModuleByCourse(String courseId);
    // get course for bookmarked menu
    LiveData<List<CourseEntity>> getBookmarkedCourses();
    // get content whree courseid and moduleid same from data
    LiveData<ModuleEntity> getContent(String courseId, String moduleId);
}
