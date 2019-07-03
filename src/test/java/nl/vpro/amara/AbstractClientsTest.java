package nl.vpro.amara;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Michiel Meeuwissen
 */
public class AbstractClientsTest {
    static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileReader(new File(System.getProperty("user.home") + "/conf/amara.properties")));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    Client client = Client.builder()
        .url(PROPERTIES.getProperty("amara.api.url", "https://amara.org/"))
        .team(PROPERTIES.getProperty("amara.api.team", "netinnederland"))
        .user(PROPERTIES.getProperty("amara.api.user", "netinnl"))
        .apiKey(PROPERTIES.getProperty("amara.api.key"))
        .futureVersion(Client.FUTURE_VERSION)
        .build();
}
