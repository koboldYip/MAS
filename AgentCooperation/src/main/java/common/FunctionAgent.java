package common;

import common.Behaviours.Consumer;
import common.Behaviours.ReceiveInitiation;
import common.Behaviours.ReceiveRequest;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class FunctionAgent extends Agent {

    private double max = 5;
    private double min = -5;
    private double x;
    private double delta = 1;
    private double epsilon = 0.01;
    private Operation operation;
    private List<Operation> functions = Arrays.asList(d -> Math.exp(0.2 * x),
            d -> Math.pow(2, -x),
            Math::cos);

    @SneakyThrows
    protected void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(this.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Agent");
        sd.setName(this.getLocalName());
        dfd.addServices(sd);
        DFService.register(this, dfd);
        x = new Random().doubles(min, max).findFirst().getAsDouble();

        addBehaviour(new ReceiveRequest(this));
        addBehaviour(new ReceiveInitiation(this));

        if (this.getLocalName().equals("third")) {
            this.operation = functions.get(2);
        }
        if (this.getLocalName().equals("second")) {
            this.operation = functions.get(1);
        }
        if (this.getLocalName().equals("first")) {
            this.operation = functions.get(0);
            addBehaviour(new Consumer(this));
        }
    }

    public String getOperation() {
        System.out.println(this.getLocalName() + " sends " + operation.execute(x - delta) + "," + operation.execute(x) + ","
                + operation.execute(x + delta));
        return operation.execute(x - delta) + "," + operation.execute(x) + ","
                + operation.execute(x + delta);
    }
}
