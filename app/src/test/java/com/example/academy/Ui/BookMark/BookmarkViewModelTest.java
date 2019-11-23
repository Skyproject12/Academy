package com.example.academy.Ui.BookMark;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
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
        ArrayList<CourseEntity> dummyCourses= FakeDataDummy.generateDummy();
        MutableLiveData<List<CourseEntity>> courses= new MutableLiveData<>();
        courses.setValue(dummyCourses);
        when(academyRepository.getBookmarkedCourses()).thenReturn(courses);
        Observer<List<CourseEntity>> observer= mock(Observer.class);
        bookmarkViewModel.getBookmarks().observeForever(observer);

        verify(observer).onChanged(dummyCourses);
    }

}