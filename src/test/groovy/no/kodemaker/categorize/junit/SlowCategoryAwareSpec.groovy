package no.kodemaker.categorize.junit

import no.kodemaker.categorize.TestCategory
import org.junit.Rule
import org.junit.internal.AssumptionViolatedException
import org.junit.internal.runners.statements.FailOnTimeout
import org.junit.runner.Description
import org.junit.runners.model.Statement
import spock.lang.Specification

class SlowCategoryAwareSpec extends Specification{

    @Rule Categorizable categorizable = new Categorizable()


    def "should throw illegalstate if frameworkmethod is null"(){
        when :
             categorizable.apply(null,null)
        then :
            thrown(IllegalStateException)
    }

    def "should check that test category againts specified method category and return FailOnTimeout if same"(){
        given :
            System.setProperty("testcategory","slow")
            Description description = new Description("slow", Slowest.annotations)
        when :
            def statement = categorizable.apply(new Statement(){
                @Override
                void evaluate() {}
            },description)
        then :
            statement != null
            statement instanceof FailOnTimeout
    }

    def "should check that test category againts specified method category and return null if not the same"(){
        given :
            System.setProperty("testcategory","slow")
        when :
            categorizable.apply(null,new Description("slow", Fastest.annotations))
        then :
            thrown(AssumptionViolatedException)
    }

    def "should check that test category againts specified method category and return FailOnTimeOut if same"(){
        given :
            System.setProperty("testcategory","fast")
        when :
            def statement = categorizable.apply(null,new Description("fast", Fastest.annotations))
        then :
            statement != null
            statement instanceof FailOnTimeout
    }

    def "should return same as input if no category specified"(){
        given :
            System.setProperty("testcategory","fast")
            Statement statement = new FailOnTimeout(null,30)
        when :
            def returnStatement = categorizable.apply(statement,new Description("fast"))
        then :
            statement == returnStatement
    }

    def "should use same timeout on return statement as on category annotation"(){
        given :
            System.setProperty("testcategory","fast")
        when :
            def returnStatement = categorizable.apply(null,new Description("fast", Fastest.annotations))
        then :
            returnStatement != null
            returnStatement instanceof FailOnTimeout
            30 == getTimeout(returnStatement)
    }

    int getTimeout(FailOnTimeout failOnTimeout) {
        return failOnTimeout.metaPropertyValues.get(0).bean.fTimeout
    }

    @TestCategory(name = "slow")
    class Slowest {}

    @TestCategory(name = "fast", timeout = 30)
    class Fastest {}
}
