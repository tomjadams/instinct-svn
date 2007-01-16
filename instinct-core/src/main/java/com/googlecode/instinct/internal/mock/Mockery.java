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

package com.googlecode.instinct.internal.mock;

import org.jmock.builder.NameMatchBuilder;
import org.jmock.core.Constraint;
import org.jmock.core.InvocationMatcher;
import org.jmock.core.Stub;

public interface Mockery {
    <T> T mock(Class<T> toMock);

    <T> T mock(Class<T> toMock, String roleName);

    NameMatchBuilder expects(Object mockedObject);

    NameMatchBuilder expects(Object mockedObject, InvocationMatcher expectation);

    InvocationMatcher once();

    InvocationMatcher times(int expectedNumberOfCalls);

    Constraint same(Object argument);

    Constraint anything();

    Constraint eq(Object argument);

    Stub returnValue(Object returnValue);

    void verify();
}
