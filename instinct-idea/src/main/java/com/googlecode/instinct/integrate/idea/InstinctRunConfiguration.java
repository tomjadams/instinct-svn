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

package com.googlecode.instinct.integrate.idea;

import static java.util.Arrays.asList;
import java.util.Collection;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationPerRunnerSettings;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.configurations.RuntimeConfiguration;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.JavaProgramRunner;
import com.intellij.execution.runners.RunnerInfo;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.PathUtil;
import com.googlecode.instinct.internal.util.Suggest;
import org.jdom.Element;

public final class InstinctRunConfiguration extends RuntimeConfiguration {
    public String contextClassName;
    public String behaviorMethod;
    public String moduleName;

    public InstinctRunConfiguration(Project project, ConfigurationFactory factory, String name) {
        super(name, project, factory);
    }

    @Suggest("Can we use the factory to create this?")
    public InstinctRunConfiguration createInstance() {
        return new InstinctRunConfiguration(getProject(), getFactory(), getName());
    }

    public SettingsEditor<InstinctRunConfiguration> getConfigurationEditor() {
        return new InstinctRunSettingsEditor(getProject());
    }

    @Override
    public SettingsEditor<JDOMExternalizable> getRunnerSettingsEditor(final JavaProgramRunner runner) {
        return null;
    }

    @Override
    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    @Override
    public void writeExternal(final Element element) throws WriteExternalException {
        super.writeExternal(element);
        DefaultJDOMExternalizer.writeExternal(this, element);
    }

    public RunProfileState getState(final DataContext context, final RunnerInfo runnerInfo, final RunnerSettings runner,
            final ConfigurationPerRunnerSettings configuration) throws ExecutionException {
        final IntinctCommandLineState commandLineState = new IntinctCommandLineState(this, runner, configuration);
        commandLineState.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(getProject()));
        commandLineState.setModulesToCompile(getModules());
        return commandLineState;
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        super.checkConfiguration();
    }

    @Override
    public Module[] getModules() {
        return ModuleManager.getInstance(getProject()).getModules();
    }

    public Module getModule() {
        return lookUp(getModules(), moduleName);
    }

    public void setModule(final Module module) {
        moduleName = module == null ? null : module.getName();
    }

    public String getWorkingDirectoryPath() {
        return PathUtil.getCanonicalPath(getProject().getProjectFile().getParent().getPath());
    }

    public String getContextClassName() {
        return contextClassName == null ? "" : contextClassName;
    }

    public void setContextClassName(final String contextClassName) {
        this.contextClassName = contextClassName;
    }

    public Collection<Module> getValidModules() {
        return asList(ModuleManager.getInstance(getProject()).getModules());
    }

    public String getSpecificationMethodName() {
        return behaviorMethod == null ? "" : behaviorMethod;
    }

    public void setSpecificationMethodName(final String methodName) {
        behaviorMethod = methodName;
    }

    private Module lookUp(final Module[] modules, final String moduleName) {
        for (final Module module : modules) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        return modules.length > 0 ? modules[0] : null;
    }
}
