package nl.vpro.amara.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michiel Meeuwissen
 * @since 0.9
 */
public abstract class AbstractCollection<T> {

    @Getter
    @Setter
    Meta meta;

    @Getter
    @Setter
    @JsonProperty("objects")
    List<T> objects;
}
