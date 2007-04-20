package com.googlecode.instinct.sandbox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
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

public final class InterfaceComposer {

    public <T> T compose(Class<T> iface, Object... implementers) {
        final InvocationHandler handler = new ComposingInvocationHandler<T>(implementers);
        return (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class[]{iface}, handler);
    }
}
