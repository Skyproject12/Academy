package com.example.academy.Data.source;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;

import java.util.List;

public interface AcademyDataSource {

    List<CourseEntity> getAllCourse();
    // get corse with id (this interface to menyambungkan repository yang dibuat)
    CourseEntity getCourseWithModules(String courseId);
    // get all module (interface untuk menyambungkan repository yang di buat )
    List<ModuleEntity> getAllModuleByCourse(String courseId);
    // get course for bookmarked menu
    List<CourseEntity> getBookmarkedCourses();
    // get content whree courseid and moduleid same from data
    ModuleEntity getContent(String courseId, String moduleId);
}
