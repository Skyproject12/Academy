package com.example.academy.Data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule= new InstantTaskExecutorRule();
    private RemoteRepository remote =  Mockito.mock(RemoteRepository.class);
    private FakeAcademyRepository academyRepository= new FakeAcademyRepository(remote);
    // get data from dummy data
    private ArrayList<CourseResponse> courseResponses= FakeDataDummy.generateRemoteDummyCourse();
    private String courseId= courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses= FakeDataDummy.generateRemoteDummyModules(courseId);
    private String moduleId= moduleResponses.get(0).getModuleId();
    private ContentResponse content= FakeDataDummy.generateRemoteDummyContent(moduleId);


    @Test
    public void getAllCourses(){
        when(remote.getAllCourses()).thenReturn(courseResponses);
        // get data from result academy repository
        List<CourseEntity> courseEntities= academyRepository.getAllCourse();
        verify(remote).getAllCourses();
        assertNotNull(courseEntities);
        // check jumlah size apkah sesuai
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getAllModulesByCourse(){
        when(remote.getModule(courseId)).thenReturn(moduleResponses);
        List<ModuleEntity> moduleEntities= academyRepository.getAllModuleByCourse(courseId);
        verify(remote).getModule(courseId);
        assertNotNull(moduleEntities);
        assertEquals(moduleResponses.size(), moduleEntities.size());

    }

    @Test
    public void getBookmarkedCourses(){
        when(remote.getAllCourses()).thenReturn(courseResponses);
        List<CourseEntity> courseEntities= academyRepository.getBookmarkedCourses();
        verify(remote).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getContent(){
        when(remote.getModule(courseId)).thenReturn(moduleResponses);
        when(remote.getContent(moduleId)).thenReturn(content);
        ModuleEntity resultModule= academyRepository.getContent(courseId, moduleId);
        verify(remote).getContent(moduleId);
        assertNotNull(resultModule);
        assertEquals(content.getContent(), resultModule.contentEntity.getmContent());

    }

    @Test
    public void getCourseWithModules(){
        when(remote.getAllCourses()).thenReturn(courseResponses);
        CourseEntity resultCourse= academyRepository.getCourseWithModules(courseId);
        verify(remote).getAllCourses();
        assertNotNull(resultCourse);
        assertEquals(courseResponses.get(0).getTitle(), resultCourse.getTitle());
    }
    

}