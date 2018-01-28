package fr.m2.miage.flights.behaviors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import static fr.m2.miage.flights.services.DatabaseService.createRandomVols;

public class CreateVolBehaviour extends TickerBehaviour{
    public CreateVolBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        createRandomVols();
    }
}
