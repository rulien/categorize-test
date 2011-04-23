package no.kodemaker.categorize

import spock.lang.Specification
import org.junit.internal.runners.statements.FailOnTimeout
import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import org.junit.runners.model.Statement
import org.junit.internal.runners.statements.InvokeMethod


class SlowCategoryAwareSpec extends Specification{

    Categorizable categorizable = new Categorizable()


    def "should throw illegalstate if frameworkmethod is null"(){
        given :
            System.setProperty("category","slow")
        when :
            categorizable.apply(null,null,null)
        then :
            thrown(IllegalStateException)


    }

    @Category(name = "slow")
    def "should check that test category againts specified method category and return FailOnTimeout if same"(){
        given :
            System.setProperty("category","slow")
            Method methodCall = this.getClass().getMethod("should check that test category againts specified method category and return FailOnTimeout if same")
            FrameworkMethod method = new FrameworkMethod(methodCall)
        when :
            def statement = categorizable.apply(null,method,null)
        then :
            statement != null
            statement instanceof FailOnTimeout
    }


    @Category(name = "fast")
    def "should check that test category againts specified method category and return null if not the same"(){
        given :
            System.setProperty("category","slow")
            Method methodCall = this.getClass().getMethod("should check that test category againts specified method category and return null if not the same")
            FrameworkMethod method = new FrameworkMethod(methodCall)
        when :
            def statement = categorizable.apply(null,method,null)
        then :
            statement == null
    }

    @Category(name = "fast")
    def "should check that test category againts specified method category and return FailOnTimeOut if same"(){
        given :
            System.setProperty("category","fast")
            Method methodCall = this.getClass().getMethod("should check that test category againts specified method category and return FailOnTimeOut if same")
            FrameworkMethod method = new FrameworkMethod(methodCall)
        when :
            def statement = categorizable.apply(null,method,null)
        then :
            statement != null
            statement instanceof FailOnTimeout
    }

    def "should return same as input if no category specified"(){
        given :
            System.setProperty("category","fast")
            Method methodCall = this.getClass().getMethod("should return same as input if no category specified")
            FrameworkMethod method = new FrameworkMethod(methodCall)
            Statement statement = new InvokeMethod(method,this)
        when :
            def returnStatement = categorizable.apply(statement,method,null)
        then :
            statement == returnStatement
    }

    @Category(name = "fast", timeout = 20)
    def "should use same timout on return statement as on category annotation"(){
        given :
            System.setProperty("category","fast")
            Method methodCall = this.getClass().getMethod("should use same timout on return statement as on category annotation")
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
}
