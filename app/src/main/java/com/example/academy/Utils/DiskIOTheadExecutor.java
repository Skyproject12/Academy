package com.example.academy.Utils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOTheadExecutor implements Executor {
    private final Executor mDiskIO;
    DiskIOTheadExecutor(){
        mDiskIO= Executors.newSingleThreadExecutor();

    }
    @Override
    public void execute(@NonNull Runnable command){
        mDiskIO.execute(command);

    }
}
