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

package com.googlecode.instinct.internal.core;

import static com.googlecode.instinct.expect.Expect.expect;
import static com.googlecode.instinct.expect.behaviour.Mocker.mock;
import com.googlecode.instinct.internal.runner.SpecificationResult;
import com.googlecode.instinct.internal.runner.SpecificationRunner;
import com.googlecode.instinct.internal.util.Suggest;
import com.googlecode.instinct.marker.annotate.Dummy;
import com.googlecode.instinct.marker.annotate.Mock;
import com.googlecode.instinct.marker.annotate.Subject;
import com.googlecode.instinct.runner.SpecificationListener;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;
import static com.googlecode.instinct.test.reflect.SubjectCreator.createSubjectWithConstructorArgs;
import java.lang.annotation.Annotation;
import java.util.Collection;
import org.jmock.Expectations;

@SuppressWarnings({"unchecked"})
@Suggest({"Todo:", "Add a run method, pass a spec runner as a dependency, pass in other methods required for runners."})
public final class SpecificationMethodImplAtomicTest extends InstinctTestCase {
    @Subject(auto = false) private SpecificationMethod specificationMethod;
    @Mock private SpecificationRunner specificationRunner;
    @Mock private LifecycleMethod specMethod;
    @Mock private SpecificationResult specificationResult;
    @Mock private Collection<LifecycleMethod> beforeSpecMethods;
    @Mock private Collection<LifecycleMethod> afterSpecMethods;
    @Dummy private String methodName;
    @Dummy private Class<?> declaringClass;

    @Override
    public void setUpSubject() {
        final Object[] constructorArgs = {specMethod, beforeSpecMethods, afterSpecMethods};
        specificationMethod = createSubjectWithConstructorArgs(SpecificationMethodImpl.class, constructorArgs, specificationRunner);
    }

    public void testConformsToClassTraits() {
        checkClass(SpecificationMethodImpl.class, SpecificationMethod.class);
    }

    public void testPassesSpecificationListenersToSpecificationRunner() {
        final SpecificationListener specificationListener = mock(SpecificationListener.class);
        expect.that(new Expectations() {
            {
                atLeast(1).of(specificationRunner).addSpecificationListener(specificationListener);
            }
        });
        specificationMethod.addSpecificationListener(specificationListener);
    }

    public void testRunsSpecificationUsingASpecificationRunner() {
        expect.that(new Expectations() {
            {
                atLeast(1).of(specificationRunner).run(specificationMethod); will(returnValue(specificationResult));
            }
        });
        final SpecificationResult result = specificationMethod.run();
        expect.that(result).sameInstanceAs(specificationResult);
    }

    public void testReturnsUnderlyingSpecificationMethod() {
        final LifecycleMethod returnedSpecMethod = specificationMethod.getSpecificationMethod();
        expect.that(returnedSpecMethod).sameInstanceAs(specMethod);
    }

    public void testReturnsUnderlyingBeforeSpecMethods() {
        final Collection<LifecycleMethod> returnedBeforeSpecMethods = specificationMethod.getBeforeSpecificationMethods();
        expect.that(returnedBeforeSpecMethods).sameInstanceAs(beforeSpecMethods);
    }

    public void testReturnsUnderlyingAfterSpecMethods() {
        final Collection<LifecycleMethod> returnedAfterSpecMethods = specificationMethod.getAfterSpecificationMethods();
        expect.that(returnedAfterSpecMethods).sameInstanceAs(afterSpecMethods);
    }

    public void testReturnsDeclaringClassOfLifecycleMethod() {
        expect.that(new Expectations() {
            {
                atLeast(1).of(specMethod).getDeclaringClass(); will(returnValue(declaringClass));
            }
        });
        assertEquals(declaringClass, specificationMethod.getDeclaringClass());
    }

    public void testReturnsNameFromUnderlyingLifecycleMethod() {
        expect.that(new Expectations() {
            {
                atLeast(1).of(specMethod).getName(); will(returnValue(methodName));
            }
        });
        expect.that(specificationMethod.getName()).equalTo(methodName);
    }

    public void testReturnsParameterAnnotationsFromUnderlyingLifecycleMethod() {
        final Annotation[][] fakeAnnotations = new Annotation[1][1];
        expect.that(new Expectations() {
            {
                atLeast(1).of(specMethod).getParameterAnnotations(); will(returnValue(fakeAnnotations));
            }
        });
        expect.that(specificationMethod.getParameterAnnotations()).sameInstanceAs(fakeAnnotations);
    }
}
