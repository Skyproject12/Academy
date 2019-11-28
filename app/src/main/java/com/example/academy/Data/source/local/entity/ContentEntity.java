package com.example.academy.Data.source.local.entity;

import androidx.room.ColumnInfo;

public class ContentEntity {
    @ColumnInfo(name="content")
    private String mContent;

    public ContentEntity(String mContent) {
        this.mContent = mContent;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }
}
