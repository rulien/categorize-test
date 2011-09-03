package no.kodemaker.categorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestCategory {
    String name();
    int timeout() default 0;
}
