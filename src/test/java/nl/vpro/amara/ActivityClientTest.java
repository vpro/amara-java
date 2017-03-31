package nl.vpro.amara;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import nl.vpro.amara.domain.Activity;
import nl.vpro.amara.domain.Task;

/**
 * @author Michiel Meeuwissen
 */
@Ignore("This is integration test requiring actual key in ~/conf/amara.properties")
public class ActivityClientTest extends AbstractClientsTest {


    @Test
    public void getActivity() throws IOException {

        Activity activity = client.activity().get("bla");


    }
    
    @Test
    public void getTasks() {
        Duration duration = Duration.ofDays(5);
        System.out.println(duration);
        Instant after = Instant.now().minus(duration);
        List<Task> approve = client.teams().getTasks("Approve", after).getTasks();
        for (Task t: approve) {
            System.out.println(t);
        }
    }
    
    
}
