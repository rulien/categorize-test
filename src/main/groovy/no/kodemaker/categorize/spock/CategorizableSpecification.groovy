package no.kodemaker.categorize.spock

import spock.lang.Specification
import no.kodemaker.categorize.junit.Categorizable
import org.junit.Rule


class CategorizableSpecification extends Specification {
    @Rule public Categorizable categorizable = new Categorizable();
}
