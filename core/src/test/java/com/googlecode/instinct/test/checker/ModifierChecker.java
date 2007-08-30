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

package com.googlecode.instinct.test.checker;

import au.net.netstorm.boost.test.reflect.checker.DefaultModifierTestChecker;
import au.net.netstorm.boost.test.reflect.checker.ModifierTestChecker;

public final class ModifierChecker {
    private static final ModifierTestChecker MODIFIER_CHECKER = new DefaultModifierTestChecker();

    private ModifierChecker() {
        throw new UnsupportedOperationException();
    }

    public static <T> void checkPublic(final Class<T> cls) {
        MODIFIER_CHECKER.checkPublic(cls);
    }

    public static <T> void checkFinal(final Class<T> cls) {
        MODIFIER_CHECKER.checkFinal(cls);
    }
}
