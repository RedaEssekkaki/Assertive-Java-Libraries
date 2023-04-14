package comparisons.basicTests;

import org.examples.Dwarf.Dwarf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    }

    @Test
    void testSize()
    {
        dwarf.setSize(40.0);

        //test size is properly set
    }

    @Test
    void testWeight()
    {
        dwarf.setWeight(24.7);

        //test weight is properly set
    }

    @Test
    void testBeard()
    {
        dwarf.shave();

        //test dwarf has no beard

        dwarf.growBeard();

        //test dwarf has beard
    }

    @Test
    void testHungover()
    {
        dwarf.goesToTavern();
        //test that dwarf IS hungover
        dwarf.sleep();
        //test that dwarf is NOT hungover
    }

    @Test
    void testSongs()
    {
        String learnedSong = "i am a dwarf and i'm digging a hole";
        dwarf.learnSong(learnedSong);
        //test that dwarf has learned song

        //to intercept print ln
        PrintStream defaultoutput = System.out;
        ByteArrayOutputStream intercept = new ByteArrayOutputStream();
        System.setOut(new PrintStream(intercept));

        dwarf.goesToTavern();

        //Interception of the System.out.println()
        String sang = intercept.toString();
        System.setOut(defaultoutput);
        System.out.println(sang);

        //test that sang == value of the learned song

        //test that dwarf.getLearnedSongs() does not contain the song he shouldve forgotten


    }
}
