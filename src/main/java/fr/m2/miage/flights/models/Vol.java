package fr.m2.miage.flights.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@NamedQueries({
        @NamedQuery(
                name = "getVolByPaysAndVolumeInf",
                query = "select v from Vol v where v.pays = :pays and v.volume > :volume"
        )
})

@Entity
public class Vol implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idVol;
    private String pays;
    private java.util.Date dateArrivee;
    private double volume;
    private double prix;

    public Vol() {
    }

    public Vol(String pays, java.util.Date dateArrivee, double volume, double prix) {
        this.pays = pays;
        this.dateArrivee = dateArrivee;
        this.volume = volume;
        this.prix = prix;
    }

    public String getIdVol() {
        return idVol;
    }

    public void setIdVol(String idVol) {
        this.idVol = idVol;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}