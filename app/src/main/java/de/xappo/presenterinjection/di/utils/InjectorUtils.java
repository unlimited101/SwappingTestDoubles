package de.xappo.presenterinjection.di.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.components.DaggerActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerFragmentComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.modules.FragmentModule;

public final class InjectorUtils {

    public static void setUp(Activity activity) {
        setupWithContext(activity.getApplicationContext(), activity);
    }

    public static void setUp(Fragment fragment) {
        setupWithContext(fragment.getContext().getApplicationContext(), fragment);
    }

    @SuppressWarnings("unchecked")
    private static void setupWithContext(final Context context, final Activity activity) {
        AndroidApplication app = getApplication(context);

        // TODO: Only optional if ActivityComponent depends on ApplicationComponent
        ApplicationComponent applicationComponent = app.getApplicationComponent();

        ActivityComponent activityComponent = getOrCreateActivityComponent(activity);

        ((InjectsComponent<ActivityComponent>) activity).injectComponent(activityComponent);
    }

    @SuppressWarnings("unchecked")
    private static void setupWithContext(final Context context, final Fragment fragment) {
        AndroidApplication app = getApplication(context);

        // TODO: Only optional if ActivityComponent depends on ApplicationComponent
        ApplicationComponent applicationComponent = app.getApplicationComponent();

        // TODO: Only optional if FragmentComponent depends on ActivityComponent
        ActivityComponent activityComponent = ((BaseActivity) fragment.getActivity()).getComponent();

        FragmentComponent fragmentComponent = getOrCreateFragmentComponent(fragment);

        ((InjectsComponent<FragmentComponent>) fragment).injectComponent(fragmentComponent);
    }


    @SuppressWarnings("unchecked")
    private static ActivityComponent getOrCreateActivityComponent(final Activity activity) {
        HasComponent<ActivityComponent> holder = (HasComponent<ActivityComponent>) activity;
        if (holder.getComponent() == null) {
            holder.setComponent(createActivityComponent());
        }
        return holder.getComponent();
    }

    @SuppressWarnings("unchecked")
    private static FragmentComponent getOrCreateFragmentComponent(final Fragment fragment) {
        HasComponent<FragmentComponent> holder = (HasComponent<FragmentComponent>) fragment;
        if (holder.getComponent() == null) {
            holder.setComponent(createFragmentComponent());
        }
        return holder.getComponent();

    }

    private static ActivityComponent createActivityComponent() {
        return DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule())
                .build();
    }


    private static FragmentComponent createFragmentComponent() {
        return DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule())
                .build();
    }

    private static AndroidApplication getApplication(Context context) {
        return (AndroidApplication) context.getApplicationContext();
    }

}
