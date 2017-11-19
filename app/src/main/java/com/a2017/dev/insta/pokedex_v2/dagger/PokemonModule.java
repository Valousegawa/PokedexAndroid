package com.a2017.dev.insta.pokedex_v2.dagger;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Telest on 16/11/2017.
 */

@Module
public class PokemonModule {

    public static final String MAIN_THREAD = "mainThread";
    public static final String BACKGROUND_THREAD= "backgroundThread";

    @Provides
    @Singleton
    @Named(BACKGROUND_THREAD)
    Executor provideBackgroundExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    @Named(MAIN_THREAD)
    Executor provideMainExecutor(){
        return new HandlerExecutor(new Handler(Looper.getMainLooper()));
    }

    private class HandlerExecutor implements Executor{
        private Handler handler;

        public HandlerExecutor(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void execute(@NonNull final Runnable runnable) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            });
        }
    }
}
