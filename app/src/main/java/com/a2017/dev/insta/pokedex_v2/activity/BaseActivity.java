package com.a2017.dev.insta.pokedex_v2.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Telest on 17/11/2017.
 * Extends this activity for OLD SCHOOL FONTS ! HAIL POKEMON !!
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context context){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
}