package com.example.academy.Utils;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class IddlingTesting {
    private static final String RESOURCE="GLOBAL";
    private static CountingIdlingResource countingIdlingResource= new CountingIdlingResource(RESOURCE);
    public static void increment(){
        countingIdlingResource.increment();
    }
    public static void decrement(){
        countingIdlingResource.decrement();
    }
    public static IdlingResource getIddlingTesting(){
        return countingIdlingResource;
    }
}
