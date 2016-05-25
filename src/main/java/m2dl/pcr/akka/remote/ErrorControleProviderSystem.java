package m2dl.pcr.akka.remote;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.stringservices.ErreurControleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by franck on 25/05/2016.
 */
public class ErrorControleProviderSystem {

    public static final Logger log = LoggerFactory.getLogger(ErrorControleProviderSystem.class);

    public static void main(String... args) throws Exception {

        log.debug("Launch error controle provider system...");

        final ActorSystem cryptageProviderSystem = ActorSystem.create("ErrorControleProviderSystem",
                ConfigFactory.load(("system_erreur_controle_provider")));
        cryptageProviderSystem.actorOf(Props.create(ErreurControleProvider.class), "error-controle-provider");

        log.debug("Error controle provider system launched and listening...");

    }
}
