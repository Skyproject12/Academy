package com.example.academy.Utils;

import android.app.Application;
import android.widget.Toast;

import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    // memparse json into String format
    private String parsingFileToString(String fileName){
        try {
            // memberi parameter untuk input fileName
            InputStream is= application.getAssets().open(fileName);
            byte[] buffer= new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        }
        catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    // covert json to array format
    public List<CourseResponse> loadCourse(){
        ArrayList<CourseResponse> list= new ArrayList<>();
        try {
            // take object file json
            JSONObject responseObject= new JSONObject(parsingFileToString("CourseResponses.json"));
            // take array course
            JSONArray listArray= responseObject.getJSONArray("courses");
            for (int i = 0; i < listArray.length() ; i++) {
                // take object array
                JSONObject course= listArray.getJSONObject(i);
                String id= course.getString("id");
                String title= course.getString("title");
                String description= course.getString("description");
                String date= course.getString("date");
                String imagePath= course.getString("imagePath");
                // tampung dalam object
                CourseResponse courseResponse= new CourseResponse(id, title, description, date, imagePath);
                list.add(courseResponse);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<ModuleResponse> loadModule(String courseId){
        // mengambil file modul sesuai dengan nama file
        String fileName= String.format("Module_%s.json", courseId);
        ArrayList<ModuleResponse> list= new ArrayList<>();
        try {
            // memparsing file into string format
            String result= parsingFileToString(fileName);
            if(result!=null){
                // melakukan parsing file into variable
                JSONObject responseObject= new JSONObject(result);
                JSONArray listArray= responseObject.getJSONArray("modules");
                for (int i = 0; i <listArray.length() ; i++) {
                    JSONObject course= listArray.getJSONObject(i);
                    String moduleId= course.getString("moduleId");
                    String title= course.getString("title");
                    String position= course.getString("position");
                    ModuleResponse courseResponse= new ModuleResponse(moduleId, courseId, title, Integer.parseInt(position));
                    list.add(courseResponse);

                }
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return list;
    }
    public ContentResponse loadContent(String moduleId){
        String fileName= String.format("Content_%s.json", moduleId);
        ContentResponse contentResponse= null;
        try {
            // call the file
            String result= parsingFileToString(fileName);
            if(result!=null){
                // covert file from object json itu String
                JSONObject responseObject= new JSONObject(result);
                String content= responseObject.getString("content");
                // save result into object java
                contentResponse= new ContentResponse(moduleId, content);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return contentResponse;
    }
}
