package m2dl.pcr.akka.mobile2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JPA on 30/05/2018
 */
public class ScriptMobile {

    public static final Logger log = LoggerFactory.getLogger(ScriptMobile.class);

    public static void main(String... args) throws Exception {

        final ActorSystem Marseille = ActorSystem.create("Marseille");
        final ActorSystem Lyon = ActorSystem.create("Lyon");
        final ActorSystem Paris = ActorSystem.create("Paris");

        // Création de l'acteur UntypedMobileActor à Marseille
        final ActorRef theMobileActor = Marseille.actorOf(Props.create(Mobile.class), "mobile-actor");
        theMobileActor.tell("Where are you?", null);
        Thread.sleep(2000);

        theMobileActor.tell(Lyon, null); // L'acteur mobile doit se déplacer à Lyon
        Thread.sleep(2000);
        theMobileActor.tell("Where are you?", null);
        Thread.sleep(2000);

        theMobileActor.tell(Paris, null); // L'acteur mobile doit se déplacer à Paris
        Thread.sleep(2000);
        theMobileActor.tell("Where are you?", null);
        Thread.sleep(2000);

        log.debug("ActorSystem Marseille Shutdown Starting...");
        Marseille.terminate();
        log.debug("ActorSystem Lyon Shutdown Starting...");
        Lyon.terminate();
        log.debug("ActorSystem Paris Shutdown Starting...");
        Paris.terminate();
    }
}

