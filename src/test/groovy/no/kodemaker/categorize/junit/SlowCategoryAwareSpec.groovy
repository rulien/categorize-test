package no.kodemaker.categorize.junit

import spock.lang.Specification
import org.junit.internal.runners.statements.FailOnTimeout
import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import org.junit.runners.model.Statement
import org.junit.internal.runners.statements.InvokeMethod
import org.junit.internal.AssumptionViolatedException

import spock.lang.Ignore
import no.kodemaker.categorize.TestCategory
import org.junit.Rule


class SlowCategoryAwareSpec extends Specification{

    @Rule Categorizable categorizable = new Categorizable()


    def "should throw illegalstate if frameworkmethod is null"(){
        given :
            System.setProperty("testcategory","slow")
        when :
            categorizable.apply(null,null,null)
        then :
            thrown(IllegalStateException)


    }

    def "should check that test category againts specified method category and return FailOnTimeout if same"(){
        given :
            System.setProperty("testcategory","slow")
            Method methodCall = Slow.getMethod("slow")
            FrameworkMethod method = new FrameworkMethod(methodCall)
        when :
            def statement = categorizable.apply(null,method,null)
        then :
            statement != null
            statement instanceof FailOnTimeout
    }

    def "should check that test category againts specified method category and return null if not the same"(){
        given :
            System.setProperty("testcategory","slow")
            Method methodCall = Fast.getMethod("fast")
            FrameworkMethod method = new FrameworkMethod(methodCall)
        when :
            categorizable.apply(null,method,null)
        then :
            thrown(AssumptionViolatedException)
    }

    def "should check that test category againts specified method category and return FailOnTimeOut if same"(){
        given :
            System.setProperty("testcategory","fast")
            Method methodCall = Fast.getMethod("fast")
            FrameworkMethod method = new FrameworkMethod(methodCall)
        when :
            def statement = categorizable.apply(null,method,null)
        then :
            statement != null
            statement instanceof FailOnTimeout
    }

    def "should return same as input if no category specified"(){
        given :
            System.setProperty("testcategory","fast")
            Method methodCall = Fast.getMethod("nospecified")
            FrameworkMethod method = new FrameworkMethod(methodCall)
            Statement statement = new InvokeMethod(method,this)
        when :
            def returnStatement = categorizable.apply(statement,method,null)
        then :
            statement == returnStatement
    }

    def "should use same timout on return statement as on category annotation"(){
        given :
            System.setProperty("testcategory","fast")
            Method methodCall = Fast.getMethod("withtimeout")
            FrameworkMethod method = new FrameworkMethod(methodCall)
            Statement statement = new InvokeMethod(method,this)
        when :
            def returnStatement = categorizable.apply(statement,method,null)
        then :
            returnStatement != null
            returnStatement instanceof FailOnTimeout
            20 == getTimeout(returnStatement)
    }

    int getTimeout(FailOnTimeout failOnTimeout) {
        return failOnTimeout.metaPropertyValues.get(0).bean.fTimeout
    }
    
    class Slow {
        @TestCategory(name = "slow")
        public void slow(){}
    }

    class Medium {
        @TestCategory(name = "medium")
        public void medium(){}
    }

    class Fast {
        @TestCategory(name = "fast")
        public void fast(){}
        public void nospecified(){}
        @TestCategory(name = "fast", timeout=20)
        public void withtimeout(){}
    }
}
