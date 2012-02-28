package no.kodemaker.categorize.junit;


import no.kodemaker.categorize.TestCategory;
import org.apache.commons.lang.StringUtils;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class Categorizable implements TestRule{


    @Override
    public Statement apply(Statement statement, Description description) {
        validateInput(description);
        String runCategory = System.getProperty("testcategory");
        if(StringUtils.isBlank(runCategory)) return statement;
        return shouldTestCategoryRun(statement, description, runCategory);
    }

    private Statement shouldTestCategoryRun(Statement statement, Description description, String runCategory) {
        TestCategory cat = description.getAnnotation(TestCategory.class);
        if(cat == null){
            return statement;
        }else if(StringUtils.equals(cat.name(), runCategory) || StringUtils.equals("all", runCategory)){
            return new FailOnTimeout(statement,cat.timeout());
        }else{
            throw new AssumptionViolatedException("Only test category " + runCategory + "   will run now");
        }
    }

    private void validateInput(Description description) {
        if(description == null) throw new IllegalStateException("Input to a Rule cannot be null, something wrong in Junit ??");
    }

}
