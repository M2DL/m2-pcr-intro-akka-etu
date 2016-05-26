package m2dl.pcr.akka.benoit.cribleeratostene;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.event.slf4j.Logger;
import m2dl.pcr.akka.benoit.exercice12.HelloGoodbyeActor;

/**
 * Created by Benoît Sauvère on 26/05/2016.
 */
public class EratosteneNode extends UntypedActor {

    private final int primeNumber;

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef previousNode;
    private ActorRef nextNode;

    public EratosteneNode(int primeNumber) {
        this.primeNumber = primeNumber;
        log.info("EratosteneNode constructor with number=[{}]", primeNumber);
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof String && "QUIT".equals(msg)) {
            previousNode = getSender();
            if (nextNode == null) {
                getSender().tell("STOP", getSelf());
                Thread.sleep(100);
                getContext().stop(getSelf());

            } else {
                nextNode.tell(msg, getSelf());
            }

        } else if (msg instanceof String && "STOP".equals(msg)) {
            previousNode.tell("QUIT", getSelf());
            Thread.sleep(100);
            getContext().stop(getSelf());

        } else if (msg instanceof Integer) {
            int number = (Integer) msg;
            if (number % primeNumber == 0) {

            } else {
                if (nextNode == null) {
                    nextNode = getContext().actorOf(Props.create(EratosteneNode.class, number), "eratostene-node-" + number);
                }
                nextNode.tell(number, getSelf());
            }

        } else {
            unhandled(msg);
        }
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("Terminating eratostene-node-" + primeNumber);
        if (nextNode == null) {
            getContext().system().terminate();
        }
    }
}
