package m2dl.pcr.akka.mobile2;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

/**
 * Created by JPA on 30/05/2018
 */
public abstract class UntypedMobileActor extends UntypedActor {
    ActorRef nextActor;

    Procedure<Object> forwarder = new Procedure<Object>() {
        public void apply(Object message) throws Exception {
            nextActor.tell(message, null);
        }
    };
}
