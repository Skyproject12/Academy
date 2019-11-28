package com.example.academy.Ui.BookMark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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

}
