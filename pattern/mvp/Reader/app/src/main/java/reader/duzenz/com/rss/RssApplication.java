package reader.duzenz.com.rss;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import reader.duzenz.com.rss.app.SessionData;
import reader.duzenz.com.rss.di.component.ApplicationComponent;
import reader.duzenz.com.rss.di.module.ApplicationModule;

public class RssApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    SessionData mSessionData;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public SessionData getSessionData() {
        return mSessionData;
    }
}