package de.xappo.presenterinjection.base;

import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import de.xappo.presenterinjection.di.TestActivityComponentHolder;
import de.xappo.presenterinjection.di.components.DaggerTestActivityComponent;
import de.xappo.presenterinjection.di.components.TestActivityComponent;
import de.xappo.presenterinjection.di.modules.TestActivityModule;
import de.xappo.presenterinjection.di.components.InjectsComponent;

public class ActivityTest implements TestActivityComponentHolder.ComponentCreator {

    @Rule
    public IntentsTestRule<TestActivity> activityRule = new IntentsTestRule<>(TestActivity.class, true, false);

    @Before
    public void createActivityGraphAndSetupView() {
        TestActivityComponentHolder.setCreator(this);
        TestActivity activity = getActivity();
        injectActivityGraph();
    }

    @After
    public void cleanUp() {
        TestActivityComponentHolder.release();
    }


    @SuppressWarnings("unchecked")
    private void injectActivityGraph() {
        ((InjectsComponent<TestActivityComponent>) this).injectComponent(TestActivityComponentHolder.getComponent());
    }

    @Override
    public TestActivityComponent createComponent(final Activity activity) {
        return DaggerTestActivityComponent.builder()
                .testActivityModule(new TestActivityModule())
                .build();
    }

    private TestActivity getActivity() {
        TestActivity activity = activityRule.getActivity();
        if (activity == null) {
            activity = activityRule.launchActivity(null);
        }
        return activity;
    }
}
