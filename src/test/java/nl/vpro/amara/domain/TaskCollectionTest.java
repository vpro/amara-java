package nl.vpro.amara.domain;

import nl.vpro.amara.AmaraObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * @author Michiel Meeuwissen

 */
@Disabled
public class TaskCollectionTest {


    String example = "{\n" +
        "  \"meta\" : {\n" +
        "    \"previous\" : null,\n" +
        "    \"next\" : null,\n" +
        "    \"offset\" : 0,\n" +
        "    \"limit\" : 100,\n" +
        "    \"total_count\" : 16\n" +
        "  },\n" +
        "  \"objects\" : [ {\n" +
        "    \"id\" : 1803899,\n" +
        "    \"video_id\" : \"juY2IuoPHEy4\",\n" +
        "    \"language\" : \"ar\",\n" +
        "    \"type\" : \"Approve\",\n" +
        "    \"assignee\" : {\n" +
        "      \"username\" : \"mohammed-abdulrahman\",\n" +
        "      \"id\" : \"WsywnFE7KZ1gJQOHSEFoXaq8xGpqCnjBKudT2wdOYdU\",\n" +
        "      \"uri\" : \"https://amara.org/api/users/id$WsywnFE7KZ1gJQOHSEFoXaq8xGpqCnjBKudT2wdOYdU/\"\n" +
        "    },\n" +
        "    \"priority\" : 0,\n" +
        "    \"created\" : \"2016-11-30T11:08:30Z\",\n" +
        "    \"modified\" : \"2016-11-30T11:18:28Z\",\n" +
        "    \"completed\" : \"2016-11-30T11:18:28Z\",\n" +
        "    \"approved\" : \"Approved\",\n" +
        "    \"resource_uri\" : \"https://amara.org/api/teams/netinnederland/tasks/1803899/\"\n" +
        "  }]}";


    @Test
    public void json() throws IOException {
        TaskCollection collection = AmaraObjectMapper.INSTANCE.readerFor(TaskCollection.class).readValue(new StringReader(example));
        assertThat(collection.getObjects().get(0).getAssignee().getUri().toString()).isEqualTo("https://amara.org/api/users/id$WsywnFE7KZ1gJQOHSEFoXaq8xGpqCnjBKudT2wdOYdU/");
    }


}
