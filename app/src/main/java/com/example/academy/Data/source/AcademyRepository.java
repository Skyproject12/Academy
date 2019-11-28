package com.example.academy.Data.source;

import android.util.Log;
import android.widget.Toast;

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


    private volatile static AcademyRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;
    private final LocalRepository localRepository;

    // call remote repository
    private AcademyRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.appExecutors = appExecutors;

    }

    public static AcademyRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        // instancee in academy repository when definition remote repository
        if (INSTANCE == null) {
            synchronized (AcademyRepository.class) {
                if (INSTANCE == null) {
                    // call remote repository
                    INSTANCE = new AcademyRepository(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    // get All course
    @Override
    public LiveData<Resource<List<CourseEntity>>> getAllCourse() {
        // get NetworkBoudResource
        return new NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            @Override
            protected LiveData<List<CourseEntity>> loadFormDB() {
                // get local repository
                return localRepository.getAllCourses();

            }

            @Override
            protected Boolean shouldFetch(List<CourseEntity> data) {
                // check data null or not
                // check size is 0 or not
                return (data == null) || (data.size() == 0);

            }

            @Override
            protected LiveData<ApiResponse<List<CourseResponse>>> createCall() {
                // select all from course use remoteRepository
                return remoteRepository.getAllCoursesAsLiveData();
            }

            @Override
            protected void saveCallResult(List<CourseResponse> data) {
                List<CourseEntity> courseEntities = new ArrayList<>();
                for (CourseResponse courseResponse : data) {
                    courseEntities.add(new CourseEntity(
                            courseResponse.getId(),
                            courseResponse.getTitle(),
                            courseResponse.getDescription(),
                            courseResponse.getDate(),
                            null,
                            courseResponse.getImagePath()
                    ));
                }
                // save data into local repository
                localRepository.insertCourse(courseEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<CourseWithModule>> getCourseWithModules(String courseId) {
        return new NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            @Override
            protected LiveData<CourseWithModule> loadFormDB() {
                return localRepository.getCourseWithModule(courseId);

            }

            @Override
            protected Boolean shouldFetch(CourseWithModule data) {
                return (data == null) || (data.mModule == null);

            }

            @Override
            protected LiveData<ApiResponse<List<ModuleResponse>>> createCall() {
                return remoteRepository.getAllModulesByCourseLiveData(courseId);

            }

            @Override
            protected void saveCallResult(List<ModuleResponse> data) {
                List<ModuleEntity> moduleEntities = new ArrayList<>();
                for (ModuleResponse moduleResponse : data) {
                    // add moduleEntities into list ModuleEntitys
                    moduleEntities.add(new ModuleEntity(moduleResponse.getModuleId(), courseId, moduleResponse.getTitle(), moduleResponse.getPosition(), null));
                }
                // insert data into local repository
                localRepository.insertModules(moduleEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<ModuleEntity>>> getAllModuleByCourse(String courseId) {
        return new NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            @Override
            protected LiveData<List<ModuleEntity>> loadFormDB() {
                // get from local
                return localRepository.getAllModuleByCourse(courseId);

            }

            @Override
            protected Boolean shouldFetch(List<ModuleEntity> data) {
                // check data null, size null or not
                return (data == null) || (data.size() == 0);

            }

            @Override
            protected LiveData<ApiResponse<List<ModuleResponse>>> createCall() {
                // get all module from online
                return remoteRepository.getAllModulesByCourseLiveData(courseId);

            }

            @Override
            protected void saveCallResult(List<ModuleResponse> data) {
                // menampung data
                List<ModuleEntity> moduleEntities = new ArrayList<>();
                // melakukan perulangan untuk mengeadd data
                for (ModuleResponse moduleResponse : data) {
                    moduleEntities.add(new ModuleEntity(moduleResponse.getModuleId(), courseId, moduleResponse.getTitle(), moduleResponse.getPosition(), null));
                }
                // melakuakan add ke dalam local
                localRepository.insertModules(moduleEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<CourseEntity>>> getBookmarkedCourses() {
        return new NetworkBoundResource<List<CourseEntity>, List<ContentResponse>>(appExecutors) {
            @Override
            protected LiveData<List<CourseEntity>> loadFormDB() {
                return localRepository.getBookmarkedCourses();

            }

            @Override
            protected Boolean shouldFetch(List<CourseEntity> data) {
                return false;

            }

            @Override
            protected LiveData<ApiResponse<List<ContentResponse>>> createCall() {
                return null;

            }

            @Override
            protected void saveCallResult(List<ContentResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<ModuleEntity>> getContent(String moduleId) {
        return new NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            @Override
            protected LiveData<ModuleEntity> loadFormDB() {
                return localRepository.getModuleWithContent(moduleId);

            }

            @Override
            protected Boolean shouldFetch(ModuleEntity data) {
                return (data.contentEntity == null);

            }

            @Override
            protected LiveData<ApiResponse<ContentResponse>> createCall() {
                return remoteRepository.getContentAsLiveData(moduleId);

            }

            @Override
            protected void saveCallResult(ContentResponse data) {
                localRepository.updateContent(data.getContent(), moduleId);

            }
        }.asLiveData();
    }

    @Override
    public void setCourseBookmark(CourseEntity course, boolean state) {
        Runnable runnable = () -> localRepository.setCourseBookmark(course, state);
        appExecutors.diskIO().execute(runnable);

    }

    @Override
    public void setReadModule(ModuleEntity module) {
        Runnable runnable = () -> localRepository.setReadModule(module);
        appExecutors.diskIO().execute(runnable);

    }
}
