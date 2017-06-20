package br.com.maiconhellmann.dribbble.injection.component;

import br.com.maiconhellmann.dribbble.ui.shot.detail.ShotDetailFragment;
import br.com.maiconhellmann.dribbble.ui.shot.detail.UserFragment;
import dagger.Component;
import br.com.maiconhellmann.dribbble.injection.ConfigPersistent;
import br.com.maiconhellmann.dribbble.injection.module.ActivityModule;
import br.com.maiconhellmann.dribbble.ui.base.BaseActivity;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    void inject(UserFragment userFragment);

    void inject(ShotDetailFragment shotDetailFragment);
}