package nl.vpro.amara.domain;

import lombok.Data;
import lombok.ToString;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import nl.vpro.amara.domain.json.UserJson;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class Task {


    public final static String TASK_APPROVED = "Approved";

    String video_id;
    String language;
    TaskType type;

    @JsonSerialize(using = UserJson.Serializer.class)
    @JsonDeserialize(using = UserJson.Deserializer.class)
    User assignee;

    int priority;
    Instant completed;
    String resource_uri;


    public Task() {
    }

    public Task(String video_id, String language, TaskType type, User assignee) {
        this.video_id = video_id;
        this.language = language;
        this.type = type;
        this.assignee = assignee;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String approved;


}
