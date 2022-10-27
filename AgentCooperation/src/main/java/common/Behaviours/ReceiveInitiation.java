package common.Behaviours;

import common.FunctionAgent;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;

public class ReceiveInitiation extends Behaviour {

    public ReceiveInitiation(Agent a) {
        super(a);

    }

    @Override
    @SneakyThrows
    public void action() {
        ACLMessage msg = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if (msg != null) {
            ((FunctionAgent) getAgent()).setDelta(Double.parseDouble(msg.getContent().split(",")[1]));
            ((FunctionAgent) getAgent()).setX(Double.parseDouble(msg.getContent().split(",")[0]));
            System.out.println(getAgent().getLocalName() + " инициатор");
            getAgent().addBehaviour(new Consumer(getAgent()));
        } else block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
