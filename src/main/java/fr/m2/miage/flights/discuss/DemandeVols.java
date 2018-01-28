package fr.m2.miage.flights.discuss;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class DemandeVols implements Serializable {

    private String pays;
    private Date date;
    private Integer volume;

    public DemandeVols() {
    }

    public DemandeVols(String pays, Date date, Integer volume) throws ParseException {
        this.pays = pays;
        this.date = date;
        this.volume = volume;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "DemandeVols{" +
                "pays='" + pays + '\'' +
                ", date=" + date +
                ", volume=" + volume +
                '}';
    }
}
