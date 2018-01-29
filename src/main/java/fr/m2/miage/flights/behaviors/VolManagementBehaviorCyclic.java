package fr.m2.miage.flights.behaviors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.m2.miage.flights.discuss.DemandeVols;
import fr.m2.miage.flights.models.Vol;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static fr.m2.miage.flights.services.DatabaseService.getFlightsMatchingDemand;

public class VolManagementBehaviorCyclic extends CyclicBehaviour {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VolManagementBehaviorCyclic.class);
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
    private ACLMessage manageCFP(ACLMessage cfp) {

        // On récupère la demande
        String message = cfp.getContent();
        logger.info("Demande de vol : \n" + message);

        // On construit une réponse
        ACLMessage response = cfp.createReply();
        response.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);

        // On mappe de notre coté la demande
        DemandeVols demandeVols = gson.fromJson(message, DemandeVols.class);

        // On récupère la liste des vols pertinents
        List<Vol> volsMatching = getFlightsMatchingDemand(demandeVols.getPays(), demandeVols.getVolume(), demandeVols.getDate());

        logger.info("La liste contient : " + volsMatching.size() + " vols !");

        String messageAssociationContent = gson.toJson(volsMatching);

        if (volsMatching.size() > 0) {
            response.setPerformative(ACLMessage.PROPOSE);
            response.setContent(messageAssociationContent);
        } else {
            response.setPerformative(ACLMessage.REFUSE);
        }

        logger.info("Liste de vols envoyée aux associations : " + messageAssociationContent);

        return response;
    }

    private ACLMessage manageACCEPT_PROPOSAL(ACLMessage acceptProposal) {
        String volsChoisis = acceptProposal.getContent();
        logger.info("Liste de vols acceptés : " + volsChoisis);

        ACLMessage response = acceptProposal.createReply();
        response.setPerformative(ACLMessage.INFORM);

        Vol volAccepte = gson.fromJson(volsChoisis, Vol.class);

        System.out.println("Vols acceptés : " + volAccepte.toString());

        System.out.println(response.getContent());
        response.setContent(gson.toJson(volAccepte));

        return response;
    }
}



