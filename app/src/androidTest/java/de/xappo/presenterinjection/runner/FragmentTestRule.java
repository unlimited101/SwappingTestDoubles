package de.xappo.presenterinjection.runner;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.TestActivity;

/**
 * Own FragmentTestRule class to test a Fragment independent from Activities which contain in
 * in the production environment
 * @param <F>
 */

public class FragmentTestRule<F extends Fragment> extends ActivityTestRule<TestActivity> {
    private static final String TAG = "FragmentTestRule";
    private final Class<F> mFragmentClass;
    private F mFragment;

    public FragmentTestRule(final Class<F> fragmentClass) {
        super(TestActivity.class, true, false);
        mFragmentClass = fragmentClass;
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        try {
            mFragment = mFragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();

        //Instantiate and insert the fragment into the container layout
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragmentContainer, mFragment);
        transaction.commit();
    }


    public F getFragment() {
        return mFragment;
    }
}
