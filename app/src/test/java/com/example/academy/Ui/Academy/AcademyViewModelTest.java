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
    // defination of repository
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    // set username
    private String USERNAME="Dicoding";

    @Before
    public void setUp() {
        academyViewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses() {
        // get course from dummy data
        Resource<List<CourseEntity>> resource= Resource.success(FakeDataDummy.generateDummy());
        // make mutable
        MutableLiveData<Resource<List<CourseEntity>>> dummyCourses= new MutableLiveData<>();
        // mengeset mutable live data use data resource
        dummyCourses.setValue(resource);
        // melakukan pengecekan
        when(academyRepository.getAllCourse()).thenReturn(dummyCourses);
        Observer<Resource<List<CourseEntity>>> observer= mock(Observer.class);
        academyViewModel.setUsername(USERNAME);
        // melakukan observer secara forever untuk mendapatkan data yang realtime
        academyViewModel.course.observeForever(observer);
        verify(observer).onChanged(resource);

    }

}