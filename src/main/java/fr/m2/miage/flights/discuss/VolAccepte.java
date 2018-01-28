package fr.m2.miage.flights.discuss;

import java.io.Serializable;

public class VolAccepte implements Serializable {
    private String uuid;
    private Integer capacite;

    public VolAccepte() {
    }

    public VolAccepte(String uuid, Integer capacite) {
        this.uuid = uuid;
        this.capacite = capacite;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "VolAccepte{" +
                "uuid='" + uuid + '\'' +
                ", capacite=" + capacite +
                '}';
    }
}
