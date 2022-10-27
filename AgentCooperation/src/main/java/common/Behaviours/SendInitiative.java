package common.Behaviours;

import common.FunctionAgent;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class SendInitiative extends OneShotBehaviour {

    public SendInitiative(Agent a) {
        super(a);
    }

    @Override
    @SneakyThrows
    public void action() {
        if (((FunctionAgent) getAgent()).getDelta() < ((FunctionAgent) getAgent()).getEpsilon()) {
            System.err.println("Результат: " + ((FunctionAgent) getAgent()).getX());
        } else {
            ServiceDescription sd = new ServiceDescription();
            sd.setType("Agent");
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.addServices(sd);
            DFAgentDescription[] result = DFService.search(getAgent(), dfd);
            ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
            msg.setContent(((FunctionAgent) getAgent()).getX() + "," + ((FunctionAgent) getAgent()).getDelta());
            msg.addReceiver(Arrays.stream(result)
                    .map(DFAgentDescription::getName)
                    .filter(aid -> !aid.getLocalName().equals(getAgent().getLocalName()))
                    .collect(Collectors.toList())
                    .get(new Random().nextInt(2)));
            getAgent().send(msg);
        }
    }
}
