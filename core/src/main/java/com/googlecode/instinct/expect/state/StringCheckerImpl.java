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

import com.googlecode.instinct.internal.util.Suggest;
import org.hamcrest.Matchers;

@Suggest("Test the happy paths through this class.")
public class StringCheckerImpl extends ComparableCheckerImpl<String> implements StringChecker {
    public StringCheckerImpl(final String subject) {
        super(subject);
    }

    public final void equalToIgnoringCase(final String string) {
        nullCheckString(string, "equalToIgnoringCase");
        getAsserter().expectThat(subject, Matchers.equalToIgnoringCase(string));
    }

    public final void equalToIgnoringWhiteSpace(final String string) {
        nullCheckString(string, "equalToIgnoringWhiteSpace");
        getAsserter().expectThat(subject, Matchers.equalToIgnoringWhiteSpace(string));
    }

    public final void notEqualToIgnoringCase(final String string) {
        nullCheckString(string, "notEqualToIgnoringCase");
        getAsserter().expectNotThat(subject, Matchers.equalToIgnoringCase(string));
    }

    public final void notEqualToIgnoringWhiteSpace(final String string) {
        nullCheckString(string, "notEqualToIgnoringWhiteSpace");
        getAsserter().expectNotThat(subject, Matchers.equalToIgnoringWhiteSpace(string));
    }

    public final void containsString(final String string) {
        nullCheckString(string, "containsString");
        getAsserter().expectThat(subject, Matchers.containsString(string));
    }

    public final void doesNotContainString(final String string) {
        nullCheckString(string, "doesNotContainString");
        getAsserter().expectNotThat(subject, Matchers.containsString(string));
    }

    public final void endsWith(final String string) {
        nullCheckString(string, "endsWith");
        getAsserter().expectThat(subject, Matchers.endsWith(string));
    }

    public final void doesNotEndWith(final String string) {
        nullCheckString(string, "doesNotEndWith");
        getAsserter().expectNotThat(subject, Matchers.endsWith(string));
    }

    public final void startsWith(final String string) {
        nullCheckString(string, "startsWith");
        getAsserter().expectThat(subject, Matchers.startsWith(string));
    }

    public final void doesNotStartWith(final String string) {
        nullCheckString(string, "doesNotStartWith");
        getAsserter().expectNotThat(subject, Matchers.startsWith(string));
    }

    public final void isEmpty() {
        getAsserter().expectThat(subject, StringLengthMatcher.hasLength(0));
    }

    public final void notEmpty() {
        getAsserter().expectThat(subject, Matchers.not(StringLengthMatcher.hasLength(0)));
    }

    public final void hasLength(final int length) {
        getAsserter().expectThat(subject, StringLengthMatcher.hasLength(length));
    }

    public final void matchesRegex(final String regularExpression) {
        nullCheckString(regularExpression, "matchesRegex");
        getAsserter().expectThat(subject, RegularExpressionMatcher.matchesRegex(regularExpression));
    }

    public final void doesNotMatchRegex(final String regularExpression) {
        nullCheckString(regularExpression, "doesNotMatchRegex");
        getAsserter().expectNotThat(subject, RegularExpressionMatcher.matchesRegex(regularExpression));
    }

    @Suggest("Move this nice form of null check into ParamChecker.")
    private void nullCheckString(final String stringToCheck, final String methodName) {
        if (stringToCheck == null) {
            throw new IllegalArgumentException("Cannot pass a null string into " + methodName);
        }
    }
}
