package nl.vpro.amara;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Michiel Meeuwissen
 * @since 0.3
 */
public class AmaraObjectMapper extends ObjectMapper {

    public static ObjectMapper INSTANCE = new AmaraObjectMapper();


    private AmaraObjectMapper() {
        super();
        registerModule(new JavaTimeModule());
    }
}
