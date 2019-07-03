package nl.vpro.amara;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import nl.vpro.amara.domain.*;
import nl.vpro.util.BatchedReceiver;

/**
 * Returned by Client#videos(). No need to instantiate this yourself.

 * @author Michiel Meeuwissen
 * @since 1.0
 */
@Slf4j
public class VideosClient extends SubClient {

    public VideosClient(Client client) {
        super(client, "videos");
    }


    /**
     * See <a href="https://amara.org/api/videos/">amara doc</a>
     * TODO unfinished
     */
    public Iterator<Video> list() {

        URI uri = builder()
            .path("/")
            .queryParam("team", client.getTeam())
            .build()
            .encode()
            .toUri();


        return
            BatchedReceiver.<Video>builder()
                .batchGetter(new Supplier<Iterator<Video>>() {
                    ResponseEntity<VideoCollection> response;
                    @Override
                    public Iterator<Video> get() {
                        if (response == null) {
                            response = VideosClient.this.get(uri, VideoCollection.class);
                        } else {
                            String nextUrl = response.getBody().getMeta().getNext();
                            if (nextUrl == null) {
                                return null;
                            }
                            response = VideosClient.this.get(URI.create(nextUrl), VideoCollection.class);
                        }
                        return response.getBody().getObjects().iterator();

                    }
                })
                .build();
    }


    /**
     * See <a href="https://amara.org/api/videos/">amara doc</a>
     */
    public Subtitles post(Subtitles amaraSubtitlesIn, String video_id, String language_code) {

        Subtitles amaraSubtitlesOut = null;
        URI uri = builder()
            .pathSegment(video_id, "languages", language_code, "subtitles")
            .path("/")
            .queryParam("team", client.getTeam())
            .build().encode().toUri();
        try {
            // do request
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Subtitles> request = new HttpEntity<>(amaraSubtitlesIn, client.getPostHeaders());
            ResponseEntity<Subtitles> response = restTemplate.exchange(uri, HttpMethod.POST, request, Subtitles.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                amaraSubtitlesOut = response.getBody();
            } else {
                log.error("Status code for {} is {}", uri, response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            log.error("{} : {}:{}", uri,  e.getMessage(), e.getResponseBodyAsString());
        }

        return amaraSubtitlesOut;
    }


    public List<Action> getActions(String video_id, String language_code) {
        URI uri = builder()
            .pathSegment(
                video_id,
                "languages",
                language_code,
                "subtitles",
                "actions")
            .path("/")
            .build().encode().toUri();

        ResponseEntity<Action[]> response = get(uri, Action[].class);

        Action[] body = response.getBody();
        return Arrays.asList(body);
    }

    public String getAsVTT(String video_id, String language_code, String format) {

        String amaraSubtitles = null;

        try {
            // construct uri
            URI uri = builder()
                .pathSegment(
                    video_id,
                    "languages",
                    language_code,
                    "subtitles")
                .path("/")
                .queryParam("team", client.getTeam())
                .queryParam("format", format)
                .build().encode().toUri();
            HttpEntity<String> response = get(uri, String.class);
            log.info(response.getBody());

            // // TODO: 09/04/16 fix
//            if (response.statusCode = HttpStatus.OK) {
            amaraSubtitles = response.getBody();
//            }

        } catch (HttpClientErrorException e) {
            log.info(e.toString());
            String responseBody = e.getResponseBodyAsString();
            log.info(responseBody);
        }

        return amaraSubtitles;
    }


    public Subtitles getSubtitles(String video_id, String language_code, String format) {

        Subtitles amaraSubtitlesOut = null;

        try {
            URI uri = builder()
                .pathSegment(
                    video_id,
                    "languages",
                    language_code,
                    "subtitles")
                .path("/")
            //                    .queryParam("team", client.getTeam())
                .queryParam("sub_format", format)
                .build().encode().toUri();

            ResponseEntity<Subtitles> response = get(uri, Subtitles.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                amaraSubtitlesOut = response.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.info(e.toString());
            String responseBody = e.getResponseBodyAsString();
            log.info(responseBody);
        }

        return amaraSubtitlesOut;
    }

    public Video get(String video_id) {
        URI uri = builder()
            .pathSegment(video_id)
            .path("/")
            .queryParam("team", client.getTeam())
            .build().encode().toUri();

        ResponseEntity<Video> response = get(uri, Video.class);
        HttpHeaders headers = response.getHeaders();
        log.info("headers {}", headers);
        return response.getBody();
    }


    public Video post(Video amaraVideoIn) {

        URI uri = uri(builder().path("/"));
        try {

            ResponseEntity<Video> response = post(uri, amaraVideoIn, Video.class);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                return response.getBody();
            } else {
                log.warn("No video created for {}. Status code: {} {}", amaraVideoIn, response.getStatusCode(), response.toString());
            }
        } catch (HttpClientErrorException | org.springframework.web.client.HttpServerErrorException e) {
            log.error("For " + uri + " " + toString(amaraVideoIn) + ":" + e.getMessage());
            log.error(e.getResponseBodyAsString());
        }
        return null;
    }

    public SubtitleAction post(SubtitleAction amaraSubtitleAction, String video_id, String language_code) {

        SubtitleAction amaraSubtitleActionOut = null;

        try {
            URI uri = uri(
                builder()
                    .pathSegment(video_id, "languages", language_code, "subtitles", "actions")
                    .path("/")
            );

            ResponseEntity<SubtitleAction> response = post(uri, amaraSubtitleAction, SubtitleAction.class);

            // status code is not set in this case
//            if (response.getStatusCode() == HttpStatus.CREATED) {
//                amaraSubtitleActionOut = response.getBody();
//            }
        } catch (HttpClientErrorException e) {
            log.info(e.toString());
            log.info(e.getResponseBodyAsString());
        }

        return amaraSubtitleActionOut;
    }

}
