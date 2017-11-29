package nl.vpro.amara.domain;

import lombok.Data;

/**
 * @author joost
 */
@Data
public class SubtitleAction {

    final public static String ACTION_COMPLETE = "complete";
    final public static String ACTION_APPROVE = "approve";

    private String action; // only used to approve nl subtitle

    public SubtitleAction() {
    }

    public SubtitleAction(String action) {
        this.action = action;
    }

}
