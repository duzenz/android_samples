package reader.duzenz.com.rss.model;

/**
 * Created by faruktoptas on 29/01/17.
 */

public class RError {

    private final String mMessage;

    public RError(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
