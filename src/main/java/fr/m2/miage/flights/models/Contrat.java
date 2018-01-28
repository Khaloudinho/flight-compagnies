package fr.m2.miage.flights.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Contrat implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idContrat;
    private Date dateContrat;
    private Date dateVol;
    private Double prix;

    public Contrat(){}

    public Contrat(Date dateContrat, Date dateVol, Double prix) {
        this.dateContrat = dateContrat;
        this.dateVol = dateVol;
        this.prix = prix;
    }

    public String getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(String idContrat) {
        this.idContrat = idContrat;
    }

    public Date getDateContrat() {
        return dateContrat;
    }

    public void setDateContrat(Date dateContrat) {
        this.dateContrat = dateContrat;
    }

    public Date getDateVol() {
        return dateVol;
    }

    public void setDateVol(Date dateVol) {
        this.dateVol = dateVol;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "idContrat='" + idContrat + '\'' +
                ", dateContrat=" + dateContrat +
                ", dateVol=" + dateVol +
                ", prix=" + prix +
                '}';
    }
}

