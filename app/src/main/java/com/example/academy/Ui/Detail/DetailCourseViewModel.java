package com.example.academy.Ui.Detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.CourseWithModule;
import com.example.academy.ValueObject.Resource;

public class DetailCourseViewModel extends ViewModel {

    private AcademyRepository academyRepository;
    private CourseEntity mCourse;
    private MutableLiveData<String> couseId = new MutableLiveData<>();

    // this constructor for viewmodelvactory
    public DetailCourseViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;

    }

    // set CourseWith Module
    public LiveData<Resource<CourseWithModule>> courseModule = Transformations.switchMap(couseId, mCourseId -> academyRepository.getCourseWithModules(mCourseId));

    public void setCourseId(String courseId) {
        this.couseId.setValue(courseId);

    }

    public String getCourseId() {
        if (couseId.getValue() == null) return null;
        return couseId.getValue();

    }

    // set bookmark
    void setBookmark() {
        if (courseModule.getValue() != null) {
            // how to getCoursewith module
            CourseWithModule courseWithModule = courseModule.getValue().data;
            if (courseWithModule != null) {
                CourseEntity courseEntity = courseWithModule.mCourse;
                final boolean newState = !courseEntity.isBookmarked();
                academyRepository.setCourseBookmark(courseEntity, newState);
            }
        }
    }

}
