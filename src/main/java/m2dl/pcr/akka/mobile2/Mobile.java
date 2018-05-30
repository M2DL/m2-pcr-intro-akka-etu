package m2dl.pcr.akka.mobile2;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by JPA on 30/05/2018
 */
public class Mobile extends UntypedMobileActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Mobile() {
        log.info("Mobile constructor");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("Received msg: " + message);
        if (message instanceof ActorSystem) {
            ActorSystem nextPlace = (ActorSystem) message;
            log.info(getSelf() + " : I go to " + message + "!");
            nextActor = nextPlace.actorOf(Props.create(Mobile.class), "mobile-actor");
            getContext().become(forwarder, true);
        } else if (message.equals("Where are you?")) {
            log.info(getSelf() + " : I am on " + getSelf().path().address());
        } else {
            unhandled(message);
        }
    }
}
