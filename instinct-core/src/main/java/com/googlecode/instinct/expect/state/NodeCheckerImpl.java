/*
 * Copyright 2006-2007 Ben Warren
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

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.w3c.dom.Node;

// TODO Test this
public class NodeCheckerImpl<T extends Node> extends ObjectCheckerImpl<T> implements NodeChecker<T> {
    public NodeCheckerImpl(T subject) {
        super(subject);
    }

    public final void hasXPath(String path, Matcher<String> matcher) {
        getAsserter().expectThat(subject, Matchers.hasXPath(path, matcher));
    }

    public final void hasXPath(String path) {
        getAsserter().expectThat(subject, Matchers.hasXPath(path));
    }

    public final void notHaveXPath(String path, Matcher<String> matcher) {
        getAsserter().expectNotThat(subject, Matchers.hasXPath(path, matcher));
    }

    public final void notHaveXPath(String path) {
        getAsserter().expectNotThat(subject, Matchers.hasXPath(path));
    }
}
