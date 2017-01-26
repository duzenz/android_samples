package app.duzenz.com.ksoapusage;

/**
 * Created by ASUS on 26.1.2017.
 */

public class VerifyEmailResult {
    private String responseText;
    private int responseCode;
    private String lastMailServer;
    private boolean goodEmail;

    public VerifyEmailResult() {
        super();
    }

    public VerifyEmailResult(String responseText, int responseCode,
                             String lastMailServer, boolean goodEmail) {
        super();
        this.responseText = responseText;
        this.responseCode = responseCode;
        this.lastMailServer = lastMailServer;
        this.goodEmail = goodEmail;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getLastMailServer() {
        return lastMailServer;
    }

    public void setLastMailServer(String lastMailServer) {
        this.lastMailServer = lastMailServer;
    }

    public boolean isGoodEmail() {
        return goodEmail;
    }

    public void setGoodEmail(boolean goodEmail) {
        this.goodEmail = goodEmail;
    }

    @Override
    public String toString() {
        return "VerifyEmailResult [responseText=" + responseText
                + ", responseCode=" + responseCode + ", lastMailServer="
                + lastMailServer + ", goodEmail=" + goodEmail + "]";
    }
}
