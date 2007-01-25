/*
 * Created by IntelliJ IDEA.
 * User: amrk
 * Date: 12/07/2006
 * Time: 21:54:47
 */
package com.theoryinpractice.testng;

import javax.swing.*;

import com.intellij.execution.configurations.coverage.CoverageConfigurable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.pom.java.LanguageLevel;
import org.jetbrains.annotations.NotNull;

public class TestNGCoverageConfigurationEditor extends CoverageConfigurable<TestNGConfiguration>
{
    public TestNGCoverageConfigurationEditor(Project project) {
        super(project);
    }

    protected boolean isJre50Configured(TestNGConfiguration configuration) {
        //the next two lines are awkward for a reason, using compareTo for some reason causes a JVM class verification error!

        Module[] modules = configuration.getModules();
        if (modules != null) {
            for (Module module : modules) {
                LanguageLevel effectiveLanguageLevel = module.getEffectiveLanguageLevel();
                boolean is15 = effectiveLanguageLevel != LanguageLevel.JDK_1_4 && effectiveLanguageLevel != LanguageLevel.JDK_1_3;
                if (!is15) {
                    return false;
                }
            }
        }
        return true;
    }

    @NotNull
    public JComponent createEditor() {
        return super.createEditor();
    }

    public void resetEditorFrom(TestNGConfiguration configuration) {
        super.resetEditorFrom(configuration);
    }

    public void applyEditorTo(TestNGConfiguration configuration) throws ConfigurationException {
        super.applyEditorTo(configuration);
    }

    public void disposeEditor() {
        super.disposeEditor();
    }
}