package com.app.our.foodplanner.app_vp.view.presenter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.app.our.foodplanner.app_vp.view.favorite.FavouriteFragmentInterface;
import com.app.our.foodplanner.app_vp.view.home.HomeFragmentInterface;
import com.app.our.foodplanner.app_vp.view.login.LogInFragmentInterface;
import com.app.our.foodplanner.app_vp.view.meal.MealFragmentInterface;
import com.app.our.foodplanner.app_vp.view.plan.PlanFragmentInterface;
import com.app.our.foodplanner.app_vp.view.plans.PlansFragmentInterface;
import com.app.our.foodplanner.app_vp.view.profile.ProfileFragmentInterface;
import com.app.our.foodplanner.app_vp.view.signup.SignupFragmentInterface;
import com.app.our.foodplanner.model.Area;
import com.app.our.foodplanner.model.Category;
import com.app.our.foodplanner.model.Ingredient;
import com.app.our.foodplanner.model.Meal;
import com.app.our.foodplanner.model.PlanOfWeek;
import com.app.our.foodplanner.model.Repository;
import com.app.our.foodplanner.network.NetworkDelegate;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Presenter implements NetworkDelegate , PresenterInterface {
    //Data Holders

    private LogInFragmentInterface LogInFragmentInterface;
    private ProfileFragmentInterface profileFragmentInterface;

    private FavouriteFragmentInterface favouriteFragmentInterface;
    public void setLogInFragmentInterface(LogInFragmentInterface LogInFragmentInterface) {
        this.LogInFragmentInterface = LogInFragmentInterface;
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
    private boolean isLogedIn;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInOptions googleSignInOptions;
    MealFragmentInterface mealFragmentInterface;
    private HomeFragmentInterface homeFragment;
    private PlansFragmentInterface plansInterface;
    private  PlanFragmentInterface planFragmentInterface;


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

    //end Data Holders

    public Presenter(Context context)
    {

        this.context=context;
        repository=Repository.getInstance(context);
        uData=repository.getUserData();
        if(uData!=null)
        {
            if (!uData[0].isEmpty()){
                isLogedIn=true;
            }
        }
        meals=new ArrayList<>();
        categories=new ArrayList<>();
        ingredients=new ArrayList<>();
        areas=new ArrayList<>();
        plans=new ArrayList<>();
        uData=repository.getUserData();
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null) {
            if (user.isEmailVerified()) {
                isLogedIn = true;
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

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<PlanOfWeek> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<PlanOfWeek> plans) {
        this.plans = plans;
    }

    public String[] getuData() {
        return uData;
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
        homeFragment.showCategories(Res);
    }

    @Override
    public void listAllArea_Just_NamesOnSuccessResults(ArrayList< Area > Res) {

    }

    @Override
    public void listAllIngredients_Just_NamesOnSuccessResults(ArrayList< Ingredient > Res) {

    }

    @Override
    public void filterByMainIngredientOnSuccessResults(ArrayList<Meal> Res) {

    }

    @Override
    public void filterByCategoryOnSuccessResults(ArrayList<Meal> Res) {
        meals=Res;
        homeFragment.showMeals(Res);
    }

    @Override
    public void filterByAreaOnSuccessResults(ArrayList<Meal> Res) {

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
    //end home functions

    //Meal Page functions
    @Override
    public void addToFav(Meal meal) {
        meal.setIsFavorite(true);
        repository.insertMeal(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(()->{
            mealFragmentInterface.setAddFavRes(true);
        }).subscribe();
        repository.updateFavoriteInMeal(true,meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void addToPlan(Meal meal) {

    }

    @Override
    public void showVideo(String url) {

    }

    @Override
    public void setTargetAddMealToPlan(Meal meal) {
        targetMeal=meal;
    }

    @Override
    public void removePlan(PlanOfWeek plan) {
        repository.deletePlan(plan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
    @Override
    public void getMealsInPlan(PlanOfWeek plan) {
        repository.getAllMealsInPlan(plan.getWeek(),plan.getMonth(),plan.getYear()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(i->{
            if(i!=null) {
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
        repository.insertPlan(plan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        plansInterface.updateList_AddPlan(plan);
    }

    @Override
    public String[] getUserData() {
        String[]data=new String[3];
        Log.i(TAG, "getUserData: isLogedIn "+isLogedIn);
        if(checkout==true)
        {

            data[1]=firebaseAuth.getCurrentUser().getEmail();
            Log.i(TAG, "getUserData:////// "+data[1]);
            data[0]=firebaseAuth.getCurrentUser().getDisplayName();
            data[0]=nameProfile;
            data[2]=firebaseAuth.getCurrentUser().getUid();
        }
        if(checkout==false){
            Log.i(TAG, "checkout: "+checkout);
        }

        return data;
    }
    @Override
    public void doLogin(String email, String pass) {

        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    isLogedIn=true;
                    checkout=true;
                    LogInFragmentInterface.onLoginResult(isLogedIn);
                    repository.setUserData(email,email,pass);
                    FirebaseUser user=firebaseAuth.getCurrentUser();
                    uData=new String[3];
                    uData[0]=nameProfile;
                    uData[1]=user.getEmail();
                    uData[2]=user.getUid();
                    repository.setUserData(uData[0],uData[1],uData[2]);
                }
                else{
                    isLogedIn=false;
                    checkout=false;
                    Log.i(TAG, "login fail: "+checkout+" "+isLogedIn);
                    LogInFragmentInterface.onLoginResult(isLogedIn);
                }
            }
        });
    }

    @Override
    public void logout() {
        isLogedIn = false;
        checkout = false;
        Log.i(TAG, "logout: " + checkout + " " + isLogedIn);
        firebaseAuth.signOut();
    }
    public void setPlanInterface(PlanFragmentInterface planInterface) {
        planFragmentInterface=planInterface;
    }

    @Override
    public void putUserData(String userName, String email, String password) {

             nameProfile=userName;
             uData[0]=nameProfile;

        Log.i(TAG, "putUserData: "+nameProfile);
               firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(s->{
                   isLogedIn=true;
                   checkout=true;
                   signupFragmentInterface.getSignUpStatus(isLogedIn);
               })
               .addOnFailureListener(f->{isLogedIn=false; checkout=false; Log.e("",f.toString());});
        if(isLogedIn)
        {
            repository.setUserData(userName,email,password);
        }
    }

    @Override

    public void deleteToFav(Meal mealdelete) {
        repository.deleteMeal(mealdelete);
    }

    @Override
    public Observable<List<Meal>> getAllFavouriteList() {
       return repository.getAllFavMealsLive(true);
    }

    @Override
    public void UpdateMealOfFavouriteList(Boolean isFav, String Meal) {
        repository.updateFavoriteInMeal(isFav,Meal);
    }

    public void getAllPlans() {
        repository.getPlans().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(i->{
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
        repository.insertMeal(targetMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        plansInterface.setTarget("showPlan");
    }

}
