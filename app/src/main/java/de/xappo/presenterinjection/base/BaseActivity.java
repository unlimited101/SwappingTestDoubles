package de.xappo.presenterinjection.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.utils.InjectorUtils;
import de.xappo.presenterinjection.di.components.InjectsComponent;

public abstract class  BaseActivity extends AppCompatActivity implements
        HasComponent<ActivityComponent>, InjectsComponent<ActivityComponent> {

    private static final String TAG = "BaseActivity";

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectorUtils.setUp(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

    @VisibleForTesting
    @Override
    public void setComponent(final ActivityComponent component) {
        this.activityComponent = component;
    }

    @Override
    public void injectComponent(final ActivityComponent component) {
        component.inject(this);
    }

}
