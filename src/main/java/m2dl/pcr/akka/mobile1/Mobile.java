package m2dl.pcr.akka.mobile1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

/**
 * Created by JPA on 30/05/2018
 */
public class Mobile extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    ActorRef nextActor;

    public Mobile() {
        log.info("Mobile constructor");
    }

    Procedure<Object> forwarder = new Procedure<Object>() {
        public void apply(Object message) throws Exception {
            log.info("Received msg: " + message);
            log.info(getSelf() + " : I forward the message " + message + ". ");
            nextActor.tell(message, null);
            }
        };

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("Received msg: " + message);
        if (message instanceof ActorSystem) {
            ActorSystem nextPlace = (ActorSystem) message;
            log.info(getSelf() + " : I go to " + message + "!");
            nextActor = nextPlace.actorOf(Props.create(Mobile.class), "mobile-actor");
            getContext().become(forwarder,true);
        } else if (message.equals("Where are you?")) {
            log.info(getSelf() + " : I am on " + getSelf().path().address());
        } else {
            unhandled(message);
        }
    }
}
