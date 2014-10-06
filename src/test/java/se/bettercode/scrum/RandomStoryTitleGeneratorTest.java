package se.bettercode.scrum;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RandomStoryTitleGeneratorTest extends TestCase {

    RandomStoryTitleGenerator generator = new RandomStoryTitleGenerator();

    public void testGenerateOne()  {
        String result = generator.generateOne();
        assertNotNull(result);
    }

    public void testGenerateTen()  {
        ArrayList<String> result = generator.generate(10);
        assertEquals(10, result.size());
    }

    public void testUniqueness() {
        ArrayList<String> result = generator.generate(10);
        Set<String> resultSet = new HashSet<>(result);
        assertEquals(10, resultSet.size());
    }

    /*
    public void testHighN() {
        ArrayList<String> result = generator.generate(1000);
    }
    */

}
