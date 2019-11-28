package com.example.academy.di;

import android.app.Application;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.local.AcademyDatabase;
import com.example.academy.Data.source.local.LocalRepository;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Utils.AppExecutors;
import com.example.academy.Utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application) {
        AcademyDatabase database = AcademyDatabase.getInstance(application);

        // set injection in local repository to instance dao
        LocalRepository localRepository = LocalRepository.getInstance(database.academyDao());
        // set injection in remote repository to instance application jsonhelper
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();

        return AcademyRepository.getInstance(localRepository, remoteRepository, appExecutors);

    }
}
