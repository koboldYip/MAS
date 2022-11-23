package ru.mpei.behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.Getter;
import lombok.Setter;
import ru.mpei.common.Content;
import ru.mpei.common.config.Configuration;

@Getter
@Setter
public class SendFirstRequest extends OneShotBehaviour {

    private Configuration configuration;

    public SendFirstRequest(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void action() {
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
