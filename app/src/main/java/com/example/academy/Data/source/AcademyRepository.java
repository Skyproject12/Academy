package com.example.academy.Data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Data.source.remote.response.ContentResponse;
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
    public LiveData<List<CourseEntity>> getAllCourse() {
        MutableLiveData<List<CourseEntity>> coursesEnti= new MutableLiveData<>();
        remoteRepository.getAllCourses(new RemoteRepository.LoadCoursesCallback() {
            // mengambil data dari remote repository jika data berhasil di load
            @Override
            public void onAllCoursesReceived(List<CourseResponse> courseResponses) {
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

                // melakukan post kedalam viewmodel live data
                coursesEnti.postValue(courseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return coursesEnti;
    }

    @Override
    public LiveData<CourseEntity> getCourseWithModules(String courseId) {
        MutableLiveData<CourseEntity> courseEnti= new MutableLiveData<>();
        remoteRepository.getAllCourses(new RemoteRepository.LoadCoursesCallback() {
            @Override
            public void onAllCoursesReceived(List<CourseResponse> courseResponses) {
                for (int i=0; i<courseResponses.size(); i++){
                    CourseResponse response= courseResponses.get(i);
                    if(response.getId().equals(courseId)){
                        // ketika response id sesuai dengan course id dari module maka akan mereturn hasil
                        CourseEntity course= new CourseEntity(
                                response.getId(),
                                // menampung response dalam class entity
                                response.getTitle(),
                                response.getDescription(),
                                response.getDate(),
                                false,
                                response.getImagePath());
                        // post value from array list into live data viewmodel
                        courseEnti.postValue(course);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return courseEnti;
    }

    @Override
    public LiveData<List<ModuleEntity>> getAllModuleByCourse(String courseId) {
       MutableLiveData<List<ModuleEntity>> moduleEnti= new MutableLiveData<>();
       remoteRepository.getModule(courseId, new RemoteRepository.LoadModulesCallback() {
           @Override
           public void onAllModulesReceived(List<ModuleResponse> moduleResponses) {
               ArrayList<ModuleEntity> moduleList= new ArrayList<>();
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
               // post arraylist into livedata viewmodel
               moduleEnti.postValue(moduleList);
           }

           @Override
           public void onDataNotAvailable() {

           }
       });
       return moduleEnti;
    }

    @Override
    public LiveData<List<CourseEntity>> getBookmarkedCourses() {
        MutableLiveData<List<CourseEntity>> courseEnti= new MutableLiveData<>();
        remoteRepository.getAllCourses(new RemoteRepository.LoadCoursesCallback() {
            @Override
            public void onAllCoursesReceived(List<CourseResponse> courseResponses) {
                List<CourseEntity> courseEntities= new ArrayList<>();
                for (int i=0; i<courseResponses.size(); i++){
                    CourseResponse response= courseResponses.get(i);
                    CourseEntity courseBook= new CourseEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());
                    courseEntities.add(courseBook);
                }
                courseEnti.postValue(courseEntities);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return  courseEnti;
    }

    @Override
    public LiveData<ModuleEntity> getContent(String courseId, String moduleId) {
        MutableLiveData<ModuleEntity> moduleEnti= new MutableLiveData<>();
        remoteRepository.getModule(courseId, new RemoteRepository.LoadModulesCallback() {
            @Override
            public void onAllModulesReceived(List<ModuleResponse> moduleResponses) {
                ModuleEntity  module;
                for (int i=0; i<moduleResponses.size(); i++){
                    ModuleResponse moduleResponse= moduleResponses.get(i);
                    // mengmbil id dari module untuk menjalankan content
                    String id= moduleResponse.getModuleId();
                    if(id.equals(moduleId)){
                        // set module from remote repositori architecture android
                        module= new ModuleEntity(id, moduleResponse.getCourseId(), moduleResponse.getTitle(), moduleResponse.getPosition(), false);
                        remoteRepository.getContent(moduleId, new RemoteRepository.GetContentCallback() {
                            @Override
                            public void onContentReceived(ContentResponse contentResponse) {
                                module.contentEntity= new ContentEntity(contentResponse.getContent());
                                // get arraylist and add ito live data  viewmodel
                                moduleEnti.postValue(module);
                            }

                            @Override
                            public void onDataNotAvailable() {

                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return moduleEnti;
    }
}
