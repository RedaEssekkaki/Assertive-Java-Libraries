package comparisons.basicTests;

import org.examples.Dwarf.Dwarf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.atlanmod.commons.Guards;

public class AtlanmodBasicTest {

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
        Guards.checkEqualTo(dwarf.getName(),"Jeremy");
        Guards.checkEqualTo(dwarf.getSize(),80.4);
        Guards.checkEqualTo(dwarf.getWeight(),90.3);
        Guards.checkEqualTo(dwarf.isBearded(),false);
    }

    @Test
    void testSize()
    {
        dwarf.setSize(40.0);

        //test size is properly set
        Guards.checkEqualTo(dwarf.getSize(),40.0);
    }

    @Test
    void testWeight()
    {
        dwarf.setWeight(24.7);

        //test weight is properly set
        Guards.checkEqualTo(dwarf.getWeight(),24.7);
    }

    @Test
    void testBeard()
    {
        dwarf.shave();

        //test dwarf has no beard
        Guards.checkEqualTo(dwarf.isBearded(),false);

        dwarf.growBeard();

        //test dwarf has beard
        Guards.checkArgument(dwarf.isBearded());
    }

    @Test
    void testHungover()
    {
        dwarf.goesToTavern();

        //test that dwarf IS hungover
        Guards.checkArgument(dwarf.isHungover());

        dwarf.sleep();
        //test that dwarf is NOT hungover
        Guards.checkEqualTo(dwarf.isHungover(),false);
    }

    @Test
    void testSongs()
    {
        String learnedSong = "i am a dwarf and i'm digging a hole";
        dwarf.learnSong(learnedSong);
        //test that dwarf has learned song
        Guards.checkArgument(dwarf.getLearnedSongs().contains(learnedSong));

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
        Guards.checkEqualTo(sang,learnedSong);

        //test that dwarf.getLearnedSongs() does not contain the song he should've forgotten
        Guards.checkEqualTo(dwarf.getLearnedSongs().contains(sang), false);

    }
}
