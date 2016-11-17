package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.TestApplicationModule;
import de.xappo.presenterinjection.view.MainActivityTest;

@Singleton
@Component(modules = TestApplicationModule.class)
public interface TestApplicationComponent extends ApplicationComponent{
    void inject(MainActivityTest mainActivityTest);
}
