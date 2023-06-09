package comparisons.basicTests;

import org.examples.Dwarf.Dwarf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.apache.commons.lang3.Validate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class ApacheCommonsBasicTest {

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
        Validate.isTrue(Objects.equals(dwarf.getName(), "Jeremy"));
        Validate.isTrue( dwarf.getSize() == 80.4);
        Validate.isTrue( dwarf.getWeight() == 90.3);
        Validate.isTrue(!dwarf.isBearded());
    }

    @Test
    void testSize()
    {
        dwarf.setSize(40.0);

        //test size is properly set
        Validate.isTrue( dwarf.getSize() == 40.0);
    }

    @Test
    void testWeight()
    {
        dwarf.setWeight(24.7);

        //test weight is properly set
        Validate.isTrue( dwarf.getWeight() == 24.7);
    }

    @Test
    void testBeard()
    {
        dwarf.shave();

        //test dwarf has no beard
        Validate.isTrue(!dwarf.isBearded());

        dwarf.growBeard();

        //test dwarf has beard
        Validate.isTrue(dwarf.isBearded());
    }

    @Test
    void testHungover()
    {
        dwarf.goesToTavern();
        //test that dwarf IS hungover
        Validate.isTrue( dwarf.isHungover() );


        dwarf.sleep();
        //test that dwarf is NOT hungover
        Validate.isTrue( !dwarf.isHungover() );
    }

    @Test
    void testSongs()
    {
        String learnedSong = "i am a dwarf and i'm digging a hole";
        dwarf.learnSong(learnedSong);
        //test that dwarf has learned song
        Validate.isTrue(dwarf.getLearnedSongs().contains(learnedSong));

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
        Validate.isTrue(sang.equals(learnedSong));

        //test that dwarf.getLearnedSongs() does not contain the song he shouldve forgotten
        Validate.isTrue( !dwarf.getLearnedSongs().contains(learnedSong) );

    }


}
