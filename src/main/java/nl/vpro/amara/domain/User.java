package nl.vpro.amara.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.net.URI;

/**
 * @author Michiel Meeuwissen
 */
@ToString
@Data
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
}
