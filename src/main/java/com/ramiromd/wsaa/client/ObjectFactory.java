package com.ramiromd.wsaa.client;

import com.ramiromd.wsaa.data.LoginCmsResponse;
import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }
    public LoginCmsResponse createLoginCmsResponse() {
        return new LoginCmsResponse();
    }
}
