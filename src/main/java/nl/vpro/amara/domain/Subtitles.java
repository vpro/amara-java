package nl.vpro.amara.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subtitles {

    String action; // only used for post, possible values: complete
    String version_number; // version number for the subtitles
    String subtitles; // Subtitle data (str)
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

    public void setMetadata(VideoMetadata metadata) {
        this.metadata = metadata;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getSub_format() {
        return sub_format;
    }

    public void setSub_format(String sub_format) {
        this.sub_format = sub_format;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_description() {
        return video_description;
    }

    public void setVideo_description(String video_description) {
        this.video_description = video_description;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getSite_uri() {
        return site_uri;
    }

    public void setSite_uri(String site_uri) {
        this.site_uri = site_uri;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVersion_no() {
        return version_no;
    }

    public void setVersion_no(String version_no) {
        this.version_no = version_no;
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
