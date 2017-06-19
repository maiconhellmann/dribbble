package br.com.maiconhellmann.dribbble.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.maiconhellmann.dribbble.data.DataManager;
import br.com.maiconhellmann.dribbble.data.remote.DribbbleService;
import br.com.maiconhellmann.dribbble.injection.ApplicationContext;
import br.com.maiconhellmann.dribbble.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext Context context();
    Application application();
    DribbbleService githubService();
    DataManager dataManager();

}
