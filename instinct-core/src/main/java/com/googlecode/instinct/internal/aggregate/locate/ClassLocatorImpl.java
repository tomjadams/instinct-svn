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

package com.googlecode.instinct.internal.aggregate.locate;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.googlecode.instinct.internal.util.JavaClassName;
import com.googlecode.instinct.internal.util.JavaClassNameImpl;
import static com.googlecode.instinct.internal.util.ParamChecker.checkNotNull;
import com.googlecode.instinct.internal.util.Suggest;

@Suggest("Test drive this class")
public final class ClassLocatorImpl implements ClassLocator {
    private final Comparator<File> comparator = new FileNameComparator();

    public JavaClassName[] locate(final File root, final FileFilter filter) {
        checkNotNull(root, filter);
        final File[] files = sortedDeepLocate(root, filter);
        return toClasses(root, files);
    }

    private File[] sortedDeepLocate(final File root, final FileFilter filter) {
        final List<File> result = new ArrayList<File>();
        recursiveLocate(result, root, filter);
        sort(result);
        return result.toArray(new File[result.size()]);
    }

    private void recursiveLocate(final List<File> result, final File searchBase, final FileFilter filter) {
        ensureDir(searchBase);
        final File[] subdirs = getSubdirectories(searchBase);
        for (final File subdir : subdirs) {
            recursiveLocate(result, subdir, filter);
        }
        findMatchingClasses(result, searchBase, filter);
    }

    private File[] getSubdirectories(final File dir) {
        final FileFilter filter = new DirectoryFilter();
        return dir.listFiles(filter);
    }

    private JavaClassName[] toClasses(final File root, final File[] files) {
        final JavaClassName[] result = new JavaClassNameImpl[files.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new JavaClassNameImpl(root, files[i]);
        }
        return result;
    }

    private void findMatchingClasses(final List<File> result, final File dir, final FileFilter filter) {
        final List<File> list = asList(dir.listFiles(filter));
        result.addAll(list);
    }

    private void sort(final List<File> files) {
        Collections.sort(files, comparator);
    }

    private void ensureDir(final File path) {
        if (!path.exists()) {
            throw new IllegalArgumentException(path + " " + "does not exist");
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException(path + " " + "must be a directory.");
        }
    }
}
