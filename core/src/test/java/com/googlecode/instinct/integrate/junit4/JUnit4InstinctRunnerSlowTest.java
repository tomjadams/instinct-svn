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

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.internal.util.Suggest;
import com.googlecode.instinct.marker.annotate.Specification;
import org.junit.runner.RunWith;

@Suggest("Invoke JUnit programmatically to do this test so that the suites work.")
@RunWith(InstinctRunner.class)
public class JUnit4InstinctRunnerSlowTest {

    @Specification
    public void mustEqualTrue() {
        expect.that(Boolean.TRUE).isTrue();
    }

    public void mustEqualFalse() {
        expect.that(Boolean.FALSE).isFalse();
    }
}
