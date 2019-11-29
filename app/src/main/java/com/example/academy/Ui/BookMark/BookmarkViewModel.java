package com.example.academy.Ui.BookMark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Utils.DataDummy;
import com.example.academy.ValueObject.Resource;

import java.util.ArrayList;
import java.util.List;

public class BookmarkViewModel extends ViewModel {

    // this constructor for viewmodelvactory

    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        // call academy repository
        this.academyRepository= mAcademyRepository;
    }

    public LiveData<Resource<List<CourseEntity>>> getBookmarks(){
        return academyRepository.getBookmarkedCourses();

    }

    public LiveData<Resource<PagedList<CourseEntity>>> getBookmarksPaged(){
        return academyRepository.getBookmarkedCoursePaged();

    }

    void setBookmark(CourseEntity courseEntity){
        // mengeset isBookmarked !courseEntity.isBookmarked
        // ketika dieksekusi lagi maka akan melakukan set lagi
        final boolean newState= !courseEntity.isBookmarked();
        academyRepository.setCourseBookmark(courseEntity, newState);

    }

}
