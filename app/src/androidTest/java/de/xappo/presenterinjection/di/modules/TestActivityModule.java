package de.xappo.presenterinjection.di.modules;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.fake.FakeMainInteractor;
import de.xappo.presenterinjection.interactor.MainInteractor;

@Module
public class TestActivityModule {
    @Provides
    @PerActivity
    MainInteractor provideMainInteractor () {
        return new FakeMainInteractor();
    }
}
