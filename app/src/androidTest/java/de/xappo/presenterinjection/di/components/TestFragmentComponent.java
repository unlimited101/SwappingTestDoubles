package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.TestFragmentModule;
import de.xappo.presenterinjection.di.scopes.PerFragment;
import de.xappo.presenterinjection.view.MainActivityTest;
import de.xappo.presenterinjection.view.MainFragmentTest;

@PerFragment
@Component(modules = TestFragmentModule.class)
public interface TestFragmentComponent extends FragmentComponent{
    void inject(MainActivityTest mainActivityTest);

    void inject(MainFragmentTest mainFragmentTest);
}
