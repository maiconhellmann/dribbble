package br.com.maiconhellmann.dribbble.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.maiconhellmann.dribbble.DribbbleApplication;
import br.com.maiconhellmann.dribbble.injection.component.ConfigPersistentComponent;
import br.com.maiconhellmann.dribbble.injection.component.DaggerConfigPersistentComponent;
import timber.log.Timber;

public class BaseFragment extends Fragment {

    private static final String KEY_ACTIVITY_ID = "KEY_FRAGMENT_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();

    private long fragmentId;
    private ConfigPersistentComponent configPersistentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        fragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        if (!sComponentsMap.containsKey(fragmentId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", fragmentId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(DribbbleApplication.get(getContext()).getComponent())
                    .build();
            sComponentsMap.put(fragmentId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", fragmentId);
            configPersistentComponent = sComponentsMap.get(fragmentId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, fragmentId);
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", fragmentId);
            sComponentsMap.remove(fragmentId);
        }
        super.onDestroy();
    }

    public ConfigPersistentComponent getConfigPersistentComponent() {
        return configPersistentComponent;
    }
}
