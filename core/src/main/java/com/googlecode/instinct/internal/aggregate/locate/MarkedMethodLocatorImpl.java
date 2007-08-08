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

import com.googlecode.instinct.internal.util.Suggest;
import com.googlecode.instinct.marker.MarkingScheme;
import com.googlecode.instinct.marker.naming.NamingConvention;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class MarkedMethodLocatorImpl implements MarkedMethodLocator {
    private final AnnotatedMethodLocator annotatedMethodLocator = new AnnotatedMethodLocatorImpl();
    private final NamingConventionMethodLocator namingConventionMethodLocator = new NamingConventionMethodLocatorImpl();

    @Suggest("Return an unmodifiable collection.")
    public <T> Collection<Method> locateAll(final Class<T> cls, final MarkingScheme markingScheme) {
        final Collection<Method> annotatedMethods = findMethodsByAnnotation(cls, markingScheme.getAnnotationType());
        final Collection<Method> namedMethods = findMethodsByNamingConvention(cls, markingScheme.getNamingConvention());
        final Set<Method> methods = new HashSet<Method>();
        methods.addAll(annotatedMethods);
        methods.addAll(namedMethods);
        return methods;
    }

    private <T> Collection<Method> findMethodsByNamingConvention(final Class<T> cls, final NamingConvention namingConvention) {
        return namingConventionMethodLocator.locate(cls, namingConvention);
    }

    private <A extends Annotation, T> Collection<Method> findMethodsByAnnotation(final Class<T> cls, final Class<A> annotationType) {
        return annotatedMethodLocator.locate(cls, annotationType);
    }
}
