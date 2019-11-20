package com.example.academy.Data.source.local.entity;

import com.example.academy.Data.source.local.entity.ContentEntity;

public class ModuleEntity {
    // call content entity in module
    public ContentEntity contentEntity;
    private String mModuleId;
    private String mCourseId;
    private String mTitle;
    private Integer mPosition;
    // use status read
    private boolean mRead= false;

    public ModuleEntity(String mModuleId, String mCourseId, String mTitle, Integer mPosition, Boolean read) {
        this.mModuleId = mModuleId;
        this.mCourseId = mCourseId;
        this.mTitle = mTitle;
        this.mPosition = mPosition;
        if(read!=null){
            this.mRead= read;
        }
    }

    public ContentEntity getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(ContentEntity contentEntity) {
        this.contentEntity = contentEntity;
    }

    public String getmModuleId() {
        return mModuleId;
    }

    public void setmModuleId(String mModuleId) {
        this.mModuleId = mModuleId;
    }

    public String getmCourseId() {
        return mCourseId;
    }

    public void setmCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Integer getmPosition() {
        return mPosition;
    }

    public void setmPosition(Integer mPosition) {
        this.mPosition = mPosition;
    }

    public boolean ismRead() {
        return mRead;
    }

    public void setmRead(boolean mRead) {
        this.mRead = mRead;
    }
}
