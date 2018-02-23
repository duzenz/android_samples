package duzenz.com.mvpusage;

import org.junit.Before;
import org.junit.Test;

import duzenz.com.mvpusage.login.LoginActivity;
import duzenz.com.mvpusage.login.LoginActivityMVP;
import duzenz.com.mvpusage.login.LoginActivityPresenter;
import duzenz.com.mvpusage.login.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by z00284305 on 04-Feb-18.
 */

public class PresenterTests {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter;
    User user;

    @Before
    public void setup() {

        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user = new User("Fox", "Mulder");

        mockView = mock(LoginActivityMVP.View.class);

        presenter = new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockView);

    }

    @Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent() {

        when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, times(1)).setFirstName("Fox");
        verify(mockView, times(1)).setLastName("Mulder");
        verify(mockView, never()).showUserNotAvailable();

    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull() {

        when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, never()).setFirstName("Fox");
        verify(mockView, never()).setLastName("Mulder");
        verify(mockView, times(1)).showUserNotAvailable();

    }

    @Test
    public void shouldCreateErrorMessageIfFieldAreEmpty() {

        // Set up the view mock
        when(mockView.getFirstName()).thenReturn(""); // empty string

        presenter.saveUser();

        verify(mockView, times(1)).getFirstName();
        verify(mockView, never()).getLastName();
        verify(mockView, times(1)).showInputError();

        // Now tell mockView to return a value for first name and an empty last name
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(2)).getFirstName(); // Called two times now, once before, and once now
        verify(mockView, times(1)).getLastName();  // Only called once
        verify(mockView, times(2)).showInputError(); // Called two times now, once before and once now
    }

    @Test
    public void shouldBeAbleToSaveAValidUser() {

        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("Scully");

        presenter.saveUser();

        // Called two more times in the saveUser call.
        verify(mockView, times(2)).getFirstName();
        verify(mockView, times(2)).getLastName();

        // Make sure the repository saved the user
        verify(mockLoginModel, times(1)).createUser("Dana", "Scully");

        // Make sure that the view showed the user saved message
        verify(mockView, times(1)).showUserSavedMessage();
    }
}
