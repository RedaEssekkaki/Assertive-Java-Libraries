package comparisons.basicTests;

import org.examples.Dwarf.Dwarf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.google.common.truth.Truth.assertThat;

public class TruthBasicTest {

    private Dwarf dwarf;

    @BeforeEach
    void setup()
    {
        dwarf = new Dwarf("Jeremy", 80.4, 90.3, false);
    }

    @Test
    void testConstructor()
    {
        //test that all attributes set by constructor in setup() are correct
        assertThat(dwarf.getName()).isEqualTo("Jeremy");
        assertThat(dwarf.getSize()).isEqualTo(80.4);
        assertThat(dwarf.getWeight()).isEqualTo(90.3);
        assertThat(dwarf.isBearded()).isFalse();
    }

    @Test
    void testSize()
    {
        dwarf.setSize(40.0);

        //test size is properly set
        assertThat(dwarf.getSize()).isEqualTo(40.0);
    }

    @Test
    void testWeight()
    {
        dwarf.setWeight(24.7);

        //test weight is properly set
        assertThat(dwarf.getWeight()).isEqualTo(24.7);
    }

    @Test
    void testBeard()
    {
        dwarf.shave();

        //test dwarf has no beard
        assertThat(dwarf.isBearded()).isFalse();

        dwarf.growBeard();

        //test dwarf has beard
        assertThat(dwarf.isBearded()).isTrue();
    }

    @Test
    void testHungover()
    {
        dwarf.goesToTavern();
        //test that dwarf IS hungover
        assertThat(dwarf.isHungover()).isTrue();
        dwarf.sleep();
        //test that dwarf is NOT hungover
        assertThat(dwarf.isHungover()).isFalse();
    }

    @Test
    void testSongs()
    {
        String learnedSong = "i am a dwarf and i'm digging a hole";
        dwarf.learnSong(learnedSong);
        //test that dwarf has learned song
        assertThat(dwarf.isKnown("i am a dwarf and i'm digging a hole")).isTrue();

        //to intercept print ln
        PrintStream defaultoutput = System.out;
        ByteArrayOutputStream intercept = new ByteArrayOutputStream();
        System.setOut(new PrintStream(intercept));

        dwarf.goesToTavern();

        //Interception of the System.out.println()
        String sang = intercept.toString().replace("\n","");
        System.setOut(defaultoutput);
        System.out.println(sang);

        //test that sang == value of the learned song
        assertThat(sang).isEqualTo("i am a dwarf and i'm digging a hole");

        //test that dwarf.getLearnedSongs() does not contain the song he shouldve forgotten
        assertThat(dwarf.getLearnedSongs().contains("i am a dwarf and i'm digging a hole")).isFalse();

    }
}
