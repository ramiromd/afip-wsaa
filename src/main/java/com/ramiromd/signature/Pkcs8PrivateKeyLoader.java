package com.ramiromd.signature;

import org.bouncycastle.util.encoders.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

abstract public class Pkcs8PrivateKeyLoader {

    final private static String RSA = "RSA";

    public static PrivateKey from(String aStringContents) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return from(aStringContents.getBytes());
    }
    public static PrivateKey from(File aFile) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        try (InputStream is = new FileInputStream(aFile)) {
            byte[] aByteStream = is.readAllBytes();
            return from(aByteStream);
        }
    }

    public static PrivateKey from(byte[] aByteStream) throws InvalidKeySpecException, NoSuchAlgorithmException {

        // Remove comments to avoid fails
        String base64encoded = new String(aByteStream);
        base64encoded = base64encoded.replace("-----BEGIN PRIVATE KEY-----\n", "")
                .replace("-----END PRIVATE KEY-----", "");


        byte[] binaryKey = Base64.decode(base64encoded);

        KeyFactory rsaKeyFactory = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(binaryKey);
        return rsaKeyFactory.generatePrivate(keySpecPv);
    }
}
