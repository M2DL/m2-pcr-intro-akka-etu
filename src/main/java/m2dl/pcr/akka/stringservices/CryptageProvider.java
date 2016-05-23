package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by franck on 23/05/2016.
 */
public class CryptageProvider extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            Message message = (Message)msg ;
            log.info("crypte \"" + message.getContenu() + "\" and send the result to " + message.getRecepteur().path().name());
            String res = StringUtils.crypte(message.getContenu()) ;
            message.getRecepteur().tell(new Message(res,message.getRecepteur()), getSelf());
        } else {
            unhandled(msg);
        }
    }
}
