package com.example.academy.Utils;

import android.app.Application;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {
    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    // memparse json into String format
    private String parsingFileToString(String fileName){
        try {
            // memberi parameter untuk input fileName
            InputStream is= application.getAssets().open(fileName);
            byte[] buffer= new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        }
        catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
