package duzenz.com.mvpusage.login;

/**
 * Created by z00284305 on 31-Jan-18.
 */

public interface LoginActivityMVP {

    interface View {

        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaveMessage();

        void setFirstName(String firstName);
        void setLastName(String lastName);

        void showUserSavedMessage();
    }

    interface Presenter {

        void setView (LoginActivityMVP.View view);

        void loginButtonClicked();

        void getCurrentUser();

        void saveUser();
    }

    interface Model {

        void createUser(String firstName, String lastName);

        User getUser();

    }
}
