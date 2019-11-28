package com.example.academy.Data.source;

import androidx.lifecycle.LiveData;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.CourseWithModule;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.ValueObject.Resource;

import java.util.List;

public interface AcademyDataSource {

    // bungkus semua data use livedata

    LiveData<Resource<List<CourseEntity>>> getAllCourse();
    // get corse with id (this interface to menyambungkan repository yang dibuat)
    LiveData<Resource<CourseWithModule>> getCourseWithModules(String courseId);
    // get all module (interface untuk menyambungkan repository yang di buat )
    LiveData<Resource<List<ModuleEntity>>> getAllModuleByCourse(String courseId);
    // get course for bookmarked menu
    LiveData<Resource<List<CourseEntity>>> getBookmarkedCourses();
    // get content whree courseid and moduleid same from data
    LiveData<Resource<ModuleEntity>> getContent(String moduleId);

    void setCourseBookmark(CourseEntity course, boolean state);
    // berfungsi menambah course ke dalam bookmark

    // berfunsgi memperlihatkan modul yang terakhir dibaca
    void setReadModule(ModuleEntity module);
}
