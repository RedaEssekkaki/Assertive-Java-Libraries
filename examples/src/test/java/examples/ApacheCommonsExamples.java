package examples;

import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.fail;

/**
 * The purpose of this class is to tinker with apache.commons.lang3.Validate 's
 * guard system, in order to draw a comparison with a few other similar systems,
 * such as Guava.
 */
public class ApacheCommonsExamples {

    private StudentGrade testedStudent = new StudentGrade();

    @BeforeEach
    public void setup () {
        ArrayList<Double> grades = new ArrayList<>(Arrays.asList(12.5, 5.25, 8.75, 15.5, 18.0, 11.25, 13.75, 14.0));
        testedStudent.set(grades);
    }

    @Test
    public void testSetMoreThan8Grades () {
        ArrayList<Double> grades = new ArrayList<Double>() {{
            add(12.5);
            add(5.25);
            add(8.75);
            add(15.5);
            add(18.0);
            add(11.25);
            add(13.75);
            add(14.0);
            add(14.0);
        }};
        try {
            testedStudent.set(grades);
            fail("Ths test is supposed to fail.");
        } catch (IllegalArgumentException expected) {
            System.out.println(expected);
        }
    }

    @Test
    public void testSetLessThan8grades () {
        ArrayList<Double> grades = new ArrayList<Double>() {{
            add(12.5);
            add(5.25);
            add(8.75);
            add(15.5);
            add(18.0);
            add(11.25);
            add(13.75);
        }};
        try {
            testedStudent.set(grades);
            fail("This test is supposed to fail.");
        } catch (IllegalArgumentException expected) {
            System.out.println(expected);
        }
    }

    @Test
    public void testInsertNull () {
        try {
            testedStudent.setGrade(0, null);
            fail("This test is supposed to fail.");
        } catch (NullPointerException expected) {
            System.out.println(expected);
        }
    }

    @Test
    public void testInsertOutOfBound () {
        try {
            testedStudent.setGrade(12, 18.0);
            fail("This test is supposed to fail.");
        } catch (IndexOutOfBoundsException expected) {
            System.out.println(expected);
        }
    }

    @Test
    public void testAVG () {
        try {
            testedStudent.setGrade(3, -2.0);
            Double temp = testedStudent.avg();
            System.out.println(temp);
            fail("This test is supposed to fail.");
        } catch (IllegalArgumentException expected) {
            System.out.println(expected);
        }
    }

    /**
     * Private class to set up methods with guard clauses to use in the tests
     */
    private class StudentGrade {
        private ArrayList<Double> grades;
        
        public StudentGrade () {
            this.grades = new ArrayList<Double>(8);
        }

        public void set(ArrayList<Double> newGrades) {
            Validate.isTrue(newGrades.size() == 8, "You can't have more or less grades than you have courses: current %d ; required 8", newGrades.size());
            this.grades = new ArrayList<>(newGrades);
        }

        public ArrayList<Double> get() {
            return grades;
        }

        // Sets individual course's grade
        public void setGrade(int courseNumber, Double newGrade) {
            Validate.validIndex(this.grades, courseNumber, "There are only 8 courses in this school.");
            Validate.notNull(newGrade, "You can't have a null grade.");
            this.grades.set(courseNumber, newGrade);
        }

        @org.jetbrains.annotations.NotNull
        public Double avg () {
            Validate.notEmpty(grades, "You must have a grade for each course");
            double divisor = 0.0;
            double divider = 0.0;

            for (Double each : grades) {
                Validate.isTrue(each >= 0.0, "A grade cannot be lesser than 0: %d", each.longValue());
                divisor += each;
                divider += 1.0;
            }
            return divisor / divider;
        }
    }
}