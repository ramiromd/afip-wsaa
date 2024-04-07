package com.ramiromd;

import com.ramiromd.signature.Pkcs8PrivateKeyLoader;
import com.ramiromd.signature.X509CertificateLoader;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws IOException, CertificateException, InvalidKeySpecException, NoSuchAlgorithmException {

        System.out.println("======= AFIP WSAA =======");

        System.out.println("Cargando Certificado X509");
        byte[] certContents = getFileContents("src/main/resources/certificates/homologacion.pem");
        X509Certificate certificate = X509CertificateLoader.from(certContents);

        System.out.println("Cargando Clave Privada");
        byte[] keyContents = getFileContents("src/main/resources/keys/homologacion.key");
        PrivateKey key = Pkcs8PrivateKeyLoader.from(keyContents);

        System.out.println("======= AFIP WSAA =======");
    }

    private static byte[] getFileContents(String filepath) throws IOException {
        File file = new File(filepath);
        try (InputStream is = new FileInputStream(file)) {
            byte[] aByteStream = is.readAllBytes();
            is.close();
            return aByteStream;
        }
    }
}