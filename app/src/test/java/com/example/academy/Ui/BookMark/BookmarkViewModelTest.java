package com.example.academy.Ui.BookMark;


import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookmarkViewModelTest {

    private BookmarkViewModel bookmarkViewModel;
    private AcademyRepository academyRepository= mock(AcademyRepository.class);
    @Before
    public void setUp(){
        bookmarkViewModel= new BookmarkViewModel(academyRepository);
    }
    @Test
    public void getBookmark(){
        when(academyRepository.getBookmarkedCourses()).thenReturn(FakeDataDummy.generateDummy());
        List<CourseEntity> courseEntities= bookmarkViewModel.getBookmarks();
        verify(academyRepository).getBookmarkedCourses();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());
    }

}