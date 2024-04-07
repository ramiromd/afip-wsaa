package com.ramiromd.signature;

import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

abstract public class X509CertificateLoader {

    public static X509Certificate from(String aStringContents) throws CertificateException {
        return from(aStringContents.getBytes());
    }
    public static X509Certificate from(File aFile) throws IOException, CertificateException {
        try (InputStream is = new FileInputStream(aFile)) {
            byte[] aByteStream = is.readAllBytes();
            return from(aByteStream);
        }

    }

    public static X509Certificate from(byte[] aByteStream) throws CertificateException {
        InputStream targetStream = new ByteArrayInputStream(aByteStream);
        return (X509Certificate) CertificateFactory
                .getInstance("X509")
                .generateCertificate(targetStream);
    }
}
