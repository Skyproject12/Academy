package com.example.academy.Ui.Reader;

import com.example.academy.Data.ContentEntity;
import com.example.academy.Data.ModuleEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CourseReaderViewModelTest {

    private CourseReaderViewModel courseReaderViewModel;
    private ContentEntity contentEntit;
    private String idModule;
    @Before
    public void setUp(){
        courseReaderViewModel= new CourseReaderViewModel();
        courseReaderViewModel.setCourseId("a14");
        idModule="a14m1";
        String title= courseReaderViewModel.getModules().get(0).getmTitle();
        // expectasi data yang akan ditampilkan dalam method
        contentEntit= new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");

    }
    @Test
    public void getModul(){
        ArrayList<ModuleEntity> moduleEntities= courseReaderViewModel.getModules();
        assertNotNull(moduleEntities);
        // menyocokkan apakah jumlah file sesuai dengan expected
        assertEquals(7, moduleEntities.size());

    }
    @Test
    public void getSelectedModule(){
        courseReaderViewModel.setSelectedModule(idModule);
        // mengambil nilai modul yang di select
        ModuleEntity moduleEntity= courseReaderViewModel.getSlectedModule();
        assertNotNull(moduleEntity);
        // menampung  dalam entity
        ContentEntity content= moduleEntity.contentEntity;
        assertNotNull(content);
        // mengambil nilai content yang di tampung dalam string
        String co= content.getmContent();
        assertNotNull(co);
        assertEquals(co, contentEntit.getmContent());
    }

}