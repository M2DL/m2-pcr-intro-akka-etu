package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.eratosthene.CribleNodeActor;
import m2dl.pcr.akka.eratosthene.System;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by franck on 23/05/2016.
 */
public class UC1System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        log.debug("Simple use case 1 : ActorSystem -> CryptageProvider -> Recepteur");

        final ActorSystem actorSystem = ActorSystem.create("actor-system");
        final ActorRef cryptageProviderRef = actorSystem.actorOf(Props.create(CryptageProvider.class), "cryptage-provider");
        final ActorRef recepteurRef = actorSystem.actorOf(Props.create(Recepteur.class), "recepteur");

        cryptageProviderRef.tell(new Message("hello world",recepteurRef),null);
        cryptageProviderRef.tell(new Message("je pars en vacances",recepteurRef),null);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
