package m2dl.pcr.akka.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.stringservices.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import java.util.concurrent.TimeUnit;

/**
 * Created by franck on 25/05/2016.
 */
public class ComposerRecepepteurSystem {

    public static final Logger log = LoggerFactory.getLogger(ComposerRecepepteurSystem.class);

    public static void main(String... args) throws Exception {

        log.debug("Launch error controle provider system...");

        // create the system
        final ActorSystem composerRecepteurSystem = ActorSystem.create("ComposerRecepteurSystem",
                ConfigFactory.load(("system_composer_recepteur")));

        // find actor ref on remote cryptage provider and remote erreur controle provider
        final String cryptageProviderPath = "akka.tcp://CryptageProviderSystem@127.0.0.1:2552/user/cryptage-provider";
        final Future<ActorRef> far = composerRecepteurSystem.actorSelection(cryptageProviderPath).resolveOne(FiniteDuration.apply(1000, TimeUnit.MILLISECONDS));
        final ActorRef cryptageProviderRef = Await.result(far,Duration.apply(1000,TimeUnit.MILLISECONDS)); // not very elegant (blocking a future !)

        final String errorControleProviderPath = "akka.tcp://ErrorControleProviderSystem@127.0.0.1:2553/user/error-controle-provider";
        final Future<ActorRef> far2 = composerRecepteurSystem.actorSelection(errorControleProviderPath).resolveOne(FiniteDuration.apply(1000, TimeUnit.MILLISECONDS));
        final ActorRef erreurControlProviderRef = Await.result(far2,Duration.apply(1000,TimeUnit.MILLISECONDS)); // not very elegant (blocking a future !)

        // create local recepteur and composer actors and get their actor ref
        final ActorRef recepteurRef = composerRecepteurSystem.actorOf(Props.create(Recepteur.class), "recepteur");
        final ActorRef composerRef = composerRecepteurSystem.actorOf(Props.create(Composer.class,cryptageProviderRef,erreurControlProviderRef,recepteurRef),"composer") ;

        composerRef.tell(new Message("hello world",recepteurRef),null);
        composerRef.tell(new Message("je pars",recepteurRef),null);

        Thread.sleep(1000);
        log.debug("Composer recepteur system Shutdown Starting...");

        composerRecepteurSystem.terminate();

    }
}
