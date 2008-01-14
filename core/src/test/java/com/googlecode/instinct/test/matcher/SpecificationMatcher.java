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

package com.googlecode.instinct.test.matcher;

import com.googlecode.instinct.internal.core.OldDodgySpecificationMethod;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public final class SpecificationMatcher extends BaseMatcher<OldDodgySpecificationMethod> {
    private final String methodName;

    public SpecificationMatcher(final String methodName) {
        this.methodName = methodName;
    }

    public boolean matches(final Object item) {
        final OldDodgySpecificationMethod specificationMethod = (OldDodgySpecificationMethod) item;
        return methodName.equals(specificationMethod.getName());
    }

    public void describeTo(final Description description) {
        description.appendText("a specification named ").appendValue(methodName);
    }
}