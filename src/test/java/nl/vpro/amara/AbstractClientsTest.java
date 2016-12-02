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
        //.url("https://amara.org/")
        .url("https://amara.org/")
        .team("netinnederland")
        .user("netinnl")
        .apiKey(PROPERTIES.getProperty("amara.api.key"))
        .build();
}
