package ru.mpei.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.mpei.common.Content;
import ru.mpei.common.config.Configuration;

@Getter
@Setter
public class SendFirstRequest extends WakerBehaviour {

    private Configuration configuration;

    public SendFirstRequest(Agent a, long timeout, Configuration configuration) {
        super(a, timeout);
        this.configuration = configuration;
    }

    @SneakyThrows
    @Override
    protected void onWake() {
        for (int i = 0; i < configuration.getNeighbours().size(); i++) {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(new AID(configuration.getNeighbours().get(i).getName(), false));
            Content content = new Content();
            content.getVisitedElements().add(getAgent().getLocalName());
            content.setTarget(configuration.getTarget());
            msg.setContent(content.convertToString());
            getAgent().send(msg);
        }
    }
}
