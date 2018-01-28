package fr.m2.miage.flights.discuss;

import fr.m2.miage.flights.util.TypeVol;

import java.io.Serializable;

public class VolAssociation implements Serializable {

    private String idVol;

    private String aeroport;

    private String pays;

    private java.util.Date dateArrivee;

    private double volume;

    private double prix;

    private TypeVol typeVol;

    public VolAssociation() {
    }

    public VolAssociation(String idVol, String aeroport, String pays, java.util.Date dateArrivee, double volume, double prix, TypeVol typeVol) {
        this.idVol = idVol;
        this.aeroport = aeroport;
        this.pays = pays;
        this.dateArrivee = dateArrivee;
        this.volume = volume;
        this.prix = prix;
        this.typeVol = typeVol;
    }

    public String getIdVol() {
        return idVol;
    }

    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }

    public String getAeroport() {
        return aeroport;
    }

    public void setAeroport(String aeroport) {
        this.aeroport = aeroport;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public java.util.Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(java.util.Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public double getCapaciteLibre() {
        return volume;
    }

    public void setCapaciteLibre(double volume) {
        this.volume = volume;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public TypeVol getTypeVol() {
        return typeVol;
    }

    public void setTypeVol(TypeVol typeVol) {
        this.typeVol = typeVol;
    }

    @Override
    public String toString() {
        return "VolAssociation{" +
                "idVol='" + idVol + '\'' +
                ", aeroport='" + aeroport + '\'' +
                ", pays='" + pays + '\'' +
                ", dateArrivee='" + dateArrivee + '\'' +
                ", volume=" + volume +
                ", prix=" + prix +
                ", typeVol=" + typeVol +
                '}';
    }
}
