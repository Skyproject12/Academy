package com.example.academy.Ui.Reader;

import androidx.lifecycle.ViewModel;

import com.example.academy.Data.ContentEntity;
import com.example.academy.Data.ModuleEntity;
import com.example.academy.Utils.DataDummy;

import java.util.ArrayList;

public class CourseReaderViewModel extends ViewModel {

    private String courseId;
    private String moduleId;

    // set module
    public ArrayList<ModuleEntity> getModules(){
        return DataDummy.generateDummyModules(courseId);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    // if click detail modul
    public ModuleEntity getSlectedModule(){
        ModuleEntity moduleEntity= null;
        for (int i=0; i< getModules().size(); i++){
            if(getModules().get(i).getmModuleId().equals(moduleId)){
                moduleEntity= getModules().get(i);
                moduleEntity.contentEntity = new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + moduleEntity.getmTitle() + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
                break;
            }
        }
        return moduleEntity;
    }

    public void setSelectedModule(String moduleId){
        this.moduleId= moduleId;
    }

}
