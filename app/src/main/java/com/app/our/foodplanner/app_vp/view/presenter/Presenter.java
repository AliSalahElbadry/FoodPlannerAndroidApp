package com.app.our.foodplanner.app_vp.view.presenter;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.our.foodplanner.app_vp.view.MainActivityContainerInterface;
import com.app.our.foodplanner.app_vp.view.favorite.FavouriteFragmentInterface;
import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.app_vp.view.login.LogInFragmentInterface;
import com.app.our.foodplanner.app_vp.view.meal.MealFragmentInterface;
import com.app.our.foodplanner.app_vp.view.plan.PlanFragmentInterface;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragmentInterface;
import com.app.our.foodplanner.app_vp.view.profile.ProfileFragmentInterface;
import com.app.our.foodplanner.app_vp.view.filter.FilterFragmentInterface;
import com.app.our.foodplanner.app_vp.view.signup.SignupFragmentInterface;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;
import com.app.our.foodplanner.model.Repository;
import com.app.our.foodplanner.network.NetworkDelegate;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Presenter implements NetworkDelegate , PresenterInterface {


    //Data Holders

    private LogInFragmentInterface LogInFragmentInterface;
    private FilterFragmentInterface filterFragmentInterface;
    private ProfileFragmentInterface profileFragmentInterface;

    private FavouriteFragmentInterface favouriteFragmentInterface;
    public void setLogInFragmentInterface(LogInFragmentInterface LogInFragmentInterface) {
        this.LogInFragmentInterface = LogInFragmentInterface;
    }
    public void setFilterFragmentInterface(FilterFragmentInterface filterFragmentInterface) {
        this.filterFragmentInterface = filterFragmentInterface;
    }
    public void setProfileFragmentInterface(ProfileFragmentInterface profileFragmentInterface) {
        this.profileFragmentInterface = profileFragmentInterface;
    }

    public void setfavouriteFragmentInterface(FavouriteFragmentInterface favouriteFragmentInterface) {
        this.favouriteFragmentInterface = favouriteFragmentInterface;
    }
    String nameProfile;
    Boolean checkout=false;
    private Repository repository;
    private ArrayList<Meal>meals;
    private ArrayList<Meal>targetShowPlanMeals=new ArrayList<>();
    private Meal targetMeal;
    private ArrayList<Category>categories;
    private ArrayList<Area>areas;
    private ArrayList<Ingredient>ingredients;
    private ArrayList<PlanOfWeek>plans;
    private String[]uData;

    private Context context;
    private FirebaseAuth firebaseAuth;
    MealFragmentInterface mealFragmentInterface;
    private HomeFragmentInterface homeFragment;
    private PlansFragmentInterface plansInterface;
    private  PlanFragmentInterface planFragmentInterface;

    private boolean isLogedIn=false;

    public String getuId() {
        String h=firebaseAuth.getCurrentUser().getEmail();
        if(!h.isEmpty()&&h!=null)
        {
            uId=h;
        }
        return uId;
    }

    private String uId;


    public void setPlansInterface(PlansFragmentInterface plansInterface) {
        this.plansInterface = plansInterface;
    }

    public void setSignupFragmentInterface(SignupFragmentInterface signupFragmentInterface) {
        this.signupFragmentInterface = signupFragmentInterface;
    }

    private SignupFragmentInterface signupFragmentInterface;
    public void setMealFragmentInterface(MealFragmentInterface mealFragmentInterface) {
        this.mealFragmentInterface = mealFragmentInterface;
    }

    public void setHomeFragment(HomeFragmentInterface homeFragment) {
        this.homeFragment = homeFragment;
    }


    public Presenter(Context context)
    {
        isLogedIn=false;
        this.context=context;
        repository=Repository.getInstance(context.getApplicationContext());
        uData=repository.getUserData();
        if(uData!=null)
        {

                isLogedIn=true;
                uId=uData[2];

        }
        meals=new ArrayList<>();
        categories=new ArrayList<>();
        ingredients=new ArrayList<>();
        areas=new ArrayList<>();
        plans=new ArrayList<>();
        uData=repository.getUserData();

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {
            uId = firebaseAuth.getCurrentUser().getEmail();
        }else{
            if(uData!=null)
            {
                uId=uData[2];
            }
         }
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }


    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setuData(String[] uData) {
        this.uData = uData;
    }

    @Override
    public void getMealByNameOnSuccessResults(ArrayList< Meal > Res) {
        Meal meal=Res.get(0);
        mealFragmentInterface.setMealData(meal,filterMealFromNullVals(meal));
    }
    public boolean isNotNullOrEmpty(String s1,String s2)
    {
        boolean is=false;
        if(s1!=null&&s2!=null){
         if(!s1.isEmpty()&&!s2.isEmpty())
         {
             is=true;
         }
        }
        return is;
    }
    public ArrayList<String> filterMealFromNullVals(Meal meal)
    {
        ArrayList<String>res=new ArrayList<>();
        if(isNotNullOrEmpty(meal.getStrIngredient1(),meal.getStrMeasure1()))
        {
            res.add(meal.getStrIngredient1());
            res.add(meal.getStrMeasure1());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient2(),meal.getStrMeasure2()))
        {
            res.add(meal.getStrIngredient2());
            res.add(meal.getStrMeasure2());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient3(),meal.getStrMeasure3()))
        {
            res.add(meal.getStrIngredient3());
            res.add(meal.getStrMeasure3());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient4(),meal.getStrMeasure4()))
        {
            res.add(meal.getStrIngredient4());
            res.add(meal.getStrMeasure4());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient5(),meal.getStrMeasure5()))
        {
            res.add(meal.getStrIngredient5());
            res.add(meal.getStrMeasure5());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient6(),meal.getStrMeasure6()))
        {
            res.add(meal.getStrIngredient6());
            res.add(meal.getStrMeasure6());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient7(),meal.getStrMeasure7()))
        {
            res.add(meal.getStrIngredient7());
            res.add(meal.getStrMeasure7());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient8(),meal.getStrMeasure8()))
        {
            res.add(meal.getStrIngredient8());
            res.add(meal.getStrMeasure8());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient9(),meal.getStrMeasure9()))
        {
            res.add(meal.getStrIngredient9());
            res.add(meal.getStrMeasure9());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient10(),meal.getStrMeasure10()))
        {
            res.add(meal.getStrIngredient10());
            res.add(meal.getStrMeasure10());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient11(),meal.getStrMeasure11()))
        {
            res.add(meal.getStrIngredient11());
            res.add(meal.getStrMeasure11());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient12(),meal.getStrMeasure12()))
        {
            res.add(meal.getStrIngredient12());
            res.add(meal.getStrMeasure12());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient13(),meal.getStrMeasure13()))
        {
            res.add(meal.getStrIngredient13());
            res.add(meal.getStrMeasure13());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient14(),meal.getStrMeasure14()))
        {
            res.add(meal.getStrIngredient14());
            res.add(meal.getStrMeasure14());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient15(),meal.getStrMeasure15()))
        {
            res.add(meal.getStrIngredient15());
            res.add(meal.getStrMeasure15());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient16(),meal.getStrMeasure16()))
        {
            res.add(meal.getStrIngredient16());
            res.add(meal.getStrMeasure16());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient17(),meal.getStrMeasure17()))
        {
            res.add(meal.getStrIngredient17());
            res.add(meal.getStrMeasure17());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient18(),meal.getStrMeasure18()))
        {
            res.add(meal.getStrIngredient18());
            res.add(meal.getStrMeasure18());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient19(),meal.getStrMeasure19()))
        {
            res.add(meal.getStrIngredient19());
            res.add(meal.getStrMeasure19());
        }
        if(isNotNullOrEmpty(meal.getStrIngredient20(),meal.getStrMeasure20()))
        {
            res.add(meal.getStrIngredient20());
            res.add(meal.getStrMeasure20());
        }
        return  res;
    }
    @Override
    public void listAllMealsByFirstLetterOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void lookupFullMealDetailsByIdOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void lookupASingleRandomMealOnSuccessResults(ArrayList<Meal> Res) {

        homeFragment.showRandomMeal(Res);
    }

    @Override
    public void listAllCategories_Just_NamesOnSuccessResults(ArrayList< Category > Res) {
        categories=Res;
        homeFragment.showCategories(Res);
    }

    @Override
    public void listAllArea_Just_NamesOnSuccessResults(ArrayList< Area > Res) {

        filterFragmentInterface.showArea(Res);
    }

    @Override
    public void listAllIngredients_Just_NamesOnSuccessResults(ArrayList< Ingredient > Res) {

        filterFragmentInterface.showIngradient(Res);
    }

    @Override
    public void filterByMainIngredientOnSuccessResults(ArrayList<Meal> Res) {
        filterFragmentInterface.showFilterByArea(Res);
    }

    @Override
    public void filterByCategoryOnSuccessResults(ArrayList<Meal> Res) {
        meals=Res;
        homeFragment.showMeals(Res);
    }

    @Override
    public void filterByAreaOnSuccessResults(ArrayList<Meal> Res) {
            filterFragmentInterface.showFilterByArea(Res);
    }

    @Override
    public void onFailureResults(String msg) {
        Toast.makeText(context, "Connection Error !! Please Check Connection", Toast.LENGTH_SHORT).show();
    }

   //home functions
    @Override
    public void searchMealByName(String search) {
        Stream<Meal> stream= meals.stream().filter(i->i.getStrMeal().toLowerCase().startsWith(search.toLowerCase()));
        ArrayList<Meal> mealsAfter = new ArrayList<>(stream.collect(Collectors.toList()));
        homeFragment.showMeals(mealsAfter);
    }



    @Override
    public void getMealByArea(String meal) {
        repository.enqueueCallFilterByArea(this,context,meal);
    }
    @Override
    public void getMealByIngredient(String ingredient) {
        repository.enqueueCallFilterByMainIngredient(this,context,ingredient);
    }

    @Override
    public void getMealByName(String name) {
        repository.enqueueCallGetMealByName(this,context,name);
    }


    @Override
    public void showFilter() {

    }


    @Override
    public void getMealsByCategory(String category) {

        repository.enqueueCallFilterByCategory(this,context,category);

    }

    @Override
    public void getAllCategories() {

            repository.enqueueCallListAllCategories_Just_Names(this,context);
            getMealsByCategory("Beef");
    }

    @Override
    public void getRandomMeal() {
        repository.enqueueCallLookupASingleRandomMeal(this,context);
    }

    @Override
    public void getRandomMealImage(ImageView imageView, View view, String url) {
        Glide.with(view).load(url).into(imageView);
    }

    @Override
    public boolean isLogedIn() {
        return isLogedIn;
    }
    @Override
    public void addToFav(Meal meal) {
        meal.setIsFavorite(true);
        meal.setUserId(uId);
        repository.isMealExists(meal.getIdMeal(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
           if(i>0)
           {
               repository.updateFavoriteInMeal(true,meal.getIdMeal(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
           }else if(i==0)
           {
               repository.insertMeal(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(()->{
                   mealFragmentInterface.setAddFavRes(true);
               }).subscribe();
           }
        });
        mealFragmentInterface.setAddFavRes(true);
     }

    @Override
    public void getAllFav() {
        repository.getAllFavMealsLive(true,uId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
                    favouriteFragmentInterface.showData(i);
                });
    }
    @Override
    public void setTargetAddMealToPlan(Meal meal) {
        meal.setUserId(uId);
        targetMeal=meal;
    }

    @Override
    public void removePlan(PlanOfWeek plan) {
        repository.getAllMealsInPlan(plan.getWeek(),plan.getMonth(),plan.getYear(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
            for (Meal m:i) {
                if(m.getIsFavorite())
                {
                    repository.updateDateInMeal(null,null,null,null,null,m.getIdMeal(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                }else{
                    repository.deleteMeal(m.getIdMeal(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                }
            }
        });
        repository.deletePlan(plan.getIdPlan(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
    @Override
    public void getMealsInPlan(PlanOfWeek plan) {

        repository.getAllMealsInPlan(plan.getWeek(),plan.getMonth(),plan.getYear(),uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
            if(i!=null) {
                targetShowPlanMeals=new ArrayList<>();
                targetShowPlanMeals.addAll(i);
                sendFirstDayInWeekMeals(plan.getWeek());
            }
        });
    }

    public void sendFirstDayInWeekMeals(String day){
        ArrayList<Meal>breakfast=new ArrayList<>();
        ArrayList<Meal>lunch=new ArrayList<>();
        ArrayList<Meal>dinner=new ArrayList<>();
        for (Meal i:targetShowPlanMeals) {
            if(i.getMeal_Day().equals(day))
            {
                if(i.getMeal_Time().equals("Breakfast"))
                {
                    breakfast.add(i);
                }else if(i.getMeal_Time().equals("Lunch"))
                {
                    lunch.add(i);

                }else if(i.getMeal_Time().equals("Dinner"))
                {
                    dinner.add(i);
                }
            }
        }
        planFragmentInterface.setData(breakfast,lunch,dinner);
    }

    @Override
    public void addPlan(PlanOfWeek plan) {
        plan.setUserId(uId);
        repository.insertPlan(plan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        plansInterface.updateList_AddPlan(plan);
    }
    @Override
    public String[] getUserData() {
        String[]data=new String[3];
        if(checkout)
        {

            data[1]= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail();
            data[0]=firebaseAuth.getCurrentUser().getDisplayName();
            nameProfile=data[0];
            data[2]=firebaseAuth.getCurrentUser().getUid();
            if(data[1]==null)
            {
                return  uData;
            }
        }else{
            data=uData;
        }
        return data;
    }

    @Override
    public ArrayList<String> getIngredinetsInMeal(Meal meal) {
        return filterMealFromNullVals(meal);
    }

    @Override
    public void backupYourData() {

           repository.getAllMeals(uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                   .subscribe(i->{
               backUpMeals(i);
           });

           repository.getPlans(uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
                backUpPlans(i);
            });

    }

    @Override
    public void retriveData() {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if(userId==null)
            {
                String []uDa=repository.getUserData();
                if(uDa!=null)
                    userId=repository.getUserData()[3];
            }
            if(userId!=null) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Users/" + userId);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Meal> mealsData = new ArrayList<>();
                        Iterator<DataSnapshot> iterator = snapshot.child("plans").getChildren().iterator();
                        Iterator<DataSnapshot> mealIterator = snapshot.child("meals").getChildren().iterator();
                        while (iterator.hasNext()) {
                            Map<String, String> planData = (Map<String, String>) iterator.next().getValue();

                            repository.insertPlan(new PlanOfWeek(planData.get("planWeek"), planData.get("planMonth"), planData.get("planYear"), planData.get("userId"))).subscribeOn(Schedulers.io()).onErrorComplete().observeOn(AndroidSchedulers.mainThread()).subscribe();
                            Log.e("", "Found Data................................");
                        }
                        if (!mealIterator.hasNext()) {
                            profileFragmentInterface.setRetriveDataRes(false);
                        } else {
                            Completable.fromAction(()->{
                            while (mealIterator.hasNext()) {
                                Map<String, String> mealData = (Map<String, String>) mealIterator.next().getValue();
                                Meal meal = new Meal(
                                        mealData.get("idMeal"),
                                        mealData.get("strMeal"),
                                        mealData.get("strCategory"),
                                        mealData.get("strArea"),
                                        mealData.get("strInstructions"),
                                        mealData.get("strMealThumb"),
                                        mealData.get("strYoutube"),
                                        mealData.get("strIngredient1"),
                                        mealData.get("strIngredient2"),
                                        mealData.get("strIngredient3"),
                                        mealData.get("strIngredient4"),
                                        mealData.get("strIngredient5"),
                                        mealData.get("strIngredient6"),
                                        mealData.get("strIngredient7"),
                                        mealData.get("strIngredient8"),
                                        mealData.get("strIngredient9"),
                                        mealData.get("strIngredient10"),
                                        mealData.get("strIngredient11"),
                                        mealData.get("strIngredient12"),
                                        mealData.get("strIngredient13"),
                                        mealData.get("strIngredient14"),
                                        mealData.get("strIngredient15"),
                                        mealData.get("strIngredient16"),
                                        mealData.get("strIngredient17"),
                                        mealData.get("strIngredient18"),
                                        mealData.get("strIngredient19"),
                                        mealData.get("strIngredient20"),
                                        mealData.get("strMeasure1"),
                                        mealData.get("strMeasure2"),
                                        mealData.get("strMeasure3"),
                                        mealData.get("strMeasure4"),
                                        mealData.get("strMeasure5"),
                                        mealData.get("strMeasure6"),
                                        mealData.get("strMeasure7"),
                                        mealData.get("strMeasure8"),
                                        mealData.get("strMeasure9"),
                                        mealData.get("strMeasure10"),
                                        mealData.get("strMeasure11"),
                                        mealData.get("strMeasure12"),
                                        mealData.get("strMeasure13"),
                                        mealData.get("strMeasure14"),
                                        mealData.get("strMeasure15"),
                                        mealData.get("strMeasure16"),
                                        mealData.get("strMeasure17"),
                                        mealData.get("strMeasure18"),
                                        mealData.get("strMeasure19"),
                                        mealData.get("strMeasure20"),
                                        mealData.get("isFavorite").equals("1"),
                                        mealData.get("meal_Time"),
                                        mealData.get("meal_Day"),
                                        mealData.get("meal_Week"),
                                        mealData.get("meal_Month"),
                                        mealData.get("meal_Year"),
                                        mealData.get("userId")
                                );

                                Glide.with(context).asBitmap().load(mealData.get("strMealThumb")).into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        meal.setImageBitmap(resource);
                                        repository.insertMeal(meal).subscribeOn(Schedulers.io()).subscribe();
                                    }
                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });

                            }}).subscribeOn(Schedulers.io())
                                   // .onErrorComplete()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnComplete(()->profileFragmentInterface.setRetriveDataRes(true))
                                    .subscribe();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }else{
                logout();
            }
    }

    private void backUpMeals(List<Meal>i)
    {
            if(i!=null) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (isLogedIn) {
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Users");
                    DatabaseReference usersRef = ref.child(userId).child("meals");
                    for (Meal meal : i) {
                        DatabaseReference mealref = usersRef.child("" + meal.getId());
                        Map<String, String> mealdata = new HashMap<>();
                        mealdata.put("id", "" + meal.getId());
                        mealdata.put("idMeal", meal.getIdMeal());
                        mealdata.put("strMeal", meal.getStrMeal());
                        mealdata.put("strCategory", meal.getStrCategory());
                        mealdata.put("strArea", meal.getStrArea());
                        mealdata.put("strInstructions", meal.getStrInstructions());
                        mealdata.put("strMealThumb", meal.getStrMealThumb());
                        mealdata.put("strYoutube", meal.getStrYoutube());
                        mealdata.put("strIngredient1", meal.getStrIngredient1());
                        mealdata.put("strIngredient2", meal.getStrIngredient2());
                        mealdata.put("strIngredient3", meal.getStrIngredient3());
                        mealdata.put("strIngredient4", meal.getStrIngredient4());
                        mealdata.put("strIngredient5", meal.getStrIngredient5());
                        mealdata.put("strIngredient6", meal.getStrIngredient6());
                        mealdata.put("strIngredient7", meal.getStrIngredient7());
                        mealdata.put("strIngredient8", meal.getStrIngredient8());
                        mealdata.put("strIngredient9", meal.getStrIngredient9());
                        mealdata.put("strIngredient10", meal.getStrIngredient10());
                        mealdata.put("strIngredient11", meal.getStrIngredient11());
                        mealdata.put("strIngredient12", meal.getStrIngredient12());
                        mealdata.put("strIngredient13", meal.getStrIngredient13());
                        mealdata.put("strIngredient14", meal.getStrIngredient14());
                        mealdata.put("strIngredient15", meal.getStrIngredient15());
                        mealdata.put("strIngredient16", meal.getStrIngredient16());
                        mealdata.put("strIngredient17", meal.getStrIngredient17());
                        mealdata.put("strIngredient18", meal.getStrIngredient18());
                        mealdata.put("strIngredient19", meal.getStrIngredient19());
                        mealdata.put("strIngredient20", meal.getStrIngredient20());
                        mealdata.put("strMeasure1", meal.getStrMeasure1());
                        mealdata.put("strMeasure2", meal.getStrMeasure2());
                        mealdata.put("strMeasure3", meal.getStrMeasure3());
                        mealdata.put("strMeasure4", meal.getStrMeasure4());
                        mealdata.put("strMeasure5", meal.getStrMeasure5());
                        mealdata.put("strMeasure6", meal.getStrMeasure6());
                        mealdata.put("strMeasure7", meal.getStrMeasure7());
                        mealdata.put("strMeasure8", meal.getStrMeasure8());
                        mealdata.put("strMeasure9", meal.getStrMeasure9());
                        mealdata.put("strMeasure10", meal.getStrMeasure10());
                        mealdata.put("strMeasure11", meal.getStrMeasure11());
                        mealdata.put("strMeasure12", meal.getStrMeasure12());
                        mealdata.put("strMeasure13", meal.getStrMeasure13());
                        mealdata.put("strMeasure14", meal.getStrMeasure14());
                        mealdata.put("strMeasure15", meal.getStrMeasure15());
                        mealdata.put("strMeasure16", meal.getStrMeasure16());
                        mealdata.put("strMeasure17", meal.getStrMeasure17());
                        mealdata.put("strMeasure18", meal.getStrMeasure18());
                        mealdata.put("strMeasure19", meal.getStrMeasure19());
                        mealdata.put("strMeasure20", meal.getStrMeasure20());
                        mealdata.put("isFavorite", "" + (meal.getIsFavorite()?"1":"0"));
                        mealdata.put("meal_Time", meal.getMeal_Time());
                        mealdata.put("meal_Day", meal.getMeal_Day());
                        mealdata.put("meal_Week", meal.getMeal_Week());
                        mealdata.put("meal_Month", meal.getMeal_Month());
                        mealdata.put("meal_Year", meal.getMeal_Year());
                        mealdata.put("userId", meal.getUserId());
                        mealref.setValue(mealdata);
                    }
                }
                profileFragmentInterface.setBackUpRes(true);
            }else{
                profileFragmentInterface.setBackUpRes(false);
            }

    }
    private void backUpPlans(List<PlanOfWeek>i)
    {
            if(i!=null) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (isLogedIn) {
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Users");

                    DatabaseReference usersRef = ref.child(userId).child("plans");
                    for (PlanOfWeek plan : i) {
                        DatabaseReference refplan = usersRef.child("" + plan.getIdPlan());
                        Map<String, String> pl = new HashMap<>();
                        pl.put("planId", "" + plan.getIdPlan());
                        pl.put("planYear", plan.getYear());
                        pl.put("planMonth", plan.getMonth());
                        pl.put("planWeek", plan.getWeek());
                        pl.put("userId", plan.getUserId());
                        refplan.setValue(pl);
                    }
                }
                profileFragmentInterface.setBackUpRes(true);
             }else {
                    profileFragmentInterface.setBackUpRes(false);
                }
    }

    @Override
    public void doLogin(String email, String pass) {

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener(authResult -> {
                    isLogedIn=true;
                    checkout=true;
                    uId=email;
                    LogInFragmentInterface.onLoginResult(true);
                    repository.setUserData(Objects.requireNonNull(authResult.getUser()).getDisplayName(),authResult.getUser().getEmail(),authResult.getUser().getUid());

                }).addOnFailureListener(e -> {
                    isLogedIn=false;
                    checkout=false;
                    repository.setUserData("","","");
                    LogInFragmentInterface.onLoginResult(false);
                });
    }

    @Override
    public void logout() {
        this.isLogedIn = false;
        checkout = false;
        uId="";
        FirebaseAuth.getInstance().signOut();
        repository.deleteUserData();
    }

    @Override
    public void setIsLogedIn(boolean isLogedIn) {
        this.isLogedIn=isLogedIn;
    }

    @Override
    public void deleteMealInPlan(String mealId,String mealDay,String mealTime, PlanOfWeek plan) {
                    repository.updateDateInMeal(null,null,null,null,null,mealId,uId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(this::removeUnneeded).subscribe();

    }
public  void removeUnneeded()
{
    repository.removeUnneeded().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();

}
    public void setPlanInterface(PlanFragmentInterface planInterface) {
        planFragmentInterface=planInterface;
    }

    @Override
    public void putUserData(String userName, String email, String password) {

               uData[0]=userName;
               nameProfile=userName;
               firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(s->{
                   isLogedIn=true;
                   checkout=true;
                   uId=email;
                   signupFragmentInterface.getSignUpStatus(true);
                   repository.setUserData(userName,email,password);
               })
               .addOnFailureListener(f->{isLogedIn=false; checkout=false; Log.e("",f.toString());});
    }
    public void SignUpGoogle(String userName, String email, String password)
    {
        uId=email;
        uData[0]=userName;
        nameProfile=userName;
        checkout=true;
        isLogedIn=true;
        repository.setUserData(userName,email,password);
    }


    @Override
    public void UpdateMealOfFavouriteList(Boolean isFav, String Meal) {

       repository.updateFavoriteInMeal(isFav, Meal,uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(()->removeUnneeded());

      }

    public void getAllPlans() {
        repository.getPlans(uId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(i->{
            plansInterface.setPlansData(i);
        }).subscribe();

    }

    @Override
    public void setTargetAddMealToPlanPlanData(PlanOfWeek plan) {
        if(targetMeal!=null) {
            targetMeal.setMeal_Year(plan.getYear());
            targetMeal.setMeal_Month(plan.getMonth());
            targetMeal.setMeal_Week(plan.getWeek());
        }
    }

    @Override
    public void setTargetMealDayAndTime(String day, String time) {
        targetMeal.setMeal_Day(day);
        targetMeal.setMeal_Time(time);
        targetMeal.setUserId(uId);
        repository.getAllFavLikeMeal(targetMeal.getIdMeal(),uId,true).subscribeOn(Schedulers.io()).onErrorComplete().observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
             int flag=0;
            for (Meal m:i) {
                if(m.getIsFavorite())
                {
                    targetMeal.setIsFavorite(true);

                    repository.insertMeal(targetMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                    flag=1;
                    break;
                }

            }
            if(flag==0)
            {
                repository.insertMeal(targetMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
            }
        });
         plansInterface.setTarget("showPlan");

    }

    public void googleSignIn(String email,String name,String uid){
        isLogedIn=true;
        checkout=true;
        this.uId=email;
        LogInFragmentInterface.onLoginResult(true);
        repository.setUserData(name,email,uid);
    }
    @Override
    public void getAllAreasForFilter(){
           repository.enqueueCallListAllArea_Just_Names(this, context);
    }
    @Override
    public  void getAllIngredientsForFilter()
    {
        repository.enqueueCallListAllIngredients_Just_Names(this,context);
    }


}
