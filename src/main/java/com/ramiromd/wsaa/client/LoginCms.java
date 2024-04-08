package com.ramiromd.wsaa.client;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.ResponseWrapper;
@WebService(
    name = "LoginCMS",
    portName = "LoginCms",
    serviceName = "LoginCMSService",
    targetNamespace = "http://wsaa.view.sua.dvadac.desein.afip.gov"
)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@XmlSeeAlso(ObjectFactory.class)
public interface LoginCms {

    @WebMethod(operationName = "loginCms")
    @WebResult(name = "loginCmsReturn", targetNamespace = "http://wsaa.view.sua.dvadac.desein.afip.gov")
    @ResponseWrapper(localName = "loginCmsResponse", targetNamespace = "http://wsaa.view.sua.dvadac.desein.afip.gov", className = "com.ramiromd.wsaa.data.LoginCmsResponse")
    String loginCms(@WebParam(name = "in0") String ticket);
}
