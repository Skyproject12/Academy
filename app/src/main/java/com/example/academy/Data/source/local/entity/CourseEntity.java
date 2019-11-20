package com.example.academy.Data.source.local.entity;

public class CourseEntity {
    private String courseId;
    private String title;
    private String deskription;
    private String deadline;
    private boolean bookmarked= false;
    private String imagePath;

    public CourseEntity(String courseId, String title, String deskription, String deadline, Boolean bookmarked, String imagePath) {
        this.courseId = courseId;
        this.title = title;
        this.deskription = deskription;
        this.deadline = deadline;
        // check bookmarked null or not
        if(bookmarked!=null) {
            this.bookmarked= bookmarked;
        }
        this.imagePath = imagePath;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskription() {
        return deskription;
    }

    public void setDeskription(String deskription) {
        this.deskription = deskription;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
