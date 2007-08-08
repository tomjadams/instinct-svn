/*
 * Copyright 2006-2007 Tom Adams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.googlecode.instinct.internal.aggregate.locate;

import com.googlecode.instinct.marker.annotate.Specification;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;
import java.lang.reflect.Method;
import java.util.Collection;

public final class AnnotatedMethodLocatorImplAtomicTest extends InstinctTestCase {
    private AnnotatedMethodLocator locator;

    public void testConformsToClassTraits() {
        checkClass(AnnotatedMethodLocatorImpl.class, AnnotatedMethodLocator.class);
    }

    public void testLocateOnAClassWithNoAnnotationsGiveNoMethod() {
        final Collection<Method> methods = locator.locate(WithoutRuntimeAnnotations.class, Specification.class);
        assertEquals(0, methods.size());
    }

    public void testLocateOnAClassWithSeveralAnnotationsGiveSeveralMethod() {
        final Collection<Method> methods = locator.locate(WithRuntimeAnnotations.class, Specification.class);
        assertEquals(2, methods.size());
    }

    @Override
    public void setUpSubject() {
        locator = new AnnotatedMethodLocatorImpl();
    }
}
