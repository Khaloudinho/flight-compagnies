package fr.m2.miage.flights.behaviors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.m2.miage.flights.discuss.DemandeVols;
import fr.m2.miage.flights.discuss.VolAccepte;
import fr.m2.miage.flights.discuss.VolAssociation;
import fr.m2.miage.flights.models.TypeVol;
import fr.m2.miage.flights.models.Vol;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static fr.m2.miage.flights.services.HibernateSessionProvider.getSessionFactory;

//PROVISOIRE POUR TESTER L'INTERACTION
//String message = "{\"pays\":\"Guinee\",\"date\":\"2017-01-01\",\"volume\":\"10\"}";
//System.out.println("EN DUR "+message);

public class VolManagementBehaviorCyclic extends CyclicBehaviour {

    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VolManagementBehaviorCyclic.class);
    private final Gson gson = new GsonBuilder().create();

    public VolManagementBehaviorCyclic(Agent agent) {
        super(agent);
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
                    //ACLMessage acceptation = manageACCEPT_PROPOSAL(aclMessage);
                    //myAgent.send(acceptation);
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

        //On mappe de notre cote la demande
        DemandeVols demandeVols = gson.fromJson(message, DemandeVols.class);

        //On recupere la liste des vols pertinents
        List<VolAssociation> volsChartersCorrespondantsALaDemandeGuinee = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeTunisie = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeGambie = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeCameroun = new ArrayList<>();
        List<VolAssociation> volsChartersCorrespondantsALaDemandeSenegal = new ArrayList<>();

        VolAssociation guinee1 = new VolAssociation("1", "Conacky","Guinee",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation guinee2 = new VolAssociation("2", "Conacky","Guinee",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation tunisie1 = new VolAssociation("3", "Tunis","Tunisie",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation tunisie2 = new VolAssociation("4", "Tunis","Tunisie",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation gambie1 = new VolAssociation("5", "Banjul","Gambie",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation gambie2 = new VolAssociation("6", "Banjul","Gambie",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation cameroun1 = new VolAssociation("7", "Dhouala","Cameroun",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation cameroun2 = new VolAssociation("8", "Dhouala","Cameroun",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation dakar1 = new VolAssociation("9", "Dakar","Senegal",new java.util.Date(),
         40, 40, TypeVol.Charter);

        VolAssociation dakar2 = new VolAssociation("10", "Dakar","Senegal",new java.util.Date(),
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

        List<VolAssociation> volsChartersCorrespondantsALaDemande = new ArrayList<>();
        switch (demandeVols.getPays()){
            case "Guinee":
                volsChartersCorrespondantsALaDemande = volsChartersCorrespondantsALaDemandeGuinee;
                break;

            case "Tunisie":
                volsChartersCorrespondantsALaDemande = volsChartersCorrespondantsALaDemandeTunisie;
                break;

            case "Gambie":
                volsChartersCorrespondantsALaDemande = volsChartersCorrespondantsALaDemandeGambie;
                break;

            case "Cameroun":
                volsChartersCorrespondantsALaDemande =  volsChartersCorrespondantsALaDemandeCameroun;
                break;

            case "Senegal":
                volsChartersCorrespondantsALaDemande = volsChartersCorrespondantsALaDemandeSenegal;
                break;
        }

        int tailleListeVols = volsChartersCorrespondantsALaDemande.size();
        logger.info("TAILLE LISTE VOLS : " + tailleListeVols);

        //On transforme cette de liste de resultats en JSON
        String messageAssociationContent = gson.toJson(volsChartersCorrespondantsALaDemande);

        response.setPerformative(ACLMessage.PROPOSE);
        response.setContent(messageAssociationContent);

        logger.info(messageAssociationContent);
        logger.info("Liste de vols envoyée aux associations");

        return response;
    }

    /*[{"uuid":"c0628118-e751-4ff2-8ee3-2d4d053262c2", "capacite":10},
     {"uuid":"c0a7cbdc-ac69-470f-997a-465a7d0fc584", "capacite":20}]*/
    //!\prevoir cas capacite trop grosse ?
    private ACLMessage manageACCEPT_PROPOSAL(ACLMessage acceptProposal) {

        //FIX ME @sana
        //trouver un moyen de connaitre la quantite d'argent gagne (par rapport a la liste communique) : liste des vols est de leur prix attribut ?
        //match/intersect des deux liste
        //recuperation du prix mise dans notre portefeuille = attribut representant l'argent de l'agent

        //Suite la premiere demande nous recuperons une liste de vols desires
        String volsChoisis = acceptProposal.getContent();
        logger.info("Liste de vols acceptes (idVol, capacite) : \n" + volsChoisis);

        //Nous preparons une confirmation du traitement
        ACLMessage response = acceptProposal.createReply();
        response.setPerformative(ACLMessage.INFORM);

        //On doit faire un petit hack pour remapper leurs vols
        Type collectionType = new TypeToken<Collection<VolAccepte>>() {
        }.getType();
        ArrayList<VolAccepte> volAcceptes = gson.fromJson(volsChoisis, collectionType);

        //On met a jour l'etat de la base de donnees
        for (VolAccepte volAccepte :
                volAcceptes) {
            String idVol = volAccepte.getUuid();
            Integer capaciteAUtiliser = volAccepte.getCapacite();
            System.out.println(idVol + " " + capaciteAUtiliser);
            //fr.m2.miage.flights.Main.updateCapaciteVol(idVol, capaciteAUtiliser);
        }
        //response.setContent(acceptedVols);
        return response;
    }

    /*private void filterByDate(DemandeVols demandeVols, ArrayList<VolAssociation> volsProposes){
        Date = demandeVols.getDate();
    }*/
}



