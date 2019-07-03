package nl.vpro.amara;

import java.net.URI;

import org.junit.Test;

/**
 * @author Michiel Meeuwissen
 * @since 1.8
 */
public class SubClientTest {
    @Test
    public void builder() throws Exception {
        SubClient client = new SubClient(
            new Client("https://amara.org", null, null, null, null), "test") {
        };

        URI uri = client.builder()
            .pathSegment("x", "languages", "en", "subtitles")
            .queryParam("foobar")
            .build().encode().toUri();
        System.out.println(uri);

    }

}
