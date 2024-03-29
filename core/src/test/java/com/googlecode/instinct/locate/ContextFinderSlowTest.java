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

package com.googlecode.instinct.locate;

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.internal.util.Fix;
import com.googlecode.instinct.internal.util.JavaClassName;
import com.googlecode.instinct.internal.util.Suggest;
import com.googlecode.instinct.locate.ContextFinder;
import com.googlecode.instinct.locate.ContextFinderImpl;
import static com.googlecode.instinct.marker.annotate.Specification.ALL_GROUPS;
import com.googlecode.instinct.test.InstinctTestCase;

@Fix("This is a very brittle spec. Everytime we add a new Spec this breaks!")
@Suggest("Could we have a sample directory of some sort that contains a known number of specs?")
public final class ContextFinderSlowTest extends InstinctTestCase {
    public static final int EXPECTED_MINIMUM_NOMBER_OF_CONTEXTS = 70;
    private ContextFinder finder;

    @Override
    public void setUpSubject() {
        finder = new ContextFinderImpl(ContextFinderSlowTest.class);
    }

    public void testFindsCorrectNumberOfContexts() {
        final JavaClassName[] contexts = finder.getContextNames(ALL_GROUPS);
        expect.that(contexts.length).isGreaterThan(EXPECTED_MINIMUM_NOMBER_OF_CONTEXTS);
    }
}
