/*
 * Copyright 2006-2007 Workingmouse
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

package com.googlecode.instinct.internal.runner;

import com.googlecode.instinct.internal.core.LifecycleMethod;
import com.googlecode.instinct.internal.util.MethodInvoker;
import com.googlecode.instinct.internal.util.MethodInvokerImpl;
import static com.googlecode.instinct.internal.util.param.ParamChecker.checkNotNull;
import com.googlecode.instinct.sandbox.ForAll;

public final class MethodInvokerFactoryImpl implements MethodInvokerFactory {
    private ParameterAnnotationFinder finder = new ParameterAnnotationFinderImpl();

    public MethodInvoker create(final LifecycleMethod specificationMethod) {
        checkNotNull(specificationMethod);
        if (finder.hasAnnotation(ForAll.class, specificationMethod.getParameterAnnotations())) {
            return new TheoryMethodInvokerImpl();
        } else {
            return new MethodInvokerImpl();
        }
    }
}
