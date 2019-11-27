package com.example.academy.Data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.academy.Data.source.local.Dao.AcademyDao;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;

@Database(entities = {CourseEntity.class, ModuleEntity.class},
version = 1,
exportSchema = false)

public abstract class AcademyDatabase extends RoomDatabase {
    private static AcademyDatabase INSTANCE;
    public abstract AcademyDao academyDao();

    private static final Object slock= new Object();

    public static AcademyDatabase getInstance(Context content){
        synchronized (slock){
            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(content.getApplicationContext(),
                        AcademyDatabase.class, "Academies.db")
                        .build();
            }
        }
        return INSTANCE;
    }


}
