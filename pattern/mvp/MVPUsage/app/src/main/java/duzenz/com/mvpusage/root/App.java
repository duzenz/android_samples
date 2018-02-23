package duzenz.com.mvpusage.root;

import android.app.Application;

import duzenz.com.mvpusage.login.LoginModule;

/**
 * Created by z00284305 on 31-Jan-18.
 */

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
