package com.app.our.foodplanner.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

@Entity(tableName="Meals")
public class Meal {
    @SerializedName("idMeal")
    @ColumnInfo(name="idMeal")
    private String idMeal;
    @SerializedName("strMeal")
    @ColumnInfo(name="strMeal")
    private String strMeal;
    @SerializedName("strCategory")
    @ColumnInfo(name="strCategory")
    private String strCategory;
    @SerializedName("strArea")
    @ColumnInfo(name="strArea")
    private String strArea;
    @SerializedName("strInstructions")
    @ColumnInfo(name="strInstructions")
    private String strInstructions;
    @SerializedName("strMealThumb")
    @ColumnInfo(name="strMealThumb")
    private String strMealThumb;
    @SerializedName("strYoutube")
    @ColumnInfo(name="strYoutube")
    private String strYoutube;
    @SerializedName("strIngredient1")
    @ColumnInfo(name="strIngredient1")
    private String strIngredient1;
    @SerializedName("strIngredient2")
    @ColumnInfo(name="strIngredient2")
    private String strIngredient2;
    @SerializedName("strIngredient3")
    @ColumnInfo(name="strIngredient3")
    private String strIngredient3;
    @SerializedName("strIngredient4")
    @ColumnInfo(name="strIngredient4")
    private String strIngredient4;
    @SerializedName("strIngredient5")
    @ColumnInfo(name="strIngredient5")
    private String strIngredient5;
    @SerializedName("strIngredient6")
    @ColumnInfo(name="strIngredient6")
    private String strIngredient6;
    @SerializedName("strIngredient7")
    @ColumnInfo(name="strIngredient7")
    private String strIngredient7;
    @SerializedName("strIngredient8")
    @ColumnInfo(name="strIngredient8")
    private String strIngredient8;
    @SerializedName("strIngredient9")
    @ColumnInfo(name="strIngredient9")
    private String strIngredient9;
    @SerializedName("strIngredient10")
    @ColumnInfo(name="strIngredient10")
    private String strIngredient10;
    @SerializedName("strIngredient11")
    @ColumnInfo(name="strIngredient11")
    private String strIngredient11;
    @SerializedName("strIngredient12")
    @ColumnInfo(name="strIngredient12")
    private String strIngredient12;
    @SerializedName("strIngredient13")
    @ColumnInfo(name="strIngredient13")
    private String strIngredient13;
    @SerializedName("strIngredient14")
    @ColumnInfo(name="strIngredient14")
    private String strIngredient14;
    @SerializedName("strIngredient15")
    @ColumnInfo(name="strIngredient15")
    private String strIngredient15;
    @SerializedName("strIngredient16")
    @ColumnInfo(name="strIngredient16")
    private String strIngredient16;
    @SerializedName("strIngredient17")
    @ColumnInfo(name="strIngredient17")
    private String strIngredient17;
    @SerializedName("strIngredient18")
    @ColumnInfo(name="strIngredient18")
    private String strIngredient18;
    @SerializedName("strIngredient19")
    @ColumnInfo(name="strIngredient19")
    private String strIngredient19;
    @SerializedName("strIngredient20")
    @ColumnInfo(name="strIngredient20")
    private String strIngredient20;
    @SerializedName("strMeasure1")
    @ColumnInfo(name="strMeasure1")
    private String strMeasure1;
    @SerializedName("strMeasure2")
    @ColumnInfo(name="strMeasure2")
    private String strMeasure2;
    @SerializedName("strMeasure3")
    @ColumnInfo(name="strMeasure3")
    private String strMeasure3;
    @SerializedName("strMeasure4")
    @ColumnInfo(name="strMeasure4")
    private String strMeasure4;
    @SerializedName("strMeasure5")
    @ColumnInfo(name="strMeasure5")
    private String strMeasure5;
    @SerializedName("strMeasure6")
    @ColumnInfo(name="strMeasure6")
    private String strMeasure6;
    @SerializedName("strMeasure7")
    @ColumnInfo(name="strMeasure7")
    private String strMeasure7;
    @SerializedName("strMeasure8")
    @ColumnInfo(name="strMeasure8")
    private String strMeasure8;
    @SerializedName("strMeasure9")
    @ColumnInfo(name="strMeasure9")
    private String strMeasure9;
    @SerializedName("strMeasure10")
    @ColumnInfo(name="strMeasure10")
    private String strMeasure10;
    @SerializedName("strMeasure11")
    @ColumnInfo(name="strMeasure11")
    private String strMeasure11;
    @SerializedName("strMeasure12")
    @ColumnInfo(name="strMeasure12")
    private String strMeasure12;
    @SerializedName("strMeasure13")
    @ColumnInfo(name="strMeasure13")
    private String strMeasure13;
    @SerializedName("strMeasure14")
    @ColumnInfo(name="strMeasure14")
    private String strMeasure14;
    @SerializedName("strMeasure15")
    @ColumnInfo(name="strMeasure15")
    private String strMeasure15;
    @SerializedName("strMeasure16")
    @ColumnInfo(name="strMeasure16")
    private String strMeasure16;
    @SerializedName("strMeasure17")
    @ColumnInfo(name="strMeasure17")
    private String strMeasure17;
    @ColumnInfo(name="mealDate")
    private Date mealDate;
    @SerializedName("strMeasure18")
    @ColumnInfo(name="strMeasure18")
    private String strMeasure18;
    @SerializedName("strMeasure19")
    @ColumnInfo(name="strMeasure19")
    private String strMeasure19;
    @SerializedName("strMeasure20")
    @ColumnInfo(name="strMeasure20")
    private String strMeasure20;
    @ColumnInfo(name="isFavorite")
    private boolean isFavorite;
    @ColumnInfo(name="meal_Time")
    @Nullable
    private String meal_Time;
    @ColumnInfo(name="meal_Day")
    @Nullable
    private String meal_Day;
    @ColumnInfo(name="meal_Week")
    @Nullable
    private String meal_Week;

    @ColumnInfo(name="meal_Month")
    @Nullable
    private String meal_Month;


    @ColumnInfo(name="meal_Year")
    @Nullable
    private String meal_Year;


    public boolean isFavorite() {
        return isFavorite;
    }

    @Nullable
    public String getMeal_Day() {
        return meal_Day;
    }

    public void setMeal_Day(@Nullable String meal_Day) {
        this.meal_Day = meal_Day;
    }

    @Nullable
    public String getMeal_Week() {
        return meal_Week;
    }

    public void setMeal_Week(@Nullable String meal_Week) {
        this.meal_Week = meal_Week;
    }

    @Nullable
    public String getMeal_Month() {
        return meal_Month;
    }

    public void setMeal_Month(@Nullable String meal_Month) {
        this.meal_Month = meal_Month;
    }

    @Nullable
    public String getMeal_Year() {
        return meal_Year;
    }

    public void setMeal_Year(@Nullable String meal_Year) {
        this.meal_Year = meal_Year;
    }

    public Meal(String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube, String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4, String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8, String strIngredient9, String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13, String strIngredient14, String strIngredient15, String strIngredient16, String strIngredient17, String strIngredient18, String strIngredient19, String strIngredient20, String strMeasure1, String strMeasure2, String strMeasure3, String strMeasure4, String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8, String strMeasure9, String strMeasure10, String strMeasure11, String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16, String strMeasure17, String strMeasure18, String strMeasure19, String strMeasure20, boolean isFavorite, @Nullable String meal_Time, @Nullable String meal_Day, @Nullable String meal_Week, @Nullable String meal_Month, @Nullable String meal_Year) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
        this.isFavorite = isFavorite;
        this.meal_Time = meal_Time;
        this.meal_Day = meal_Day;
        this.meal_Week = meal_Week;
        this.meal_Month = meal_Month;
        this.meal_Year = meal_Year;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    public boolean getFavorite() {
        return isFavorite;
    }
    public String getMeal_Time() {
        return meal_Time;
    }

    public void setMeal_Time(String meal_Time) {
        this.meal_Time = meal_Time;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }
}