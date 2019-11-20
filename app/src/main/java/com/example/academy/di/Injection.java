package com.example.academy.di;

import android.app.Application;

import com.example.academy.Data.source.AcademyRepository;
import com.example.academy.Data.source.remote.RemoteRepository;
import com.example.academy.Utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application){
        RemoteRepository remoteRepository= RemoteRepository.getInstance(new JsonHelper(application));
        return AcademyRepository.getInstance(remoteRepository);

    }
}
