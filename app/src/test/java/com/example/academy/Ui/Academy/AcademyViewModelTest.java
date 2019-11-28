package com.example.academy.Ui.Academy;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.ValueObject.Resource;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();

    private AcademyViewModel academyViewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private String USERNAME="Dicoding";

    @Before
    public void setUp() {
        academyViewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses() {
        Resource<List<CourseEntity>> resource= Resource.success(FakeDataDummy.generateDummy());
        MutableLiveData<Resource<List<CourseEntity>>> dummyCourses= new MutableLiveData<>();
        dummyCourses.setValue(resource);
        when(academyRepository.getAllCourse()).thenReturn(dummyCourses);
        Observer<Resource<List<CourseEntity>>> observer= mock(Observer.class);
        academyViewModel.setUsername(USERNAME);
        academyViewModel.course.observeForever(observer);
        verify(observer).onChanged(resource);

    }

}