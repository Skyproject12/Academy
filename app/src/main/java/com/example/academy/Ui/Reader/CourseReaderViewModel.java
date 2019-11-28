package com.example.academy.Ui.Reader;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.ValueObject.Resource;

import java.util.List;

public class CourseReaderViewModel extends ViewModel {

    private AcademyRepository academyRepository;
    private MutableLiveData<String> moduleId = new MutableLiveData<>();
    private MutableLiveData<String> courseId = new MutableLiveData<>();


    // this constructor for viewmodelvactory
    public CourseReaderViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;

    }

    // set module
    public LiveData<Resource<List<ModuleEntity>>> modules = Transformations.switchMap(courseId, mCourseId -> academyRepository.getAllModuleByCourse(mCourseId));

    // set course id
    public void setCourseId(String courseId) {
        this.courseId.setValue(courseId);
    }

    // select content with module
    public LiveData<Resource<ModuleEntity>> selectedContent = Transformations.switchMap(moduleId, selectedPosition -> academyRepository.getContent(selectedPosition));

    public void setSelectedModule(String moduleId) {
        this.moduleId.setValue(moduleId);

    }

    // set status module suadah read atau belum
    public void readContent(ModuleEntity module) {
        academyRepository.setReadModule(module);

    }

    // menegset next page
    public void setNextPage() {
        if (selectedContent.getValue() != null && modules.getValue() != null) {
            // mengeset content dari module untuk mengambil position
            ModuleEntity moduleEntity = selectedContent.getValue().data;
            // mengambil modul berdasarkan kontent
            List<ModuleEntity> moduleEntitises = modules.getValue().data;
            if (moduleEntity != null && moduleEntitises != null) {
                // get position
                int position = moduleEntity.getmPosition();
                // ketika position page masih kurang dari size module &&  position lebih dari kosong
                if (position < moduleEntitises.size() && position >= 0) {
                    // set position pluss every click
                    setSelectedModule(moduleEntitises.get(position + 1).getmModuleId());
                }
            }
        }
    }

    public int getModuleSize() {
        if (modules.getValue() != null) {
            // mengambil data semua module berdasarkan course
            List<ModuleEntity> moduleEntitises = modules.getValue().data;
            if (moduleEntitises != null) {
                // memberikan nilai list modul size
                return moduleEntitises.size();
            }
        }
        return 0;
    }

    public void setPrevPage(){
        if(selectedContent.getValue()!=null && modules.getValue()!=null){
            ModuleEntity moduleEntity= selectedContent.getValue().data;
            List<ModuleEntity> moduleEntitises = modules.getValue().data;
            if(moduleEntity!=null && moduleEntitises!=null){
                int position= moduleEntity.getmPosition();
                if(position< moduleEntitises.size() && position>=0){
                    setSelectedModule(moduleEntitises.get(position-1).getmModuleId());

                }
            }
        }
    }


}
