package com.example.academy.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtils {
    @SuppressWarnings("unchecked")
    public static <T> T getValue(LiveData<T>liveData){
        Object[] data= new Object[1];
        CountDownLatch latch= new CountDownLatch(1);
        Observer<T> observer= new Observer<T>() {
            @Override
            public void onChanged(T t) {
                data[0]=t;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        try {
            latch.await(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return (T) data[0];

    }
}
