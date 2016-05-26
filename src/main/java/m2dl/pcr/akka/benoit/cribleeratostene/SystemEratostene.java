package m2dl.pcr.akka.benoit.cribleeratostene;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Benoît Sauvère on 26/05/2016.
 */
public class SystemEratostene {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("root-actor-eratostene");

        final ActorRef actorRef = actorSystem.actorOf(Props.create(EratosteneNode.class, 3), "eratostene-node-3");

        Integer N = Integer.parseInt(args[0]);
        for (int i = 3; i <= N; i++) {
            actorRef.tell(i, null);
        }

        actorRef.tell("QUIT", null);
    }
}
