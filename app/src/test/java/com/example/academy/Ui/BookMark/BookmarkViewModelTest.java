package com.example.academy.Ui.BookMark;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.ValueObject.Resource;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookmarkViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();

    private BookmarkViewModel bookmarkViewModel;
    private AcademyRepository academyRepository= mock(AcademyRepository.class);
    @Before
    public void setUp(){
        bookmarkViewModel= new BookmarkViewModel(academyRepository);
    }
    @Test
    public void getBookmark(){
        // menambahkan pagelist dalam pengecekan
        MutableLiveData<Resource<PagedList<CourseEntity>>> dummyCourse= new MutableLiveData<>();
        PagedList<CourseEntity> pagedList= mock(PagedList.class);
        dummyCourse.setValue(Resource.success(pagedList));
        // membandingkan antara data hasil select dari bookmarkedCoursePaged dan dummycourse
        when(academyRepository.getBookmarkedCoursePaged()).thenReturn(dummyCourse);
        Observer<Resource<PagedList<CourseEntity>>> observer= mock(Observer.class);
        // melakukan observer voreber untuk dapat mengambil data terbaru
        bookmarkViewModel.getBookmarksPaged().observeForever(observer);
        verify(observer).onChanged(Resource.success(pagedList));

    }

}