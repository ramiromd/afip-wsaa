package com.ramiromd.wsaa.client;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;

import javax.xml.namespace.QName;
import java.net.URL;

@WebServiceClient(
    name = "LoginCMSService",
    wsdlLocation = "https://wsaahomo.afip.gov.ar/ws/services/LoginCms?wsdl"
)
public class LoginCmsService extends Service {

    protected LoginCmsService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    protected LoginCmsService(URL wsdlDocumentLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlDocumentLocation, serviceName, features);
    }

    @WebEndpoint(name = "LoginCms")
    public LoginCms getPortName() {
        return super.getPort(new QName("LoginCms"), LoginCms.class);
    }
}
