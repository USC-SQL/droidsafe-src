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
 * Copyright (C) 2013 The Android Open Source Project
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


package android.print;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;

/**
 * Base class that provides the content of a document to be printed.
 *
 * <h3>Lifecycle</h3>
 * <p>
 * <ul>
 * <li>
 * Initially, you will receive a call to {@link #onStart()}. This callback
 * can be used to allocate resources.
 * </li>
 * <li>
 * Next, you will get one or more calls to {@link #onLayout(PrintAttributes,
 * PrintAttributes, CancellationSignal, LayoutResultCallback, Bundle)} to
 * inform you that the print attributes (page size, density, etc) changed
 * giving you an opportunity to layout the content to match the new constraints.
 * </li>
 * <li>
 * After every call to {@link #onLayout(PrintAttributes, PrintAttributes,
 * CancellationSignal, LayoutResultCallback, Bundle)}, you <strong>may</strong> get
 * a call to {@link #onWrite(PageRange[], ParcelFileDescriptor, CancellationSignal,
 * WriteResultCallback)} asking you to write a PDF file with the content for
 * specific pages.
 * </li>
 * <li>
 * Finally, you will receive a call to {@link #onFinish()}. You can use this
 * callback to release resources allocated in {@link #onStart()}.
 * </li>
 * </ul>
 * <p>
 * The {@link #onStart()} callback is always the first call you will receive and
 * is useful for doing one time setup or resource allocation before printing. You
 * will not receive a subsequent call here.
 * </p>
 * <p>
 * The {@link #onLayout(PrintAttributes, PrintAttributes, CancellationSignal,
 * LayoutResultCallback, Bundle)} callback requires that you layout the content
 * based on the current {@link PrintAttributes}. The execution of this method is
 * not considered completed until you invoke one of the methods on the passed in
 * callback instance. Hence, you will not receive a subsequent call to any other
 * method of this class until the execution of this method is complete by invoking
 * one of the callback methods.
 * </p>
 * <p>
 * The {@link #onWrite(PageRange[], ParcelFileDescriptor, CancellationSignal,
 * WriteResultCallback)} requires that you render and write the content of some
 * pages to the provided destination. The execution of this method is not
 * considered complete until you invoke one of the methods on the passed in
 * callback instance. Hence, you will not receive a subsequent call to any other
 * method of this class until the execution of this method is complete by invoking
 * one of the callback methods. You will never receive a sequence of one or more
 * calls to this method without a previous call to {@link #onLayout(PrintAttributes,
 * PrintAttributes, CancellationSignal, LayoutResultCallback, Bundle)}.
 * </p>
 * <p>
 * The {@link #onFinish()} callback is always the last call you will receive and
 * is useful for doing one time cleanup or resource deallocation after printing.
 * You will not receive a subsequent call here.
 * </p>
 * </p>
 * <h3>Implementation</h3>
 * <p>
 * The APIs defined in this class are designed to enable doing part or all
 * of the work on an arbitrary thread. For example, if the printed content
 * does not depend on the UI state, i.e. on what is shown on the screen, then
 * you can offload the entire work on a dedicated thread, thus making your
 * application interactive while the print work is being performed. Note that
 * while your activity is covered by the system print UI and a user cannot
 * interact with it, doing the printing work on the main application thread
 * may affect the performance of your other application components as they
 * are also executed on that thread.
 * </p>
 * <p>
 * You can also do work on different threads, for example if you print UI
 * content, you can handle {@link #onStart()} and {@link #onLayout(PrintAttributes,
 * PrintAttributes, CancellationSignal, LayoutResultCallback, Bundle)} on
 * the UI thread (assuming onStart initializes resources needed for layout).
 * This will ensure that the UI does not change while you are laying out the
 * printed content. Then you can handle {@link #onWrite(PageRange[], ParcelFileDescriptor,
 * CancellationSignal, WriteResultCallback)} and {@link #onFinish()} on another
 * thread. This will ensure that the main thread is busy for a minimal amount of
 * time. Also this assumes that you will generate the printed content in
 * {@link #onLayout(PrintAttributes, PrintAttributes, CancellationSignal,
 * LayoutResultCallback, Bundle)} which is not mandatory. If you use multiple
 * threads, you are responsible for proper synchronization.
 * </p>
 */
public abstract class PrintDocumentAdapter {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.653 -0400", hash_original_field = "BD3B028644C78DAC7A4EA74C56313701", hash_generated_field = "877889578E96BC0E7624296F818872BD")

    public static final String EXTRA_PRINT_PREVIEW = "EXTRA_PRINT_PREVIEW";

    /**
     * Called when printing starts. You can use this callback to allocate
     * resources. This method is invoked on the main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.654 -0400", hash_original_method = "0451E38101EC2D69951E664FB36904F0", hash_generated_method = "483DC96BA5F85C191A2841286F4BF20F")
    
public void onStart() {
        /* do nothing - stub */
    }

    /**
     * Called when the print attributes (page size, density, etc) changed
     * giving you a chance to layout the content such that it matches the
     * new constraints. This method is invoked on the main thread.
     * <p>
     * After you are done laying out, you <strong>must</strong> invoke: {@link
     * LayoutResultCallback#onLayoutFinished(PrintDocumentInfo, boolean)} with
     * the last argument <code>true</code> or <code>false</code> depending on
     * whether the layout changed the content or not, respectively; or {@link
     * LayoutResultCallback#onLayoutFailed(CharSequence)}, if an error occurred;
     * or {@link LayoutResultCallback#onLayoutCancelled()} if layout was
     * cancelled in a response to a cancellation request via the passed in
     * {@link CancellationSignal}. Note that you <strong>must</strong> call one of
     * the methods of the given callback for this method to be considered complete
     * which is you will not receive any calls to this adapter until the current
     * layout operation is complete by invoking a method on the callback instance.
     * The callback methods can be invoked from an arbitrary thread.
     * </p>
     * <p>
     * One of the arguments passed to this method is a {@link CancellationSignal}
     * which is used to propagate requests from the system to your application for
     * canceling the current layout operation. For example, a cancellation may be
     * requested if the user changes a print option that may affect layout while
     * you are performing a layout operation. In such a case the system will make
     * an attempt to cancel the current layout as another one will have to be performed.
     * Typically, you should register a cancellation callback in the cancellation
     * signal. The cancellation callback <strong>will not</strong> be made on the
     * main thread and can be registered as follows:
     * </p>
     * <pre>
     * cancellationSignal.setOnCancelListener(new OnCancelListener() {
     *     &#064;Override
     *     public void onCancel() {
     *         // Cancel layout
     *     }
     * });
     * </pre>
     * <p>
     * <strong>Note:</strong> If the content is large and a layout will be
     * performed, it is a good practice to schedule the work on a dedicated
     * thread and register an observer in the provided {@link
     * CancellationSignal} upon invocation of which you should stop the
     * layout.
     * </p>
     *
     * @param oldAttributes The old print attributes.
     * @param newAttributes The new print attributes.
     * @param cancellationSignal Signal for observing cancel layout requests.
     * @param callback Callback to inform the system for the layout result.
     * @param extras Additional information about how to layout the content.
     *
     * @see LayoutResultCallback
     * @see CancellationSignal
     * @see #EXTRA_PRINT_PREVIEW
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.655 -0400", hash_original_method = "DFC36F014132CE56E5ABD442B9A4E495", hash_generated_method = "44E5115750903A36F5D063AF703DB842")
    
public abstract void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
            CancellationSignal cancellationSignal, LayoutResultCallback callback,
            Bundle extras);

    /**
     * Called when specific pages of the content should be written in the
     * form of a PDF file to the given file descriptor. This method is invoked
     * on the main thread.
     *<p>
     * After you are done writing, you should close the file descriptor and
     * invoke {@link WriteResultCallback#onWriteFinished(PageRange[])}, if writing
     * completed successfully; or {@link WriteResultCallback#onWriteFailed(
     * CharSequence)}, if an error occurred; or {@link WriteResultCallback#onWriteCancelled()},
     * if writing was cancelled in a response to a cancellation request via the passed
     * in {@link CancellationSignal}. Note that you <strong>must</strong> call one of
     * the methods of the given callback for this method to be considered complete which
     * is you will not receive any calls to this adapter until the current write
     * operation is complete by invoking a method on the callback instance. The callback
     * methods can be invoked from an arbitrary thread.
     * </p>
     * <p>
     * One of the arguments passed to this method is a {@link CancellationSignal}
     * which is used to propagate requests from the system to your application for
     * canceling the current write operation. For example, a cancellation may be
     * requested if the user changes a print option that may affect layout while
     * you are performing a write operation. In such a case the system will make
     * an attempt to cancel the current write as a layout will have to be performed
     * which then may be followed by a write. Typically, you should register a
     * cancellation callback in the cancellation signal. The cancellation callback
     * <strong>will not</strong> be made on the main thread and can be registered
     * as follows:
     * </p>
     * <pre>
     * cancellationSignal.setOnCancelListener(new OnCancelListener() {
     *     &#064;Override
     *     public void onCancel() {
     *         // Cancel write
     *     }
     * });
     * </pre>
     * <p>
     * <strong>Note:</strong> If the printed content is large, it is a good
     * practice to schedule writing it on a dedicated thread and register an
     * observer in the provided {@link CancellationSignal} upon invocation of
     * which you should stop writing.
     * </p>
     *
     * @param pages The pages whose content to print - non-overlapping in ascending order.
     * @param destination The destination file descriptor to which to write.
     * @param cancellationSignal Signal for observing cancel writing requests.
     * @param callback Callback to inform the system for the write result.
     *
     * @see WriteResultCallback
     * @see CancellationSignal
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.656 -0400", hash_original_method = "33746656152B4D848F02F741F3952B39", hash_generated_method = "1C2941C8EE4D326422A1EE36B59E4DFB")
    
public abstract void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
            CancellationSignal cancellationSignal, WriteResultCallback callback);

    /**
     * Called when printing finishes. You can use this callback to release
     * resources acquired in {@link #onStart()}. This method is invoked on
     * the main thread.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.657 -0400", hash_original_method = "9ED06CCFCFACDB9CA8B363E3B5FDDEA1", hash_generated_method = "ACB5767BBD2A92BCDA88E2F209857C10")
    
public void onFinish() {
        /* do nothing - stub */
    }

    /**
     * Base class for implementing a callback for the result of {@link
     * PrintDocumentAdapter#onWrite(PageRange[], ParcelFileDescriptor, CancellationSignal,
     * WriteResultCallback)}.
     */
    public static abstract class WriteResultCallback {

        /**
         * @hide
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.658 -0400", hash_original_method = "CA383B615E2AD90CA9344242AA594CC2", hash_generated_method = "FAC5ED288D27A0EBB6CC876989860939")
        
public WriteResultCallback() {
            /* do nothing - hide constructor */
        }

        /**
         * Notifies that all the data was written.
         *
         * @param pages The pages that were written. Cannot be <code>null</code>
         * or empty.
         */
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.659 -0400", hash_original_method = "67F1BBA01BD092CBBA1437D08F1D0DB0", hash_generated_method = "BB9B69738E2AAB8AEA85C2D71DB5E528")
        
public void onWriteFinished(PageRange[] pages) {
            /* do nothing - stub */
        }

        /**
         * Notifies that an error occurred while writing the data.
         *
         * @param error The <strong>localized</strong> error message.
         * shown to the user. May be <code>null</code> if error is unknown.
         */
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.660 -0400", hash_original_method = "D1C6B77F30DFCE9F26DD38BC0841311D", hash_generated_method = "B5F64E6D6BEAAC9C224EB7647F9B0FB8")
        
public void onWriteFailed(CharSequence error) {
            /* do nothing - stub */
        }

        /**
         * Notifies that write was cancelled as a result of a cancellation request.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.661 -0400", hash_original_method = "BD3FEA1F9BABEFCB8861DF5E68C4E255", hash_generated_method = "7E1C84E190C779E79AA693C8D0952D01")
        
public void onWriteCancelled() {
            /* do nothing - stub */
        }
    }

    /**
     * Base class for implementing a callback for the result of {@link
     * PrintDocumentAdapter#onLayout(PrintAttributes, PrintAttributes,
     * CancellationSignal, LayoutResultCallback, Bundle)}.
     */
    public static abstract class LayoutResultCallback {

        /**
         * @hide
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.663 -0400", hash_original_method = "1F49A6BB9DC63F6BF26CF1049C313C74", hash_generated_method = "7C20DB68A64CC051A32AD7476C91FAD8")
        
public LayoutResultCallback() {
            /* do nothing - hide constructor */
        }

        /**
         * Notifies that the layout finished and whether the content changed.
         *
         * @param info An info object describing the document. Cannot be <code>null</code>.
         * @param changed Whether the layout changed.
         *
         * @see PrintDocumentInfo
         */
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.664 -0400", hash_original_method = "BEF5A08F3B4930C16ABE0638CA15753C", hash_generated_method = "C1950AC7438F5EB63F6434E3D92EF714")
        
public void onLayoutFinished(PrintDocumentInfo info, boolean changed) {
            /* do nothing - stub */
        }

        /**
         * Notifies that an error occurred while laying out the document.
         *
         * @param error The <strong>localized</strong> error message.
         * shown to the user. May be <code>null</code> if error is unknown.
         */
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.666 -0400", hash_original_method = "097BFBD434F8D3342C3F0968773EA509", hash_generated_method = "EF9ADED59D25D772ECDA9E494312A6CF")
        
public void onLayoutFailed(CharSequence error) {
            /* do nothing - stub */
        }

        /**
         * Notifies that layout was cancelled as a result of a cancellation request.
         */
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-05 14:41:22.666 -0400", hash_original_method = "8585989943102480E7ACCEF6AC5446A6", hash_generated_method = "504EC4CD187DDD078C47E074734245B0")
        
public void onLayoutCancelled() {
            /* do nothing - stub */
        }
    }
}
