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

package com.googlecode.instinct.integrate.junit4;

import com.googlecode.instinct.internal.core.SpecificationMethod;
import com.googlecode.instinct.internal.edge.org.junit.runner.DescriptionEdge;
import com.googlecode.instinct.internal.edge.org.junit.runner.DescriptionEdgeImpl;
import com.googlecode.instinct.internal.runner.SpecificationResult;
import com.googlecode.instinct.internal.util.ExceptionFinder;
import com.googlecode.instinct.internal.util.ExceptionFinderImpl;
import com.googlecode.instinct.internal.util.ObjectFactory;
import com.googlecode.instinct.internal.util.ObjectFactoryImpl;
import com.googlecode.instinct.internal.util.ParamChecker;
import java.util.Collection;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public final class SpecificationRunnerImpl implements SpecificationRunner {
    private final DescriptionEdge descriptionEdge = new DescriptionEdgeImpl();
    private final ExceptionFinder exceptionFinder = new ExceptionFinderImpl();
    private final ObjectFactory objectFactory = new ObjectFactoryImpl();
    private final RunNotifier notifier;

    public SpecificationRunnerImpl(final RunNotifier notifier) {
        ParamChecker.checkNotNull(notifier);
        this.notifier = notifier;
    }

    public void run(final Collection<SpecificationMethod> specificationMethods) {
        ParamChecker.checkNotNull(specificationMethods);
        for (final SpecificationMethod specificationMethod : specificationMethods) {
            final Description description = descriptionEdge.createTestDescription(specificationMethod.getSpecificationMethod().getDeclaringClass(),
                    specificationMethod.getName());
            notifier.fireTestStarted(description);
            final SpecificationResult specificationResult = specificationMethod.run();
            if (specificationResult.completedSuccessfully()) {
                notifier.fireTestFinished(description);
            } else {
                notifier.fireTestFailure(createFailure(description, specificationResult));
            }
        }
    }

    private Failure createFailure(final Description description, final SpecificationResult specificationResult) {
        final Throwable rootCause = exceptionFinder.getRootCause((Throwable) specificationResult.getStatus().getDetailedStatus());
        return objectFactory.create(Failure.class, description, rootCause);
    }
}
