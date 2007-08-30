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

package com.googlecode.instinct.expect.behaviour;

import com.googlecode.instinct.internal.expect.behaviour.JMock2Mockery;
import com.googlecode.instinct.internal.expect.behaviour.JMock2MockeryImpl;
import org.jmock.Sequence;

public final class Mocker {
    private static final JMock2Mockery J_MOCK2_MOCKERY = new JMock2MockeryImpl();

    private Mocker() {
        throw new UnsupportedOperationException();
    }

    public static JMock2Mockery getJMock2Mockery() {
        return J_MOCK2_MOCKERY;
    }

    public static <T> T mock(final Class<T> typeToMock) {
        return J_MOCK2_MOCKERY.mock(typeToMock);
    }

    public static <T> T mock(final Class<T> typeToMock, final String roleName) {
        return J_MOCK2_MOCKERY.mock(typeToMock, roleName);
    }

    public static Sequence sequence() {
        return J_MOCK2_MOCKERY.sequence();
    }

    public static void verify() {
        J_MOCK2_MOCKERY.verify();
    }

    public static void reset() {
        J_MOCK2_MOCKERY.reset();
    }
}
