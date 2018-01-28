package fr.m2.miage.flights;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.m2.miage.flights.discuss.VolAssociation;
import fr.m2.miage.flights.models.*;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

public class Main {
    private static final Gson gson = new GsonBuilder().create();

    public static ArrayList<VolAssociation> getVols(TypeVol typeVol, String date, String pays, int capaciteLibre) {

        String query = "Vol.getVolsCorrespondantsALaDemande";
        ArrayList<VolAssociation> volsPourLesAssociation;
        List<Object[]> volsCorrespondantsALaDemande;

        volsCorrespondantsALaDemande = daoGetvols(typeVol, query, date, pays, capaciteLibre);
        showFlightsResults(volsCorrespondantsALaDemande);
        volsPourLesAssociation = formatVol(volsCorrespondantsALaDemande);

        //Object to JSON in String
        //String volsReguliersInJSON = mapper.writeValueAsString(volsPourLesAssociation);
        String volsReguliersInJSON = gson.toJson(volsPourLesAssociation);

        //demandeVols = gson.fromJson(message, DemandeVols.class);

        System.out.println("Réguliers : " + volsReguliersInJSON);

        return volsPourLesAssociation;
    }

    public static List<Object[]> daoGetvols(TypeVol typeVol, String query, String date, String pays, int capaciteLibre) {
        Session session = getSessionFactory().openSession();

        Query queryVolsReguliersCorrespondantsALaDemande = session.createNamedQuery(query, Object[].class);
        queryVolsReguliersCorrespondantsALaDemande.setParameter("date", new java.util.Date(date));
        queryVolsReguliersCorrespondantsALaDemande.setParameter("pays", pays);
        queryVolsReguliersCorrespondantsALaDemande.setParameter("capaciteLibre", capaciteLibre);
        queryVolsReguliersCorrespondantsALaDemande.setParameter("typeVol", typeVol);

        List<Object[]> vols = queryVolsReguliersCorrespondantsALaDemande.getResultList();

        session.close();

        return vols;
    }

    public static void showFlightsResults(List<Object[]> volsChartersCorrespondantsALaDemande) {
        for (Object[] o : volsChartersCorrespondantsALaDemande) {
            System.out.println("============== VOL CORRESPONDANT ==============");
            System.out.println("Aéroport : " + o[0].toString());
            System.out.println("Pays : " + o[1].toString());
            System.out.println("Date arrivée : " + o[2].toString());
            System.out.println("Capacité libre : " + o[3].toString());
            System.out.println("Prix : " + o[4].toString());
            System.out.println("IdVol : " + o[5].toString());
            System.out.println("========================================================");
        }
    }

    public static ArrayList<VolAssociation> formatVol(List<Object[]> volsCorrespondantsALaDemande) {
        ArrayList<VolAssociation> volsPourLesAssociation = new ArrayList<>();
        for (Object[] o : volsCorrespondantsALaDemande) {
            volsPourLesAssociation.add(
                    new VolAssociation(
                            o[5].toString(),
                            o[0].toString(),
                            o[1].toString(),
                            new java.util.Date(o[2].toString()),
                            Integer.parseInt(o[3].toString()),
                            Integer.parseInt(o[4].toString().substring(0, o[4].toString().indexOf("."))),
                            TypeVol.Charter
                    )
            );
        }
        return volsPourLesAssociation;
    }

    public static /*Map<String, Integer>*/ void calculerPrixVols() {
        Session session = getSessionFactory().openSession();

        final int prixKeroseneParHeure = 1140;

        System.out.println("Requête : Vol.calculerLesPrixDesVols");
        List<Vol> volsPourPrix = session.createNamedQuery("Vol.calculerLesPrixDesVols", Vol.class).getResultList();
        //List<Object[]> paramPourCalculerLesPrixDesVols = queryCalculerLesPrixDesVols.getResultList();
        //Map<String, Integer> tousLesPrix = new HashMap<>();

        for (Vol v : volsPourPrix) {
            //String idVol = v.getIdVol();
            int consommationCarburant = v.getAvion().getConsommationCarburant();//Integer.valueOf(o[1].toString());
            int heuresVolDepuisParis = v.getAeroportArrivee().getHeuresVolDepuisParis();//Integer.valueOf(o[2].toString());
            int taxeAeroport = v.getAeroportArrivee().getTaxeAeroport();//Integer.valueOf(o[3].toString());
            int prix = consommationCarburant * heuresVolDepuisParis * prixKeroseneParHeure + taxeAeroport;

            v.setPrixDeVente(prix);
            //tousLesPrix.put(idVol, prix);
        }

        session.close();

        //return tousLesPrix;
    }


    public static void main(String[] args) {

        // On peut récupérer globalement des sessions
        Session session = getSessionFactory().openSession();
        session.beginTransaction(); // Ouvre la tx

        Set<Lieu> lieux = new HashSet<>();
        Lieu l1 = new Lieu("Conakry", "Guinee");
        Lieu l2 = new Lieu("Dakar", "Senegal");
        Lieu l3 = new Lieu("Banjul", "Gambie");
        Lieu l4 = new Lieu("Abidjan", "Cote d'Ivoire");
        Lieu l5 = new Lieu("Douala", "Cameroun");
        Lieu l6 = new Lieu("Gaborone", "Bostwana");

        lieux.add(l1);
        lieux.add(l2);
        lieux.add(l3);
        lieux.add(l4);
        lieux.add(l5);
        lieux.add(l6);

        for (Lieu lieu : lieux) {
            session.save(lieu);
        }

        Set<Avion> avions = new HashSet<>();
        Avion av1 = new Avion("AF9-151-PKL", 40, 1500, 3);
        Avion av2 = new Avion("GF6-051-PPP", 40, 1500, 3);
        Avion av3 = new Avion("FR4-401-ZKK", 40, 1500, 3);
        Avion av4 = new Avion("GB2-398-WYR", 40, 1500, 3);

        avions.add(av1);
        avions.add(av2);
        avions.add(av3);
        avions.add(av4);

        for (Avion avion : avions) {
            session.save(avion);
        }

        // java.util.Date dateDepart = new java.util.Date("2017-01-01");
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateDepart = new java.util.Date();
        java.util.Date dateDepartVolsReguliers = new java.util.Date();
        java.util.Date dateArrivee = new java.util.Date();
        java.util.Date dateArriveeVolsReguliers = new java.util.Date();
/*
        try {
            dateDepart = formatter.parse("2017-01-01");
            dateDepartVolsReguliers = formatter.parse("2017-03-11");
            dateArrivee = formatter.parse("2017-05-16");
            dateArriveeVolsReguliers = formatter.parse("2017-06-17");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        Set<Aeroport> aeroports = new HashSet<>();
        Aeroport a1 = new Aeroport();
        a1.setNomAeroport("Aéroport international de Conakry"); // Guinee
        a1.setTaxeAeroport((int) (Math.random() * 1000 + 1500));
        a1.setHeuresVolDepuisParis(8);
        a1.setLieu(l1);

        Aeroport a2 = new Aeroport();
        a2.setNomAeroport("Aéroport international Léopold-Sédar-Senghor"); // Senegal
        a2.setTaxeAeroport((int) (Math.random() * 1000 + 1500));
        a2.setHeuresVolDepuisParis(6);
        a2.setLieu(l2);

        Aeroport a3 = new Aeroport();
        a3.setNomAeroport("Aéroport international de Banjul - Yundum"); // Gambie
        a3.setTaxeAeroport((int) (Math.random() * 1000 + 1500));
        a3.setHeuresVolDepuisParis(8);
        a3.setLieu(l3);

        Aeroport a4 = new Aeroport();
        a4.setNomAeroport("Aéroport international Félix-Houphouët-Boigny"); // Cote ivoire
        a4.setTaxeAeroport((int) (Math.random() * 1000 + 1500));
        a4.setHeuresVolDepuisParis(7);
        a4.setLieu(l4);

        Aeroport a5 = new Aeroport();
        a5.setNomAeroport("Aéroport international de Douala"); // Cameroun
        a5.setTaxeAeroport((int) (Math.random() * 1000 + 1500));
        a5.setHeuresVolDepuisParis(7);
        a5.setLieu(l5);

        Aeroport a6 = new Aeroport();
        a6.setNomAeroport("Aéroport international de Gaborone"); // Bostwana
        a6.setTaxeAeroport((int) (Math.random() * 1000 + 1500));
        a6.setHeuresVolDepuisParis(10);
        a6.setLieu(l6);

        aeroports.add(a1);
        aeroports.add(a2);
        aeroports.add(a3);
        aeroports.add(a4);
        aeroports.add(a5);
        aeroports.add(a6);

        for (Aeroport aeroport : aeroports) {
            session.save(aeroport);
        }

        Set<Vol> vols = new HashSet<>();
        Vol v1 = new Vol(dateDepart, dateArrivee, TypeVol.Charter, av1, a1, 40);
        Vol v2 = new Vol(dateDepart, dateArrivee, TypeVol.Charter, av2, a2, 40);
        Vol v3 = new Vol(dateDepartVolsReguliers, dateArriveeVolsReguliers, TypeVol.Regulier, av3, a3, 40);
        Vol v4 = new Vol(dateDepartVolsReguliers, dateArriveeVolsReguliers, TypeVol.Regulier, av4, a4, 40);
        Vol v5 = new Vol(dateDepartVolsReguliers, dateArriveeVolsReguliers, TypeVol.Regulier, av2, a5, 40);
        Vol v6 = new Vol(dateDepartVolsReguliers, dateArriveeVolsReguliers, TypeVol.Regulier, av1, a6, 40);
        Vol v7 = new Vol(dateDepart, dateArriveeVolsReguliers, TypeVol.Regulier, av3, a1, 40);

        vols.add(v1);
        vols.add(v2);
        vols.add(v3);
        vols.add(v4);
        vols.add(v5);
        vols.add(v6);
        vols.add(v7);

        for (Vol vol : vols) {
            session.save(vol);
        }

        Set<Abonnement> abonnements = new HashSet<>();
        Abonnement ab = new Abonnement("Abonnement 1", 15095.82);
        Abonnement ab2 = new Abonnement("Abonnement 2", 18795.82);
        Abonnement ab3 = new Abonnement("Abonnement 3", 19595.82);

        abonnements.add(ab);
        abonnements.add(ab2);
        abonnements.add(ab3);

        for (Abonnement abo : abonnements) {
            session.save(abo);
        }

        //final Map<String, Integer> prixVols = calculerPrixVols();

        //for (Vol vol : vols) {
        //vol.setPrixDeVente(prixVols.get(vol.getIdVol()));
        //}

        session.getTransaction().commit();
        session.close();

        getSessionFactory().close();
    }
}
