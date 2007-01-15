package com.googlecode.instinct.core.naming;

import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;

public final class SpecificationNamingConventionAtomicTest extends InstinctTestCase {
    public void testProperties() {
        checkClass(SpecificationNamingConvention.class, NamingConvention.class);
    }

    public void testGetPattern() {
        assertEquals("^must", new SpecificationNamingConvention().getPattern());
    }
}
