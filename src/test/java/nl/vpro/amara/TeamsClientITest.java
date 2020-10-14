package nl.vpro.amara;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import nl.vpro.amara.domain.Task;
import nl.vpro.amara.domain.TaskType;

/**
 * @author Michiel Meeuwissen
 */
//@Disabled("This is integration test requiring actual key in ~/conf/amara.properties")
@Slf4j
public class TeamsClientITest extends AbstractClientsTest {



    @Test
    public void getApproveTasks() {
        Duration duration = Duration.ofDays(10);
        System.out.println(duration);
        Instant after = Instant.now().minus(duration);
        Iterator<Task> approve = client
            .teams().getTasks(TaskType.Approve, after);
        int count = 0;
        while(approve.hasNext()) {
            count++;
            log.info("Found task {}", approve.next());
        }
        log.info("Found {}", count);
    }
}
