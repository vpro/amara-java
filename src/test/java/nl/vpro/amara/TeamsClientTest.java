package nl.vpro.amara;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import nl.vpro.amara.domain.Task;
import nl.vpro.amara.domain.TaskCollection;

/**
 * @author Michiel Meeuwissen
 */
@Ignore("This is integration test requiring actual key in ~/conf/amara.properties")
@Slf4j
public class TeamsClientTest extends AbstractClientsTest {



    @Test
    public void getApproveTasks() {
        Duration duration = Duration.ofDays(10);
        System.out.println(duration);
        Instant after = Instant.now().minus(duration);
        List<Task> approve = client
            .teams().getTasks(TaskType.Approve, after);
        for (Task t : approve) {
            log.info("Found task {}", t);
        }
        log.info("Found {}", approve.size());
    }
}
