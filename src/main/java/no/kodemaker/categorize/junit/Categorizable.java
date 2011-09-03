package no.kodemaker.categorize.junit;


import no.kodemaker.categorize.TestCategory;
import org.apache.commons.lang.StringUtils;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class Categorizable implements MethodRule{


    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object o) {
        validateInput(frameworkMethod);
        String runCategory = System.getProperty("testcategory");
        if(StringUtils.isBlank(runCategory)) return statement;
        return shouldTestCategoryRun(statement, frameworkMethod, runCategory);
    }

    private Statement shouldTestCategoryRun(Statement statement, FrameworkMethod frameworkMethod, String runCategory) {
        TestCategory cat = frameworkMethod.getAnnotation(TestCategory.class);
        if(cat == null){
            return statement;
        }else if(StringUtils.equals(cat.name(), runCategory)){
            return new FailOnTimeout(statement,cat.timeout());
        }else{
            throw new AssumptionViolatedException("Only test category " + runCategory + "   will run now");
        }
    }

    private void validateInput(FrameworkMethod frameworkMethod) {
        if(frameworkMethod == null) throw new IllegalStateException("Input to a Rule cannot be null, something wrong in Junit ??");
    }
}
