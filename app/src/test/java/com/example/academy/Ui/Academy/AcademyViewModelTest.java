package com.example.academy.Ui.Academy;

import com.example.academy.Data.CourseEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AcademyViewModelTest {

    private AcademyViewModel academyViewModel;

    @Before
    public void setUp(){
        academyViewModel= new AcademyViewModel();
    }

    @Test
    public void getCourses(){
        List<CourseEntity> courseEntities= academyViewModel.getCourse();
        // memastikan courseEntity dalam academy tidak null
        assertNotNull(courseEntities);
        // membandingkan jumlah file
        assertEquals(5,courseEntities.size());
    }

}