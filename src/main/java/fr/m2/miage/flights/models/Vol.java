package fr.m2.miage.flights.models;

import fr.m2.miage.flights.util.TypeVol;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Vol.getVolsCorrespondantsALaDemande",
                query = "SELECT v.aeroportArrivee.nomAeroport, v.aeroportArrivee.lieu.pays, " +
                        "v.dateArrivee, v.capaciteLibre, v.prixDeVente, " +
                        "v.idVol " +
                        "FROM Vol v " +
                        "WHERE v.dateArrivee = :date " +
                        "AND v.aeroportArrivee.lieu.pays = :pays " +
                        "AND v.capaciteLibre >= :capaciteLibre " +
                        "AND v.typeVol = :typeVol "),

        @NamedQuery(
                name = "Vol.calculerLesPrixDesVols",
                query = "SELECT v FROM Vol v "
        ),
        @NamedQuery(
                name = "Vol.updateCapaciteLibreVol",
                query = "UPDATE Vol v SET v.capaciteLibre = (v.capaciteLibre-:capacitePrise) WHERE v.id = :idVol "
        ),
})

public class Vol implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idVol;
    private java.util.Date dateDepart;
    private java.util.Date dateArrivee;
    private double prixCoutant, prixDeVente;

    @Enumerated(EnumType.STRING)
    private TypeVol typeVol;

    @OneToOne
    private Avion avion;

    @OneToOne
    private Aeroport aeroportArrivee;

    @Column(nullable = true)
    private double capaciteLibre;

    public Vol() {
    }

    public Vol(java.util.Date dateDepart, java.util.Date dateArrivee, TypeVol typeVol, Avion avion, Aeroport aeroportArrivee, double capaciteLibre) {
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.typeVol = typeVol;
        this.avion = avion;
        this.aeroportArrivee = aeroportArrivee;
        this.capaciteLibre = capaciteLibre;
        this.prixCoutant = 0;
        this.prixDeVente = 0;
    }

    public String getIdVol() {
        return idVol;
    }

    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }

    public java.util.Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(java.util.Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public java.util.Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(java.util.Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public TypeVol getTypeVol() {
        return typeVol;
    }

    public void setTypeVol(TypeVol typeVol) {
        this.typeVol = typeVol;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }

    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }

    public double getPrixCoutant() {
        return prixCoutant;
    }

    public void setPrixCoutant(double prixCoutant) {
        this.prixCoutant = prixCoutant;
    }

    public double getPrixDeVente() {
        return prixDeVente;
    }

    public void setPrixDeVente(double prixDeVente) {
        this.prixDeVente = prixDeVente;
    }

    public double getCapaciteLibre() {
        return capaciteLibre;
    }

    public void setCapaciteLibre(double capaciteLibre) {
        this.capaciteLibre = capaciteLibre;
    }
}
