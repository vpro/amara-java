package nl.vpro.amara.domain;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author joost

 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {


    private final static Logger LOG = LoggerFactory.getLogger(Task.class);

    public final static String TYPE_TRANSLATE = "Translate";

    public final static String TASK_APPROVED = "Approved";

    String video_id;
    String language;
    String type;
    User assignee;

    int priority;
    Instant completed;
    String resource_uri;


    public Task() {
    }

    public Task(String video_id, String language, String type, User assignee) {
        this.video_id = video_id;
        this.language = language;
        this.type = type;
        this.assignee = assignee;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String approved;

    public String getApproved() {
        return approved;
    }
    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Instant getCompleted() {
        return completed;
    }

    public void setCompleted(Instant completed) {
        this.completed = completed;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    @Override
    public String toString() {
        return "AmaraTask{" +
                "video_id='" + video_id + '\'' +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", assignee='" + assignee + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                ", approved='" + approved + '\'' +
                ", resource_uri='" + resource_uri + '\'' +
                '}';
    }





}
