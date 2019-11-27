package com.example.academy.Data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.academy.Data.source.remote.ApiResponse;
import com.example.academy.Utils.AppExecutors;
import com.example.academy.ValueObject.Resource;

public abstract class NetworkBoundResource <ResultType, RequestType>{

    // berfungsi untuk menambahkan localRepository ke dalam academyRepository
    public String message;
    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    private AppExecutors mExecutors;

    protected void onFetchFailed(){

    }

    protected abstract LiveData<ResultType> loadFormDB();
    protected abstract Boolean shouldFetch(ResultType data);
    protected abstract LiveData<ApiResponse<RequestType>> createCall();
    protected abstract void saveCallResult(RequestType data);

    public NetworkBoundResource(AppExecutors appExecutors){
        this.mExecutors= appExecutors;
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource= loadFormDB();
        result.addSource(dbSource, data->{
            result.removeSource(dbSource);
            if(shouldFetch(data)){
                fetchFromNetwork(dbSource);
            }
            else{
                result.addSource(dbSource, newData-> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource){
        LiveData<ApiResponse<RequestType>> apiResponse= createCall();
        result.addSource(dbSource, mData-> result.setValue(Resource.loading(mData)));
        result.addSource(apiResponse, response-> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            switch (response.status){
                case SUCCESS:
                    mExecutors.diskIO().execute(()->{
                        saveCallResult(response.body);
                        mExecutors.mainThread().execute(()-> result.addSource(loadFormDB(), newData-> result.setValue(Resource.success(newData))));
                    });
                    break;
                case EMPTY:
                    mExecutors.mainThread().execute(()->
                            result.addSource(loadFormDB(), newData-> result.setValue(Resource.success(newData))));
                    break;
                case ERROR:
                    onFetchFailed();
                    result.addSource(dbSource, newData-> result.setValue(Resource.error(response.message, newData)));
                    break;
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData(){
        return result;

    }



}
