package com.app.our.foodplanner.network;

import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConcreteRemoteSource implements  RemoteSource{

    public static  final  String BASE_URL="";
    private  static  ConcreteRemoteSource productClient=null;
    private  ConcreteRemoteSource()
    {

    }
    public static ConcreteRemoteSource getInstance()
    {
        if(productClient==null)
        {
            productClient= new ConcreteRemoteSource();
        }
        return  productClient;
    }

    @Override
    public void enqueueCall(NetworkDelegate networkDelegate,Context context) {
        int cacheSize=10*1024*1024;
        Cache cache=new Cache(context.getCacheDir(),cacheSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceInterface mysService=retrofit.create(ServiceInterface.class);
        //Call<Root> myproducts=mysService.getAllProducts();
       /* myproducts.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                networkDelegate.onSuccessResults (response.body().getProducts());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });*/
    }
}
