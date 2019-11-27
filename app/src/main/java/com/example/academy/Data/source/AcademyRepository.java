package com.example.academy.Data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.academy.Data.source.local.LocalRepository;
import com.example.academy.Data.source.local.entity.ContentEntity;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.CourseWithModule;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.ApiResponse;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.Utils.AppExecutors;
import com.example.academy.ValueObject.Resource;

import java.util.ArrayList;
import java.util.List;

public class AcademyRepository implements AcademyDataSource {


    private volatile  static AcademyRepository INSTANCE= null;
    private final RemoteRepository remoteRepository ;
    private final AppExecutors appExecutors;
    private final LocalRepository localRepository;

    // call remote repository
    private AcademyRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors){
        this.remoteRepository= remoteRepository;
        this.localRepository= localRepository;
        this.appExecutors= appExecutors;

    }

    public static AcademyRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors){
        // instancee in academy repository when definition remote repository
        if(INSTANCE==null){
            synchronized (AcademyRepository.class){
                if(INSTANCE==null){
                    // call remote repository
                    INSTANCE= new AcademyRepository(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<CourseEntity>>> getAllCourse() {
        return new NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            @Override
            protected LiveData<List<CourseEntity>> loadFormDB() {
                return localRepository.getAllCourses();

            }

            @Override
            protected Boolean shouldFetch(List<CourseEntity> data) {
                return (data==null) || (data.size()==0);

            }

            @Override
            protected LiveData<ApiResponse<List<CourseResponse>>> createCall() {
                return remoteRepository.getAllCoursesAsLiveData();
            }

            @Override
            protected void saveCallResult(List<CourseResponse> data) {
                List<CourseEntity> courseEntities= new ArrayList<>();
                for(CourseResponse courseResponse: data){
                    courseEntities.add(new CourseEntity(
                            courseResponse.getId(),
                            courseResponse.getTitle(),
                            courseResponse.getDescription(),
                            courseResponse.getDate(),
                            null,
                            courseResponse.getImagePath()
                            ));
                }
                localRepository.insertCourse(courseEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<CourseEntity>> getCourseWithModules(String courseId) {
        return new NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            @Override
            protected LiveData<CourseWithModule> loadFormDB() {
                return localRepository.getCourseWithModule(courseId);

            }

            @Override
            protected Boolean shouldFetch(CourseWithModule data) {
                return (data==null) || (data.mModule==null);

            }

            @Override
            protected LiveData<ApiResponse<List<ModuleResponse>>> createCall() {
                return remoteRepository.getAllModulesByCourseLiveData(courseId);

            }

            @Override
            protected void saveCallResult(List<ModuleResponse> data) {

            }
        }
    }

    @Override
    public LiveData<Resource<List<ModuleEntity>>> getAllModuleByCourse(String courseId) {
        return null;
    }

    @Override
    public LiveData<Resource<List<CourseEntity>>> getBookmarkedCourses() {
        return null;
    }

    @Override
    public LiveData<ModuleEntity> getContent(String courseId, String moduleId) {
        return null;
    }

    @Override
    public void setCourseBookmark(CourseEntity course, boolean state) {

    }

    @Override
    public void setReadModule(ModuleEntity module) {

    }
}
