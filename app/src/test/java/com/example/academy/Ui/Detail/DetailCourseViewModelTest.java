package com.example.academy.Ui.Detail;


import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailCourseViewModelTest {

    private DetailCourseViewModel detailCourseViewModel;
    // mengambil isi data dummyCourse di index 0
    private CourseEntity courseEntity = FakeDataDummy.generateDummy().get(0);
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    // mengambil value courseId
    private String courseId = courseEntity.getCourseId();

    @Before
    public void setUp() {
        detailCourseViewModel = new DetailCourseViewModel(academyRepository);
        detailCourseViewModel.setCourseId(courseId);

    }

    @Test
    public void getCourse() {
        // membuat academy repository mengkases FakeDummy data tampa harus mengakses json
        when(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntity);
        // mengambil data dari view model
        CourseEntity course = detailCourseViewModel.getCourse();
        verify(academyRepository).getCourseWithModules(courseId);
        // memastikan data tidak kosong saat dijalankan
        assertNotNull(course);
        // get course id
        String courseId = courseEntity.getCourseId();
        assertNotNull(courseId);
        // check result , expectation
        assertEquals(courseEntity.getCourseId(), courseId);
    }

    @Test
    public void getModul() {
        when(academyRepository.getAllModuleByCourse(courseId)).thenReturn(FakeDataDummy.generateDummyModules(courseId));
        List<ModuleEntity> moduleEntities = detailCourseViewModel.getModules();
        verify(academyRepository).getAllModuleByCourse(courseId);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }

}