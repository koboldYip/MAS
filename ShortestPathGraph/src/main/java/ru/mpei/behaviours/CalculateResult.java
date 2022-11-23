package ru.mpei.behaviours;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.mpei.common.Content;
import ru.mpei.common.config.Configuration;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class CalculateResult extends WakerBehaviour {

    private Configuration configuration;
    private List<Content> successfulPaths;
    private List<Content> failedPaths;

    public CalculateResult(Agent a, long timeout, Configuration configuration,
                           List<Content> successfulPaths, List<Content> failedPaths) {
        super(a, timeout);
        this.configuration = configuration;
        this.successfulPaths = successfulPaths;
        this.failedPaths = failedPaths;
    }

    @SneakyThrows
    @Override
    protected void onWake() {
        Collections.sort(successfulPaths);
        System.out.println(getAgent().getLocalName() + " --> " + configuration.getTarget() + "\n"
                + "Лучший путь: " + successfulPaths.get(0).getVisitedElements() + "\n"
                + "Лучший вес: " + successfulPaths.get(0).getWeight() + "\n");
    }
}
