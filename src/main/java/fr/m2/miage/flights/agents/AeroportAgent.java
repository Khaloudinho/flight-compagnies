package fr.m2.miage.flights.agents;

import fr.m2.miage.flights.behaviors.CreateVolBehaviour;
import fr.m2.miage.flights.behaviors.RegisterService;
import jade.core.Agent;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

public class AeroportAgent extends Agent {

    private static final String SERVICE_TYPE = "Airport";
    private static final String SERVICE_NAME = "Creator";

    @Override
    protected void setup() {
        RegisterService rs = new RegisterService(this, SERVICE_NAME, SERVICE_TYPE);
        this.addBehaviour(rs);
        CreateVolBehaviour cvb = new CreateVolBehaviour(this, 3000);
        this.addBehaviour(cvb);
    }

    @Override
    protected void takeDown() {
        getSessionFactory().close();
    }
}
