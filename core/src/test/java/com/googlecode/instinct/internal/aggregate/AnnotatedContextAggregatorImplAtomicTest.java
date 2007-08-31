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

package com.googlecode.instinct.internal.aggregate;

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.internal.locate.AnnotationFileFilter;
import com.googlecode.instinct.internal.locate.ClassLocator;
import com.googlecode.instinct.internal.util.JavaClassName;
import com.googlecode.instinct.internal.util.ObjectFactory;
import com.googlecode.instinct.marker.annotate.Context;
import com.googlecode.instinct.marker.annotate.Mock;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.reflect.SubjectCreator.createSubjectWithConstructorArgs;
import java.io.File;
import java.io.FileFilter;
import org.jmock.Expectations;

public final class AnnotatedContextAggregatorImplAtomicTest extends InstinctTestCase {
    private static final Class<?> CLASS_IN_SPEC_TREE = AnnotatedContextAggregatorImplAtomicTest.class;
    private static final JavaClassName[] CLASS_NAMES = {};
    private static final String PACKAGE_ROOT = "";
    private ContextAggregator aggregator;
    @Mock private PackageRootFinder packageRootFinder;
    @Mock private ClassLocator classLocator;
    @Mock private ObjectFactory objectFactory;
    @Mock private File packageRoot;
    @Mock private FileFilter fileFilter;

    @Override
    public void setUpSubject() {
        aggregator = createSubjectWithConstructorArgs(AnnotatedContextAggregatorImpl.class, new Object[]{CLASS_IN_SPEC_TREE}, packageRootFinder, classLocator, objectFactory);
    }

    public void testGetContextNames() {
        expect.that(new Expectations() {
            {
                one(packageRootFinder).getPackageRoot(CLASS_IN_SPEC_TREE); will(returnValue(PACKAGE_ROOT));
                one(objectFactory).create(File.class, PACKAGE_ROOT); will(returnValue(packageRoot));
                one(objectFactory).create(AnnotationFileFilter.class, packageRoot, Context.class); will(returnValue(fileFilter));
                one(classLocator).locate(packageRoot, fileFilter); will(returnValue(CLASS_NAMES));
            }
        });
        final JavaClassName[] names = aggregator.getContextNames();
        assertSame(CLASS_NAMES, names);
    }
}
