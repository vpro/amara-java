package nl.vpro.amara.domain;

import java.net.URI;

/**
 * @author Michiel Meeuwissen
 */
public class User {
    String id;
    String  username;
    URI uri;

    public static User ofUsername(String username) {
        User user = new User();
        user.username = username;
        return user;
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
