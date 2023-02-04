package com.app.our.foodplanner.network;

import android.content.Context;

import com.app.our.foodplanner.model.RootArea;
import com.app.our.foodplanner.model.RootCategory;
import com.app.our.foodplanner.model.RootIngredient;
import com.app.our.foodplanner.model.RootMeal;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ConcreteRemoteSource implements  RemoteSource{

    public static  final  String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private  static  ConcreteRemoteSource productClient=null;
    private Retrofit retrofit;
    private  ServiceInterface mysService;
    private  ConcreteRemoteSource(Context context)
    {
        int cacheSize=10*1024*1024;
        Cache cache=new Cache(context.getCacheDir(),cacheSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .cache(cache)
                .build();

         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mysService=retrofit.create(ServiceInterface.class);
    }
    public static ConcreteRemoteSource getInstance(Context context)
    {
        if(productClient==null)
        {
            productClient= new ConcreteRemoteSource(context);
        }
        return  productClient;
    }

    @Override
    public void enqueueCallGetMealByName(NetworkDelegate networkDelegate, Context context, String data) {
        Call<RootMeal> getMealByName=mysService.getMealByName(data);
        getMealByName.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.getMealByNameOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallListAllMealsByFirstLetter(NetworkDelegate networkDelegate, Context context, String data) {
        Call<RootMeal>listAllMealsByFirstLetter=mysService.listAllMealsByFirstLetter(data.charAt(0));
        listAllMealsByFirstLetter.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.listAllMealsByFirstLetterOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });


    }

    @Override
    public void enqueueCallLookupFullMealDetailsById(NetworkDelegate networkDelegate, Context context, String data) {
        Call<RootMeal>lookupFullMealDetailsById=mysService.lookupFullMealDetailsById(data);
        lookupFullMealDetailsById.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.lookupFullMealDetailsByIdOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallLookupASingleRandomMeal(NetworkDelegate networkDelegate, Context context) {
        Call<RootMeal>lookupASingleRandomMeal=mysService.lookupASingleRandomMeal();
        lookupASingleRandomMeal.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.lookupASingleRandomMealOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallListAllCategories_Just_Names(NetworkDelegate networkDelegate, Context context) {
        Call<RootCategory>listAllCategories_Just_Names=mysService.listAllCategories_Just_Names("list");
        listAllCategories_Just_Names.enqueue(new Callback<RootCategory>() {
            @Override
            public void onResponse(Call<RootCategory> call, Response<RootCategory> response) {
                networkDelegate.listAllCategories_Just_NamesOnSuccessResults (response.body().getCategories());
            }

            @Override
            public void onFailure(Call<RootCategory> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallListAllArea_Just_Names(NetworkDelegate networkDelegate, Context context) {
        Call<RootArea>listAllArea_Just_Names=mysService.listAllArea_Just_Names("list");
        listAllArea_Just_Names.enqueue(new Callback<RootArea>() {
            @Override
            public void onResponse(Call<RootArea> call, Response<RootArea> response) {
                networkDelegate.listAllArea_Just_NamesOnSuccessResults (response.body().getAreas());
            }

            @Override
            public void onFailure(Call<RootArea> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallListAllIngredients_Just_Names(NetworkDelegate networkDelegate, Context context) {
        Call<RootIngredient>listAllIngredients_Just_Names=mysService.listAllIngredients_Just_Names("list");
        listAllIngredients_Just_Names.enqueue(new Callback<RootIngredient>() {
            @Override
            public void onResponse(Call<RootIngredient> call, Response<RootIngredient> response) {
                networkDelegate.listAllIngredients_Just_NamesOnSuccessResults (response.body().getIngredients());
            }

            @Override
            public void onFailure(Call<RootIngredient> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallFilterByMainIngredient(NetworkDelegate networkDelegate, Context context, String data) {
        Call<RootMeal>filterByMainIngredient=mysService.filterByMainIngredient(data);
        filterByMainIngredient.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.filterByMainIngredientOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, Context context, String data) {
        Call<RootMeal>filterByCategory=mysService.filterByCategory(data);
        filterByCategory.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.filterByCategoryOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });

    }

    @Override
    public void enqueueCallFilterByArea(NetworkDelegate networkDelegate, Context context, String data) {
        Call<RootMeal>filterByArea=mysService.filterByArea(data);
        filterByArea.enqueue(new Callback<RootMeal>() {
            @Override
            public void onResponse(Call<RootMeal> call, Response<RootMeal> response) {
                networkDelegate.filterByAreaOnSuccessResults (response.body().getMeals());
            }

            @Override
            public void onFailure(Call<RootMeal> call, Throwable t) {
                networkDelegate.onFailureResults("Fail");
            }
        });
    }
}
