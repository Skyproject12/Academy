package com.example.academy.Data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseResponse implements Parcelable {
    private String id;
    private String title;
    private String description;
    private String date;
    private String imagePath;

    public CourseResponse(String id, String title, String description, String date, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.imagePath = imagePath;
    }

    public CourseResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.imagePath);
    }

    protected CourseResponse(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.imagePath = in.readString();
    }

    public static final Parcelable.Creator<CourseResponse> CREATOR = new Parcelable.Creator<CourseResponse>() {
        @Override
        public CourseResponse createFromParcel(Parcel source) {
            return new CourseResponse(source);
        }

        @Override
        public CourseResponse[] newArray(int size) {
            return new CourseResponse[size];
        }
    };
}
