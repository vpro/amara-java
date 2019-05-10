package nl.vpro.amara.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Slf4j
@Data
public class Video {

    private String id;
    private String video_url;
    private String title;
    private String description;
//    private String duration;
    private String primary_audio_language_code;
    private String thumbnail;
    private VideoMetadata metadata;
    private String team;
    private String project;
    private String[] all_urls;

    private Language amaraLanguage;


    // constructor with no parameter
    public Video() {}

    public Video(String video_url, String primary_audio_language_code, String title, String description, String team, VideoMetadata metadata) {
        this.video_url = video_url;
        this.primary_audio_language_code = primary_audio_language_code;
        this.title = title;
        this.description = description;
        this.team = team;
        this.metadata = metadata;
    }




    @Override
    public String toString() {
        return "AmaraVideo{" +
                "id='" + id + '\'' +
                ", video_url='" + video_url + '\'' +
                ", primary_audio_language_code='" + primary_audio_language_code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", team='" + team + '\'' +
                ", amaraLanguage=" + amaraLanguage +
                '}';
    }


    /**
     * Get videoUrl from all_urls
     * @return null if not found
     */
    public String getVideoUrlFromAllUrls() {
        String videoUrl = null;

        if (all_urls != null && all_urls.length > 0) {
            videoUrl = all_urls[0];
        }

        return  videoUrl;
    }

    /**
     * Get Poms MID from url
     * e.g. http://download.omroep.nl/vpro/netinnederland/h264/VPWON_1166750.m4v where VPWON_1166750 is Poms mid
     * @return poms mid if found, null otherwise
     */
    public String getPomsMidFromVideoUrl() {
        String pomsMid = null;

        String videoUrl = getVideoUrlFromAllUrls();

        if (videoUrl != null) {
            String parts[] = videoUrl.split("/");
            if (videoUrl.startsWith("http") && videoUrl.contains("download.omroep.nl")) {
                String filename = parts[parts.length - 1];
                String filenameParts[] = filename.split(Pattern.quote("."));
                if (filenameParts.length == 2) {
                    pomsMid = filenameParts[0];
                }
            }
        }

        return pomsMid;
    }

    public String getPomsMid() {
        if (getMetadata() != null && getMetadata().getLocation() != null) {
            return getMetadata().getLocation();
        }
        return getPomsMidFromVideoUrl();
    }

}

