package no.kodemaker.categorize;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestFast extends AbstractCategorizeTests {

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("category","fast");
    }

    @Test
    @Category(name = "fast")
    public void testSlow() throws Exception {
        String category = System.getProperty("category");
        assertThat(category,is("fast"));
    }

    @Test
    @Category(name = "slow")
    public void testFastNotRun() throws Exception {
        fail("Slow was run but expected only fast");
    }
}
