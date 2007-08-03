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

package com.googlecode.instinct.test.reflect;

import com.googlecode.instinct.internal.util.ObjectFactory;
import com.googlecode.instinct.internal.util.ObjectFactoryImpl;
import static com.googlecode.instinct.internal.util.ParamChecker.checkNotNull;
import com.googlecode.instinct.internal.util.Suggest;

@Suggest("Add another method to use constructor directly.")
public final class SubjectCreator {
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactoryImpl();

    private SubjectCreator() {
        throw new UnsupportedOperationException();
    }

    public static <T> T createSubject(final Class<T> subjectClass, final Object... dependencies) {
        checkNotNull(subjectClass, dependencies);
        return createSubjectWithConstructorArgs(subjectClass, new Object[]{}, dependencies);
    }

    public static <T> T createSubjectWithConstructorArgs(final Class<T> subjectClass, final Object[] constructorArgs, final Object... dependencies) {
        checkNotNull(subjectClass, dependencies);
        final T subject = OBJECT_FACTORY.create(subjectClass, constructorArgs);
        for (final Object dependency : dependencies) {
            Reflector.insertFieldValueUsingInferredType(subject, dependency);
        }
        return subject;
    }
}
