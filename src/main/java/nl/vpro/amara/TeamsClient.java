package nl.vpro.amara;

import java.net.URI;
import java.time.Instant;

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

    public TaskCollection getTasks(String taskType, Instant after) {

        URI uri = uri(
            tasks()
                .path("/")
                .queryParam("type", taskType)
                .queryParam("limit", 300)
                .queryParam("completed")
                .queryParam("completed-after", after.toEpochMilli() / 1000));

        ResponseEntity<TaskCollection> response = get(uri, TaskCollection.class);
        TaskCollection tasks = response.getBody();

        HttpStatus httpStatus = response.getStatusCode();
//        logger.info(String.valueOf(response));

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
