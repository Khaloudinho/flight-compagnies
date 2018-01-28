package fr.m2.miage.flights.services;

import fr.m2.miage.flights.models.Vol;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

public class DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    private static String getRandomPays() {
        List<String> pays = new ArrayList<>();
        pays.add("Guinee");
        pays.add("Tunisie");
        pays.add("Gambie");
        pays.add("Cameroun");
        pays.add("Senegal");

        Random random = new Random();
        return pays.get(random.nextInt(pays.size()));
    }

    public static void createRandomVols() {
        Session session = getSessionFactory().openSession();

        String pays = getRandomPays();
        Random random = new Random();
        java.util.Date dateArrivee = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateArrivee);
        cal.add(Calendar.SECOND, +random.nextInt(240) + 1);
        dateArrivee = cal.getTime();

        // Volume au hasard entre 100 et 200
        double volume = 100 + 100 * random.nextDouble();
        // Prix entre 50 et 100
        double prix = 50 + 50 * random.nextDouble();

        Vol vol = new Vol(pays, dateArrivee, volume, prix);
        session.beginTransaction();
        session.save(vol);

        session.getTransaction().commit();
        session.close();
    }

    public static void updateVolsPrices() {
        Session session = getSessionFactory().openSession();

        List<Vol> vols = session
                .createNamedQuery("getVols")
                .setParameter("pays", "Guinee")
                .getResultList();

        for (Vol vol : vols) {
            vol.setPrix(vol.getPrix() - 0.05 * vol.getPrix());
            session.beginTransaction();
            session.save(vol);
        }

        session.getTransaction().commit();
        session.close();
    }

    public static List<Vol> getVolsMatching(String pays, Double volume, java.util.Date date) {
        Session session = getSessionFactory().openSession();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -1);
        Date dateInf = cal.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.add(Calendar.MINUTE, +1);
        Date dateSup = cal2.getTime();

        List<Vol> vols = session
                .createNamedQuery("getVolsMatchingDemand")
                .setParameter("pays", pays)
                .setParameter("volume", volume)
                .setParameter("dateInf", dateInf)
                .setParameter("dateSup", dateSup)
                .getResultList();
        return vols;
    }
}
