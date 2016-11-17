package de.xappo.presenterinjection.di.modules;

import de.xappo.presenterinjection.interactor.MainInteractor;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.base.AndroidApplication;
import javax.inject.Singleton;

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MainInteractor provideMainInteractor () {
        return new MainInteractor();
    }

}
