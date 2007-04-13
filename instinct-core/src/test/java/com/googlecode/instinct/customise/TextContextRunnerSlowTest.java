package com.googlecode.instinct.customise;

import java.io.ByteArrayOutputStream;
import com.googlecode.instinct.internal.runner.ASimpleContext;
import com.googlecode.instinct.internal.runner.BehaviourContextRunner;
import com.googlecode.instinct.test.InstinctTestCase;

public final class TextContextRunnerSlowTest extends InstinctTestCase {
    private BehaviourContextRunner contextRunner;
    private ByteArrayOutputStream out;

    @Override
    public void setUpTestDoubles() {
        out = new ByteArrayOutputStream();
    }

    @Override
    public void setUpSubject() {
        contextRunner = new TextContextRunner(new BriefContextResultMessageBuilder(), out);
    }

    public void testRunnerSendsSpeciciationResultsToOutput() {
        contextRunner.run(ASimpleContext.class);
        checkRunnerSendsSpeciciationResultsToOutput();
    }

    private void checkRunnerSendsSpeciciationResultsToOutput() {
        final String runnerOutput = new String(out.toByteArray());
        assertTrue(runnerOutput.contains("Context: ASimpleContext"));
        assertTrue(runnerOutput.contains("Specifications run:"));
    }
}
