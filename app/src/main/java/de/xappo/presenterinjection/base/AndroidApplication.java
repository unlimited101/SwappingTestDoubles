package de.xappo.presenterinjection.base;

import android.app.Application;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.components.DaggerApplicationComponent;
import de.xappo.presenterinjection.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private static final String TAG = "AndroidApplication";
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        Log.i(TAG, "injectDagger initializeInjector()");
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @VisibleForTesting
    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;

    }
}
