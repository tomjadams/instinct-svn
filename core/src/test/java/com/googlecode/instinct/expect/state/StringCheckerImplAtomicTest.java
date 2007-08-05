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

import static com.googlecode.instinct.test.ExceptionTestChecker.expectException;
import com.googlecode.instinct.test.InstinctTestCase;
import static com.googlecode.instinct.test.checker.AssertThrowsChecker.assertThrows;
import static com.googlecode.instinct.test.checker.ModifierChecker.checkPublic;
import static com.googlecode.instinct.test.triangulate.Triangulation.getInstance;

public final class StringCheckerImplAtomicTest extends InstinctTestCase {
    private static final String SUBJECT_STRING = getInstance(String.class);
    private StringChecker checker;

    @Override
    public void setUpSubject() {
        checker = new StringCheckerImpl(SUBJECT_STRING);
    }

    public void testConformsToClassTraits() {
        checkPublic(StringCheckerImpl.class);
    }

    public void testMatchesRegularExpressions() {
        match("[a-z]+", "astring");
    }

    public void testThrowsExceptionWhenStringPassedToMatchesRegexDoesNotMatchRegularExpression() {
        expectException(AssertionError.class, new Runnable() {
            public void run() {
                match("[a-z]+", "a-string-123");
            }
        });
    }

    public void testMatchesFailingRegularExpressions() {
        final StringChecker checker = new StringCheckerImpl("[a-z]+");
        checker.doesNotMatchRegex("a-string-123");
    }

    public void testThrowsExceptionWhenStringPassedMatchesRegularExpressionButIsExpectedToFail() {
        expectException(AssertionError.class, new Runnable() {
            public void run() {
                final String matchingRegex = "[a-z]+[1-9]+";
                final StringChecker checker = new StringCheckerImpl("astring123");
                checker.doesNotMatchRegex(matchingRegex);
            }
        });
    }

    public void testShowsHumanReadableStringWhenMatchesRegexIsPassedNull() {
        expectNullRejected("matchesRegex", new Runnable() {
            public void run() {
                checker.matchesRegex(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenEqualsIgnoringCaseIsPassedNull() {
        expectNullRejected("equalToIgnoringCase", new Runnable() {
            public void run() {
                checker.equalToIgnoringCase(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenEqualsIgnoringWhiteSpaceIsPassedNull() {
        expectNullRejected("equalToIgnoringWhiteSpace", new Runnable() {
            public void run() {
                checker.equalToIgnoringWhiteSpace(null);
            }
        });
    }

    public void testShowHumanReadableStringWhenNotEqualIgnoringCaseIsPassedNull() {
        expectNullRejected("notEqualToIgnoringCase", new Runnable() {
            public void run() {
                checker.notEqualToIgnoringCase(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenNotEqualToIgnoringWhiteSpaceIsPassedNull() {
        expectNullRejected("notEqualToIgnoringWhiteSpace", new Runnable() {
            public void run() {
                checker.notEqualToIgnoringWhiteSpace(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenDoesNotContainStringIsPassedNull() {
        expectNullRejected("doesNotContainString", new Runnable() {
            public void run() {
                checker.doesNotContainString(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenDoesNotEndWithIsPassedNull() {
        expectNullRejected("doesNotEndWith", new Runnable() {
            public void run() {
                checker.doesNotEndWith(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenStartsWthIsPassedNull() {
        expectNullRejected("startsWith", new Runnable() {
            public void run() {
                checker.startsWith(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenDoesNotStartWithIsPassedNull() {
        expectNullRejected("doesNotStartWith", new Runnable() {
            public void run() {
                checker.doesNotStartWith(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenEndsWithIsPassedNull() {
        expectNullRejected("endsWith", new Runnable() {
            public void run() {
                checker.endsWith(null);
            }
        });
    }

    public void testShowsHumanReadableStringWhenContainsStringIsPassedNull() {
        expectNullRejected("containsString", new Runnable() {
            public void run() {
                checker.containsString(null);
            }
        });
    }

    private void match(final String regularExpression, final String stringToMatch) {
        final StringChecker checker = new StringCheckerImpl(stringToMatch);
        checker.matchesRegex(regularExpression);
    }

    private void expectNullRejected(final String methodName, final Runnable block) {
        assertThrows(IllegalArgumentException.class, "Cannot pass a null string into " + methodName, block);
    }
}
