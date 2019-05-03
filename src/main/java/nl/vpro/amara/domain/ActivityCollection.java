package nl.vpro.amara.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityCollection extends AbstractCollection<Activity> {


    @Deprecated
    public List<Activity> getActivities() {
        return getObjects();
    }
}
