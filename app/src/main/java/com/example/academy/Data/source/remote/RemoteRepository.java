package com.example.academy.Data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.Utils.IddlingTesting;
import com.example.academy.Utils.JsonHelper;

import java.util.List;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;


    // inisial jsonhelper
    public RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    //instnace jsonhelper
    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    // mengganti callback dengan Livedata ApiResponse
    public LiveData<ApiResponse<List<CourseResponse>>> getAllCoursesAsLiveData() {
        // start iddling testing
        IddlingTesting.increment();
        // menampung hasil dari request jsonHelper.loadCourse
        MutableLiveData<ApiResponse<List<CourseResponse>>> resultCourse = new MutableLiveData<>();
        Handler handler = new Handler();
        // give delay 2000 second in handler
        handler.postDelayed(() -> {
            // ketika api response berjalan success
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadCourse()));
            // netralkan iddling testing
            IddlingTesting.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultCourse;
    }

    public LiveData<ApiResponse<List<ModuleResponse>>> getAllModulesByCourseLiveData(String courseId) {
        IddlingTesting.increment();
        MutableLiveData<ApiResponse<List<ModuleResponse>>> resultModules = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultModules.setValue(ApiResponse.success(jsonHelper.loadModule(courseId)));

            IddlingTesting.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultModules;

    }

    public LiveData<ApiResponse<ContentResponse>> getContentAsLiveData(String moduleId) {
        IddlingTesting.increment();
        MutableLiveData<ApiResponse<ContentResponse>> resultContent = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resultContent.setValue(ApiResponse.success(jsonHelper.loadContent(moduleId)));
                IddlingTesting.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultContent;

    }

}
