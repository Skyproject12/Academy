package com.example.academy.Ui.Academy;

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

public class AcademyViewModelTest {

    private AcademyViewModel academyViewModel;
    private AcademyRepository academyRepository= mock(AcademyRepository.class);

    @Before
    public void setUp(){
        academyViewModel= new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses(){
        // memanipulasi pemanggilan data mengunakan generateDummyData tanpa harus mengakses json
        when(academyRepository.getAllCourse()).thenReturn(FakeDataDummy.generateDummy());
        List<CourseEntity> courseEntities= academyViewModel.getCourse();
        // verify repository
        verify(academyRepository).getAllCourse();
        // memastikan courseEntity dalam academy tidak null
        assertNotNull(courseEntities);
        // membandingkan jumlah file
        assertEquals(5,courseEntities.size());
    }

}