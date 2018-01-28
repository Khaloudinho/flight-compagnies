package fr.m2.miage.flights.discuss;

import java.io.Serializable;

public class VolAccepte implements Serializable {
    private String uuid;
    private double capacite;

    public VolAccepte() {
    }

    public VolAccepte(String uuid, double capacite) {
        this.uuid = uuid;
        this.capacite = capacite;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "VolAccepte{" +
                "UUID='" + uuid + '\'' +
                ", capacite=" + capacite +
                '}';
    }
}
