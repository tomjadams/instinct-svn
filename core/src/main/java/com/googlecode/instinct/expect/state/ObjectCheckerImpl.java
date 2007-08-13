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

import com.googlecode.instinct.internal.edge.org.hamcrest.MatcherAssertEdge;
import com.googlecode.instinct.internal.edge.org.hamcrest.MatcherAssertEdgeImpl;
import com.googlecode.instinct.internal.util.Fix;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

// SUPPRESS VisibilityModifier|IllegalToken {
@Fix("Test this.")
public class ObjectCheckerImpl<T> implements ObjectChecker<T> {
    @SuppressWarnings({"ProtectedField"})
    protected final T subject;
    private MatcherAssertEdge asserter = new MatcherAssertEdgeImpl();

    public ObjectCheckerImpl(final T subject) {
        this.subject = subject;
    }

    protected final MatcherAssertEdge getAsserter() {
        return asserter;
    }

    public final void equalTo(final T t) {
        getAsserter().expectThat(subject, Matchers.equalTo(t));
    }

    public final void notEqualTo(final T t) {
        getAsserter().expectNotThat(subject, Matchers.equalTo(t));
    }

    public final void instanceOf(final Class<T> cls) {
        getAsserter().expectThat(subject, Matchers.instanceOf(cls));
    }

    public final void notInstanceOf(final Class<T> cls) {
        getAsserter().expectNotThat(subject, Matchers.instanceOf(cls));
    }

    public final void sameInstanceAs(final T t) {
        getAsserter().expectThat(subject, Matchers.sameInstance(t));
    }

    public final void notSameInstanceAs(final T t) {
        getAsserter().expectNotThat(subject, Matchers.sameInstance(t));
    }

    public final void isNull() {
        getAsserter().expectThat(subject, Matchers.nullValue());
    }

    public final void notNull() {
        getAsserter().expectThat(subject, Matchers.notNullValue());
    }

    public final void hasToString(final Matcher<String> matcher) {
        getAsserter().expectThat(subject, Matchers.hasToString(matcher));
    }

    public final void matchesAllOf(final Matcher<T>... matchers) {
        getAsserter().expectThat(subject, Matchers.allOf(matchers));
    }

    public final void matchesAllOf(final Iterable<Matcher<? extends T>> iterable) {
        getAsserter().expectThat(subject, Matchers.allOf(iterable));
    }

    public final void notMatchAllOf(final Matcher<T>... matchers) {
        getAsserter().expectNotThat(subject, Matchers.allOf(matchers));
    }

    public final void notMatchAllOf(final Iterable<Matcher<? extends T>> iterable) {
        getAsserter().expectNotThat(subject, Matchers.allOf(iterable));
    }

    public final void matchesAnyOf(final Matcher<T>... matchers) {
        getAsserter().expectThat(subject, Matchers.anyOf(matchers));
    }

    public final void matchesAnyOf(final Iterable<Matcher<? extends T>> iterable) {
        getAsserter().expectThat(subject, Matchers.anyOf(iterable));
    }

    public final void notMatchAnyOf(final Matcher<T>... matchers) {
        getAsserter().expectNotThat(subject, Matchers.anyOf(matchers));
    }

    public final void notMatchAnyOf(final Iterable<Matcher<? extends T>> iterable) {
        getAsserter().expectNotThat(subject, Matchers.anyOf(iterable));
    }

    public final void hasBeanProperty(final String string) {
        getAsserter().expectThat(subject, Matchers.hasProperty(string));
    }

    public final void hasBeanProperty(final String string, final Matcher<?> matcher) {
        getAsserter().expectThat(subject, Matchers.hasProperty(string, matcher));
    }
}
// } SUPPRESS VisibilityModifier|IllegalToken
