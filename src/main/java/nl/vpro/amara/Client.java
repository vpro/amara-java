package nl.vpro.amara;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

// why spring?

/**
 * A java-client for AMARA (See http://www.amara.org).
 *
 * @author Michiel Meeuwissen
 * @since 1.2
 */
@Slf4j
public class Client {

    public static String FUTURE_VERSION = "20190619";

    private final String amaraUrl;
    private final String username;
    private final String apiKey;
    private final String team;
    private final String futureVersion;

    private final VideosClient videos = new VideosClient(this);
    private final ActivityClient activity = new ActivityClient(this);
    private final TeamsClient teams = new TeamsClient(this);

    @Builder
    public Client(String url, String user, String apiKey, String team, String futureVersion) {
        this.amaraUrl = url;
        this.username = user;
        this.apiKey = apiKey;
        this.team = team;
        this.futureVersion = futureVersion;
        if (this.futureVersion != null) {
            log.info("Using FUTURE VERSION {}", this.futureVersion);
        }

    }

    public VideosClient videos() {
        return videos;
    }

    public ActivityClient activity() {
        return activity;
    }

    public TeamsClient teams() {
        return teams;
    }


/*
    public TaskCollection getTasks() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TaskCollection> request = new HttpEntity<>(getGetHeaders());
        ResponseEntity<TaskCollection> response = restTemplate.exchange(getUriForPath("api/task"), HttpMethod.GET, request, TaskCollection.class);
        TaskCollection tasks = response.getBody();
        HttpStatus httpStatus = response.getStatusCode();

        LOG.debug(String.valueOf(response));

        return tasks;
    }*/




    protected HttpHeaders getGetHeaders() {
        HttpHeaders headers = new HttpHeaders();
        genericHeaders(headers);
        return headers;
    }

    protected HttpHeaders getPostHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        genericHeaders(headers);
        return headers;
    }

    protected void authenticate(HttpHeaders headers) {
        headers.add("X-api-username", username);
        headers.add("X-api-key", apiKey);
    }

    protected void genericHeaders(HttpHeaders headers) {
        authenticate(headers);
        if (futureVersion != null) {
            headers.add("X-API-FUTURE", futureVersion);
        }
    }



    @Override
    public String toString() {
        return "AmaraClient(" + username + "@" + amaraUrl + ")";
    }

    public String getAmaraUrl() {
        return amaraUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getTeam() {
        return team;
    }

}
