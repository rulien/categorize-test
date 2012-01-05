package no.kodemaker.categorize.junit;

import org.junit.Rule;

public abstract class AbstractCategorizeTest {

    @Rule public Categorizable categorizable = new Categorizable();


}
