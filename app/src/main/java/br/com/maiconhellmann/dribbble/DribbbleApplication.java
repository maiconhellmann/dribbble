package br.com.maiconhellmann.dribbble;

import android.app.Application;
import android.content.Context;

import br.com.maiconhellmann.dribbble.injection.component.ApplicationComponent;
import br.com.maiconhellmann.dribbble.injection.component.DaggerApplicationComponent;
import br.com.maiconhellmann.dribbble.injection.module.ApplicationModule;
import timber.log.Timber;

public class DribbbleApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            //Não utilizarei no projeto
//            Fabric.with(this, new Crashlytics());
        }
    }

    public static DribbbleApplication get(Context context) {
        return (DribbbleApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
