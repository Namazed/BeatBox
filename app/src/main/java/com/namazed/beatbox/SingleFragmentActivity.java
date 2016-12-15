package com.namazed.beatbox;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class SingleFragmentActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_fragment;
    private static final int CONTAINER_FRAGMENT = R.id.fragment_container;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(CONTAINER_FRAGMENT);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction().add(CONTAINER_FRAGMENT, fragment).commit();
        }
    }
}
