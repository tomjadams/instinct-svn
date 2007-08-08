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

import static com.googlecode.instinct.internal.util.ParamChecker.checkNotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class AnnotatedMethodLocatorImpl implements AnnotatedMethodLocator {
    private final AnnotationChecker annotationChecker = new AnnotationCheckerImpl();

    public <A extends Annotation, T> Collection<Method> locate(final Class<T> cls, final Class<A> runtimeAnnotationType) {
        checkNotNull(cls, runtimeAnnotationType);
        final List<Method> annotatedMethods = new ArrayList<Method>();
        for (final Method method : cls.getDeclaredMethods()) {
            if (annotationChecker.isAnnotated(method, runtimeAnnotationType)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
