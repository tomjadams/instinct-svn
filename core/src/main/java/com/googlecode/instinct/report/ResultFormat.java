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

package com.googlecode.instinct.report;

import com.googlecode.instinct.internal.report.BriefResultMessageBuilder;
import com.googlecode.instinct.internal.report.VerboseResultMessageBuilder;
import com.googlecode.instinct.internal.util.Suggest;

@Suggest("Figure out how to test this sucker.")
public enum ResultFormat {
    BRIEF {
        @Override
        public ResultMessageBuilder getMessageBuilder() {
            return new BriefResultMessageBuilder();
        }
    },

    VERBOSE {
        @Override
        public ResultMessageBuilder getMessageBuilder() {
            return new VerboseResultMessageBuilder();
        }
    };

    // SUPPRESS IllegalToken {
    public abstract ResultMessageBuilder getMessageBuilder();
    // } SUPPRESS IllegalToken
}
