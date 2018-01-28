package fr.m2.miage.flights.behaviors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import static fr.m2.miage.flights.services.DatabaseService.createRandomVols;
import static fr.m2.miage.flights.services.DatabaseService.updateVolsPrices;

public class UpdateVolBehaviour extends TickerBehaviour{
    public UpdateVolBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        updateVolsPrices();
    }
}
