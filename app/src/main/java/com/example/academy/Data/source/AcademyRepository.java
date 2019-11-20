package com.example.academy.Data.source;

import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;

import java.util.ArrayList;
import java.util.List;

public class AcademyRepository implements AcademyDataSource {


    private volatile  static AcademyRepository INSTANCE= null;
    private final RemoteRepository remoteRepository;

    // call remote repository
    private AcademyRepository(RemoteRepository remoteRepository){
        this.remoteRepository= remoteRepository;
    }

    public static AcademyRepository getInstance(RemoteRepository remoteRepository){
        // instancee in academy repository when definition remote repository
        if(INSTANCE==null){
            synchronized (AcademyRepository.class){
                if(INSTANCE==null){
                    // call remote repository
                    INSTANCE= new AcademyRepository(remoteRepository);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public List<CourseEntity> getAllCourse() {
        // get getallcourse from remoteRepository
        List<CourseResponse> courseResponses= remoteRepository.getAllCourses();
        ArrayList<CourseEntity> courseList= new ArrayList<>();
        for (int i=0; i< courseResponses.size(); i++){
            // tampung hasil dari get ke dalam suatu perulangan object CourseResponse
            CourseResponse response= courseResponses.get(i);
            // memasukkan hasil select kedalam object course entity
            CourseEntity course=new CourseEntity(
                    response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getDate(),
                    false,
                    response.getImagePath());

            courseList.add(course);

        }// mereturn arraylist yang telah menampung data
        return courseList;

    }

    @Override
    public CourseEntity getCourseWithModules(String courseId) {
        CourseEntity cours= null;
        List<CourseResponse> course= remoteRepository.getAllCourses();
        for (int i=0; i<course.size(); i++){
            CourseResponse response= course.get(i);
            if(response.getId().equals(courseId)){
                // ketika response id sesuai dengan course id dari module maka akan mereturn hasil
                cours= new CourseEntity(
                        response.getId(),
                        // menampung response dalam class entity
                        response.getTitle(),
                        response.getDescription(),
                        response.getDate(),
                        false,
                        response.getImagePath()
                );
            }
        }
        return cours;
    }

    @Override
    public List<ModuleEntity> getAllModuleByCourse(String courseId) {
        ArrayList<ModuleEntity> moduleList= new ArrayList<>();
        List<ModuleResponse> moduleResponses= remoteRepository.getModule(courseId);
        for (int i=0; i< moduleResponses.size(); i++){
            ModuleResponse response= moduleResponses.get(i);
            ModuleEntity course= new ModuleEntity(
                    response.getModuleId(),
                    response.getCourseId(),
                    response.getTitle(),
                    response.getPosition(),
                    false
                    );
            moduleList.add(course);
        }
        return moduleList;
    }

    @Override
    public List<CourseEntity> getBookmarkedCourses() {
        ArrayList<CourseEntity> courseEntities= new ArrayList<>();
        List<CourseResponse> course= remoteRepository.getAllCourses();
        for (int i=0; i<course.size(); i++){
            CourseResponse response= course.get(i);
            CourseEntity courseBook= new CourseEntity(
                 response.getId(),
                 response.getTitle(),
                 response.getDescription(),
                 response.getDate(),
                 false,
                 response.getImagePath());
            courseEntities.add(courseBook);
        }
        return courseEntities;

    }

    @Override
    public ModuleEntity getContent(String courseId, String moduleId) {
        List<ModuleResponse> moduleResponses= remoteRepository.getModule(courseId);
        ModuleEntity  module= null;
        for (int i=0; i<moduleResponses.size(); i++){
            ModuleResponse moduleResponse= moduleResponses.get(i);
            // mengmbil id dari module untuk menjalankan content
            String id= moduleResponse.getModuleId();
            if(id.equals(moduleId)){
//                ketika module sama dengan id module yang di kirim
                module= new ModuleEntity(
                        id,
                        moduleResponse.getCourseId(),
                        moduleResponse.getTitle(),
                        moduleResponse.getPosition(),
                        false
                );
                // mengeset content entitiy berdasarkan content entity pada ModuleEntity
                module.contentEntity= new ContentEntity(remoteRepository.getContent(moduleId).getContent());
                break;
            }
        }
        return module;
    }
}
