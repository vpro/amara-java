package nl.vpro.amara.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Subtitles {

    String action; // only used for post, possible values: complete
    String version_number; // version number for the subtitles
    String subtitles; // Subtitle data
    String sub_format; // Format of the subtitles
    Language language; // Language data


    String title; // Video title, translated into the subtitle’s language
    String description; // Video description, translated into the subtitle’s language
    VideoMetadata metadata; // Video metadata, translated into the subtitle’s language
    String video_title; // Video title, translated into the video’s language
    String video_description; // Video description, translated into the video’s language
    String resource_uri; // API URI for the subtitles
    String site_uri; // URI to view the subtitles on site
    String video; // Copy of video_title (deprecated)
    String version_no; // Copy of version_number (deprecated)

    public Subtitles() {
    }

    public Subtitles(String title, String sub_format, String subtitles, String description, String action) {
        this.subtitles = subtitles;
        this.sub_format = sub_format;
        this.title = title;
        this.description = description;
        this.action = action;
    }

    @JsonIgnore
    public VideoMetadata getMetadata() {
        return metadata;
    }



    @Override
    public String toString() {
        return "AmaraSubtitles{" +
            "action='" + action + '\'' +
            ", version_number='" + version_number + '\'' +
            ", subtitles='" + subtitles + '\'' +
            ", sub_format='" + sub_format + '\'' +
            ", language=" + language +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", metadata='" + metadata + '\'' +
            ", video_title='" + video_title + '\'' +
            ", video_description='" + video_description + '\'' +
            ", resource_uri='" + resource_uri + '\'' +
            ", site_uri='" + site_uri + '\'' +
            ", video='" + video + '\'' +
            ", version_no='" + version_no + '\'' +
            '}';
    }
}
