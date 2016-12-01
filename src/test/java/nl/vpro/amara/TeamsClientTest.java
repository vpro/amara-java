package nl.vpro.amara;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import nl.vpro.amara.domain.TaskCollection;

/**
 * @author Michiel Meeuwissen
 */
public class TeamsClientTest extends AbstractClientsTest {


    @Test
    public void getApprovals() throws IOException {
        Instant after = Instant.now().minus(Duration.ofDays(2));

        TaskCollection approve = client.teams().getTasks("Approve", after);


    }
}