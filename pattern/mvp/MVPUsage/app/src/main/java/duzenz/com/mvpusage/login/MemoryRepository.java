package duzenz.com.mvpusage.login;

/**
 * Created by z00284305 on 31-Jan-18.
 */

public class MemoryRepository implements LoginRepository {

    private User user;

    @Override
    public User getUser() {
        if (user == null) {
            User user = new User("Zafer", "Duzen");
            user.setId(0);
            return user;
        } else {
            return user;
        }
    }

    @Override
    public void saveUser(User user) {
        if (user == null){
            user = getUser();
        }
        this.user = user;
    }
}
