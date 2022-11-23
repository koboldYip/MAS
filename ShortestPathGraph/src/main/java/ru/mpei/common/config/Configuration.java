package ru.mpei.common.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "cfg")
public class Configuration {
    @JacksonXmlProperty(isAttribute = true)
    private String target;
    @JacksonXmlProperty(isAttribute = true)
    private boolean initiative;
    private String name;
    @JacksonXmlElementWrapper
    private List<Neighbor> neighbours;
}
