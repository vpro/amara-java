package nl.vpro.amara.domain.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import nl.vpro.amara.domain.User;

/**
 * @author Michiel Meeuwissen
 * @since 0.3
 */
public class UserJson {

    public static class Serializer extends JsonSerializer<User> {

        @Override
        public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            if (value == null) {
                gen.writeNull();
            } else if (value.getUsername() == null && value.getId() != null) {
                gen.writeString("id$" + value.getId());
            } else if (value.getUsername() != null && value.getId() == null) {
                gen.writeString(value.getUsername());
            } else {
                gen.writeObject(value);;
            }
        }
    }

    public static class Deserializer extends JsonDeserializer<User> {

        @Override
        public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode node = p.readValueAsTree();
            if (node.isTextual()) {
                String s = node.textValue();
                if (s.startsWith("id$")) {
                    return User.builder().id(s.substring(3)).build();
                } else {
                    return User.builder().username(s).build();
                }
            } else {
                User user = p.getCodec().treeToValue(node, User.class);
                return user;
            }
        }
    }
}
