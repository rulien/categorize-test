package no.kodemaker.categorize;


import junit.framework.TestResult;
import org.apache.commons.lang.StringUtils;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class Categorizable implements MethodRule{


    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object o) {
        String runCategory = System.getProperty("category");
        if(StringUtils.isBlank(runCategory)) return statement;
        Category cat = frameworkMethod.getAnnotation(Category.class);
        if(cat == null) return statement;
        if(StringUtils.isNotBlank(runCategory) && StringUtils.equals(cat.name(),runCategory)){
            return new FailOnTimeout(statement,100);
        }else{
            return statement;
        }
    }
}
