package no.kodemaker.categorize.junit;


import no.kodemaker.categorize.Category;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestMedium extends AbstractCategorizeTest {

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("category","medium");
    }

    @Test
    @Category(name = "medium")
    public void testMedium() throws Exception {
        String category = System.getProperty("category");
        assertThat(category,is("medium"));
    }

    @Test
    @Category(name = "fast")
    public void testFastNotRun() throws Exception {
        fail("Fast was run but expected only medium");
    }
}
