package com.ramiromd.wsaa.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "loginTicketRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginTicketRequest {

    final public static String VERSION_1_0 = "1.0";

    @XmlAttribute
    private String version;
    private LoginTicketHeader header;

    private String service;
}
