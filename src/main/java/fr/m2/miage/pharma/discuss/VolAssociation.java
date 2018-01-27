package fr.m2.miage.pharma.discuss;

import fr.m2.miage.pharma.models.TypeVol;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class VolAssociation implements Serializable {

    private String idVol;

    private String aeroport;

    private String pays;

    private String dateArrivee;

    private int capaciteLibre;

    private int prix;

    private TypeVol typeVol;

    public VolAssociation() {
    }

    public VolAssociation(String idVol, String aeroport, String pays, Date dateArrivee, int capaciteLibre, int prix, TypeVol typeVol) {
        this.idVol = idVol;
        this.aeroport = aeroport;
        this.pays = pays;
        this.dateArrivee = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(dateArrivee).toString();
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

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public int getCapaciteLibre() {
        return capaciteLibre;
    }

    public void setCapaciteLibre(int capaciteLibre) {
        this.capaciteLibre = capaciteLibre;
    }

    public long getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
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
