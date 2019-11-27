package com.example.academy.Data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// melakukan pengecekan apakah api berhasil di load atau tidak
public class ApiResponse<T> {
    @NonNull
    public final StatusResponse status;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    public ApiResponse(@NonNull StatusResponse status, @Nullable T body, @Nullable String message ){
        this.status= status;
        this.body= body;
        this.message= message;
    }

    public static <T> ApiResponse<T> success(@Nullable T body){
        return new ApiResponse<>(StatusResponse.SUCCESS, body, null);
    }

    public static <T> ApiResponse<T> empty (String msg, @Nullable T body){
        return new ApiResponse<>(StatusResponse.EMPTY, body, msg);
    }

    public static <T> ApiResponse <T> error(String msg, @Nullable T body){
        return new ApiResponse<>(StatusResponse.ERROR, body, msg);
    }

}
