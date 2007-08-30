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

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.internal.util.ClassInstantiator;
import com.googlecode.instinct.internal.util.ClassInstantiatorFactory;
import com.googlecode.instinct.marker.annotate.Context;
import com.googlecode.instinct.marker.annotate.Mock;
import com.googlecode.instinct.marker.annotate.Subject;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.ClassChecker.checkClass;
import static com.googlecode.instinct.test.reflect.SubjectCreator.createSubjectWithConstructorArgs;
import java.io.File;
import org.jmock.Expectations;

public final class AnnotatedClassFileCheckerImplAtomicTest extends InstinctTestCase {
    @Subject(auto = false) private AnnotatedClassFileChecker checker;
    @Mock private File packageRoot;
    @Mock private File classFile;
    @Mock private AnnotationChecker annotationChecker;
    @Mock private ClassInstantiator instantiator;
    @Mock private ClassInstantiatorFactory instantiatorFactory;

    @Override
    public void setUpSubject() {
        checker = createSubjectWithConstructorArgs(AnnotatedClassFileCheckerImpl.class, new Object[]{packageRoot}, annotationChecker, instantiatorFactory);
    }

    public void testConformsToClassTraits() {
        checkClass(AnnotatedClassFileCheckerImpl.class, AnnotatedClassFileChecker.class);
    }

    public void testIsAnnotated() {
        expect.that(new Expectations() {
            {
                one(classFile).getName(); will(returnValue("Class.class"));
                one(instantiatorFactory).create(); will(returnValue(instantiator));
                one(instantiator).instantiateClass(classFile, packageRoot); will(returnValue(Class.class));
                one(annotationChecker).isAnnotated(Class.class, Context.class); will(returnValue(true));
            }
        });
        assertTrue(checker.isAnnotated(classFile, Context.class));
    }
}
