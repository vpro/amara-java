package nl.vpro.amara;

import nl.vpro.amara.domain.Activity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Michiel Meeuwissen
 */
@Disabled("This is integration test requiring actual key in ~/conf/amara.properties")
public class ActivityClientTest extends AbstractClientsTest {


    @Test
    public void getActivity() throws IOException {

        Activity activity = client.activity().get("bla");


    }



}
