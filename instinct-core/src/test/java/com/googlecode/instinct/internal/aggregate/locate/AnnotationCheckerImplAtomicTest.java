package com.googlecode.instinct.internal.aggregate.locate;

import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;

public final class AnnotationCheckerImplAtomicTest extends InstinctTestCase {
    public void testProperties() {
        checkClass(AnnotationCheckerImpl.class, AnnotationChecker.class);
    }
}
