package com.example.academy.Data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.utils.FakeDataDummy;
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
    private FakeAcademyRepository academyRepository= new FakeAcademyRepository(remote);
    // get data from dummy data
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
        doAnswer(invocation -> {
            ((RemoteRepository.LoadCoursesCallback) invocation.getArguments()[0]).onAllCoursesReceived(courseResponses);
            return null;

        }).when(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
        List<CourseEntity> result= LiveDataTestUtils.getValue(academyRepository.getAllCourse());
        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
        assertNotNull(result);
        assertEquals(courseResponses.size(), result.size());

    }

    @Test
    public void getAllModuleByCourse(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadModulesCallback) invocation.getArguments()[1]).onAllModulesReceived(moduleResponses);
            return null;
        }).when(remote).getModule(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
        List<ModuleEntity> result= LiveDataTestUtils.getValue(academyRepository.getAllModuleByCourse(courseId));
        verify(remote, times(1)).getModule(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
        assertNotNull(result);
        assertEquals(moduleResponses.size(), result.size());

    }

    @Test
    public void getBookmarkeedCourses(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadCoursesCallback) invocation.getArguments()[0]).onAllCoursesReceived(courseResponses);
            return null;
        }).when(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
        List<CourseEntity> result= LiveDataTestUtils.getValue(academyRepository.getBookmarkedCourses());
        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
        assertNotNull(result);
        assertEquals(courseResponses.size(), result.size());

    }

    @Test
    public void getContent(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadModulesCallback) invocation.getArguments()[1])
                    .onAllModulesReceived(moduleResponses);
            return null;
        }).when(remote).getModule(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
        doAnswer(invocation -> {
            ((RemoteRepository.GetContentCallback)invocation.getArguments()[1])
                    .onContentReceived(content);
            return null;

        }).when(remote).getContent(eq(moduleId), any(RemoteRepository.GetContentCallback.class));
        ModuleEntity result= LiveDataTestUtils.getValue(academyRepository.getContent(courseId, moduleId));
        verify(remote, times(1)).getModule(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
        verify(remote, times(1)).getContent(eq(moduleId), any(RemoteRepository.GetContentCallback.class));
        assertNotNull(result);
        assertNotNull(result.contentEntity);
        assertNotNull(result.contentEntity.getmContent());
        assertEquals(content.getContent(), result.contentEntity.getmContent());

    }

    @Test
    public void getCourseWithModules(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadCoursesCallback) invocation.getArguments()[0])
                    .onAllCoursesReceived(courseResponses);
            return null;
        }).when(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
        CourseEntity result= LiveDataTestUtils.getValue(academyRepository.getCourseWithModules(courseId));
        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
        assertNotNull(result);
        assertNotNull(result.getTitle());
        assertEquals(courseResponses.get(0).getTitle(), result.getTitle());

    }
    

}