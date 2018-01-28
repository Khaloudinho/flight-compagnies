package fr.m2.miage.flights.agents;

import fr.m2.miage.flights.behaviors.RegisterService;
import fr.m2.miage.flights.behaviors.VolManagementBehaviorCyclic;
import jade.core.Agent;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

public class CompagnieCharterAgent extends Agent implements Compagnie {

    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CompagnieCharterAgent.class);

    private static final String SERVICE_TYPE = "compagnie";
    private static final String SERVICE_NAME = "Vols-Association";

    @Override
    protected void setup() {
        logger.info("Initialisation de l'agent : " + this.getName());

        RegisterService rs = new RegisterService(this, SERVICE_NAME, SERVICE_TYPE);
        this.addBehaviour(rs);

        VolManagementBehaviorCyclic volManagementBehaviorCyclic = new VolManagementBehaviorCyclic(this);
        this.addBehaviour(volManagementBehaviorCyclic);
    }

    @Override
    protected void takeDown() {
        logger.info("Taking down " + this.getName() + " gracefully");
        getSessionFactory().close();
    }

}
