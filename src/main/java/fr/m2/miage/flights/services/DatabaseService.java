package fr.m2.miage.flights.services;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

import fr.m2.miage.flights.models.Vol;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    public static String getRandomPays() {
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
        System.out.println("test");
        Session session = getSessionFactory().openSession();

        String pays = getRandomPays();
        java.util.Date dateArrivee = new Date();
        Random random = new Random();
        // Volume au hasard entre 100 et 200
        double volume = 100 + 100 * random.nextDouble();
        // prix entre 50 et 100
        double prix = 50 + 50 * random.nextDouble();

        Vol vol = new Vol(pays, dateArrivee, volume, prix);
        session.beginTransaction();
        session.save(vol);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Vol> getVolMatching(String pays, Double volume) {
        Session session = getSessionFactory().openSession();
        List<Vol> vols = session
                .createNamedQuery("getVolByPaysAndVolumeInf")
                .setParameter("pays", pays)
                .setParameter("volume", volume)
                .getResultList();
        return vols;
    }
}
