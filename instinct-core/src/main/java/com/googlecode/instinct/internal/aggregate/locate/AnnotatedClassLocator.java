package com.googlecode.instinct.internal.aggregate.locate;

import java.io.File;
import java.io.FileFilter;
import com.googlecode.instinct.internal.util.ClassName;

public interface AnnotatedClassLocator {
    ClassName[] locate(File root, final FileFilter filter);
}
