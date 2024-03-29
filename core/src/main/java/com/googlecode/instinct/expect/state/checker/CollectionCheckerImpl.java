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

package com.googlecode.instinct.expect.state.checker;

import static com.googlecode.instinct.expect.state.matcher.EqualityMatcher.equalTo;
import static java.util.Arrays.asList;
import java.util.Collection;
import org.hamcrest.Matchers;

public class CollectionCheckerImpl<E, T extends Collection<E>> extends IterableCheckerImpl<E, T> implements CollectionChecker<E, T> {
    public CollectionCheckerImpl(final T subject) {
        super(subject);
    }

    @Override
    public final void isEmpty() {
        getAsserter().expectThat(subject.isEmpty(), Matchers.describedAs("isEmpty() = <true>", equalTo(true)));
    }

    @Override
    public final void isNotEmpty() {
        getAsserter().expectThat(subject.isEmpty(), Matchers.describedAs("isEmpty() = <false>", equalTo(false)));
    }

    public final void isOfSize(final int size) {
        getAsserter().expectThat(subject.size(), equalTo(size));
    }

    public final void hasTheSameContentAs(final Collection<E> items) {
        getAsserter().expectThat(subject.size(), equalTo(items.size()));
        containsItems(items);
    }

    public final void hasTheSameContentAs(final E... items) {
        hasTheSameContentAs(asList(items));
    }
}
