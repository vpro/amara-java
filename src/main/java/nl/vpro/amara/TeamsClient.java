package nl.vpro.amara;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.time.Instant;
import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import nl.vpro.amara.domain.Task;
import nl.vpro.amara.domain.TaskCollection;
import nl.vpro.amara.domain.TaskType;
import nl.vpro.util.BatchedReceiver;

/**
 * Returned by Client#teams(). No need to instantiate this yourself.
 * @author Michiel Meeuwissen
 * @since 1.0
 */
@Slf4j
public class TeamsClient extends SubClient {

    TeamsClient(Client client) {
        super(client, "teams");
    }

    public TaskCollection getTasks(TaskType taskType, Instant after, long offset, int limit) {


        URI uri = uri(
            tasks()
                .path("/")
                .queryParam("type", taskType.name())
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .queryParam("completed")
                .queryParam("completed-after", after.toEpochMilli() / 1000));

        ResponseEntity<TaskCollection> response = get(uri, TaskCollection.class);
        TaskCollection tasks = response.getBody();
        log.debug("Total size: {}", tasks.getMeta().getTotal_count());
        HttpStatus httpStatus = response.getStatusCode();
        return tasks;
    }

    public Iterator<Task> getTasks(TaskType taskType, Instant after) {
        return BatchedReceiver.<Task>builder()
            .batchSize(100)
            .batchGetter((offset, max) -> getTasks(taskType, after, offset, max).getTasks().iterator())
            .build();

    }

    public Task post(Task amaraTaskIn) {
        Task amaraTaskOut = null;
        try {
            URI uri = uri(tasks().path("/"));
            ResponseEntity<Task> response = post(uri, amaraTaskIn, Task.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                amaraTaskOut = response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOG.info(e.toString());
            String responseBody = e.getResponseBodyAsString();
            LOG.info(responseBody);
        }

        return amaraTaskOut;
    }

    private UriComponentsBuilder tasks() {
        return builder()
            .pathSegment(client.getTeam(), "tasks");
    }


}
