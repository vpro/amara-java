package nl.vpro.amara;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import nl.vpro.amara.domain.Task;
import nl.vpro.amara.domain.TaskCollection;

/**
 * Returned by Client#teams(). No need to instantiate this yourself.
 * @author Michiel Meeuwissen
 * @since 1.0
 */
public class TeamsClient extends SubClient {

    TeamsClient(Client client) {
        super(client, "teams");
    }

    public TaskCollection getTasks(TaskType taskType, Instant after, int offset, int limit) {
        
        
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
        HttpStatus httpStatus = response.getStatusCode();
        return tasks;
    }
    
    public List<Task> getTasks(TaskType taskType, Instant after) {
        final List<Task> tasks = new ArrayList<>();
        final int batchSize = 100;
        int offset = 0;
        while(true) {
            TaskCollection sub = getTasks(taskType, after, offset, batchSize);
            tasks.addAll(sub.getTasks());
            if (sub.getTasks().size() == 0
                || sub.getMeta().getTotal_count() == tasks.size()) {
                break;
            }
            offset += batchSize;
        }
        return tasks;
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
