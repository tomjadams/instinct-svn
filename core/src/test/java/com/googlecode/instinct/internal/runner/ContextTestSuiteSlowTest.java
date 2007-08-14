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

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.integrate.junit3.ContextTestSuite;
import com.googlecode.instinct.test.InstinctTestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public final class ContextTestSuiteSlowTest extends InstinctTestCase {
    public void testRunsTheTestsInASimpleContextClass() {
        final TestSuite testSuite = new ContextTestSuite(ASimpleContext.class);
        expect.that(testSuite.testCount()).equalTo(1);
        final TestResult result = new TestResult();
        testSuite.runTest(testSuite.testAt(0), result);
        expect.that(result.failureCount()).equalTo(0);
    }
}
