package m2dl.pcr.akka.eratosthene;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import m2dl.pcr.akka.helloworld2.NameActor;

/**
 * Created by franck on 23/05/2016.
 */
public class CribleNodeActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef nextActorRef;
    private Integer integerNode;

    public CribleNodeActor(Integer theInteger) {
        integerNode = theInteger ;
        //log.info("Construtor : integerNode = " + integerNode);
    }

    Procedure<Object> endNode = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                Integer nextInteger = (Integer)msg ;
                if (nextInteger % integerNode != 0) { // c'est 1 nb premier
                    log.info(msg.toString()); // affiche le nombre
                    nextActorRef = getContext().actorOf(Props.create(CribleNodeActor.class, nextInteger),nextInteger.toString());
                    getContext().become(interNode);
                }
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> interNode = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                Integer nextInteger = (Integer)msg ;
                if (nextInteger % integerNode != 0) {
                    nextActorRef.tell(nextInteger,getSelf());
                }
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        endNode.apply(msg);
    }
}
