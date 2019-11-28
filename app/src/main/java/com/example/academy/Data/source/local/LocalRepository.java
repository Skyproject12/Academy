package com.example.academy.Data.source.local;

import androidx.lifecycle.LiveData;

import com.example.academy.Data.source.local.Dao.AcademyDao;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.CourseWithModule;
import com.example.academy.Data.source.local.entity.ModuleEntity;

import java.util.List;

public class LocalRepository {
    private final AcademyDao academyDao;

    private LocalRepository(AcademyDao academyDao){
        this.academyDao= academyDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(AcademyDao academyDao){
        if(INSTANCE==null){
            INSTANCE= new LocalRepository(academyDao);
        }
        return INSTANCE;
    }

    // get all data from class dao
    public LiveData<List<CourseEntity>> getAllCourses(){
        return academyDao.getCourse();
    }
    public LiveData<List<CourseEntity>> getBookmarkedCourses(){
        return academyDao.getBookmarkedCourse();
    }

    public LiveData<CourseWithModule> getCourseWithModule(String courseId){
        return academyDao.getCourseWithModuleById(courseId);
    }

    public LiveData<List<ModuleEntity>> getAllModuleByCourse(String courseId){
        return academyDao.getModuleByCourseId(courseId);

    }
    public void insertCourse(List<CourseEntity> course){
        academyDao.insertCourses(course);

    }
    public void insertModules(List<ModuleEntity> module){
        academyDao.insertModules(module);

    }
    public void setCourseBookmark(CourseEntity couse, boolean newState){
        couse.setBookmarked(newState);
        academyDao.updateCourses(couse);

    }
    public LiveData<ModuleEntity> getModuleWithContent(String moduleId){
        return academyDao.getModuleById(moduleId);

    }
    public void updateContent(String content, String moduleId){
        academyDao.updateModuleByContent(content, moduleId);

    }
    public void setReadModule(ModuleEntity module){
        module.setRead(true);
        academyDao.updateModule(module);

    }







}
