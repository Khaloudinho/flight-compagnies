package fr.m2.miage.flights.discuss;

import fr.m2.miage.flights.util.TypeVol;

import java.io.Serializable;

public class VolAssociation implements Serializable {

    private String idVol;

    private String aeroport;

    private String pays;

    private java.util.Date dateArrivee;

    private int capaciteLibre;

    private double prix;

    private TypeVol typeVol;

    public VolAssociation() {
    }

    public VolAssociation(String idVol, String aeroport, String pays, java.util.Date dateArrivee, int capaciteLibre, double prix, TypeVol typeVol) {
        this.idVol = idVol;
        this.aeroport = aeroport;
        this.pays = pays;
        this.dateArrivee = dateArrivee;
        //this.dateArrivee = dateArrivee;
        this.capaciteLibre = capaciteLibre;
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

    public int getCapaciteLibre() {
        return capaciteLibre;
    }

    public void setCapaciteLibre(int capaciteLibre) {
        this.capaciteLibre = capaciteLibre;
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
                ", capaciteLibre=" + capaciteLibre +
                ", prix=" + prix +
                ", typeVol=" + typeVol +
                '}';
    }
}
