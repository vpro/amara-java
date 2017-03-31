package nl.vpro.amara;

import java.net.URI;
import java.time.Instant;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import nl.vpro.amara.domain.Task;
import nl.vpro.amara.domain.TaskCollection;
import nl.vpro.amara.domain.TaskType;

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
    
    public Iterator<Task> getTasks(TaskType taskType, Instant after) {
        final int batchSize = 100;
        
        return new Iterator<Task>() {
            int count = 0;
            int offset = 0;
            TaskCollection sub = getTasks(taskType, after, offset, batchSize);
            Iterator<Task> subIterator = sub.getTasks().iterator();

            @Override
            public boolean hasNext() {
                return (subIterator.hasNext() || sub.getMeta().getTotal_count() > count) && sub.getTasks().size() > 0;
            }

            @Override
            public Task next() {
                if (! hasNext()) {
                    throw new NoSuchElementException();
                }
                if (! subIterator.hasNext()) {
                    offset += batchSize;
                    sub = getTasks(taskType, after, offset, batchSize);
                    subIterator = sub.getTasks().iterator();
                }
                count++;
                return subIterator.next();
            }
        };
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
