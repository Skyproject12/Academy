package com.example.academy.Data.source.local.entity;

import androidx.room.ColumnInfo;

public class ContentEntity {
    @ColumnInfo(name="content")
    private String mContent;

    public ContentEntity(String mContent) {
        this.mContent = mContent;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
