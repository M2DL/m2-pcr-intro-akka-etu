package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by franck on 23/05/2016.
 */
public class ComposerHelper extends UntypedActor {

    private ActorRef recepteur ;
    private ActorRef nextProvider ;

    public ComposerHelper(ActorRef nextProvider, ActorRef recepteur) {
        this.nextProvider = nextProvider ;
        this.recepteur = recepteur ;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            nextProvider.tell(new Message(((Message) msg).getContenu(),recepteur),null);
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }
}
