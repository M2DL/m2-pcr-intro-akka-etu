package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by franck on 23/05/2016.
 */
public class Composer extends UntypedActor {

    private ActorRef firstProvider ;
    private ActorRef secondProvider ;
    private ActorRef recepteur ;

    public Composer(ActorRef firstProvider, ActorRef secondProvider, ActorRef recepteur) {
        this.firstProvider = firstProvider;
        this.secondProvider = secondProvider;
        this.recepteur = recepteur;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            Message message = (Message)msg ;
            ActorRef helper = getContext().actorOf(Props.create(ComposerHelper.class, secondProvider, recepteur)) ;
            firstProvider.tell(new Message(message.getContenu(),helper),getSelf());
        } else {
            unhandled(msg);
        }

    }
}
