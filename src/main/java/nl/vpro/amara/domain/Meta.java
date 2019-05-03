package nl.vpro.amara.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Meta {

    private String previous;
    private String next;
    private int offset;
    private int limit;
    private int total_count;

}
