package nl.vpro.amara;

import java.io.IOException;

import org.junit.Test;

import nl.vpro.amara.domain.Activity;

/**
 * @author Michiel Meeuwissen
 */
public class ActivityClientTest extends AbstractClientsTest {


    @Test
    public void getActivity() throws IOException {

        Activity activity = client.activity().get("bla");


    }
}