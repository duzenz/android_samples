package duzenz.com.mvpusage.login;

/**
 * Created by z00284305 on 31-Jan-18.
 */

public interface LoginRepository {

    User getUser();
    void saveUser(User user);
}
