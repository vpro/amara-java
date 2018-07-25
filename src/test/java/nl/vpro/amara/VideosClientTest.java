package nl.vpro.amara;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import nl.vpro.amara.domain.Subtitles;
import nl.vpro.amara.domain.Video;
import nl.vpro.amara.domain.VideoMetadata;

/**
 * @author Michiel Meeuwissen
 * @since 0.2
 */
@Ignore("This is integration test requiring actual key in ~/conf/amara.properties")
@Slf4j
public class VideosClientTest extends AbstractClientsTest {

    public static String example = "{\"video_url\":\"http://download.omroep.nl/vpro/netinnederland/hasp/WO_NTR_425175.mp4\",\"title\":\"De invloed van de SER // Adviezen en regeringsbeleid\",\"description\":\"De Sociaal Economische Raad (SER) adviseert de regering over belangrijke dingen, zoals de WAO en de ziektekostenwet.\",\"primary_audio_language_code\":\"nl\",\"thumbnail\":\"http://images-test.poms.omroep.nl/image/32071124.jpg\",\"metadata\":{\"location\":\"WO_NTR_425175\",\"speaker-name\":\"De invloed van de SER\"},\"team\":\"netinnederland-staging\",\"project\":\"current\"}";

    public static String video  = "{\n" +
        "  \"video_url\" : \"http://download.omroep.nl/vpro/netinnederland/hasp/WO_NTR_425175.mp4\",\n" +
        "  \"title\" : \"De invloed van de SER // Adviezen en regeringsbeleid\",\n" +
        "  \"description\" : \"De Sociaal Economische Raad (SER) adviseert de regering over belangrijke dingen, zoals de WAO en de ziektekostenwet.  \",\n" +
        "  \"primary_audio_language_code\" : \"nl\",\n" +
        //"  \"thumbnail\" : \"http://images-test.poms.omroep.nl/image/32071124.jpg\",\n" +
        "  \"metadata\" : {\n" +
        "    \"location\" : \"WO_NTR_425175\",\n" +
        "    \"speaker-name\" : \"De invloed van de SER\"\n" +
        "  },\n" +
        "  \"team\" : \"netinnederland\",\n" +
        "  \"project\" : \"current\"\n" +
        "}";



    @Test
    public void postSubtitles() throws IOException {
        Subtitles in = AmaraObjectMapper.INSTANCE.readerFor(Subtitles.class).readValue(example);
        in.setAction("save-draft");
        System.out.println(client.videos().post(in, "6dNTmqJsQf1x", "nl"));
    }

/*

    @Test
    public void postSubtitles2() throws IOException {
        Subtitles in = AmaraObjectMapper.INSTANCE.readerFor(Subtitles.class).readValue(example);
        in.setAction("save-draft");
        System.out.println(client.videos().post(constructVideo(), "6dNTmqJsQf1x", "nl"));
    }

*/

    protected Video constructVideo() {
        String pomsMidBroadcast = "POW_03372509";
        String videoTitel = "test";
        String speakerName = "";

        String thumbnailUrl = "http://images-test.poms.omroep.nl/image/32071124.jpg";

        VideoMetadata amaraVideoMetadata = new VideoMetadata(speakerName, pomsMidBroadcast);
        Video amaraVideo = new Video("http://download.omroep.nl/vpro/netinnederland/hasp/WO_NTR_425175.mp4",
            "nl",
            videoTitel,
            "descriptoin",
            client.getTeam(),
            amaraVideoMetadata);
        amaraVideo.setThumbnail(thumbnailUrl);
        amaraVideo.setProject("current");
        return amaraVideo;
    }


    @Test
    // GIVES 504. I don't know why.
    public void postVideo() throws IOException {
        Video in = AmaraObjectMapper.INSTANCE.readerFor(Video.class).readValue(video);
        //
        System.out.println(video);
        System.out.println(client.videos().post(in));

    }

    @Test
    public void getSubtitles() {
        Subtitles subtitles = client.videos().getSubtitles("BNkV7s6mKMNb", "ar", "vtt");
        log.info("" + subtitles);
    }


    @Test
    public void getVideo() {
        Video video = client.videos().get("BNkV7s6mKMNb");
        log.info("" + video);
    }

}
