package m2dl.pcr.akka.helloworld2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class HelloActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef nameActorRef;

    public HelloActor() {
        log.info("HelloActor constructor");
        nameActorRef = getContext().actorOf(Props.create(NameActor.class), "name-actor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("Hello");
            nameActorRef.tell(msg,getSelf());
        } else {
            unhandled(msg);
        }
    }


}
