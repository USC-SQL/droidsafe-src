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
 * This file is part of TagSoup and is Copyright 2002-2008 by John Cowan.
 *
 * TagSoup is licensed under the Apache License,
 * Version 2.0.  You may obtain a copy of this license at
 * http: *www.apache.org/licenses/LICENSE-2.0 .  You may also have
 * additional legal rights not granted by this license.
 *
 * TagSoup is distributed in the hope that it will be useful, but
 * unless required by applicable law or agreed to in writing, TagSoup
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied; not even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */ 


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/

// Interface to objects that translate InputStreams to Readers by auto-detection

package org.ccil.cowan.tagsoup;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.InputStream;
import java.io.Reader;

public interface AutoDetector {

	@DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    public Reader autoDetectingReader(InputStream i);

	}
