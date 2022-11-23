package ru.mpei.common;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jade.core.Agent;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.mpei.behaviours.ReceiveBackwardRequest;
import ru.mpei.behaviours.ReceiveForwardRequest;
import ru.mpei.behaviours.StartSearch;
import ru.mpei.common.config.Configuration;

import java.io.File;

@Getter
@Setter
public class NodeAgent extends Agent {

    private Configuration configuration;

    @Override
    @SneakyThrows
    protected void setup() {
        System.out.println("ShortestPathGraph/src/main/resources/data/" + getLocalName() + " started");
        configuration = new XmlMapper()
                .readValue(new File("ShortestPathGraph/src/main/resources/data/" + getLocalName()),
                        Configuration.class);
        addBehaviour(new ReceiveForwardRequest(configuration));
        if (configuration.isInitiative()) {
            addBehaviour(new StartSearch(this, 500, configuration));
        } else addBehaviour(new ReceiveBackwardRequest(configuration));
    }
}
