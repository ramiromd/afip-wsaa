package com.ramiromd.wsaa.client;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(
    name = "LoginCMS",
    portName = "LoginCms",
    serviceName = "LoginCMSService"
)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface LoginCms {

    @WebMethod(operationName = "loginCms")
    String loginCms(@WebParam(name = "in0") String ticket);
}
