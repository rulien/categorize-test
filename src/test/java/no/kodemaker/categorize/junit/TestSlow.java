package no.kodemaker.categorize.junit;


import no.kodemaker.categorize.TestCategory;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Ignore
public class TestSlow {

    @Rule
    public Categorizable categorizable = new Categorizable();

    @Rule
    public AddTestCategorySystemPropertyRule addTestCategorySystemPropertyRule = new AddTestCategorySystemPropertyRule("slow");

    @Test
    @TestCategory(name = "slow")
    public void testSlow() throws Exception {
        String category = System.getProperty("testcategory");
        assertThat(category, is("slow"));
    }

    @Test
    @TestCategory(name = "fast")
    public void testFastNotRun() throws Exception {
        fail("Fast was run but expected only slow");
    }
}
