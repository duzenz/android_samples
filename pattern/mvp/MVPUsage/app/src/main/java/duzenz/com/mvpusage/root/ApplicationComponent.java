package duzenz.com.mvpusage.root;

import javax.inject.Singleton;

import dagger.Component;
import duzenz.com.mvpusage.login.LoginActivity;
import duzenz.com.mvpusage.login.LoginModule;

/**
 * Created by z00284305 on 31-Jan-18.
 */

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);
}
