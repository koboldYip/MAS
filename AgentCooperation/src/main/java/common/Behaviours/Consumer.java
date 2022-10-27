package common.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

public class Consumer extends FSMBehaviour {

    public Consumer(Agent a) {
        super(a);
        registerFirstState(new SendRequest(a), "first");
        registerLastState(new SendInitiative(a), "second");


        registerDefaultTransition("first", "second");

    }
}
