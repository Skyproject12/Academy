package com.example.academy.Data.source.remote;

import android.os.Handler;

import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.Utils.JsonHelper;

import java.util.List;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS=2000;


    // inisial jsonhelper
    public RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    //instnace jsonhelper
    public static RemoteRepository getInstance(JsonHelper helper){
        if(INSTANCE==null){
            INSTANCE= new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    public void  getAllCourses(LoadCoursesCallback callback){
        Handler handler= new Handler();
        // give delay 2000 second in handler
        handler.postDelayed(()->callback.onAllCoursesReceived(jsonHelper.loadCourse()), SERVICE_LATENCY_IN_MILLIS);

    }

    public void  getModule(String courseId, LoadModulesCallback callback){
       Handler handler= new Handler();
       handler.postDelayed(()->callback.onAllModulesReceived(jsonHelper.loadModule(courseId)), SERVICE_LATENCY_IN_MILLIS);

    }

    public void  getContent(String moduleId, GetContentCallback callback){
       Handler handler= new Handler();
       handler.postDelayed(()->callback.onContentReceived(jsonHelper.loadContent(moduleId)), SERVICE_LATENCY_IN_MILLIS);

    }

    // make interface for response in repository
    public interface LoadCoursesCallback{
        // give response format list save with request view model
        void onAllCoursesReceived(List<CourseResponse> courseResponses);
        void onDataNotAvailable();
    }

    public interface LoadModulesCallback{
        void onAllModulesReceived(List<ModuleResponse> moduleResponses);
        void onDataNotAvailable();

    }

    public interface GetContentCallback{
        void onContentReceived(ContentResponse contentResponse);
        void onDataNotAvailable();
    }

}
