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
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.xml.sax.helpers;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DefaultHandler implements EntityResolver, DTDHandler, ContentHandler, ErrorHandler {
    
    @DSComment("constructor")
    @DSSafe(DSCat.XML)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:44.326 -0400", hash_original_method = "C916F3EC761E0C154A1A9AFCDD294D40", hash_generated_method = "C916F3EC761E0C154A1A9AFCDD294D40")
    public DefaultHandler ()
    {
        //Synthesized constructor
    }

    ////////////////////////////////////////////////////////////////////
    // Default implementation of the EntityResolver interface.
    ////////////////////////////////////////////////////////////////////

    /**
     * Resolve an external entity.
     *
     * <p>Always return null, so that the parser will use the system
     * identifier provided in the XML document.  This method implements
     * the SAX default behaviour: application writers can override it
     * in a subclass to do special translations such as catalog lookups
     * or URI redirection.</p>
     *
     * @param publicId The public identifer, or null if none is
     *                 available.
     * @param systemId The system identifier provided in the XML
     *                 document.
     * @return The new input source, or null to require the
     *         default behaviour.
     * @exception java.io.IOException If there is an error setting
     *            up the new input source.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.EntityResolver#resolveEntity
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.132 -0500", hash_original_method = "9EAB8438172711193BA945D78BF156AE", hash_generated_method = "6079F24DCCF84F8A2034CB9B0484C6F5")
    
public InputSource resolveEntity (String publicId, String systemId)
    throws IOException, SAXException
    {
    return null;
    }

    ////////////////////////////////////////////////////////////////////
    // Default implementation of DTDHandler interface.
    ////////////////////////////////////////////////////////////////////

    /**
     * Receive notification of a notation declaration.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass if they wish to keep track of the notations
     * declared in a document.</p>
     *
     * @param name The notation name.
     * @param publicId The notation public identifier, or null if not
     *                 available.
     * @param systemId The notation system identifier.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.DTDHandler#notationDecl
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.135 -0500", hash_original_method = "E183481C16DEC5D87834DCD30C3A4B47", hash_generated_method = "ABC3A129BCCCAFA25078A7742DEAC82A")
    
public void notationDecl (String name, String publicId, String systemId)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of an unparsed entity declaration.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to keep track of the unparsed entities
     * declared in a document.</p>
     *
     * @param name The entity name.
     * @param publicId The entity public identifier, or null if not
     *                 available.
     * @param systemId The entity system identifier.
     * @param notationName The name of the associated notation.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.DTDHandler#unparsedEntityDecl
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.137 -0500", hash_original_method = "A4218B7445EF8022439A8D7DC57F2FF7", hash_generated_method = "39EA436AC2CA313C3173E04B950DFC15")
    
public void unparsedEntityDecl (String name, String publicId,
                    String systemId, String notationName)
    throws SAXException
    {
    // no op
    }

    ////////////////////////////////////////////////////////////////////
    // Default implementation of ContentHandler interface.
    ////////////////////////////////////////////////////////////////////

    /**
     * Receive a Locator object for document events.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass if they wish to store the locator for use
     * with other document events.</p>
     *
     * @param locator A locator for all SAX document events.
     * @see org.xml.sax.ContentHandler#setDocumentLocator
     * @see org.xml.sax.Locator
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.140 -0500", hash_original_method = "764EC710ED4FDFB68C9E6FE9ED249649", hash_generated_method = "9BA8507971680553E082ED561F93A7C5")
    
public void setDocumentLocator (Locator locator)
    {
    // no op
    }

    /**
     * Receive notification of the beginning of the document.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the beginning
     * of a document (such as allocating the root node of a tree or
     * creating an output file).</p>
     *
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#startDocument
     */
    @DSComment("No op default handler")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.142 -0500", hash_original_method = "FAA725B72A2ADC391C781CAB49B849AD", hash_generated_method = "3955D568D23D91849FC2AFF57016EC23")
    @DSVerified 
public void startDocument ()
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of the end of the document.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end
     * of a document (such as finalising a tree or closing an output
     * file).</p>
     *
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#endDocument
     */
    @DSComment("No op default handler")
    @DSSafe(DSCat.XML)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.144 -0500", hash_original_method = "08AD4CBC251CA96B103DE58FB6AA2921", hash_generated_method = "2E393C8D7D82B97D34D7D61966B266D5")
    
public void endDocument ()
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of the start of a Namespace mapping.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each Namespace prefix scope (such as storing the prefix mapping).</p>
     *
     * @param prefix The Namespace prefix being declared.
     * @param uri The Namespace URI mapped to the prefix.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#startPrefixMapping
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.147 -0500", hash_original_method = "9A8DBFCDD6673580DBBCEDB8AE69733A", hash_generated_method = "E318F5A382F6DD3A6D9B9ED0A805C948")
    
public void startPrefixMapping (String prefix, String uri)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of the end of a Namespace mapping.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each prefix mapping.</p>
     *
     * @param prefix The Namespace prefix being declared.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#endPrefixMapping
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.149 -0500", hash_original_method = "0E90E85A66154559EA3C98CC7177C34F", hash_generated_method = "12507F578F76F1D4B4C4275405456B5F")
    
public void endPrefixMapping (String prefix)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of the start of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.151 -0500", hash_original_method = "4370BBADFC324ACBBC533D016E5331B9", hash_generated_method = "A43BCFB3590903ED462CCADA0953A76D")
    @DSVerified
    @DSSafe(DSCat.XML)
public void startElement (String uri, String localName,
                  String qName, Attributes attributes)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#endElement
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.153 -0500", hash_original_method = "D1BB65E44FB06EB911D3CDAFED8D37CD", hash_generated_method = "0BCE982FF5852BF7039A7416E6F1C03A")
    @DSVerified
    @DSSafe(DSCat.XML)
public void endElement (String uri, String localName, String qName)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#characters
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.156 -0500", hash_original_method = "62C2434D460BC8554CF92975C4745DF3", hash_generated_method = "D16272D013A1528940CEA00C0F124898")
    @DSVerified
    @DSSafe(DSCat.XML)
public void characters (char ch[], int start, int length)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of ignorable whitespace in element content.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of ignorable
     * whitespace (such as adding data to a node or buffer, or printing
     * it to a file).</p>
     *
     * @param ch The whitespace characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#ignorableWhitespace
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.158 -0500", hash_original_method = "9049C36C2683070F72629A8BA1D4C193", hash_generated_method = "D8858A88BA20CC0A6A20C0B768D5BE33")
    @DSVerified
    @DSSafe(DSCat.SAFE_OTHERS)
public void ignorableWhitespace (char ch[], int start, int length)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of a processing instruction.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions for each
     * processing instruction, such as setting status variables or
     * invoking other methods.</p>
     *
     * @param target The processing instruction target.
     * @param data The processing instruction data, or null if
     *             none is supplied.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#processingInstruction
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.161 -0500", hash_original_method = "D7C64873BD0FC0A0449628F53B089507", hash_generated_method = "E7A100FD0C93F22A1CE30CA6639AC7DE")
    @DSVerified
    @DSSafe(DSCat.SAFE_OTHERS)
public void processingInstruction (String target, String data)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of a skipped entity.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions for each
     * processing instruction, such as setting status variables or
     * invoking other methods.</p>
     *
     * @param name The name of the skipped entity.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ContentHandler#processingInstruction
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.163 -0500", hash_original_method = "1616136BE44E483876717F8A446FD8E6", hash_generated_method = "5C21F38AD3AF8C855A3835FBFBE5B603")
    
public void skippedEntity (String name)
    throws SAXException
    {
    // no op
    }

    ////////////////////////////////////////////////////////////////////
    // Default implementation of the ErrorHandler interface.
    ////////////////////////////////////////////////////////////////////

    /**
     * Receive notification of a parser warning.
     *
     * <p>The default implementation does nothing.  Application writers
     * may override this method in a subclass to take specific actions
     * for each warning, such as inserting the message in a log file or
     * printing it to the console.</p>
     *
     * @param e The warning information encoded as an exception.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ErrorHandler#warning
     * @see org.xml.sax.SAXParseException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.165 -0500", hash_original_method = "2057473EBAC4D7FF54AEDE0043F741F4", hash_generated_method = "30BA8887E7A010663223CDC747054835")
    
public void warning (SAXParseException e)
    throws SAXException
    {
    // no op
    }

    /**
     * Receive notification of a recoverable parser error.
     *
     * <p>The default implementation does nothing.  Application writers
     * may override this method in a subclass to take specific actions
     * for each error, such as inserting the message in a log file or
     * printing it to the console.</p>
     *
     * @param e The warning information encoded as an exception.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ErrorHandler#warning
     * @see org.xml.sax.SAXParseException
     */
    @DSComment("No op default handler")
    @DSSafe(DSCat.XML)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.168 -0500", hash_original_method = "3C476190220BFC4330D41CB0A7E5D96B", hash_generated_method = "CACE9FD48F5BBAC46481DE007E53B3CB")
    
public void error (SAXParseException e)
    throws SAXException
    {
    // no op
    }

    /**
     * Report a fatal XML parsing error.
     *
     * <p>The default implementation throws a SAXParseException.
     * Application writers may override this method in a subclass if
     * they need to take specific actions for each fatal error (such as
     * collecting all of the errors into a single report): in any case,
     * the application must stop all regular processing when this
     * method is invoked, since the document is no longer reliable, and
     * the parser may no longer report parsing events.</p>
     *
     * @param e The error information encoded as an exception.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.ErrorHandler#fatalError
     * @see org.xml.sax.SAXParseException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:49.170 -0500", hash_original_method = "3CBD306431C0846BE7A7FBEF3B9920DA", hash_generated_method = "8BE9290E5A78003CD3400FC7458B6660")
    
public void fatalError (SAXParseException e)
    throws SAXException
    {
    throw e;
    }
    
}

