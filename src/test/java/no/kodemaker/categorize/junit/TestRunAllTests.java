package no.kodemaker.categorize.junit;

import no.kodemaker.categorize.TestCategory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class TestRunAllTests {
    @Rule public Categorizable categorizable = new Categorizable();

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("testcategory", "all");
    }


    @Test
    @TestCategory(name = "integration")
    public void shouldRunAllTests() throws Exception {
        assertTrue(true);
    }
}
