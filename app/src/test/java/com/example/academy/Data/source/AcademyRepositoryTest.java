package com.example.academy.Data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.academy.Data.source.local.LocalRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.CourseWithModule;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.ValueObject.Resource;
import com.example.academy.utils.FakeDataDummy;
import com.example.academy.utils.InstantAppExecutors;
import com.example.academy.utils.LiveDataTestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();

    private RemoteRepository remote =  Mockito.mock(RemoteRepository.class);
    // call  local repository
    private LocalRepository local= Mockito.mock(LocalRepository.class);
    private InstantAppExecutors instantAppExecutors= Mockito.mock(InstantAppExecutors.class);
    private FakeAcademyRepository academyRepository= new FakeAcademyRepository(local, remote, instantAppExecutors);

    private ArrayList<CourseResponse> courseResponses= FakeDataDummy.generateRemoteDummyCourse();
    private String courseId= courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses= FakeDataDummy.generateRemoteDummyModules(courseId);
    private String moduleId= moduleResponses.get(0).getModuleId();
    private ContentResponse content= FakeDataDummy.generateRemoteDummyContent(moduleId);

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void getAllCourses(){
        MutableLiveData<List<CourseEntity>> dummyCourse= new MutableLiveData<>();
        // set mutablelive data use  fake dummy data
        dummyCourse.setValue(FakeDataDummy.generateDummy());
        // melakukan pengecekan apakah lokal sama dengan hasil dari mutablelive data
        when(local.getAllCourses()).thenReturn(dummyCourse);
        // melakukan pengambilan data all course from academy repository
        Resource<List<CourseEntity>> result= LiveDataTestUtils.getValue(academyRepository.getAllCourse());
        verify(local).getAllModuleByCourse(courseId);

        // memastikan data tidak kosong
        assertNotNull(result);
        // memastikan data memiliki size sesuai dengan yang diharapkan
        assertEquals(courseResponses.size(), result.data.size());

    }

    @Test
    public void getAllModuleByCourse(){
       MutableLiveData<List<ModuleEntity>> dummyModule= new MutableLiveData<>();
       dummyModule.setValue(FakeDataDummy.generateDummyModules(courseId));
       when(local.getAllModuleByCourse(courseId)).thenReturn(dummyModule);
       Resource<List<ModuleEntity>> result= LiveDataTestUtils.getValue(academyRepository.getAllModuleByCourse(courseId));
       verify(local).getAllModuleByCourse(courseId);

        assertNotNull(result);
        assertEquals(moduleResponses.size(), result.data.size());


    }

    @Test
    public void getBookmarkeedCourses(){
        MutableLiveData<List<CourseEntity>> dummyCourse= new MutableLiveData<>();
        dummyCourse.setValue(FakeDataDummy.generateDummy());
        when(local.getBookmarkedCourses()).thenReturn(dummyCourse);
        Resource<List<CourseEntity>> result= LiveDataTestUtils.getValue(academyRepository.getBookmarkedCourses());
        verify(local).getBookmarkedCourses();

        assertNotNull(result);
        assertEquals(5, result.data.size());

    }

    @Test
    public void getContent(){
      MutableLiveData<ModuleEntity> dummyEntity= new MutableLiveData<>();
      dummyEntity.setValue(FakeDataDummy.generateDummyModuleWithContent(moduleId));
      when(local.getModuleWithContent(courseId)).thenReturn(dummyEntity);
      Resource<ModuleEntity> result= LiveDataTestUtils.getValue(academyRepository.getContent(courseId));
      verify(local).getModuleWithContent(courseId);

      assertNotNull(result);
      assertNotNull(result.data);
      assertNotNull(result.data.contentEntity);
        assertNotNull(result.data.contentEntity.getContent());
        assertEquals(content.getContent(), result.data.contentEntity.getContent());

    }

    @Test
    public void getCourseWithModules(){
       MutableLiveData<CourseWithModule> dummyEntity= new MutableLiveData<>();
       dummyEntity.setValue(FakeDataDummy.generateDummyCourseWithModules(FakeDataDummy.generateDummy().get(0), false));
       when(local.getCourseWithModule(courseId)).thenReturn(dummyEntity);
       Resource<CourseWithModule> result= LiveDataTestUtils.getValue(academyRepository.getCourseWithModules(courseId));
       verify(local).getCourseWithModule(courseId);


       assertNotNull(result.data);
        assertNotNull(result.data.mCourse.getTitle());
        assertEquals(courseResponses.get(0).getTitle(), result.data.mCourse.getTitle());


    }
    

}