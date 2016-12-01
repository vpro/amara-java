package nl.vpro.amara;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.vpro.amara.domain.Subtitles;
import nl.vpro.amara.domain.Video;

/**
 * @author Michiel Meeuwissen
 * @since 0.2
 */
@Ignore("This is integration test requiring actual key in ~/conf/amara.properties")
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
        "  \"team\" : \"netinnederland-staging\",\n" +
        "  \"project\" : \"current\"\n" +
        "}";

 

    @Test
    public void postSubtitles() throws IOException {
        Subtitles in = new ObjectMapper().readerFor(Subtitles.class).readValue(example);

        System.out.println(client.videos().post(in, "bla", "nl"));
    }
    @Test
    // GIVES 504. I don't know why.
    public void postVideo() throws IOException {
        Video in = new ObjectMapper().readerFor(Video.class).readValue(video);
        //
        System.out.println(video);
        System.out.println(client.videos().post(in));

    }

}
