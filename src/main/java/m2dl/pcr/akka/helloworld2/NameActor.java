package m2dl.pcr.akka.helloworld2;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class NameActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public NameActor() {
        log.info("NameActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof String) {
           log.info(msg + "!");
        } else {
            unhandled(msg);
        }
    }


}
