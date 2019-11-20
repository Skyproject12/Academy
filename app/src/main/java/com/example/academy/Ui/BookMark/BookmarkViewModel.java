package com.example.academy.Ui.BookMark;

import androidx.lifecycle.ViewModel;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Utils.DataDummy;

import java.util.ArrayList;

public class BookmarkViewModel extends ViewModel {

    ArrayList<CourseEntity> getBookmarks(){
        return DataDummy.generateDummy();
    }

}
