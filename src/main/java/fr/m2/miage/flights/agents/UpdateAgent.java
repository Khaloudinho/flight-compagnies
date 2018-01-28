package fr.m2.miage.flights.agents;

import fr.m2.miage.flights.behaviors.RegisterService;
import fr.m2.miage.flights.behaviors.UpdateVolBehaviour;
import jade.core.Agent;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

public class UpdateAgent extends Agent {

    private static final String SERVICE_TYPE = "Airport";
    private static final String SERVICE_NAME = "Updater";

    @Override
    protected void setup() {
        RegisterService rs = new RegisterService(this, SERVICE_NAME, SERVICE_TYPE);
        this.addBehaviour(rs);
        UpdateVolBehaviour cvb = new UpdateVolBehaviour(this, 20000);
        this.addBehaviour(cvb);
    }

    @Override
    protected void takeDown() {
        getSessionFactory().close();
    }
}
