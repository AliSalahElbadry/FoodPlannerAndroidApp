package com.app.our.foodplanner.network;

import java.util.ArrayList;

public interface NetworkDelegate {
    public void onSuccessResults(/*ArrayList<Product> products*/);
    public void onFailureResults(String msg);
}
