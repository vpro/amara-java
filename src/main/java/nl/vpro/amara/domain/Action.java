package nl.vpro.amara.domain;

import lombok.Data;

/**
 * @author Michiel Meeuwissen
 * @since 1.2
 */
@Data
public class Action {

    private String action;
    private String label;
    private Boolean complete;


    @Override
    public String toString() {
        return action + ":" + label + ":" + complete;

    }
}
