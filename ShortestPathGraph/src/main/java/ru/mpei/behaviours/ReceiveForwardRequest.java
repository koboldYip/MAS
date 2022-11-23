package ru.mpei.behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;
import ru.mpei.common.Content;
import ru.mpei.common.config.Configuration;
import ru.mpei.common.config.Neighbor;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiveForwardRequest extends Behaviour {

    private Configuration configuration;

    public ReceiveForwardRequest(Configuration configuration) {
        this.configuration = configuration;
    }

    @SneakyThrows
    @Override
    public void action() {
        ACLMessage msg = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (msg != null) {
            Content content = Content.convertToContent(msg.getContent());
            content.getVisitedElements().add(getAgent().getLocalName());
            content.increaseWeight(configuration.getNeighbours().stream()
                    .filter(neighbor -> neighbor.getName().equals(msg.getSender().getLocalName()))
                    .findFirst().get()
                    .getWeight());
            if (content.getTarget().equals(getAgent().getLocalName())) {
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.CONFIRM);
                reply.setContent(content.convertToString());
                getAgent().send(reply);
            } else {
                List<Neighbor> sendList = configuration.getNeighbours().stream()
                        .filter(neighbor -> !content.getVisitedElements().contains(neighbor.getName()))
                        .collect(Collectors.toList());
                if (sendList.size() > 0) {
                    for (Neighbor n :
                            sendList) {
                        ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
                        msg1.addReceiver(new AID(n.getName(), false));
                        msg1.setContent(content.convertToString());
                        getAgent().send(msg1);
                    }
                } else {
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.FAILURE);
                    reply.setContent(content.convertToString());
                    getAgent().send(reply);
                }
            }
        } else block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
