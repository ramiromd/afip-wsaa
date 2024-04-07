package com.ramiromd;

import com.ramiromd.signature.LegacySigner;
import com.ramiromd.signature.Pkcs8PrivateKeyLoader;
import com.ramiromd.signature.SignerException;
import com.ramiromd.signature.X509CertificateLoader;
import com.ramiromd.wsaa.data.LoginTicketHeader;
import com.ramiromd.wsaa.data.LoginTicketRequest;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

public class Main {

    final public static String TIMEZONE = "America/Argentina/Buenos_Aires";

    final public static long GENERATION_TIME_DELAY_MIN = 10L;
    public static void main(String[] args) throws IOException, CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, JAXBException, SignerException {

        System.out.println("======= AFIP WSAA =======");

        System.out.println("Cargando Certificado X509");
        byte[] certContents = getFileContents("src/main/resources/certificates/homologacion.pem");
        X509Certificate certificate = X509CertificateLoader.from(certContents);

        System.out.println("Cargando Clave Privada");
        byte[] keyContents = getFileContents("src/main/resources/keys/homologacion.key");
        PrivateKey key = Pkcs8PrivateKeyLoader.from(keyContents);

        System.out.println("Generando Ticket");

        ZoneId tzId = ZoneId.of(TIMEZONE);
        ZonedDateTime now = ZonedDateTime.now(tzId);


        LoginTicketHeader aTicketHeader = LoginTicketHeader.builder()
                .source(certificate.getSubjectX500Principal().toString())
                .destination("CN=wsaahomo, O=AFIP, C=AR, SERIALNUMBER=CUIT 33693450239")
                .uniqueId(now.toEpochSecond())
                .generationTime(now.minusMinutes(GENERATION_TIME_DELAY_MIN))
                .expirationTime(now.plusMinutes(2L))
                .build();

        LoginTicketRequest aTicketData = LoginTicketRequest.builder()
                .version(LoginTicketRequest.VERSION_1_0)
                .header(aTicketHeader)
                .service("wsicdb")
                .build();

        JAXBContext jaxbContext = JAXBContext.newInstance(LoginTicketRequest.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(aTicketData ,writer);

        System.out.println();
        System.out.println(writer);

        LegacySigner legacySigner = new LegacySigner();
        legacySigner.setCertificate(certificate);
        legacySigner.setPrivateKey(key);

        System.out.println("Firmando Ticket");

        byte[] signature = legacySigner.sign(writer.toString());
        String encoded = Base64.getEncoder().encodeToString(signature);

        System.out.println();
        System.out.println(encoded);


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