package fr.m2.miage.flights.behaviors;

import static fr.m2.miage.flights.services.DatabaseService.getVolMatching;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.m2.miage.flights.discuss.DemandeVols;
import fr.m2.miage.flights.discuss.VolAssociation;
import fr.m2.miage.flights.discuss.VolAssociationFinal;
import fr.m2.miage.flights.models.Vol;
import fr.m2.miage.flights.util.TypeVol;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VolManagementBehaviorCyclic extends CyclicBehaviour {

    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VolManagementBehaviorCyclic.class);
    private final Gson gson = new GsonBuilder().create();
    private List<VolAssociation> volsChartersCorrespondantsALaDemande = new ArrayList<>();

    private DemandeVols demandeVols = new DemandeVols();

    public VolManagementBehaviorCyclic(Agent agent) {
        super(agent);
        initVolAssociations();
    }

    private void initVolAssociations() {
        VolAssociation guinee1 = new VolAssociation("1", "Conakry", "Guinee", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation guinee2 = new VolAssociation("2", "Conakry", "Guinee", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation tunisie1 = new VolAssociation("3", "Tunis", "Tunisie", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation tunisie2 = new VolAssociation("4", "Tunis", "Tunisie", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation gambie1 = new VolAssociation("5", "Banjul", "Gambie", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation gambie2 = new VolAssociation("6", "Banjul", "Gambie", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation cameroun1 = new VolAssociation("7", "Dhouala", "Cameroun", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation cameroun2 = new VolAssociation("8", "Dhouala", "Cameroun", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation dakar1 = new VolAssociation("9", "Dakar", "Senegal", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        VolAssociation dakar2 = new VolAssociation("10", "Dakar", "Senegal", new java.util.Date(),
                40.0, 40.0, TypeVol.Charter);

        volsChartersCorrespondantsALaDemande.add(gambie1);
        volsChartersCorrespondantsALaDemande.add(gambie2);
        volsChartersCorrespondantsALaDemande.add(tunisie1);
        volsChartersCorrespondantsALaDemande.add(tunisie2);
        volsChartersCorrespondantsALaDemande.add(guinee1);
        volsChartersCorrespondantsALaDemande.add(guinee2);
        volsChartersCorrespondantsALaDemande.add(dakar1);
        volsChartersCorrespondantsALaDemande.add(dakar2);
        volsChartersCorrespondantsALaDemande.add(cameroun1);
        volsChartersCorrespondantsALaDemande.add(cameroun2);
    }

    @Override
    public void action() {
        ACLMessage aclMessage = myAgent.receive();

        if (aclMessage != null) {
            switch (aclMessage.getPerformative()) {
                case ACLMessage.CFP:
                    //ACLMessage vols = aclMessage.createReply();
                    //vols.setPerformative(ACLMessage.PROPOSE);
                    ACLMessage vols = manageCFP(aclMessage);
                    myAgent.send(vols);
                    break;

                case ACLMessage.ACCEPT_PROPOSAL:
                    ACLMessage acceptation = manageACCEPT_PROPOSAL(aclMessage);
                    myAgent.send(acceptation);
                    break;

                default:
                    break;
            }
        } else {
            block();
        }
    }

    /**
     * Methode qui gere la demande initiale (volume, pays, date)
     * en renvoyant la liste des vols
     *
     * @param cfp message
     * @return message..
     */
    //{"pays":"Guinee","date":"May 16, 2017 09:10:10 AM","volume":"10"}
    private ACLMessage manageCFP(ACLMessage cfp) {

        //On recupere la demande
        String message = cfp.getContent();
        logger.info("Demande de vol : \n" + message);

        //On construit une reponse
        ACLMessage response = cfp.createReply();
        response.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);

        //On mappe de notre coté la demande
        demandeVols = gson.fromJson(message, DemandeVols.class);

        List<Vol> volsMatching = getVolMatching(demandeVols.getPays(), demandeVols.getVolume(), demandeVols.getDate());
        //On recupere la liste des vols pertinents

        int tailleListeVols = volsMatching.size();
        logger.info("TAILLE LISTE VOLS : " + tailleListeVols);

        //On transforme cette de liste de resultats en JSON
//        List<VolAssociation> vols = filterVols(demandeVols, volsChartersCorrespondantsALaDemande);
//        for (VolAssociation volAssociation : vols) {
//            volAssociation.setVolume(demandeVols.getVolume());
//        }

        String messageAssociationContent = gson.toJson(volsMatching);

        if (tailleListeVols > 0) {
            response.setPerformative(ACLMessage.PROPOSE);
            response.setContent(messageAssociationContent);
        } else {
            response.setPerformative(ACLMessage.REFUSE);
        }

        logger.info(messageAssociationContent);
        logger.info("Liste de vols envoyée aux associations");

        return response;
    }

    /*[{"uuid":"c0628118-e751-4ff2-8ee3-2d4d053262c2", "capacite":10},
     {"uuid":"c0a7cbdc-ac69-470f-997a-465a7d0fc584", "capacite":20}]*/
    //!\prevoir cas capacite trop grosse ?
    private ACLMessage manageACCEPT_PROPOSAL(ACLMessage acceptProposal) {
        String volsChoisis = acceptProposal.getContent();
        logger.info("Liste de vols acceptes : \n" + volsChoisis);

        ACLMessage response = acceptProposal.createReply();
        response.setPerformative(ACLMessage.INFORM);

        VolAssociationFinal volAccepte = gson.fromJson(volsChoisis, VolAssociationFinal.class);

        System.out.println("ACCEPTATIONS : " + volAccepte.toString());

        // On met a jour l'etat de la base de donnees
        for (VolAssociation volAssociation : volsChartersCorrespondantsALaDemande) {
            if (volAssociation.getIdVol().equals(volAccepte.getIdVol())) {
                double difference = volAssociation.getCapaciteLibre() - demandeVols.getVolume();
                if (difference < 0) {
                    response.setPerformative(ACLMessage.REFUSE);
                    return response;
                } else {
                    volAssociation.setCapaciteLibre(volAssociation.getCapaciteLibre() -
                            demandeVols.getVolume());
                }
            }
        }
        System.out.println(response.getContent());
        response.setContent(gson.toJson(volAccepte));
        return response;
    }

    private List<VolAssociation> filterVols(DemandeVols demandeVols, List<VolAssociation> volsProposes) {
        List<VolAssociation> vols = new ArrayList<>();

        for (VolAssociation vol : volsProposes) {
            if (demandeVols.getPays().equals(vol.getPays())
                    && demandeVols.getVolume() <= vol.getCapaciteLibre()
                    && isSameDay(demandeVols.getDate(), vol.getDateArrivee())) {
                vols.add(vol);
            }
        }
        return vols;
    }

    public boolean isSameDay(Date d1, Date d2) {
        Calendar date1 = Calendar.getInstance();
        date1.setTime(d1);

        Calendar date2 = Calendar.getInstance();
        date2.setTime(d2);

        return date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH) &&
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR);
    }
}



