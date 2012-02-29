package no.kodemaker.categorize.junit;

import no.kodemaker.categorize.TestCategory;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@TestCategory(name = "fast")
@Ignore
public class TestFastClass extends AbstractClassCategorizeTest {

    @Test
    public void testFast() throws Exception {
        String category = System.getProperty("testcategory");
        assertThat(category, is("fast"));
    }
}