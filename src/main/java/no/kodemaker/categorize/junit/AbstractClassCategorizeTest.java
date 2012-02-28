package no.kodemaker.categorize.junit;

import org.junit.ClassRule;

public abstract class AbstractClassCategorizeTest {

    @ClassRule static final public Categorizable categorizable = new Categorizable();


}
