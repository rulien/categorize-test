package no.kodemaker.categorize.junit;

import no.kodemaker.categorize.TestCategory;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestRunAllTests {
    @Rule
    public Categorizable categorizable = new Categorizable();

    @Rule
    public AddTestCategorySystemPropertyRule addTestCategorySystemPropertyRule = new AddTestCategorySystemPropertyRule("all");


    @Test
    @TestCategory(name = "integration")
    public void shouldRunAllTests() throws Exception {
        assertThat("all", is(System.getProperty("testcategory")));
    }
}
