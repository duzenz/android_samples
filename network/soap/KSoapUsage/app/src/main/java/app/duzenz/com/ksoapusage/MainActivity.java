package app.duzenz.com.ksoapusage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private String celsius;

    VerifyEmailResult object = null;
    final String eMail = "zafer_duzen_1988@hotmail.com";
    final String licenseKey = "test";
    Thread thread = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.convert);
        txt = (TextView) findViewById(R.id.answer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerifyEmailTask().execute(eMail, licenseKey);
            }
        });
    }

    private class VerifyEmailTask extends AsyncTask<String, Void, VerifyEmailResult> {

        @Override
        protected VerifyEmailResult doInBackground(String... params) {
            EmailValidator emailValidator = new EmailValidator();
            return emailValidator.verifyEmail(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(VerifyEmailResult result) {
            TextView txt = (TextView) findViewById(R.id.answer);
            txt.setText(result.getResponseText());
        }

        @Override
        protected void onPreExecute() {

        }

    }

}
