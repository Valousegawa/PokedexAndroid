package com.a2017.dev.insta.pokedex_v2;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Telest on 17/11/2017.
 */

public class PokedexApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/pkmn_font.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
