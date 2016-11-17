package de.xappo.presenterinjection.di.modules;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.interactor.MainInteractor;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    MainInteractor provideMainInteractor () {
        return new MainInteractor();
    }
}
