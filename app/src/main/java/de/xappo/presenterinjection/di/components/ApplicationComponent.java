package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
}
