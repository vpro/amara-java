package nl.vpro.amara;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import nl.vpro.amara.domain.Subtitles;

/**
 * @author Michiel Meeuwissen
 * @since 1.0
 */
public abstract class SubClient {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());


    protected final Client client;
    private final String path;

    protected SubClient(Client client, String path) {
        this.client = client;
        this.path = path;
    }

    protected UriComponentsBuilder builder() {
        return UriComponentsBuilder
            .fromHttpUrl(client.getAmaraUrl())
            .pathSegment("api")
            .pathSegment(path);
    }

    protected <T> ResponseEntity<T> get(URI uri, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Subtitles> request = new HttpEntity<>(client.getGetHeaders());
        return restTemplate.exchange(uri, HttpMethod.GET, request, clazz);

    }
    protected <S, T> ResponseEntity<T> post(URI uri, S in, Class<T> clazz) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<S> request = new HttpEntity<>(in, client.getPostHeaders());
            return restTemplate.exchange(uri, HttpMethod.POST, request, clazz);
        } catch (RuntimeException rte) {
            String object;
            try {
                object = AmaraObjectMapper.INSTANCE.writer().writeValueAsString(in);
            } catch (JsonProcessingException e) {
                LOG.warn(e.getMessage());
                object = String.valueOf(in);
            }
            LOG.error("For {} posting {} {}", uri, clazz, object);
            throw rte;
        }
    }
    protected URI uri(UriComponentsBuilder builder) {
        return builder.build().encode().toUri();
    }

    protected <T> String toString(T object) {
        if (object == null) {
            return "NULL";
        } else {
            try {
                return AmaraObjectMapper.INSTANCE.writerFor(object.getClass()).writeValueAsString(object);
            } catch (JsonProcessingException e) {
                return object.toString() + " (" + e.getMessage() + ")";
            }
        }
    }
}
