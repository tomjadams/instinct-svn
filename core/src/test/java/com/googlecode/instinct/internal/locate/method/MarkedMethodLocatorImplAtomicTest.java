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

package com.googlecode.instinct.internal.locate.method;

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.internal.runner.AContextThatHasAMethodWithAnnotationAndNamingConvention;
import com.googlecode.instinct.internal.runner.AContextWithAnnotationsAndNamingConventions;
import com.googlecode.instinct.internal.runner.ASimpleContext;
import com.googlecode.instinct.internal.runner.ASimpleNamingConventionContext;
import static com.googlecode.instinct.marker.AnnotationAttribute.IGNORE;
import com.googlecode.instinct.marker.MarkingSchemeImpl;
import com.googlecode.instinct.marker.annotate.BeforeSpecification;
import com.googlecode.instinct.marker.annotate.Mock;
import com.googlecode.instinct.marker.annotate.Specification;
import com.googlecode.instinct.marker.annotate.Subject;
import com.googlecode.instinct.marker.naming.NamingConvention;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;
import com.googlecode.instinct.test.matcher.MethodMatcher;
import fj.data.List;
import java.lang.reflect.Method;
import org.hamcrest.Matcher;
import org.jmock.Expectations;

@SuppressWarnings({"unchecked", "UnusedDeclaration"})
public final class MarkedMethodLocatorImplAtomicTest extends InstinctTestCase {
    @Subject private MarkedMethodLocator locator;
    @Mock private NamingConvention namingConvention;

    public void testConformsToClassTraits() {
        checkClass(MarkedMethodLocatorImpl.class, MarkedMethodLocator.class);
    }

    public void testFindsAnnotatedSpecificationMethodsInASimpleContext() {
        expect.that(specificationMethodsInASimpleContextClass()).containsItem(aMethodNamed("toCheckVerification"));
    }

    public void testFindsSpecificationMethodsConformingToNamingConventionInASimpleNamingConventionContext() {
        final List<Method> specificationMethods = getSpecificationMethodsFromContextClass(ASimpleNamingConventionContext.class);
        expect.that(specificationMethods).isOfSize(2);
        expect.that(specificationMethods).containsItem(aMethodNamed("mustAlwaysReturnTrue"));
        expect.that(specificationMethods).containsItem(aMethodNamed("shouldAlwaysReturnFalse"));
    }

    public void testFindsBothAnnotatedAndNamedSpecificationMethodsInTheSameClass() {
        final List<Method> methods = getSpecificationMethodsFromContextClass(AContextWithAnnotationsAndNamingConventions.class);
        expect.that(methods).isOfSize(3);
        expect.that(methods).containsItem(aMethodNamed("mustDoSomethingRatherVague"));
        expect.that(methods).containsItem(aMethodNamed("doSomeCrazyRequirement"));
        expect.that(methods).containsItem(aMethodNamed("shouldDoSomethingReallyImportant"));
    }

    public void testFindsAnnotatedBeforeSpecificationMethodsInASimpleContext() {
        final List<Method> methods = getBeforeSpecificationMethodsFromContextClass(ASimpleContext.class, "");
        expect.that(methods).isOfSize(2);
        expect.that(methods).containsItem(aMethodNamed("setUp"));
        expect.that(methods).containsItem(aMethodNamed("setUpAgain"));
    }

    public void testFindsNamingConventionBeforeSpecificationMethodsInASimpleNamingConventionContext() {
        final List<Method> methods = getBeforeSpecificationMethodsFromContextClass(ASimpleNamingConventionContext.class, "^before.*");
        expect.that(methods).isOfSize(2);
        expect.that(methods).containsItem(aMethodNamed("beforeSpecification"));
        expect.that(methods).containsItem(aMethodNamed("beforeWeDoStuff"));
    }

    public void testReturnsAUniqueListOfMethodsWhenAMethodHasBothAnnotationAndNamingConvention() {
        final List<Method> methods = getSpecificationMethodsFromContextClass(AContextThatHasAMethodWithAnnotationAndNamingConvention.class);
        expect.that(methods).isOfSize(1);
        expect.that(methods).containsItem(aMethodNamed("mustDoSomething"));
    }

    private List<Method> specificationMethodsInASimpleContextClass() {
        return getSpecificationMethodsFromContextClass(ASimpleContext.class);
    }

    private Matcher<Method> aMethodNamed(final String methodName) {
        return new MethodMatcher(methodName);
    }

    private <T> List<Method> getBeforeSpecificationMethodsFromContextClass(final Class<T> cls, final Object namingPattern) {
        expect.that(new Expectations() {
            {
                atLeast(1).of(namingConvention).getPattern();
                will(returnValue(namingPattern));
            }
        });
        return locator.locateAll(cls, new MarkingSchemeImpl(BeforeSpecification.class, namingConvention, IGNORE));
    }

    private <T> List<Method> getSpecificationMethodsFromContextClass(final Class<T> cls) {
        expect.that(new Expectations() {
            {
                atLeast(1).of(namingConvention).getPattern();
                will(returnValue("^must.*|^should.*"));
            }
        });
        return locator.locateAll(cls, new MarkingSchemeImpl(Specification.class, namingConvention, IGNORE));
    }
}
