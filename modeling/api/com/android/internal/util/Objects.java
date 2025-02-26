/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 * 
 * 
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package com.android.internal.util;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.Arrays;

public class Objects {

    /**
     * Determines whether two possibly-null objects are equal. Returns:
     *
     * <ul>
     * <li>{@code true} if {@code a} and {@code b} are both null.
     * <li>{@code true} if {@code a} and {@code b} are both non-null and they are
     *     equal according to {@link Object#equals(Object)}.
     * <li>{@code false} in all other situations.
     * </ul>
     *
     * <p>This assumes that any non-null objects passed to this function conform
     * to the {@code equals()} contract.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:46.286 -0500", hash_original_method = "F296275000E1AA53C7B464F5FDA7A4B5", hash_generated_method = "F1FE59F254185FB0421C417ADE74E86C")
    
public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /**
     * Generates a hash code for multiple values. The hash code is generated by
     * calling {@link Arrays#hashCode(Object[])}.
     *
     * <p>This is useful for implementing {@link Object#hashCode()}. For example,
     * in an object that has three properties, {@code x}, {@code y}, and
     * {@code z}, one could write:
     * <pre>
     * public int hashCode() {
     *   return Objects.hashCode(getX(), getY(), getZ());
     * }</pre>
     *
     * <b>Warning</b>: When a single object is supplied, the returned hash code
     * does not equal the hash code of that object.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:46.288 -0500", hash_original_method = "5906F045128166A9E56FF6FDE6E49CCD", hash_generated_method = "88A9EA0CDDBC5F37AABAE9F35B6F717C")
    
public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:26.783 -0400", hash_original_method = "A2A31F0BD1F39D2A345E74134749A5E1", hash_generated_method = "A2A31F0BD1F39D2A345E74134749A5E1")
    public Objects ()
    {
        //Synthesized constructor
    }
    
}

