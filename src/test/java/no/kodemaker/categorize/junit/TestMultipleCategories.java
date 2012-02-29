package no.kodemaker.categorize.junit;

import no.kodemaker.categorize.TestCategory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TestMultipleCategories {

    @Rule
    public AddTestCategorySystemPropertyRule addTestCategorySystemPropertyRule = new AddTestCategorySystemPropertyRule("unit", "integration");

    Categorizable categorizable = new Categorizable();

    @Test
    public void shouldRunMultipleCategories() throws Exception {
        Statement statement = categorizable.apply(null, Description.createTestDescription(Unit.class, "test", Unit.class.getAnnotations()));
        assertNotNull(statement);
        assertThat(statement, instanceOf(FailOnTimeout.class));
    }

    @Test
    public void shouldFailIfNotAmongstCategories() throws Exception {
        categorizable.apply(null, Description.createTestDescription(Functional.class, "test", Functional.class.getAnnotations()));
    }

    @TestCategory(name = "unit")
    private class Unit {
    }

    @TestCategory(name = "functional")
    private class Functional {
    }
}
