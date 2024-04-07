package com.ramiromd.wsaa.data;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

    private final static String FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";


    @Override
    public String marshal(ZonedDateTime v) {
        DateTimeFormatter.ofPattern(FORMAT);
        return v.format(DateTimeFormatter.ofPattern(FORMAT));
    }

    @Override
    public ZonedDateTime unmarshal(String v) {
        ZonedDateTime x =  ZonedDateTime.parse(v);
        return x;
    }

}