package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.view.MainActivity;

@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(BaseActivity baseActivity);

}
