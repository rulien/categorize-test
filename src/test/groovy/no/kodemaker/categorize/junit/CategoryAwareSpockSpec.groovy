package no.kodemaker.categorize.junit

import spock.lang.Specification
import no.kodemaker.categorize.Category
import org.junit.Rule
import no.kodemaker.categorize.spock.CategorizableSpecification
import spock.lang.Ignore


@Ignore
class CategoryAwareSpockSpec extends CategorizableSpecification {

    def setupSpec(){
        System.setProperty("category","fast")
    }

    @Category(name="fast")
    def "should run test since category is set to fast"(){
        expect :
            System.getProperty("category") == "fast"
    }

    @Category(name="slow")
    def "should not run test since category is set to fast"(){
        expect :
            System.getProperty("category") == "slow"
    }
}
