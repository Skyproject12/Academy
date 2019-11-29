package com.example.academy.Data.source.local.Dao;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.CourseWithModule;
import com.example.academy.Data.source.local.entity.ModuleEntity;

import java.util.List;


@Dao

public interface AcademyDao {

    @WorkerThread
    @Query("SELECT * FROM courseentities")
    LiveData<List<CourseEntity>> getCourse();

    @WorkerThread
    @Query("SELECT * FROM courseentities where bookmarked= 1 ")
    LiveData<List<CourseEntity>> getBookmarkedCourse();

    @Transaction
    @Query("SELECT * FROM courseentities WHERE courseId= :courseId")
    LiveData<CourseWithModule> getCourseWithModuleById(String courseId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertCourses(List<CourseEntity> courses);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateCourses(CourseEntity course);

    @Query("Select * From moduleentities WHERE courseId = :courseId")
    LiveData<List<ModuleEntity>> getModuleByCourseId(String courseId);

    @Query("SELECT * FROM moduleentities WHERE moduleId = :moduleId")
    LiveData<ModuleEntity> getModuleById(String moduleId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertModules(List<ModuleEntity> module);

    @Update
    int updateModule(ModuleEntity module);

    @Query("Update moduleentities set content = :content Where moduleId = :moduleId")
    int updateModuleByContent(String content, String moduleId);

    // select * from course whew id bookmarked=1
    @Query("SELECT * FROM courseentities where bookmarked=1")
    DataSource.Factory<Integer, CourseEntity>getBookmarkedCourseAsPaged();


}
