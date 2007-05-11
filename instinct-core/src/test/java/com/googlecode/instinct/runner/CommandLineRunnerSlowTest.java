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

package com.googlecode.instinct.runner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static java.lang.System.setOut;
import com.googlecode.instinct.internal.runner.ASimpleContext;
import com.googlecode.instinct.internal.util.Suggest;
import com.googlecode.instinct.test.InstinctTestCase;

public final class CommandLineRunnerSlowTest extends InstinctTestCase {
    private static final Class<ASimpleContext> CONTEXT_CLASS_TO_RUN = ASimpleContext.class;
    private ByteArrayOutputStream outputBuffer;

    @Override
    public void setUpTestDoubles() {
        outputBuffer = new ByteArrayOutputStream();
    }

    @Suggest("Try-finally belongs in another class, pass closure, remove dupe in TextContextRunnerSlowTest.")
    @SuppressWarnings({"IOResourceOpenedButNotSafelyClosed", "UseOfSystemOutOrSystemErr"})
    public void testRunsASingleContextFromTheCommandLine() {
        final PrintStream defaultStdOut = System.out;
        try {
            setOut(new PrintStream(outputBuffer));
            CommandLineRunner.main(CONTEXT_CLASS_TO_RUN.getName());
            checkRunnerSendsSpeciciationResultsToOutput(CONTEXT_CLASS_TO_RUN);
        } finally {
            setOut(defaultStdOut);
        }
    }

    private <T> void checkRunnerSendsSpeciciationResultsToOutput(final Class<T> contextClass) {
        final String runnerOutput = new String(outputBuffer.toByteArray());
        assertTrue("Expected to find context name", runnerOutput.contains(contextClass.getSimpleName()));
        assertTrue("Expected to find the number of specs run", runnerOutput.contains("Specifications run:"));
    }
}