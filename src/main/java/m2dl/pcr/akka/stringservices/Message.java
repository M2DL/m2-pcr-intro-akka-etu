package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;

import java.io.Serializable;

/**
 * Created by franck on 23/05/2016.
 */
public class Message implements Serializable {

    private String contenu ;
    private ActorRef recepteur ;

    public Message(String contenu, ActorRef recepteur) {
        this.contenu = contenu ;
        this.recepteur = recepteur ;
    }

    public String getContenu() {
        return contenu;
    }

    public ActorRef getRecepteur() {
        return recepteur;
    }
}
