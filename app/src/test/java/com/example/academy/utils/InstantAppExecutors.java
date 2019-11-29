package com.example.academy.utils;

import com.example.academy.Utils.AppExecutors;

import java.util.concurrent.Executor;

// digunakan untuk melakukan pengcekan  si academy repository
public class InstantAppExecutors extends AppExecutors {
    private static Executor instant= Runnable::run;
    public InstantAppExecutors(){
        super(instant, instant, instant);
    }
}
