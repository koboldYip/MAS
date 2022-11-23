package ru.mpei.behaviours;

import jade.core.behaviours.WakerBehaviour;
import lombok.Getter;
import lombok.Setter;
import ru.mpei.common.Content;
import ru.mpei.common.NodeAgent;
import ru.mpei.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StartSearch extends WakerBehaviour {

    private Configuration configuration;

    public StartSearch(NodeAgent a, long timeout, Configuration configuration) {
        super(a, timeout);
        this.configuration = configuration;
    }

    @Override
    protected void onWake() {
        List<Content> successfulPaths = new ArrayList<>();
        List<Content> failedPaths = new ArrayList<>();
        getAgent().addBehaviour(new ReceiveBackwardRequest(configuration, successfulPaths, failedPaths));
        getAgent().addBehaviour(new CalculateResult(getAgent(), 1000, configuration, successfulPaths, failedPaths));
        getAgent().addBehaviour(new SendFirstRequest(configuration));
    }
}
