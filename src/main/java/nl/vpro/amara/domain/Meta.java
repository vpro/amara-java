package nl.vpro.amara.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

    private String previous;
    private String next;
    private int offset;
    private int limit;
    private int total_count;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
