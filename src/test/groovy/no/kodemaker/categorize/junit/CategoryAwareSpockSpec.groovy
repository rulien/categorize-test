package no.kodemaker.categorize.junit

import no.kodemaker.categorize.TestCategory

import no.kodemaker.categorize.spock.CategorizableSpecification
import spock.lang.Ignore


@Ignore
class CategoryAwareSpockSpec extends CategorizableSpecification {

    def setupSpec(){
        System.setProperty("testcategory","fast")
    }

    @TestCategory(name="fast")
    def "should run test since category is set to fast"(){
        expect :
            System.getProperty("testcategory") == "fast"
    }

    @TestCategory(name="slow")
    def "should not run test since category is set to fast"(){
        expect :
            System.getProperty("testcategory") == "slow"
    }
}
