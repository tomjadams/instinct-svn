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

import com.googlecode.instinct.core.annotate.AfterSpecification;
import com.googlecode.instinct.core.annotate.BeforeSpecification;
import com.googlecode.instinct.core.annotate.BehaviourContext;
import com.googlecode.instinct.core.annotate.Specification;
import static com.googlecode.instinct.verify.Verify.mustBeTrue;

@BehaviourContext
public final class ASimpleContext {
    @BeforeSpecification
    public void setUp() {
    }

    @BeforeSpecification
    public void setUpAgain() {
    }

    @Specification
    public void toCheckVerification() {
        mustBeTrue(true);
    }

    @AfterSpecification
    public void tearDown() {
    }

    @AfterSpecification
    public void tearDownAgain() {
    }
}
