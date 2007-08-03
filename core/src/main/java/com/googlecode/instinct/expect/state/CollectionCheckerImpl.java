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

package com.googlecode.instinct.expect.state;

import java.util.Collection;
import org.hamcrest.Matchers;

// TODO Test this - especially desc strings

// or make custom matchers?
public class CollectionCheckerImpl<E, T extends Collection<E>> extends IterableCheckerImpl<E, T>
        implements CollectionChecker<E, T> {

    public CollectionCheckerImpl(final T subject) {
        super(subject);
    }

    public final void isEmpty() {
        getAsserter().expectThat(subject.isEmpty(),
                Matchers.describedAs("isEmpty() = <true>", Matchers.equalTo(true)));
    }

    public final void notEmpty() {
        getAsserter().expectThat(subject.isEmpty(),
                Matchers.describedAs("isEmpty() = <false>", Matchers.equalTo(false)));
    }

    public final void hasSize(final int size) {
        getAsserter().expectThat(subject.size(), Matchers.equalTo(size));
    }
}
