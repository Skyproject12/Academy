package com.example.academy.Data.source.remote;

import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;
import com.example.academy.Utils.JsonHelper;

import java.util.List;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;


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

    public List<CourseResponse> getAllCourses(){
        // get load course from jsonHelper
        return jsonHelper.loadCourse();

    }

    public List<ModuleResponse> getModule(String courseId){
        // get module from class json helper
        return jsonHelper.loadModule(courseId);

    }

    public ContentResponse getContent(String moduleId){
        // get content from class json helper
        return jsonHelper.loadContent(moduleId);

    }

}
