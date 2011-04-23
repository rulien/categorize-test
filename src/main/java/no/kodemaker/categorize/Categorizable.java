package no.kodemaker.categorize;


import junit.framework.TestResult;
import org.apache.commons.lang.StringUtils;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class Categorizable implements MethodRule{


    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object o) {
        validateInput(frameworkMethod);
        String runCategory = System.getProperty("category");
        if(StringUtils.isBlank(runCategory)) return statement;
        Category cat = frameworkMethod.getAnnotation(Category.class);
        if(cat == null) return statement;
        if(StringUtils.isNotBlank(runCategory) && StringUtils.equals(cat.name(),runCategory)){
            return new FailOnTimeout(statement,cat.timeout());
        }else{
            throw new AssumptionViolatedException("Only category " + runCategory);
        }
    }

    private void validateInput(FrameworkMethod frameworkMethod) {
        if(frameworkMethod == null) throw new IllegalStateException("Input to a Rule cannot be null, something wrong in Junit ??");
    }
}
