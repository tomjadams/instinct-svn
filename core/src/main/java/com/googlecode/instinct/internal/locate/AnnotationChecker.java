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

package com.googlecode.instinct.internal.locate;

import com.googlecode.instinct.marker.AnnotationAttribute;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

@SuppressWarnings({"MethodParameterOfConcreteClass"})
public interface AnnotationChecker {
    <A extends Annotation> boolean isAnnotated(AnnotatedElement annotatedElement, Class<A> annotationType, AnnotationAttribute attributeConstraint);
}
