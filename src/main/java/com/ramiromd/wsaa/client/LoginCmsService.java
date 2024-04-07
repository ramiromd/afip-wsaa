package com.ramiromd.wsaa.client;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(
    name = "LoginCMSService",
    wsdlLocation = "https://wsaahomo.afip.gov.ar/ws/services/LoginCms?wsdl"
)
public class LoginCmsService extends Service {

    public LoginCmsService() throws MalformedURLException {
        //  The values of the actual arguments for this call MUST be equal (in the java.lang.Object.equals sense) to
        //  the values specified in the mandatory WebServiceClient annotation on the generated service class itself.
        super(new URL("https://wsaahomo.afip.gov.ar/ws/services/LoginCms?wsdl"), getServiceQName());
    }
    protected LoginCmsService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    protected LoginCmsService(URL wsdlDocumentLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlDocumentLocation, serviceName, features);
    }

    @WebEndpoint(name = "LoginCms")
    public LoginCms getPortName() {
        return super.getPort(getPortQName(), LoginCms.class);
    }

    private static QName getServiceQName() {
        return new QName("https://wsaahomo.afip.gov.ar/ws/services/LoginCms", "LoginCMSService");
    }

    private static QName getPortQName() {
        return new QName("https://wsaahomo.afip.gov.ar/ws/services/LoginCms", "LoginCms");
    }
}
