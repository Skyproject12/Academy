package com.example.academy.Ui.Reader;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.ValueObject.Resource;
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
    private String idModule= moduleEntiti.get(0).getModuleId();
    @Before
    public void setUp(){
        courseReaderViewModel= new CourseReaderViewModel(academyRepository);
        // set id from course entity id
        courseReaderViewModel.setCourseId(courseId);

    }
    @Test
    public void getModul(){
       MutableLiveData<Resource<List<ModuleEntity>>> moduleEntitises= new MutableLiveData<>();
       Resource<List<ModuleEntity>> resource= Resource.success(moduleEntiti);
       moduleEntitises.setValue(resource);
       when(academyRepository.getAllModuleByCourse(courseId)).thenReturn(moduleEntitises);


       Observer<Resource<List<ModuleEntity>>> observer= mock(Observer.class);
       courseReaderViewModel.modules.observeForever(observer);
       verify(observer).onChanged(resource);


    }
    @Test
    public void getSelectedModule(){
        MutableLiveData<Resource<ModuleEntity>> moduleEntity= new MutableLiveData<>();
        ModuleEntity dummyModule= moduleEntiti.get(0);
        String content="sumendra";
        dummyModule.contentEntity= new ContentEntity(content);
        Resource<ModuleEntity> resource= Resource.success(dummyModule);
        moduleEntity.setValue(resource);
        when(academyRepository.getContent(idModule)).thenReturn(moduleEntity);
        courseReaderViewModel.setSelectedModule(idModule);
        Observer<Resource<ModuleEntity>> observer= mock(Observer.class);
        courseReaderViewModel.selectedContent.observeForever(observer);
        verify(observer).onChanged(resource);

    }

}