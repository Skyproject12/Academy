package com.example.academy.Ui.BookMark;

import com.example.academy.Data.CourseEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookmarkViewModelTest {

    private BookmarkViewModel bookmarkViewModel;
    @Before
    public void setUp(){
        bookmarkViewModel= new BookmarkViewModel();
    }
    @Test
    public void getBookmark(){
        List<CourseEntity> courseEntities= bookmarkViewModel.getBookmarks();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());
    }

}