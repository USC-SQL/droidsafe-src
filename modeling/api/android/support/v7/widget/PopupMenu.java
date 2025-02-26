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


package android.support.v7.widget;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.content.Context;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Static library support version of the framework's {@link android.widget.PopupMenu}.
 * Used to write apps that run on platforms prior to Android 3.0.  When running
 * on Android 3.0 or above, this implementation is still used; it does not try
 * to switch to the framework's implementation. See the framework SDK
 * documentation for a class overview.
 */
public class PopupMenu implements MenuBuilder.Callback, MenuPresenter.Callback {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.642 -0400", hash_original_field = "B997E37019471EC8FC5B98148C7A8AD7", hash_generated_field = "C458E619396054F78BC926FB81B4386D")

    private Context mContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.644 -0400", hash_original_field = "E0FA7D9C40725579D8730D3DABE74643", hash_generated_field = "D444FB62DCB4C4F94C883BDD2D0F7012")

    private MenuBuilder mMenu;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.646 -0400", hash_original_field = "86801A6B1E54D7468FFA612E6FAA6E5E", hash_generated_field = "B5073E9D1328D8D85272F118048A8168")

    private View mAnchor;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.648 -0400", hash_original_field = "009134EDEA1B45051E0F5C702D30BB5B", hash_generated_field = "06BEDB7656EEE290CD919822CE5BD94B")

    private MenuPopupHelper mPopup;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.650 -0400", hash_original_field = "3E22CA76E708DFE9F85C28FAF8834130", hash_generated_field = "9703FDB31ADAFF0214804DE9893B785D")

    private OnMenuItemClickListener mMenuItemClickListener;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.652 -0400", hash_original_field = "8FFF3740C31C0B684496596E84733DB4", hash_generated_field = "5BB48C56597239A71FC51ED477A581A9")

    private OnDismissListener mDismissListener;

    /**
     * Callback interface used to notify the application that the menu has closed.
     */
    public interface OnDismissListener {
        /**
         * Called when the associated menu has been dismissed.
         *
         * @param menu The PopupMenu that was dismissed.
         */
        public void onDismiss(PopupMenu menu);
    }

    /**
     * Construct a new PopupMenu.
     *
     * @param context Context for the PopupMenu.
     * @param anchor Anchor view for this popup. The popup will appear below the anchor if there
     *               is room, or above it if there is not.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.657 -0400", hash_original_method = "A76A00F33E7790D7B562E67F217733EF", hash_generated_method = "89F7BAF1F7FCB127DCCC0B9122B15177")
        
public PopupMenu(Context context, View anchor) {
        mContext = context;
        mMenu = new MenuBuilder(context);
        mMenu.setCallback(this);
        mAnchor = anchor;
        mPopup = new MenuPopupHelper(context, mMenu, anchor);
        mPopup.setCallback(this);
    }

    /**
     * @return the {@link Menu} associated with this popup. Populate the returned Menu with
     * items before calling {@link #show()}.
     *
     * @see #show()
     * @see #getMenuInflater()
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.659 -0400", hash_original_method = "B2B8DF93D41CD214F77DE42BB625BB9F", hash_generated_method = "DCDBC063D5B5258CB417C095AA16E240")
        
public Menu getMenu() {
        return mMenu;
    }

    /**
     * @return a {@link MenuInflater} that can be used to inflate menu items from XML into the
     * menu returned by {@link #getMenu()}.
     *
     * @see #getMenu()
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.662 -0400", hash_original_method = "DC5914476C6059640110F9C752C58A42", hash_generated_method = "5281F3419711F62B2291D1BB99E185DC")
        
public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(mContext);
    }

    /**
     * Inflate a menu resource into this PopupMenu. This is equivalent to calling
     * popupMenu.getMenuInflater().inflate(menuRes, popupMenu.getMenu()).
     * @param menuRes Menu resource to inflate
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.664 -0400", hash_original_method = "B5DA97073214511A8C4FCCDCD0202705", hash_generated_method = "BBC392AB846AD249328566D60A8DB052")
        
public void inflate(int menuRes) {
        getMenuInflater().inflate(menuRes, mMenu);
    }

    /**
     * Show the menu popup anchored to the view specified during construction.
     * @see #dismiss()
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.666 -0400", hash_original_method = "2CE72B1B8EF107541CE02DFAE137C08B", hash_generated_method = "2AC55B0840C7CB20A1C02EC9E675B338")
        
public void show() {
        mPopup.show();
    }

    /**
     * Dismiss the menu popup.
     * @see #show()
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.668 -0400", hash_original_method = "C5794CDCFC0B9690306B70531BF35214", hash_generated_method = "1102EF4776C4D0FFA8693708B5945D92")
        
public void dismiss() {
        mPopup.dismiss();
    }

    /**
     * Set a listener that will be notified when the user selects an item from the menu.
     *
     * @param listener Listener to notify
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.671 -0400", hash_original_method = "28F3665ED7463B0146351549FFC48231", hash_generated_method = "9AA69019FACE4666B4C595CCED7CCE70")
        
public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        mMenuItemClickListener = listener;
    }

    /**
     * Set a listener that will be notified when this menu is dismissed.
     *
     * @param listener Listener to notify
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.673 -0400", hash_original_method = "C95583275768EA3036A4E9EDA3D06AF7", hash_generated_method = "51920096DC51EBF6574C50318A80DE67")
        
public void setOnDismissListener(OnDismissListener listener) {
        mDismissListener = listener;
    }

    /**
     * @hide
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.675 -0400", hash_original_method = "930C91C797D93F5ADF8BC528FF612F76", hash_generated_method = "C455005799C8DD0B355D773D8EE59884")
        
public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
        if (mMenuItemClickListener != null) {
            return mMenuItemClickListener.onMenuItemClick(item);
        }
        return false;
    }

    /**
     * @hide
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.677 -0400", hash_original_method = "86695013FCDBC0CB589820CBDB9A26C8", hash_generated_method = "DCD399C7DA41B413DC12E99156A0EE27")
        
public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        if (mDismissListener != null) {
            mDismissListener.onDismiss(this);
        }
    }

    /**
     * @hide
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.680 -0400", hash_original_method = "B948BC656B334434A5ABFAFD3C309E66", hash_generated_method = "A55AC62D984BED9F419AE79EBC42EAE5")
        
public boolean onOpenSubMenu(MenuBuilder subMenu) {
        if (subMenu == null) return false;

        if (!subMenu.hasVisibleItems()) {
            return true;
        }

        // Current menu will be dismissed by the normal helper, submenu will be shown in its place.
        new MenuPopupHelper(mContext, subMenu, mAnchor).show();
        return true;
    }

    /**
     * @hide
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.682 -0400", hash_original_method = "6B692B27380EC4C10CDFFF60DC2D0482", hash_generated_method = "CCFD8A6AE905FBA10074FF834353817F")
        
public void onCloseSubMenu(SubMenuBuilder menu) {
    }

    /**
     * @hide
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:50.684 -0400", hash_original_method = "BD3281B75359F57929C0B7D8E3BC0065", hash_generated_method = "C5DDE5ACC8940AA6876CE15F613929AA")
        
public void onMenuModeChange(MenuBuilder menu) {
    }

    /**
     * Interface responsible for receiving menu item click events if the items themselves
     * do not have individual item click listeners.
     */
    public interface OnMenuItemClickListener {
        /**
         * This method will be invoked when a menu item is clicked if the item itself did
         * not already handle the event.
         *
         * @param item {@link MenuItem} that was clicked
         * @return <code>true</code> if the event was handled, <code>false</code> otherwise.
         */
        public boolean onMenuItemClick(MenuItem item);
    }
}
