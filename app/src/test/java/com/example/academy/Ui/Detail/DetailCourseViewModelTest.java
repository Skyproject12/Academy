package com.example.academy.Ui.Detail;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailCourseViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();

    private DetailCourseViewModel detailCourseViewModel;
    // mengambil isi data dummyCourse di index 0
    private CourseEntity courseEnti = FakeDataDummy.generateDummy().get(0);
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    // mengambil value courseId
    private String courseId = courseEnti.getCourseId();
    private ArrayList<ModuleEntity> dummyModule= FakeDataDummy.generateDummyModules(courseId);


    @Before
    public void setUp() {
        detailCourseViewModel = new DetailCourseViewModel(academyRepository);
        detailCourseViewModel.setCourseId(courseId);

    }

    @Test
    public void getCourse() {
        MutableLiveData<CourseEntity> courseEntity= new MutableLiveData<>();
        courseEntity.setValue(courseEnti);
        when(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntity);
        Observer<CourseEntity> observer= mock(Observer.class);
        detailCourseViewModel.getCourse().observeForever(observer);
        verify(observer).onChanged(courseEnti);
    }

    @Test
    public void getModul() {
        MutableLiveData<List<ModuleEntity>> modulentities= new MutableLiveData<>();
        modulentities.setValue(dummyModule);
        when(academyRepository.getAllModuleByCourse(courseId)).thenReturn(modulentities);
        Observer<List<ModuleEntity>> observer= mock(Observer.class);
        detailCourseViewModel.getModules().observeForever(observer);
        verify(observer).onChanged(dummyModule);
    }

}