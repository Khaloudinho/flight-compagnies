import fr.m2.miage.flights.discuss.DemandeVols;
import fr.m2.miage.flights.discuss.VolAssociation;
import fr.m2.miage.flights.util.TypeVol;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class filterTests {

    @Test
    public void filterByCountriesTest() {

        //On recupere la liste des vols pertinents
        List<VolAssociation> volsChartersCorrespondantsALaDemandeGuinee = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeTunisie = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeGambie = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeCameroun = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeSenegal = new ArrayList<>();

        VolAssociation guinee1 = new VolAssociation("1", "Conacky", "Guinee", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation guinee2 = new VolAssociation("2", "Conacky", "Guinee", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation tunisie1 = new VolAssociation("3", "Tunis", "Tunisie", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation tunisie2 = new VolAssociation("4", "Tunis", "Tunisie", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation gambie1 = new VolAssociation("5", "Banjul", "Gambie", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation gambie2 = new VolAssociation("6", "Banjul", "Gambie", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation cameroun1 = new VolAssociation("7", "Dhouala", "Cameroun", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation cameroun2 = new VolAssociation("8", "Dhouala", "Cameroun", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation dakar1 = new VolAssociation("9", "Dakar", "Senegal", new java.util.Date(),
                40, 40, TypeVol.Charter);

        VolAssociation dakar2 = new VolAssociation("10", "Dakar", "Senegal", new java.util.Date(),
                40, 40, TypeVol.Charter);


        volsChartersCorrespondantsALaDemandeGuinee.add(guinee1);
        volsChartersCorrespondantsALaDemandeGuinee.add(guinee2);

        volsChartersCorrespondantsALaDemandeTunisie.add(tunisie1);
        volsChartersCorrespondantsALaDemandeTunisie.add(tunisie2);

        volsChartersCorrespondantsALaDemandeGambie.add(gambie1);
        volsChartersCorrespondantsALaDemandeGambie.add(gambie2);

        volsChartersCorrespondantsALaDemandeCameroun.add(cameroun1);
        volsChartersCorrespondantsALaDemandeCameroun.add(cameroun2);

        volsChartersCorrespondantsALaDemandeSenegal.add(dakar1);
        volsChartersCorrespondantsALaDemandeSenegal.add(dakar2);

        DemandeVols demandeVols = null;
        try {
            demandeVols = new DemandeVols("Guinee", new Date(), 20);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(volsChartersCorrespondantsALaDemandeCa);

        //filterByCapacite(demandeVols, volsChartersCorrespondantsALaDemandeGuinee);

    }


}
