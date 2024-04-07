package com.ramiromd.signature;

import org.bouncycastle.cms.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * Legacy implementation
 *
 * Esta implementación es la misma utilizada por el equipo de desarrollo, en la primer iteración del servicio.
 * El código fue extraído tal cual desde AfipWsaaClientImpl.java.
 *
 * todo: Contiene métodos deprecados de Bouncy Castle.
 */
public class LegacySigner implements SignerInterface {

    final public static String NAME = "legacy";
    private X509Certificate certificate;

    private PrivateKey key;

    @Override
    public void setCertificate(X509Certificate aCertificate) {
        this.certificate = aCertificate;
    }

    @Override
    public void setPrivateKey(PrivateKey aPrivateKey) {
        this.key = aPrivateKey;
    }

    @Override
    public byte[] sign(String aMessage) throws SignerException {

        try {
            ArrayList<X509Certificate> certList = new ArrayList<>();
            certList.add(this.certificate);

            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }

            CertStore cstore = CertStore
                    .getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();

            // deprecated method
            gen.addSigner(this.key, this.certificate, CMSSignedGenerator.DIGEST_SHA1);

            // deprecated method
            gen.addCertificatesAndCRLs(cstore);

            CMSProcessable data = new CMSProcessableByteArray(aMessage.getBytes());

            // deprecated method
            CMSSignedData signed = gen.generate(data, true, "BC");

            return signed.getEncoded();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException |
                 CertStoreException | IOException | NullPointerException| CMSException e  ) {

            String errorDetail = "An error has occurred trying to sign a message, using: " + NAME + " signer.";
            throw new SignerException(errorDetail, e);
        }
    }

    @Override
    public boolean verify(byte[] signature) {
        return false;
    }
}
