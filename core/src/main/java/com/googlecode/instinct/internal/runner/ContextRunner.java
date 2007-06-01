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

package com.googlecode.instinct.internal.runner;

import com.googlecode.instinct.internal.core.ContextClass;
import com.googlecode.instinct.runner.ContextListener;
import com.googlecode.instinct.runner.SpecificationListener;

public interface ContextRunner {
    /**
     * Registers a listener for context lifecycle events.
     *
     * @param contextListener A listener for context events.
     */
    void addContextListener(ContextListener contextListener);

    /**
     * Registers a listener for specification lifecycle events.
     *
     * @param specificationListener A listener for specification lifecycle events.
     */
    void addSpecificationListener(SpecificationListener specificationListener);

    /**
     * Runs the given context.
     *
     * @param contextClass A class containing specifications (a behaviour/specification context) to run.
     * @return The results of running the given context class.
     */
    ContextResult run(ContextClass contextClass);
}

