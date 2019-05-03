package nl.vpro.amara.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class TaskCollection extends AbstractCollection<Task> {


    @Deprecated
    public List<Task> getTasks() {
        return getObjects();
    }
}
