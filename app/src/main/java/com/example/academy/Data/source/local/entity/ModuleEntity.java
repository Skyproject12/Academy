package com.example.academy.Data.source.local.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "moduleentities",
    primaryKeys = {"moduleId","courseId"},
    foreignKeys= @ForeignKey(entity = CourseEntity.class,
    parentColumns = "courseId",
    childColumns = "courseId"),
    indices={@Index(value = "moduleId"),
    @Index(value = "courseId")})

public class ModuleEntity {
    @Embedded
    // call content entity in module
    public ContentEntity contentEntity;

    @NonNull
    @ColumnInfo(name="moduleId")
    private String mModuleId;

    @NonNull
    @ColumnInfo(name="courseId")
    private String mCourseId;

    @NonNull
    @ColumnInfo(name="title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name="position")
    private Integer mPosition;

    @ColumnInfo(name = "read")
    // use status read
    private boolean mRead = false;

    public ModuleEntity(String mModuleId, String mCourseId, String mTitle, Integer mPosition, Boolean read) {
        this.mModuleId = mModuleId;
        this.mCourseId = mCourseId;
        this.mTitle = mTitle;
        this.mPosition = mPosition;
        if (read != null) {
            this.mRead = read;
        }
    }

    public String getModuleId() {
        return mModuleId;
    }

    public void setModuleId(String mModuleId) {
        this.mModuleId = mModuleId;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Integer getPosition() {
        return mPosition;
    }

    public void setPosition(Integer mPosition) {
        this.mPosition = mPosition;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean mRead) {
        this.mRead = mRead;
    }
}
