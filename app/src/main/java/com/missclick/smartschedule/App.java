package com.missclick.smartschedule;

import android.app.Application;
import com.missclick.smartschedule.di.AppComponent;
import com.missclick.smartschedule.di.DaggerAppComponent;
import com.missclick.smartschedule.di.module.AppModule;

public class App extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initializeDagger();
    }

    private AppComponent initializeDagger(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
