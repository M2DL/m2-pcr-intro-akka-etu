package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by franck on 23/05/2016.
 */
public class Recepteur extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            log.info(((Message) msg).getContenu());
        } else {
            unhandled(msg);
        }
    }

}
