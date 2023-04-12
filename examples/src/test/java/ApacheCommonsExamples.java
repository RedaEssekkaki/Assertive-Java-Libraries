
import org.apache.commons.lang3.Validate;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

/**
 * The purpose of this class is to tinker with apache.commons.lang3.Validate 's
 * guard system, in order to draw a comparison with a few other similar systems,
 * such as Guava.
 */
public class ApacheCommonsExamples {

    private StudentGrade testedStudent = new StudentGrade();

    @BeforeEach
    public void setup () {
        ArrayList<Double> grades = new ArrayList<>() {{
            add(12.5);
            add(5.25);
            add(8.75);
            add(15.5);
            add(18.0);
            add(11.25);
            add(13.75);
            add(14.0);
        }};
        testedStudent.set(grades);
    }

    @Test
    public void testSetMoreThan8Grades () {
        ArrayList<Double> grades = new ArrayList<>() {{
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
        testedStudent.set(grades);
    }

    @Test
    public void testSetLessThan8grades () {
        ArrayList<Double> grades = new ArrayList<>() {{
            add(12.5);
            add(5.25);
            add(8.75);
            add(15.5);
            add(18.0);
            add(11.25);
            add(13.75);
        }};
        testedStudent.set(grades);
    }

    @Test
    public void testInsertNull () {
        testedStudent.setGrade(0, null);
    }

    @Test
    public void testAVG () {
        testedStudent.setGrade(5, -2.0);
        Double temp = testedStudent.avg();
    }

    /**
     * Private class to set up methods with guard clauses to use in the tests
     */
    private class StudentGrade {
        private ArrayList<Double> grades = new ArrayList<>(8);

        public void set(ArrayList<Double> newGrades) {
            Validate.isTrue(newGrades.size() == 8, "You can't have more or less grades than you have courses: current %d ; required 8", newGrades.size());
            this.grades = newGrades;
        }

        public ArrayList<Double> get() {
            return grades;
        }

        // Sets individual course's grade
        public void setGrade(int courseNumber, Double newGrade) {
            Validate.inclusiveBetween(1, 8, courseNumber, "There are only 8 courses in this school.");
            Validate.notNull(newGrade, "You can't have a null grade.");
            this.grades.set(courseNumber - 1, newGrade);
        }

        @org.jetbrains.annotations.NotNull
        public Double avg () {
            Validate.notEmpty(grades, "You must have a grade for each course");
            Double divisor = Double.valueOf(0.0);
            Double divider = Double.valueOf(0.0);

            for (Double each : grades) {
                Validate.isTrue(each >= 0, "A grade cannot be lesser than 0: %d", each);
                divisor += each;
                divider += Double.valueOf(1.0);
            }
            return divisor / divider;
        }
    }
}