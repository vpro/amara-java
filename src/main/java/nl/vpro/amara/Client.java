package nl.vpro.amara;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

// why spring?

/**
 * A java-client for AMARA (See http://www.amara.org).
 *
 * @author Michiel Meeuwissen
 * @since 1.2
 */
public class Client {

    private final static Logger LOG = LoggerFactory.getLogger(Client.class);
    
    private final String amaraUrl;
    private final String username;
    private final String apiKey;
    private final String team;

    private final VideosClient videos = new VideosClient(this);
    private final ActivityClient activity = new ActivityClient(this);
    private final TeamsClient teams = new TeamsClient(this);


    public Client(String url, String username, String apiKey, String team) {
        this.amaraUrl = url;
        this.username = username;
        this.apiKey = apiKey;
        this.team = team;

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
        authenticate(headers);
        return headers;
    }

    protected HttpHeaders getPostHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        authenticate(headers);
        return headers;
    }

    protected void authenticate(HttpHeaders headers) {
        headers.add("X-api-username", username);
        headers.add("X-api-key", apiKey);
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

    public static class Builder  {
        private String amaraUrl;
        private String username;
        private String apiKey;
        private String team;

        public Builder url(String amaraUrl) {
            this.amaraUrl = amaraUrl;
            return this;
        }

        public Builder user(String username) {
            this.username = username;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder team(String team) {
            this.team = team;
            return this;
        }
        public Client build() {
            return new Client(amaraUrl, username, apiKey, team);
        }
    }

}
