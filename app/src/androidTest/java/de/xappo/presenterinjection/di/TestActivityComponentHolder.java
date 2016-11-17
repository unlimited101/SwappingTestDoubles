package de.xappo.presenterinjection.di;

import android.app.Activity;
import android.support.annotation.NonNull;

import de.xappo.presenterinjection.di.components.TestActivityComponent;

import static de.xappo.presenterinjection.utils.ComponentRegisterCheckerUtil.checkRegistered;


/**
 * Because neither the Activity nor the ActivityTest can hold the TestActivityComponent (due to
 * runtime order problems we need to hold it statically
 **/
public class TestActivityComponentHolder {
    private static TestActivityComponent sComponent;
    private static ComponentCreator sCreator;

    public interface ComponentCreator {
        TestActivityComponent createComponent(Activity activity);
    }

    /**
     * Configures an ComponentCreator that is used to create an activity graph. Call that in @Before
     *
     * @param creator The creator
     */
    public static void setCreator(ComponentCreator creator) {
        sCreator = creator;
    }

    /**
     * Releases the static instances of our creator and graph. Call that in @After.
     */
    public static void release() {
        sCreator = null;
        sComponent = null;
    }

    /**
     * Returns the {@link TestActivityComponent} or creates a new one using the registered {@link
     * ComponentCreator}
     *
     * @throws IllegalStateException if no creator has been registered before
     */
    @NonNull
    public static TestActivityComponent getComponent(Activity activity) {
        if (sComponent == null) {
            checkRegistered(sCreator != null, "no creator registered");
            sComponent = sCreator.createComponent(activity);
        }
        return sComponent;
    }


    /**
     * Returns true if a custom activity component creator was configured for the current test run
     */
    public static boolean hasComponentCreator() {
        return sCreator != null;
    }

    /**
     * Returns a previously instantiated {@link TestActivityComponent}.
     *
     * @throws IllegalStateException if none has been instantiated
     */
    @NonNull
    public static TestActivityComponent getComponent() {
        checkRegistered(sComponent != null, "no component created");
        return sComponent;
    }
}
