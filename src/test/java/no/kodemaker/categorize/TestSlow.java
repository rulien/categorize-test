package no.kodemaker.categorize;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestSlow extends AbstractCategorizeTests{

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("category","slow");
    }

    @Test
    @Category(name = "slow")
    public void testSlow() throws Exception {
        String category = System.getProperty("category");
        assertThat(category,is("slow"));
    }

    @Test
    @Category(name = "fast")
    public void testFastNotRun() throws Exception {
        fail("Fast was run but expected only slow");
    }
}
