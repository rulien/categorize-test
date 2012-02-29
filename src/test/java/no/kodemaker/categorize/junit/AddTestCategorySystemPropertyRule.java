package no.kodemaker.categorize.junit;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class AddTestCategorySystemPropertyRule extends TestWatcher{
    
    private final String[] testcategory;

    public AddTestCategorySystemPropertyRule(String... testcategory) {
        this.testcategory = testcategory;
    }

    @Override
    protected void starting(Description description) {
        StringBuffer buffy = new StringBuffer();
        for(int i = 0; i < testcategory.length-1; i++) buffy.append(testcategory[i]).append(",");
        buffy.append(testcategory[testcategory.length-1]);
        System.setProperty("testcategory", buffy.toString());
    }
}
