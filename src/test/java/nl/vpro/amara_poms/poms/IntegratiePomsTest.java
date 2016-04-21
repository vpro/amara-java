package nl.vpro.amara_poms.poms;

import java.io.IOException;
import java.util.SortedSet;

import org.junit.Ignore;
import org.junit.Test;

import nl.vpro.amara_poms.Config;
import nl.vpro.domain.media.update.MemberRefUpdate;
import nl.vpro.domain.media.update.ProgramUpdate;
import nl.vpro.rs.media.MediaRestClient;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Michiel Meeuwissen
 * @since 1.0
 */
@Ignore("This is an integration test connecting to an actual server")
public class IntegratiePomsTest {

    String pomsMidBroadcast = "VPWON_1244706";
    String midCollectionFrom = "POMS_S_VPRO_1416538"; // NetInNederland - te vertalen;

    @Test
    public void testPomsClipCreate() throws IOException {
        Config.init();
        MediaRestClient client = Utils.getClient();

        String result = "";
        try {
            result = PomsClip.create(client, "VPWON_1250959", "en", "serie//test vertaalde titel", "test vertaalde description");
        } catch (Exception exception) {
            assertTrue(exception.toString(), false);
        }

        System.out.println(result);
    }

    @Test
    public void testAddProgramToCollection() {
        Config.init();
        MediaRestClient client = Utils.getClient();

        ProgramUpdate update = client.getProgram(pomsMidBroadcast); // test with this one

        SortedSet<MemberRefUpdate> memberUpdate = update.getMemberOf();

        // add collection
        MemberRefUpdate memberRefUpdate = new MemberRefUpdate(0, midCollectionFrom);
        memberUpdate.add(memberRefUpdate);

        // update
//        update.setMemberOf(memberUpdate);
        String result = client.set(update);
        System.out.println(result);
    }

    @Test
    public void removeFromTeVertalenToNetInNL() {
        Config.init();
        MediaRestClient client = Utils.getClient();

        PomsBroadcast pomsBroadcast = new PomsBroadcast(pomsMidBroadcast);
        pomsBroadcast.programUpdate = client.getProgram(pomsMidBroadcast);

        pomsBroadcast.removeFromCollection(midCollectionFrom);

    }

    @Test
    public void extractImageId() {
        Config.init();
        MediaRestClient client = Utils.getClient();

        String pomsMid = "VPRO_1122474";
        PomsBroadcast pomsBroadcast = new PomsBroadcast(pomsMid);
        pomsBroadcast.programUpdate = client.get(pomsMid); // not so nice way to test this function

        String imageId = pomsBroadcast.getImageId();
        long duration = pomsBroadcast.getProgramUpdate().getDuration().getSeconds();

        System.out.println("Duration:" + Long.toString(duration));
        System.out.println("ImageId:" + imageId);

        assertNotNull(imageId);
    }

}
