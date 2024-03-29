/*
* Copyright 2006-2008 Tom Adams
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

package com.googlecode.instinct.expect.state.matcher;

import com.googlecode.instinct.internal.util.ListEquality;
import static com.googlecode.instinct.internal.util.ParamChecker.checkNotNull;
import fj.data.List;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class ListEqualityMatcher<T> extends TypeSafeMatcher<List<T>> {
    private final List<T> listArg;

    private ListEqualityMatcher(final List<T> listArg) {
        this.listArg = listArg;
    }

    @Override
    public boolean matchesSafely(final List<T> list) {
        return ListEquality.<T>listEquals().eq(list, listArg);
    }

    public void describeTo(final Description description) {
        description.appendValue(listArg.toCollection());
    }

    @SuppressWarnings({"unchecked"})
    @Factory
    public static <T> Matcher<List<T>> equalTo(final List<T> list) {
        checkNotNull(list);
        return new ListEqualityMatcher(list);
    }
}
