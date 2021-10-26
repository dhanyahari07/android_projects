package com.example.myviewmodelapplication;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MainActivityDataGenerator extends ViewModel {
    private String TAG=this.getClass().getSimpleName();
    private String myRandomNumber;

    public String getNumber(){
        Log.i(TAG,"get number");
        if(myRandomNumber==null)
        {
            createNumber();
        }
        return myRandomNumber;

    }
    private void createNumber(){
        Log.i(TAG,"cretae new number");
        Random random=new Random();
        myRandomNumber="Number"+(random.nextInt(10-1)+1);
    }

   /* @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"View model destroyed");
    }*/
}
