package m2dl.pcr.akka.helloworld1;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class SimpleHelloWorldActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public SimpleHelloWorldActor() {
        log.info("SimpleHelloWorldActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        log.info("Received msg: " + msg);

        if (msg instanceof String) {
           log.info("Hello " + msg + "!");
        } else {
            unhandled(msg);
        }
    }


}
