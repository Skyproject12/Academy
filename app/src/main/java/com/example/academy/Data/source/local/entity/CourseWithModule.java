package com.example.academy.Data.source.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CourseWithModule {
    // mengambil suatu object  harus menggunakan embeded
    @Embedded
    public CourseEntity mCourse;

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    public List<ModuleEntity> mModule;
}
