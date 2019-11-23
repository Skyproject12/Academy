package com.example.academy.Ui.Reader;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
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

public class CourseReaderViewModelTest {

    @Rule
    // initial awal sebelum menjalankan mock android
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();

    private CourseReaderViewModel courseReaderViewModel;
    // get entity from Fake data dummy
    private CourseEntity courseEntit= FakeDataDummy.generateDummy().get(0);
    // initial repository
    private AcademyRepository academyRepository= mock(AcademyRepository.class);
    // get id course
    private String courseId= courseEntit.getCourseId();
    // save data module entity into arraylist
    private ArrayList<ModuleEntity> moduleEntiti= FakeDataDummy.generateDummyModules(courseId);
    // get id from moduleEntiti
    private String idModule= moduleEntiti.get(0).getmModuleId();
    @Before
    public void setUp(){
        courseReaderViewModel= new CourseReaderViewModel(academyRepository);
        // set id from course entity id
        courseReaderViewModel.setCourseId(courseId);

    }
    @Test
    public void getModul(){
        MutableLiveData<List<ModuleEntity>> module= new MutableLiveData<>();
        module.setValue(moduleEntiti);
        when(academyRepository.getAllModuleByCourse(courseId)).thenReturn(module);
        Observer<List<ModuleEntity>> observer= mock(Observer.class);
        courseReaderViewModel.getModules().observeForever(observer);
        verify(observer).onChanged(moduleEntiti);

    }
    @Test
    public void getSelectedModule(){
        MutableLiveData<ModuleEntity> module= new MutableLiveData<>();
        ModuleEntity dummyModule= moduleEntiti.get(0);
        String content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>";
        dummyModule.contentEntity= new ContentEntity(content);
        module.setValue(dummyModule);

        when(academyRepository.getContent(courseId, idModule)).thenReturn(module);
        courseReaderViewModel.setSelectedModule(idModule);
        Observer<ModuleEntity> observer= mock(Observer.class);
        courseReaderViewModel.getSlectedModule().observeForever(observer);
        verify(observer).onChanged(dummyModule);
    }

}