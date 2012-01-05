package no.kodemaker.categorize.junit;


import no.kodemaker.categorize.TestCategory;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestSlow {

    @Rule public Categorizable categorizable = new Categorizable();

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("testcategory","slow");
    }

    @Test
    @TestCategory(name = "slow")
    public void testSlow() throws Exception {
        String category = System.getProperty("testcategory");
        assertThat(category,is("slow"));
    }

    @Test
    @TestCategory(name = "fast")
    public void testFastNotRun() throws Exception {
        fail("Fast was run but expected only slow");
    }
}
