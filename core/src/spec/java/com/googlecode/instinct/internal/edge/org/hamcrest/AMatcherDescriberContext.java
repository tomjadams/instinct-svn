/*
 * Copyright (c) 2007, Your Corporation. All Rights Reserved.
 */

package com.googlecode.instinct.internal.edge.org.hamcrest;

import static com.googlecode.instinct.expect.Expect.expect;
import com.googlecode.instinct.integrate.junit4.InstinctRunner;
import com.googlecode.instinct.marker.annotate.Specification;
import com.googlecode.instinct.marker.annotate.Subject;
import org.junit.runner.RunWith;

@RunWith(InstinctRunner.class)
public final class AMatcherDescriberContext {
    private static final String EMPTY_STRING = "";
    private static final String NL = System.getProperty("line.separator");
    @Subject private MatcherDescriberBuilder describing = new MatcherDescriberImpl();

    @Specification
    public void shouldReturnAnEmptyStringByDefault() {
        expect.that(describing.describe()).isEqualTo(EMPTY_STRING);
    }

    @Specification
    public void shouldAcceptAReason() {
        describing.setReason("Failed to locate method.");
        expect.that(describing.describe()).isEqualTo("Failed to locate method.");
    }

    @Specification
    public void shouldAcceptAnExpectLabelName() {
        describing.setExpectedLabelName("Expected").addColon().addSpace();
        expect.that(describing.describe()).isEqualTo("Expected: ");
    }

    @Specification
    public void shouldAcceptAnExpectedValue() {
        describing.setExpectedLabelName("Expected").addColon().addSpace().setExpectedValue("not <1>");
        expect.that(describing.describe()).isEqualTo("Expected: not <1>");
    }

    @Specification
    public void shouldAcceptAReturnedLabel() {
        describing.
                setExpectedLabelName("Expected").addColon().addSpace().setExpectedValue("not <1>").
                addNewLine().
                addSpace(5).setReturnedLabelName("got").addColon().addSpace();
        expect.that(describing.describe()).isEqualTo("Expected: not <1>" + NL + "     got: ");
    }

    @Specification
    public void shouldAcceptAReturnedValue() {
        describing.setReason("Could not find property.").
                addNewLine().
                setExpectedLabelName("Expected").addColon().addSpace().setExpectedValue("not <1>").
                addNewLine().addSpace(5).
                setReturnedLabelName("got").addColon().addSpace().setReturnedValue("<1>").
                addNewLine();
        expect.that(describing.describe()).isEqualTo("Could not find property." + NL + "Expected: not <1>" + NL + "     got: <1>" + NL);
    }

    @Specification
    public void shouldReturnADifferingFormat() {
        describing.setReason("The specified property could not be found.").
                addNewLine().
                setExpectedLabelName("Expected").addColon().addSpace().setExpectedValue("To find property byte1 on String.").
                addNewLine().
                setReturnedLabelName("Result").addColon().addSpace(2).setReturnedValue("Could not find property byte1 on String.").
                addNewLine();
        expect.that(describing.describe()).isEqualTo(
                "The specified property could not be found." + NL + "Expected: To find property byte1 on String." + NL +
                        "Result:  Could not find property byte1 on String." + NL);
    }

    @Specification
    public void shouldBeUsableDirectly() {
        describing.
                addValue("Equality check failed.").
                addNewLine().
                addValue("Expected: <1> equal to <1>").
                addNewLine().
                addValue("got: <2> equal to <1>").
                addNewLine();
        expect.that(describing.describe())
                .isEqualTo("Equality check failed." + NL + "Expected: <1> equal to <1>" + NL + "got: <2> equal to <1>" + NL);
    }
}