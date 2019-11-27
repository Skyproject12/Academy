package com.example.academy.Utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final int THREAD_COUNT= 3;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    @VisibleForTesting
    public AppExecutors(Executor diskIO, Executor netwokIO, Executor mainThread){
        this.diskIO= diskIO;
        this.networkIO= netwokIO;
        this.mainThread= mainThread;

    }

    public AppExecutors(){
        this(new DiskIOTheadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }

    public Executor diskIO(){
        return diskIO;
    }
    public Executor networkIO(){
        return networkIO;
    }
    public Executor mainThread(){
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor{
        private final Handler mainThreadHandler= new Handler (Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);

        }
    }

}
