package nl.vpro.amara;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.vpro.amara.domain.Subtitles;

/**
 * @author Michiel Meeuwissen
 * @since 0.2
 */
public class VideosClientTest {
    
    public static String example = "{\"video_url\":\"http://download.omroep.nl/vpro/netinnederland/hasp/WO_NTR_425175.mp4\",\"title\":\"De invloed van de SER // Adviezen en regeringsbeleid\",\"description\":\"De Sociaal Economische Raad (SER) adviseert de regering over belangrijke dingen, zoals de WAO en de ziektekostenwet.\",\"primary_audio_language_code\":\"nl\",\"thumbnail\":\"http://images-test.poms.omroep.nl/image/32071124.jpg\",\"metadata\":{\"location\":\"WO_NTR_425175\",\"speaker-name\":\"De invloed van de SER\"},\"team\":\"netinnederland-staging\",\"project\":\"current\"}";


    static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileReader(new File(System.getProperty("user.home") + "/conf/amara.properties")));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Test
    public void post() throws IOException {
        Subtitles in = new ObjectMapper().readerFor(Subtitles.class).readValue(example);

        Client client = new Client.Builder()
            .url("https://staging.amara.org/")
            .team("netinnederland-staging")
            .user("netinnl")
            .apiKey(properties.getProperty("apiKey"))
            .build();
        
        System.out.println(client.videos().post(in, "bla", "nl"));
    }
    
}
