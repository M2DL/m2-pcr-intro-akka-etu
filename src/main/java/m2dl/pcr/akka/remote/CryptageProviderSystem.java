package m2dl.pcr.akka.remote;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.eratosthene.System;
import m2dl.pcr.akka.stringservices.CryptageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by franck on 25/05/2016.
 */
public class CryptageProviderSystem {

    public static final Logger log = LoggerFactory.getLogger(CryptageProviderSystem.class);

    public static void main(String... args) throws Exception {

        log.debug("Launch cryptage provider system...");

        final ActorSystem cryptageProviderSystem = ActorSystem.create("CryptageProviderSystem",
                ConfigFactory.load(("system_cryptage_provider")));
        cryptageProviderSystem.actorOf(Props.create(CryptageProvider.class), "cryptage-provider");

        log.debug("Cryptage provider system launched and listening...");

    }
}
