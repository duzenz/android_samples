package app.duzenz.com.ksoapusage;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by ASUS on 26.1.2017.
 */

public class EmailValidator {
    private static final String METHOD_NAME = "VerifyEmail";
    private static final String NAMESPACE = "http://ws.cdyne.com/";
    private static final String SOAP_ACTION = "http://ws.cdyne.com/VerifyEmail";
    private static final String URL = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";

    public static VerifyEmailResult verifyEmail(String eMail, String licenseKey) {
        VerifyEmailResult object = new VerifyEmailResult();

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("email", eMail);
        request.addProperty("LicenseKey", licenseKey);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject) envelope.getResponse();

            if (response.hasProperty("ResponseText")) {
                if (response.getPropertyAsString("ResponseText") == null) {
                    object.setResponseText(null);
                } else {
                    object.setResponseText(response.getPropertyAsString("ResponseText"));
                }
            }

            if (response.hasProperty("ResponseCode")) {
                if (response.getPropertyAsString("ResponseCode") == null) {
                    object.setResponseCode(-1);
                } else {
                    object.setResponseCode(Integer.parseInt(response.getPropertyAsString("ResponseCode")));
                }
            }

            if (response.hasProperty("LastMailServer")) {
                if (response.getPropertyAsString("LastMailServer") == null) {
                    object.setLastMailServer(null);
                } else {
                    object.setLastMailServer(response.getPropertyAsString("LastMailServer"));
                }
            }

            if (response.hasProperty("GoodEmail")) {
                if (response.getPropertyAsString("GoodEmail") == null) {
                    object.setGoodEmail(false);
                } else {
                    object.setGoodEmail(Boolean.parseBoolean(response.getPropertyAsString("GoodEmail")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
