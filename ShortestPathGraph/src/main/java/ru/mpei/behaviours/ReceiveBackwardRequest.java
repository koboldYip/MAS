package ru.mpei.behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.common.Content;
import ru.mpei.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ReceiveBackwardRequest extends Behaviour {

    private List<Content> failedPaths;
    private List<Content> successfulPaths;
    private Configuration configuration;

    public ReceiveBackwardRequest(Configuration configuration) {
        this.configuration = configuration;
        if (configuration.isInitiative()) {
            this.failedPaths = new ArrayList<>();
            this.successfulPaths = new ArrayList<>();
        }
    }

    @Override
    public void onStart() {
        if (configuration.isInitiative())
            getAgent().addBehaviour(new CalculateResult(getAgent(), 1000, configuration,
                    successfulPaths, failedPaths));
    }

    @Override
    public void action() {
        ACLMessage msg = getAgent().receive(MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
                MessageTemplate.MatchPerformative(ACLMessage.FAILURE)));
        if (msg != null) {
            Content content = Content.convertToContent(msg.getContent());
            if (configuration.isInitiative() &&
                    content.getVisitedElements().get(0).equals(getAgent().getLocalName())) {
                if (msg.getPerformative() == ACLMessage.FAILURE) {
                    failedPaths.add(content);
                } else {
                    successfulPaths.add(content);
                }
            } else if (content.getVisitedElements().contains(getAgent().getLocalName())) {
                ACLMessage msg1 = new ACLMessage(msg.getPerformative());
                msg1.setContent(content.convertToString());
                msg1.addReceiver(
                        new AID(content.getVisitedElements()
                                .get(content.getVisitedElements().indexOf(getAgent().getLocalName()) - 1),
                                false));
                getAgent().send(msg1);
            }
        } else block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
