package com.example.academy.Ui.Detail;

import com.example.academy.Data.CourseEntity;
import com.example.academy.Data.ModuleEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DetailCourseViewModelTest {

    private DetailCourseViewModel detailCourseViewModel;
    private CourseEntity courseEntity;

    @Before
    public void setUp(){
        detailCourseViewModel= new DetailCourseViewModel();
        // set default espect hasil yang dinginkan
        courseEntity= new CourseEntity("a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah langsung diverifikasi oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi android dengan materi testing, Debugging, Aplication, Aplication UX, Fundamental Aplication Component, Persistent Data Storage, dan Enhanced System Integration. ",
                "100 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg");

    }
    @Test
    public void getCourse(){
        detailCourseViewModel.setCourseId(courseEntity.getCourseId());
        // mengambil data dari view model
        CourseEntity course= detailCourseViewModel.getCourse();
        // memastikan data tidak kosong saat dijalankan
        assertNotNull(course);
        assertEquals(courseEntity.getCourseId(), course.getCourseId());
        assertEquals(courseEntity.getDeadline(), course.getDeadline());
        assertEquals(courseEntity.getDeskription(), course.getDeskription());
        assertEquals(courseEntity.getImagePath(), course.getImagePath());
        assertEquals(courseEntity.getTitle(), course.getTitle());
    }
    @Test
    public void getModul(){
        List<ModuleEntity> moduleEntities= detailCourseViewModel.getModules();
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }

}