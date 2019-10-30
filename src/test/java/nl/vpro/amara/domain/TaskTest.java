package nl.vpro.amara.domain;

import nl.vpro.amara.AmaraObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michiel Meeuwissen
 * @since 0.3
 */
public class TaskTest {

    @Test
    public void json() throws IOException {
        Task task = new Task("video_id", "ar", TaskType.Translate, User.builder().id("netinnl").build());

        String string = AmaraObjectMapper.INSTANCE.writer().writeValueAsString(task);
        assertThat(string).isEqualTo("{\"video_id\":\"video_id\",\"language\":\"ar\",\"type\":\"Translate\",\"assignee\":\"id$netinnl\",\"priority\":0,\"completed\":null,\"resource_uri\":null}");

        Task rounded = AmaraObjectMapper.INSTANCE.readerFor(Task.class).readValue(string);
        assertThat(rounded.getAssignee().getId()).isEqualTo("netinnl");

    }

    @Test
    public void jsonWithObject() throws IOException {
        Task task = new Task("video_id", "ar", TaskType.Translate, User.builder().id("WsywnFE7KZ1gJQOHSEFoXaq8xGpqCnjBKudT2wdOYdU").username("netinnl").build());

        String string = AmaraObjectMapper.INSTANCE.writer().writeValueAsString(task);
        assertThat(string).isEqualTo("{\"video_id\":\"video_id\",\"language\":\"ar\",\"type\":\"Translate\",\"assignee\":{\"id\":\"WsywnFE7KZ1gJQOHSEFoXaq8xGpqCnjBKudT2wdOYdU\",\"username\":\"netinnl\",\"uri\":null},\"priority\":0,\"completed\":null,\"resource_uri\":null}");

        Task rounded = AmaraObjectMapper.INSTANCE.readerFor(Task.class).readValue(string);
        assertThat(rounded.getAssignee().getUsername()).isEqualTo("netinnl");
        assertThat(rounded.getAssignee().getId()).isEqualTo("WsywnFE7KZ1gJQOHSEFoXaq8xGpqCnjBKudT2wdOYdU");

    }

}
