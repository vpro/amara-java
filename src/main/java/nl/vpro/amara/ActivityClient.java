package nl.vpro.amara;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nl.vpro.amara.domain.Activity;
import nl.vpro.amara.domain.ActivityCollection;

/**
 * Returned by Client#activity(). No need to instantiate this yourself.
 * @author Michiel Meeuwissen
 * @since 1.0
 */
public class ActivityClient extends SubClient {
    
    
    ActivityClient(Client client) {
        super(client, "activity");
    }

    public Activity get(String activity_id) {
        URI uri = uri(builder()
            .pathSegment(activity_id));
        ResponseEntity<Activity> response = get(uri, Activity.class);
        return response.getBody();
    }

    public ActivityCollection list() {
        URI uri = uri(builder());
        
        ResponseEntity<ActivityCollection> response = get(uri,  ActivityCollection.class);
        ActivityCollection amaryActivityCollection = response.getBody();

        HttpStatus httpStatus = response.getStatusCode();

        //LOG.info(String.valueOf(response));

        return amaryActivityCollection;
    }

    public ActivityCollection list(int activityType, long afterTimestampInSeconds) {

        URI uri = uri(builder()
            .queryParam("team", client.getTeam())
            .queryParam("type", activityType)
            .queryParam("after", afterTimestampInSeconds));

        ResponseEntity<ActivityCollection> response = get(uri, ActivityCollection.class);
        ActivityCollection amaryActivityCollection = response.getBody();

        HttpStatus httpStatus = response.getStatusCode();
        //LOG.info(String.valueOf(response));

        return amaryActivityCollection;
    }

}
