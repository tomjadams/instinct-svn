/*
 * Copyright 2006-2008 Workingmouse
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

import static com.googlecode.instinct.expect.state.matcher.ToStringableEither.toStringableEither;
import com.googlecode.instinct.integrate.junit4.InstinctRunner;
import com.googlecode.instinct.marker.annotate.BeforeSpecification;
import com.googlecode.instinct.marker.annotate.Specification;
import com.googlecode.instinct.marker.annotate.Stub;
import fj.data.Either;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.runner.RunWith;

@RunWith(InstinctRunner.class)
public final class AnEitherIsLeftMatcher {
    @Stub(auto = false) private ToStringableEither<Integer, Integer> left;
    @Stub(auto = false) private ToStringableEither<Integer, Integer> right;

    @BeforeSpecification
    public void before() {
        left = toStringableEither(Either.<Integer, Integer>left(1));
        right = toStringableEither(Either.<Integer, Integer>right(1));
    }

    @Specification
    public void matchesEithersThatAreLeft() {
        assertThat(left, EitherIsLeftMatcher.<Integer, Integer>isLeft());
        assertThat(left, EitherIsLeftMatcher.<Integer, Integer>isLeft(1));
        assertThat(left, EitherIsLeftMatcher.<Integer, Integer>isLeft(equalTo(1)));
    }

    @Specification
    public void doesNotMatchEithersThatAreRight() {
        assertThat(right, not(EitherIsLeftMatcher.<Integer, Integer>isLeft()));
    }
}