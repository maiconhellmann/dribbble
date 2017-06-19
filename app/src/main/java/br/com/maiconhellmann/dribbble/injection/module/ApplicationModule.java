package br.com.maiconhellmann.dribbble.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.maiconhellmann.dribbble.data.remote.DribbbleService;
import dagger.Module;
import dagger.Provides;
import br.com.maiconhellmann.dribbble.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    DribbbleService provideRibotsService() {
        return DribbbleService.Creator.newDribbbleService();
    }

}
