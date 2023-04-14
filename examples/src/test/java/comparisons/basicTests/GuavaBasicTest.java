package comparisons.basicTests;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import org.examples.Dwarf.Dwarf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.fail;

public class GuavaBasicTest {

    private Dwarf dwarf;
    private boolean test;

    @BeforeEach
    void setup()
    {
        dwarf = new Dwarf("Jeremy", 80.4, 90.3, false);
    }

    @Test
    void testConstructor()
    {
        //test that all attributes set by constructor in setup() are correct
        checkArgument(Objects.equal(dwarf.getName(), "Jeremy"), "Wrong name");

        checkArgument(Objects.equal(dwarf.getSize(), 80.4), "Wrong size");

        checkArgument(Objects.equal(dwarf.getWeight(), 90.3), "Wrong weight");

        checkArgument(!dwarf.isBearded(), "We should not have beard");

    }

    @Test
    void testSize()
    {
        dwarf.setSize(40.0);

        //test size is properly set
        test = Objects.equal(dwarf.getSize(), 40.0);
        checkArgument(test, "Wrong size");
    }

    @Test
    void testWeight()
    {
        dwarf.setWeight(24.7);

        //test weight is properly set
        test = Objects.equal(dwarf.getWeight(), 24.7);
        checkArgument(test, "Wrong weight");

    }

    @Test
    void testBeard()
    {
        dwarf.shave();

        //test dwarf has no beard
        checkArgument(!dwarf.isBearded(), "We should not have beard");


        dwarf.growBeard();

        //test dwarf has beard
        checkArgument(dwarf.isBearded(), "We should not have beard");

    }

    @Test
    void testHungover()
    {
        dwarf.goesToTavern();
        //test that dwarf IS hungover
        checkArgument(dwarf.isHungover(), "Dwarf should be hungover");


        dwarf.sleep();
        //test that dwarf is NOT hungover
        checkArgument(!dwarf.isHungover(), "Dwarf should not be hungover");

    }

    @Test
    void testSongs()
    {
        String learnedSong = "i am a dwarf and i'm digging a hole";
        dwarf.learnSong(learnedSong);
        //test that dwarf has learned song
        checkArgument(dwarf.getLearnedSongs().contains(learnedSong));


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
        test = Objects.equal(sang, learnedSong);
        checkArgument(test, "Wrong song");

        //test that dwarf.getLearnedSongs() does not contain the song he shouldve forgotten
        checkArgument(!dwarf.getLearnedSongs().contains(learnedSong), "It should not contain the song");



    }
}
