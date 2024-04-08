package com.ramiromd.wsaa.data;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "loginCmsResponse")
@XmlType(
        name = "",
        propOrder = {"loginCmsReturn"}
)
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginCmsResponse {

    @XmlElement(
            required = true,
            namespace = "http://wsaa.view.sua.dvadac.desein.afip.gov"
    )
    private String loginCmsReturn;
}
