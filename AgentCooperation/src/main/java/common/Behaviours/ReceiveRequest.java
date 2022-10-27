package common.Behaviours;

import common.FunctionAgent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;

public class ReceiveRequest extends Behaviour {

    public ReceiveRequest(FunctionAgent a) {
        super(a);
    }

    @Override
    @SneakyThrows
    public void action() {
        ACLMessage msg = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (msg != null) {
            ((FunctionAgent) getAgent()).setDelta(Double.parseDouble(msg.getContent().split(",")[1]));
            ((FunctionAgent) getAgent()).setX(Double.parseDouble(msg.getContent().split(",")[0]));
            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(((FunctionAgent) getAgent()).getOperation());
            getAgent().send(reply);
        } else block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
