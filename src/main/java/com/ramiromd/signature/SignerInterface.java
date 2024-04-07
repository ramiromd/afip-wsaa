package com.ramiromd.signature;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * Common interface for all implementation of CMS Signature supported by AFIP
 */
public interface SignerInterface {

    void setCertificate(X509Certificate aCertificate);

    void setPrivateKey(PrivateKey aPrivateKey);

    /**
     * Perfom a digital signature of a message.
     *
     * @param aMessage Message to be signed
     * @return Asm1CMS
     */
    byte[] sign(String aMessage) throws SignerException;

    boolean verify(byte[] signature);
}
