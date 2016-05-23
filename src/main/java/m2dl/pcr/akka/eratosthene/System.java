package m2dl.pcr.akka.eratosthene;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.helloworld3.HelloGoodbyeActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main runtime class.
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        Integer maxInt = 100 ; // default value
        try {
            maxInt = Integer.parseInt(args[0]); // take value from launch argument
        } catch (Exception e) {}

        final ActorSystem actorSystem = ActorSystem.create("actor-system");
        final ActorRef actorRef = actorSystem.actorOf(Props.create(CribleNodeActor.class, 2), "2");

        log.debug("Display prime numbers lower than " + maxInt);

        for (int i=3 ; i<=maxInt ; i++) {
            actorRef.tell(i,null);
            Thread.sleep(1);
        }

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
