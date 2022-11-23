package ru.mpei.common.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Neighbor {

    @JacksonXmlProperty(localName = "agentName", isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private int weight;

}
