package de.xappo.presenterinjection.di.modules;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.scopes.PerFragment;
import de.xappo.presenterinjection.interactor.MainInteractor;

@Module
public class FragmentModule {
    @Provides
    @PerFragment
    MainInteractor provideMainInteractor () {
        return new MainInteractor();
    }
}
