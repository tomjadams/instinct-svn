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

package com.googlecode.instinct.internal.edge.java.io;

import com.googlecode.instinct.internal.edge.EdgeException;
import java.io.IOException;
import java.io.OutputStream;

public final class OutputStreamEdgeImpl implements OutputStreamEdge {
    private final OutputStream stream;

    public OutputStreamEdgeImpl(final OutputStream stream) {
        this.stream = stream;
    }

    public void write(final byte[] bytes) {
        try {
            stream.write(bytes);
        } catch (IOException e) {
            throw new EdgeException(e);
        }
    }

    public void write(final byte[] b, final int off, final int len) {
        try {
            stream.write(b, off, len);
        } catch (IOException e) {
            throw new EdgeException(e);
        }
    }

    public void flush() {
        try {
            stream.flush();
        } catch (IOException e) {
            throw new EdgeException(e);
        }
    }

    public void close() {
        try {
            stream.close();
        } catch (IOException e) {
            throw new EdgeException(e);
        }
    }
}
