package com.example.academy.Ui.Academy;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();

    private AcademyViewModel academyViewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);

    @Before
    public void setUp() {
        academyViewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses() {
        // mengambil data dari dummy data
        ArrayList<CourseEntity> dummyCourses = FakeDataDummy.generateDummy();
        // membuat mutable list untuk menampung data
        MutableLiveData<List<CourseEntity>> courses = new MutableLiveData<>();
        // set data into mutable list
        courses.setValue(dummyCourses);
        // melakukan pengandaian hasil dari repository sama dengan mutable live data
        when(academyRepository.getAllCourse()).thenReturn(courses);
        Observer<List<CourseEntity>> observer = mock(Observer.class);
        // digunakan untuk melakuakan pengecekan terus menerus, sesuai dengan data
        academyViewModel.getCourse().observeForever(observer);
        // digunakan untuk memastikan apkah terdapat perubahan pada live data
        verify(observer).onChanged(dummyCourses);
    }

}