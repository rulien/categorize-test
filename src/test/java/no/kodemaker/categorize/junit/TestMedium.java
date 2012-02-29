package no.kodemaker.categorize.junit;


import no.kodemaker.categorize.TestCategory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Ignore
public class TestMedium extends AbstractCategorizeTest {

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("testcategory","medium");
    }

    @Test
    @TestCategory(name = "medium")
    public void testMedium() throws Exception {
        String category = System.getProperty("testcategory");
        assertThat(category,is("medium"));
    }

    @Test
    @TestCategory(name = "fast")
    public void testFastNotRun() throws Exception {
        fail("Fast was run but expected only medium");
    }
}
