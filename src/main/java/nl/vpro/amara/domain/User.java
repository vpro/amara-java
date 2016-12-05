package nl.vpro.amara.domain;

import lombok.Builder;
import lombok.ToString;

import java.net.URI;

/**
 * @author Michiel Meeuwissen
 */
@ToString
public class User {
    String id;
    String  username;
    URI uri;

    public User() {

    }
    @Builder
    public User(String id, String username, URI uri) {
        this.id = id;
        this.username = username;
        this.uri = uri;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
