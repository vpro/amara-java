package nl.vpro.amara;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import nl.vpro.amara.domain.Action;
import nl.vpro.amara.domain.SubtitleAction;
import nl.vpro.amara.domain.Subtitles;
import nl.vpro.amara.domain.Video;

/**
 * @author Michiel Meeuwissen
 * @since 1.0
 */
public class VideosClient extends SubClient {

    private final static Logger LOG = LoggerFactory.getLogger(Client.class);

    protected VideosClient(Client client) {
        super(client, "videos");
    }


    /**
     * See <a href="https://amara.org/api/videos/">amara doc</a>
     * TODO unfinished
     */
    public void list() {

        HttpEntity<?> request = new HttpEntity<>(client.getGetHeaders());
        RestTemplate restTemplate = new RestTemplate();
        //HttpEntity<String> response = restTemplate.exchange(getUriForGetAndPostVideos(), HttpMethod.GET, request, String.class);
        //LOG.info(String.valueOf(response));

//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<String> request = new HttpEntity<String>(headers);
//        ResponseEntity<AmaraVideo[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, AmaraVideo[].class);
//        AmaraVideo[] amaraVideos = response.getBody();

//        AmaraVideo amaraVideo = restTemplate.getForObject(url, AmaraVideo.class);
//        logger.info(amaraVideo.toString());
    }


    /**
     * See <a href="https://amara.org/api/videos/">amara doc</a>
     */
    public Subtitles post(Subtitles amaraSubtitlesIn, String video_id, String language_code) {

        Subtitles amaraSubtitlesOut = null;

        try {
            URI uri = builder()
                .pathSegment(video_id, "languages", language_code, "subtitles")
                .queryParam(client.getTeam())
                .build().encode().toUri();

            // do request
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Subtitles> request = new HttpEntity<>(amaraSubtitlesIn, client.getPostHeaders());
            ResponseEntity<Subtitles> response = restTemplate.exchange(uri, HttpMethod.POST, request, Subtitles.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                amaraSubtitlesOut = response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOG.error("{}:{}", e.getMessage(), e.getResponseBodyAsString());
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
                .queryParam("team", client.getTeam())
                .queryParam("format", format)
                .build().encode().toUri();
            HttpEntity<String> response = get(uri, String.class);
            LOG.info(response.getBody());

            // // TODO: 09/04/16 fix
//            if (response.statusCode = HttpStatus.OK) {
            amaraSubtitles = response.getBody();
//            }

        } catch (HttpClientErrorException e) {
            LOG.info(e.toString());
            String responseBody = e.getResponseBodyAsString();
            LOG.info(responseBody);
        }

        return amaraSubtitles;
    }


    // DOESN'T WORK BECAUSE SUBTITLE ARE ALSO IN JSON FORMAT - to get it working a more detailed model is needed
    public Subtitles getSubtitles(String video_id, String language_code, String format) {

        Subtitles amaraSubtitlesOut = null;

        try {
            URI uri = builder()
                .pathSegment(
                    video_id,
                    "languages",
                    language_code,
                    "subtitles")
            //                    .queryParam("team", Config.getRequiredConfig("amara.api.team"))
                .queryParam("sub_format", format)
                .build().encode().toUri();
            
            ResponseEntity<Subtitles> response = get(uri, Subtitles.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                amaraSubtitlesOut = response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOG.info(e.toString());
            String responseBody = e.getResponseBodyAsString();
            LOG.info(responseBody);
        }

        return amaraSubtitlesOut;
    }

    public Video get(String video_id) {
        URI uri = builder()
            .pathSegment(video_id)
            .queryParam("team", client.getTeam())
            .build().encode().toUri();

        ResponseEntity<Video> response = get(uri, Video.class);
        return response.getBody();
    }


    public Video post(Video amaraVideoIn) {

        Video amaraVideoOut = null;

        try {
            URI uri = uri(builder());
            
            ResponseEntity<Video> response = post(uri, amaraVideoIn, Video.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                amaraVideoOut = response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOG.info("For " + amaraVideoIn + ":" + e.getMessage());
            String responseBody = e.getResponseBodyAsString();
            LOG.info(responseBody);
        }

        return amaraVideoOut;
    }
    public SubtitleAction post(SubtitleAction amaraSubtitleAction, String video_id, String language_code) {

        SubtitleAction amaraSubtitleActionOut = null;

        try {
            URI uri = uri(
                builder()
                .pathSegment(video_id, "languages", language_code, "subtitles", "actions")
            );

            ResponseEntity<SubtitleAction> response = post(uri, amaraSubtitleAction, SubtitleAction.class);

            // status code is not set in this case
//            if (response.getStatusCode() == HttpStatus.CREATED) {
//                amaraSubtitleActionOut = response.getBody();
//            }
        } catch (HttpClientErrorException e) {
            LOG.info(e.toString());
            LOG.info(e.getResponseBodyAsString());
        }

        return amaraSubtitleActionOut;
    }
 
}
