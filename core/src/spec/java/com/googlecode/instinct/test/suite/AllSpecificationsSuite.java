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

package com.googlecode.instinct.test.suite;

import com.googlecode.instinct.actor.ADummyAutoWireDeterminatorWithANamedFieldToAutoWire;
import com.googlecode.instinct.actor.ADummyAutoWireDeterminatorWithAnAnnoatedFieldNotToAutoWire;
import com.googlecode.instinct.actor.ADummyAutoWireDeterminatorWithAnAnnoatedFieldToAutoWire;
import com.googlecode.instinct.actor.ADummyAutoWireDeterminatorWithNoMarkedFields;
import com.googlecode.instinct.api.AnObjectCheckerContext;
import com.googlecode.instinct.api.AnObjectCheckerFailure;
import com.googlecode.instinct.api.CommonAPIContext;
import com.googlecode.instinct.defect.defect23.AFixedDefect23;
import com.googlecode.instinct.defect.defect3.AFixedDefect3;
import com.googlecode.instinct.defect.defect8.AFixedDefect8WithANamingConventionLocator;
import com.googlecode.instinct.defect.defect8.AFixedDefect8WithAnAnnotationMethodLocator;
import com.googlecode.instinct.integrate.junit3.ContextTestSuite;
import com.googlecode.instinct.internal.edge.org.hamcrest.AStringFactoryImplContext;
import com.googlecode.instinct.internal.locate.field.ANamedFieldLocatorWithFieldsToFind;
import com.googlecode.instinct.internal.locate.field.ANamedFieldLocatorWithNothingNoFieldsToFind;
import com.googlecode.instinct.internal.locate.method.AHierarchicalMethodLocatorContext;
import com.googlecode.instinct.internal.locate.method.AnAnnotatedMethodLocatorContext;
import com.googlecode.instinct.internal.util.Suggest;
import junit.framework.Test;
import junit.framework.TestSuite;

public final class AllSpecificationsSuite {
    private AllSpecificationsSuite() {
        throw new UnsupportedOperationException();
    }

    @Suggest("Turn this into a JUnit 4 suite.")
    public static Test suite() {
        final TestSuite suite = new TestSuite("Specifications");
        for (final Class<?> cls : getContexts()) {
            suite.addTest(new ContextTestSuite(cls));
        }
        return suite;
    }

    @Suggest("We should be able to automatically find these. Maybe add a group to all 'testdata' specs so they don't run.")
    private static Class<?>[] getContexts() {
        return new Class<?>[]{AnAnnotatedMethodLocatorContext.class, AHierarchicalMethodLocatorContext.class,
                AFixedDefect8WithAnAnnotationMethodLocator.class, AFixedDefect8WithANamingConventionLocator.class, AFixedDefect23.class,
                AFixedDefect3.class, CommonAPIContext.class, AnObjectCheckerContext.class, AnObjectCheckerFailure.class,
                AStringFactoryImplContext.class, ANamedFieldLocatorWithNothingNoFieldsToFind.class, ANamedFieldLocatorWithFieldsToFind.class,
                ADummyAutoWireDeterminatorWithAnAnnoatedFieldToAutoWire.class, ADummyAutoWireDeterminatorWithAnAnnoatedFieldNotToAutoWire.class,
                ADummyAutoWireDeterminatorWithANamedFieldToAutoWire.class, ADummyAutoWireDeterminatorWithNoMarkedFields.class};
    }
}
