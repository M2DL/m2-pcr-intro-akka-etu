package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.eratosthene.System;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by franck on 23/05/2016.
 */
public class UC3System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        log.debug("Simple use case 2 : ActorSystem -> CryptageProvider -> ErreurControleProvider -> Recepteur");

        final ActorSystem actorSystem = ActorSystem.create("actor-system");
        Thread.sleep(5000);

        final ActorRef cryptageProviderRef = actorSystem.actorOf(Props.create(CryptageProvider.class), "cryptage-provider");
        final ActorRef erreurControlProviderRef = actorSystem.actorOf(Props.create(ErreurControleProvider.class), "erreur-controle-provider");
        final ActorRef recepteurRef = actorSystem.actorOf(Props.create(Recepteur.class), "recepteur");
        final ActorRef composerRef = actorSystem.actorOf(Props.create(Composer.class,cryptageProviderRef,erreurControlProviderRef,recepteurRef),"composer") ;

        composerRef.tell(new Message("hello world",recepteurRef),null);
        composerRef.tell(new Message("je pars",recepteurRef),null);

        Thread.sleep(1000);
        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
