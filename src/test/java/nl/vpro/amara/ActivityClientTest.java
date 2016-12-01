package nl.vpro.amara;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import nl.vpro.amara.domain.Activity;
import nl.vpro.amara.domain.TaskCollection;

/**
 * @author Michiel Meeuwissen
 */
public class ActivityClientTest extends AbstractClientsTest {


    @Test
    public void getActivity() throws IOException {

        Activity activity = client.activity().get("bla");


    }
}