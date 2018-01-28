package fr.m2.miage.flights.discuss;

import fr.m2.miage.flights.util.TypeVol;

import java.io.Serializable;
import java.util.Date;

public class VolAssociationFinal implements Serializable{

    private String pays;
    private java.util.Date dateArrivee;
    private double prix;
    private String idVol;
    private double volume;

    public VolAssociationFinal() {
    }

    public VolAssociationFinal(String pays, Date dateArrivee, double prix, String idVol, double volume) {
        this.pays = pays;
        this.dateArrivee = dateArrivee;
        this.prix = prix;
        this.idVol = idVol;
        this.volume = volume;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getIdVol() {
        return idVol;
    }

    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
