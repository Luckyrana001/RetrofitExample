package com.example.ranalucky.retrofitexample.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

/**
 * Created by Rana lucky on 8/14/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private Locale mCurrentLocale;

    @Override
    protected void onStart() {
        super.onStart();

        mCurrentLocale = getResources().getConfiguration().locale;
        setLocale(0);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
        setLocale(0);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLocale(int type) {

        final Resources resources = getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = getLocale(this,type);
        if (!configuration.locale.equals(locale)) {
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, null);
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Locale locale = getLocale(this,0);

        if (!locale.equals(mCurrentLocale)) {

            mCurrentLocale = locale;
            recreate();
        }
    }

    public  Locale getLocale(Context context,int type){/*
       SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

       String lang = sharedPreferences.getString("language", "en");
*/

        SharedPreferences prefs = getSharedPreferences("langPrefrence",MODE_PRIVATE);
        String lang = "";
        if(type==0) {
             lang = prefs.getString("language", "Arabic");
        }
        else
        {
            lang = "English";
        }
       // String lang = "Arabic";

        switch (lang) {
            case "English":
                lang = "en";
                break;
            case "Arabic":
                lang = "ar";
                break;
        }
        return new Locale(lang);
    }
}
