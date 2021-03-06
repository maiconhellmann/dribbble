package br.com.maiconhellmann.dribbble.injection.component;

import br.com.maiconhellmann.dribbble.ui.shot.detail.DetailTabActivity;
import dagger.Subcomponent;
import br.com.maiconhellmann.dribbble.injection.PerActivity;
import br.com.maiconhellmann.dribbble.injection.module.ActivityModule;
import br.com.maiconhellmann.dribbble.ui.shot.list.ShotActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ShotActivity mainActivity);
    void inject(DetailTabActivity mainActivity);
}
