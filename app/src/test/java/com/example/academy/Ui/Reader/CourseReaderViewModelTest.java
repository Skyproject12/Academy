package com.example.academy.Ui.Reader;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseReaderViewModelTest {

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
        when(academyRepository.getAllModuleByCourse(courseId)).thenReturn(moduleEntiti);
        List<ModuleEntity> moduleEntities= courseReaderViewModel.getModules();
        verify(academyRepository).getAllModuleByCourse(courseId);
        assertNotNull(moduleEntities);
        // menyocokkan apakah jumlah file sesuai dengan expected
        assertEquals(7, moduleEntities.size());

    }
    @Test
    public void getSelectedModule(){
        ModuleEntity moduleEntity= moduleEntiti.get(0);
        String content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>";
        moduleEntity.contentEntity= new ContentEntity(content);
        courseReaderViewModel.setSelectedModule(idModule);

        when(academyRepository.getContent(courseId, idModule)).thenReturn(moduleEntity);
        // mengambil nilai modul yang di select
        ModuleEntity entity= courseReaderViewModel.getSlectedModule();
        verify(academyRepository).getContent(courseId, idModule);
        assertNotNull(entity);
        // menampung  dalam entity
        ContentEntity contentEntity= moduleEntity.contentEntity;
        assertNotNull(contentEntity);

        String resultContent= contentEntity.getmContent();
        assertNotNull(resultContent);

        assertEquals(content, resultContent);
    }

}