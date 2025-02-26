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
 * Copyright 2001-2005 The Apache Software Foundation
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

/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.commons.net;
import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.util.EventListener;

/***
 * There exists a large class of IETF protocols that work by sending an
 * ASCII text command and arguments to a server, and then receiving an
 * ASCII text reply.  For debugging and other purposes, it is extremely
 * useful to log or keep track of the contents of the protocol messages.
 * The ProtocolCommandListener interface coupled with the
 * {@link ProtocolCommandEvent} class facilitate this process.
 * <p>
 * To receive ProtocolCommandEvents, you merely implement the
 * ProtocolCommandListener interface and register the class as a listener
 * with a ProtocolCommandEvent source such as
 * {@link org.apache.commons.net.ftp.FTPClient}.
 * <p>
 * <p>
 * @see ProtocolCommandEvent
 * @see ProtocolCommandSupport
 * @author Daniel F. Savarese
 ***/

public interface ProtocolCommandListener extends EventListener
{

    /***
     * This method is invoked by a ProtocolCommandEvent source after
     * sending a protocol command to a server.
     * <p>
     * @param event The ProtocolCommandEvent fired.
     ***/
    @DSVerified("Called in ProtocolCommandSupport")
    @DSSafe(DSCat.ANDROID_CALLBACK)
    public void protocolCommandSent(ProtocolCommandEvent event);

    /***
     * This method is invoked by a ProtocolCommandEvent source after
     * receiving a reply from a server.
     * <p>
     * @param event The ProtocolCommandEvent fired.
     ***/

    @DSVerified("Called in ProtocolCommandSupport")
    @DSSafe(DSCat.ANDROID_CALLBACK)
    public void protocolReplyReceived(ProtocolCommandEvent event);

}
