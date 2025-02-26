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
 * Copyright (C) 2008 The Android Open Source Project
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


package android.server;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothInputDevice;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.PowerManager;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

/**
 * @hide
 */
class BluetoothEventLoop {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.714 -0400", hash_original_field = "857A2E574C2A02ECB5A3AF335BD0D0EC", hash_generated_field = "FC9C7F2F4B9A8561BCCE5C8BCC3B9A49")

    private static final String TAG = "BluetoothEventLoop";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.716 -0400", hash_original_field = "B2601CA7445F6BA19FA7884763D82281", hash_generated_field = "1A61763F9CABC9206BB5AE6E570AB8AE")

    private static final boolean DBG = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.735 -0400", hash_original_field = "5171A00CFFD530A9264D0326C10DB67C", hash_generated_field = "7071E0A5239C6A43DE4451C080F33767")

    private static final int EVENT_PAIRING_CONSENT_DELAYED_ACCEPT = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.736 -0400", hash_original_field = "380AFC72E221F3D5071120768D176EEA", hash_generated_field = "6346711E7495EAB79D8ACEA1C3CBA25F")

    private static final int EVENT_AGENT_CANCEL = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.738 -0400", hash_original_field = "E676F7865E3D418A6A3A08146F9BBBE9", hash_generated_field = "BDC0C93D6625802B1DE609428D7DE0F4")

    private static final int CREATE_DEVICE_ALREADY_EXISTS = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.740 -0400", hash_original_field = "343BF4F57DDA02B4B2FA4E706ABD0760", hash_generated_field = "E2190FD30A39A2B8650E5F9B199B4194")

    private static final int CREATE_DEVICE_SUCCESS = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.742 -0400", hash_original_field = "69A63BAB09749E10EFFF3BFD77C6A66C", hash_generated_field = "194B27CC299C8A71F664A6C149CBD8BF")

    private static final int CREATE_DEVICE_FAILED = -1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.744 -0400", hash_original_field = "4DF5D31E376F27F1ED153009F2B9E52D", hash_generated_field = "A458E8B2DE444E0F35989DFD935DFB5E")

    private static final String BLUETOOTH_ADMIN_PERM = android.Manifest.permission.BLUETOOTH_ADMIN;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.746 -0400", hash_original_field = "7D77AF0882759DFB1A72C6B4C36DC3FE", hash_generated_field = "E2769F8C31AF358A24555782DE15D8EA")

    private static final String BLUETOOTH_PERM = android.Manifest.permission.BLUETOOTH;
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.752 -0400", hash_original_method = "1D968F463EF926C42B1E3D543C8A7203", hash_generated_method = "7A97993991630071922A1DE1DEDF4D20")
    
    private static void classInitNative(){
    	//Formerly a native method
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.837 -0400", hash_original_method = "0EBF7673E44AF610ADC3AB53A65BD597", hash_generated_method = "12E4FDCE2D2661CC6DF9F077D2C0BB73")
    
private static void log(String msg) {
        Log.d(TAG, msg);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.717 -0400", hash_original_field = "9D85BF891BBAC903760564D2E61B8CF8", hash_generated_field = "3541F10A37794BA2B05C956071806434")

    private int mNativeData;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.719 -0400", hash_original_field = "8CE20653889294789C714060A391C40F", hash_generated_field = "13B7014E5080CDE4F9CB20E28887F909")

    private Thread mThread;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.720 -0400", hash_original_field = "8C92DC16A5D2F989C0A1D4375841DEF1", hash_generated_field = "651C1B44F0CFBC9CE67518504FC6F4C2")

    private boolean mStarted;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.722 -0400", hash_original_field = "A59EBE90851823C6DA3B212C8BC621F4", hash_generated_field = "C811572F42D5DC8A72840DA7D325CDDE")

    private boolean mInterrupted;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.723 -0400", hash_original_field = "D49B4692FC08C6B5F3D724DFF1E07710", hash_generated_field = "17E9D67255E235A6437DDFB373AB02B1")

    private  HashMap<String, Integer> mPasskeyAgentRequestData;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.725 -0400", hash_original_field = "2E26A6149C81DDAFE0C397BD99A867C5", hash_generated_field = "B7C5648220BBCDB256CB01EFDAB24689")

    private  HashMap<String, Integer> mAuthorizationAgentRequestData;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.726 -0400", hash_original_field = "A159F05F3527228E8E398101D1BCF02A", hash_generated_field = "63FC39FA8AD5AA0E0E655FCBD5956634")

    private  BluetoothService mBluetoothService;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.728 -0400", hash_original_field = "367F0BC6BC216C571A7E906A286477DF", hash_generated_field = "6EAF57CC559AB78B0231BFFF469261B9")

    private  BluetoothAdapter mAdapter;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.729 -0400", hash_original_field = "EEF81FD2220F50269929A3F88D458D6D", hash_generated_field = "259DFB2E37783481F8B510005F863212")

    private  BluetoothAdapterStateMachine mBluetoothState;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.730 -0400", hash_original_field = "DE2EC2C51924DAB398DF50DA26DEFDA6", hash_generated_field = "ED714F6D724A5256FBC8C54A3394EBDF")

    private BluetoothA2dp mA2dp;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.732 -0400", hash_original_field = "B997E37019471EC8FC5B98148C7A8AD7", hash_generated_field = "C458E619396054F78BC926FB81B4386D")

    private  Context mContext;

    static { classInitNative(); }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.733 -0400", hash_original_field = "87068C2348540A5517D7F6F40E0F6E43", hash_generated_field = "11A4B38BB251E202D19EB239828730E5")

    // from remote device when Android is in Suspend state.
    private PowerManager.WakeLock mWakeLock;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.750 -0400", hash_original_field = "EAE3E9EF816D204EE52A0F95F27CD80B", hash_generated_field = "68E185F86B84D9336A20F76054EC74B9")

    private final Handler mHandler = new Handler() {
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 13:06:58.926 -0400", hash_original_method = "CB445434FE3AF3D286489B18A51A294F", hash_generated_method = "DACAC63F2AF46EAEC6F8CF15B11114BA")
        
@Override
        public void handleMessage(Message msg) {
            String address = null;
            switch (msg.what) {
            case EVENT_PAIRING_CONSENT_DELAYED_ACCEPT:
                address = (String)msg.obj;
                if (address != null) {
                    mBluetoothService.setPairingConfirmation(address, true);
                }
                break;
            case EVENT_AGENT_CANCEL:
                // Set the Bond State to BOND_NONE.
                // We always have only 1 device in BONDING state.
                String[] devices = mBluetoothService.listInState(BluetoothDevice.BOND_BONDING);
                if (devices.length == 0) {
                    break;
                } else if (devices.length > 1) {
                    Log.e(TAG, " There is more than one device in the Bonding State");
                    break;
                }
                address = devices[0];
                mBluetoothService.setBondState(address,
                        BluetoothDevice.BOND_NONE,
                        BluetoothDevice.UNBOND_REASON_REMOTE_AUTH_CANCELED);
                break;
            }
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.760 -0400", hash_original_field = "BF06F512DA875337643C01244CF8612E", hash_generated_field = "AE684F1F021734B7EFAC225BB1037B3D")

    private BluetoothProfile.ServiceListener mProfileServiceListener =
        new BluetoothProfile.ServiceListener() {
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 13:06:58.938 -0400", hash_original_method = "602683829F25447D934147DE46B82842", hash_generated_method = "73B023CD5D3AEF1D783800B60BDA9272")
        
public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == BluetoothProfile.A2DP) {
                mA2dp = (BluetoothA2dp) proxy;
            }
        }
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 13:06:58.940 -0400", hash_original_method = "7C5F7AE5AD9783EB8855B4879D8CC670", hash_generated_method = "338FE3620DA78F8FE6853E82C1857E56")
        
public void onServiceDisconnected(int profile) {
            if (profile == BluetoothProfile.A2DP) {
                mA2dp = null;
            }
        }
    };

    /* package */ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.754 -0400", hash_original_method = "9FF1BF0384D7EE094AD2C1B33926DCFB", hash_generated_method = "E45965D91680FBCFC26AF45F7721D3E2")
    
BluetoothEventLoop(Context context, BluetoothAdapter adapter,
                                     BluetoothService bluetoothService,
                                     BluetoothAdapterStateMachine bluetoothState) {
        mBluetoothService = bluetoothService;
        mContext = context;
        mBluetoothState = bluetoothState;
        mPasskeyAgentRequestData = new HashMap<String, Integer>();
        mAuthorizationAgentRequestData = new HashMap<String, Integer>();
        mAdapter = adapter;
        //WakeLock instantiation in BluetoothEventLoop class
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, TAG);
        mWakeLock.setReferenceCounted(false);
        initializeNativeDataNative();
    }

    /*package*/ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.755 -0400", hash_original_method = "492DE4E3F42FA5013C7B6650CE807AF3", hash_generated_method = "492DE4E3F42FA5013C7B6650CE807AF3")
    
void getProfileProxy() {
        mAdapter.getProfileProxy(mContext, mProfileServiceListener, BluetoothProfile.A2DP);
        mAdapter.getProfileProxy(mContext, mProfileServiceListener, BluetoothProfile.INPUT_DEVICE);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.762 -0400", hash_original_method = "3346AD3DB2B9CFF2099981600AF7874D", hash_generated_method = "6323CD735E0014F5A515F3F6B7F52898")
    
protected void finalize() throws Throwable {
        try {
            cleanupNativeDataNative();
        } finally {
            super.finalize();
        }
    }

    /* package */ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.763 -0400", hash_original_method = "2231C561092E4D51CCE8D6EC21FE69BE", hash_generated_method = "2231C561092E4D51CCE8D6EC21FE69BE")
    
HashMap<String, Integer> getPasskeyAgentRequestData() {
        return mPasskeyAgentRequestData;
    }

    /* package */ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.764 -0400", hash_original_method = "B111CFC22C9BFDD49C819EA342453097", hash_generated_method = "B111CFC22C9BFDD49C819EA342453097")
    
HashMap<String, Integer> getAuthorizationAgentRequestData() {
        return mAuthorizationAgentRequestData;
    }

    /* package */ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.765 -0400", hash_original_method = "E272D1DD7CB5F328D70078E618C5A5FB", hash_generated_method = "E272D1DD7CB5F328D70078E618C5A5FB")
    
void start() {

        if (!isEventLoopRunningNative()) {
            if (DBG) log("Starting Event Loop thread");
            startEventLoopNative();
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.767 -0400", hash_original_method = "C6EFACF08AD7480900C77CF8DF6964DE", hash_generated_method = "45FAA915EBAD3C092A11BFCFD6413862")
    
public void stop() {
        if (isEventLoopRunningNative()) {
            if (DBG) log("Stopping Event Loop thread");
            stopEventLoopNative();
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.768 -0400", hash_original_method = "B5808690A0ABE631A6FF405EF914A9C9", hash_generated_method = "022156B028CC6C651830445FC60BB5C7")
    
public boolean isEventLoopRunning() {
        return isEventLoopRunningNative();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.770 -0400", hash_original_method = "EF1EA25288450220D0BC53D541DBFF2E", hash_generated_method = "75E0D6429BF7D67ACA5A331012EE3B19")
    
private void addDevice(String address, String[] properties) {
        BluetoothDeviceProperties deviceProperties =
                mBluetoothService.getDeviceProperties();
        deviceProperties.addProperties(address, properties);
        String rssi = deviceProperties.getProperty(address, "RSSI");
        String classValue = deviceProperties.getProperty(address, "Class");
        String name = deviceProperties.getProperty(address, "Name");
        short rssiValue;
        // For incoming connections, we don't get the RSSI value. Use a default of MIN_VALUE.
        // If we accept the pairing, we will automatically show it at the top of the list.
        if (rssi != null) {
            rssiValue = (short)Integer.valueOf(rssi).intValue();
        } else {
            rssiValue = Short.MIN_VALUE;
        }
        if (classValue != null) {
            Intent intent = new Intent(BluetoothDevice.ACTION_FOUND);
            intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
            intent.putExtra(BluetoothDevice.EXTRA_CLASS,
                    new BluetoothClass(Integer.valueOf(classValue)));
            intent.putExtra(BluetoothDevice.EXTRA_RSSI, rssiValue);
            intent.putExtra(BluetoothDevice.EXTRA_NAME, name);

            mContext.sendBroadcast(intent, BLUETOOTH_PERM);
        } else {
            log ("ClassValue: " + classValue + " for remote device: " + address + " is null");
        }
    }

    /**
     * Called by native code on a DeviceFound signal from org.bluez.Adapter.
     *
     * @param address the MAC address of the new device
     * @param properties an array of property keys and value strings
     *
     * @see BluetoothDeviceProperties#addProperties(String, String[])
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.772 -0400", hash_original_method = "5049EBEAA2F4DC69419DE079E9EA2F0C", hash_generated_method = "D02592BF8EAC32EFB60770A3A5B9D213")
    
private void onDeviceFound(String address, String[] properties) {
        if (properties == null) {
            Log.e(TAG, "ERROR: Remote device properties are null");
            return;
        }
        addDevice(address, properties);
    }

    /**
     * Called by native code on a DeviceDisappeared signal from
     * org.bluez.Adapter.
     *
     * @param address the MAC address of the disappeared device
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.773 -0400", hash_original_method = "B16C5D9D64AA8FA72E850F62C51D59E8", hash_generated_method = "AA82A7700C9928612B83A82845811489")
    
private void onDeviceDisappeared(String address) {
        Intent intent = new Intent(BluetoothDevice.ACTION_DISAPPEARED);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        mContext.sendBroadcast(intent, BLUETOOTH_PERM);
    }

    /**
     * Called by native code on a DisconnectRequested signal from
     * org.bluez.Device.
     *
     * @param deviceObjectPath the object path for the disconnecting device
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.775 -0400", hash_original_method = "DE754C83318E762068C769E079ADB7A1", hash_generated_method = "2CA859495FCE04BE30AB7E12B34FD3ED")
    
private void onDeviceDisconnectRequested(String deviceObjectPath) {
        String address = mBluetoothService.getAddressFromObjectPath(deviceObjectPath);
        if (address == null) {
            Log.e(TAG, "onDeviceDisconnectRequested: Address of the remote device in null");
            return;
        }
        Intent intent = new Intent(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        mContext.sendBroadcast(intent, BLUETOOTH_PERM);
    }

    /**
     * Called by native code for the async response to a CreatePairedDevice
     * method call to org.bluez.Adapter.
     *
     * @param address the MAC address of the device to pair
     * @param result success or error result for the pairing operation
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.776 -0400", hash_original_method = "31A5CB4F3A5FC4630F0ACA0F4B056F4D", hash_generated_method = "5D194EC9AA1BCD348BC2C1DED9C2CC1F")
    
private void onCreatePairedDeviceResult(String address, int result) {
        address = address.toUpperCase();
        mBluetoothService.onCreatePairedDeviceResult(address, result);
    }

    /**
     * Called by native code on a DeviceCreated signal from org.bluez.Adapter.
     *
     * @param deviceObjectPath the object path for the created device
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.778 -0400", hash_original_method = "D4BB7C1FCBF61AF182F5688B35493AE7", hash_generated_method = "37EDB1A38E653EB2C31AE442C1E02306")
    
private void onDeviceCreated(String deviceObjectPath) {
        String address = mBluetoothService.getAddressFromObjectPath(deviceObjectPath);
        if (!mBluetoothService.isRemoteDeviceInCache(address)) {
            // Incoming connection, we haven't seen this device, add to cache.
            String[] properties = mBluetoothService.getRemoteDeviceProperties(address);
            if (properties != null) {
                addDevice(address, properties);
            }
        }
        return;
    }

    /**
     * Called by native code on a DeviceRemoved signal from org.bluez.Adapter.
     *
     * @param deviceObjectPath the object path for the removed device
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.779 -0400", hash_original_method = "1A9890B79CECEEB3AB84980BAB61A32E", hash_generated_method = "A885C61797667F9B85CC6D7E7523D081")
    
private void onDeviceRemoved(String deviceObjectPath) {
        String address = mBluetoothService.getAddressFromObjectPath(deviceObjectPath);
        if (address != null) {
            mBluetoothService.setBondState(address.toUpperCase(), BluetoothDevice.BOND_NONE,
                BluetoothDevice.UNBOND_REASON_REMOVED);
            mBluetoothService.setRemoteDeviceProperty(address, "UUIDs", null);
        }
    }

    /**
     * Called by native code on a PropertyChanged signal from
     * org.bluez.Adapter. This method is also called from
     * {@link BluetoothAdapterStateMachine} to set the "Pairable"
     * property when Bluetooth is enabled.
     *
     * @param propValues a string array containing the key and one or more
     *  values.
     */
    /*package*/ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.782 -0400", hash_original_method = "57A8D875B5CFCB060C621F91A419BD7F", hash_generated_method = "BBC1AC33F9B5B75323D836A884B64997")
    
void onPropertyChanged(String[] propValues) {
        BluetoothAdapterProperties adapterProperties =
                mBluetoothService.getAdapterProperties();

        if (adapterProperties.isEmpty()) {
            // We have got a property change before
            // we filled up our cache.
            adapterProperties.getAllProperties();
        }
        log("Property Changed: " + propValues[0] + " : " + propValues[1]);
        String name = propValues[0];
        if (name.equals("Name")) {
            adapterProperties.setProperty(name, propValues[1]);
            Intent intent = new Intent(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
            intent.putExtra(BluetoothAdapter.EXTRA_LOCAL_NAME, propValues[1]);
            intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
            mContext.sendBroadcast(intent, BLUETOOTH_PERM);
        } else if (name.equals("Pairable") || name.equals("Discoverable")) {
            adapterProperties.setProperty(name, propValues[1]);

            if (name.equals("Discoverable")) {
                mBluetoothState.sendMessage(BluetoothAdapterStateMachine.SCAN_MODE_CHANGED);
            }

            String pairable = name.equals("Pairable") ? propValues[1] :
                adapterProperties.getProperty("Pairable");
            String discoverable = name.equals("Discoverable") ? propValues[1] :
                adapterProperties.getProperty("Discoverable");

            // This shouldn't happen, unless Adapter Properties are null.
            if (pairable == null || discoverable == null)
                return;

            int mode = BluetoothService.bluezStringToScanMode(
                    pairable.equals("true"),
                    discoverable.equals("true"));
            if (mode >= 0) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
                intent.putExtra(BluetoothAdapter.EXTRA_SCAN_MODE, mode);
                intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
                mContext.sendBroadcast(intent, BLUETOOTH_PERM);
            }
        } else if (name.equals("Discovering")) {
            Intent intent;
            adapterProperties.setProperty(name, propValues[1]);
            if (propValues[1].equals("true")) {
                intent = new Intent(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            } else {
                // Stop the discovery.
                mBluetoothService.cancelDiscovery();
                intent = new Intent(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            }
            mContext.sendBroadcast(intent, BLUETOOTH_PERM);
        } else if (name.equals("Devices") || name.equals("UUIDs")) {
            String value = null;
            int len = Integer.valueOf(propValues[1]);
            if (len > 0) {
                StringBuilder str = new StringBuilder();
                for (int i = 2; i < propValues.length; i++) {
                    str.append(propValues[i]);
                    str.append(",");
                }
                value = str.toString();
            }
            adapterProperties.setProperty(name, value);
            if (name.equals("UUIDs")) {
                mBluetoothService.updateBluetoothState(value);
            }
        } else if (name.equals("Powered")) {
            mBluetoothState.sendMessage(BluetoothAdapterStateMachine.POWER_STATE_CHANGED,
                propValues[1].equals("true") ? new Boolean(true) : new Boolean(false));
        } else if (name.equals("DiscoverableTimeout")) {
            adapterProperties.setProperty(name, propValues[1]);
        }
    }

    /**
     * Called by native code on a PropertyChanged signal from
     * org.bluez.Device.
     *
     * @param deviceObjectPath the object path for the changed device
     * @param propValues a string array containing the key and one or more
     *  values.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.787 -0400", hash_original_method = "04EF0EF110937D61EB0AEE30F11FF81B", hash_generated_method = "CCC1AA8CF3A7F6B6868B64FFEF5C87C5")
    
private void onDevicePropertyChanged(String deviceObjectPath, String[] propValues) {
        String name = propValues[0];
        String address = mBluetoothService.getAddressFromObjectPath(deviceObjectPath);
        if (address == null) {
            Log.e(TAG, "onDevicePropertyChanged: Address of the remote device in null");
            return;
        }
        log("Device property changed: " + address + " property: "
            + name + " value: " + propValues[1]);

        BluetoothDevice device = mAdapter.getRemoteDevice(address);
        if (name.equals("Name")) {
            mBluetoothService.setRemoteDeviceProperty(address, name, propValues[1]);
            Intent intent = new Intent(BluetoothDevice.ACTION_NAME_CHANGED);
            intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
            intent.putExtra(BluetoothDevice.EXTRA_NAME, propValues[1]);
            intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
            mContext.sendBroadcast(intent, BLUETOOTH_PERM);
        } else if (name.equals("Alias")) {
            mBluetoothService.setRemoteDeviceProperty(address, name, propValues[1]);
        } else if (name.equals("Class")) {
            mBluetoothService.setRemoteDeviceProperty(address, name, propValues[1]);
            Intent intent = new Intent(BluetoothDevice.ACTION_CLASS_CHANGED);
            intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
            intent.putExtra(BluetoothDevice.EXTRA_CLASS,
                    new BluetoothClass(Integer.valueOf(propValues[1])));
            intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
            mContext.sendBroadcast(intent, BLUETOOTH_PERM);
        } else if (name.equals("Connected")) {
            mBluetoothService.setRemoteDeviceProperty(address, name, propValues[1]);
            Intent intent = null;
            if (propValues[1].equals("true")) {
                intent = new Intent(BluetoothDevice.ACTION_ACL_CONNECTED);
                // Set the link timeout to 8000 slots (5 sec timeout)
                // for bluetooth docks.
                if (mBluetoothService.isBluetoothDock(address)) {
                    mBluetoothService.setLinkTimeout(address, 8000);
                }
            } else {
                intent = new Intent(BluetoothDevice.ACTION_ACL_DISCONNECTED);
            }
            intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
            intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
            mContext.sendBroadcast(intent, BLUETOOTH_PERM);
        } else if (name.equals("UUIDs")) {
            String uuid = null;
            int len = Integer.valueOf(propValues[1]);
            if (len > 0) {
                StringBuilder str = new StringBuilder();
                for (int i = 2; i < propValues.length; i++) {
                    str.append(propValues[i]);
                    str.append(",");
                }
                uuid = str.toString();
            }
            mBluetoothService.setRemoteDeviceProperty(address, name, uuid);

            // UUIDs have changed, query remote service channel and update cache.
            mBluetoothService.updateDeviceServiceChannelCache(address);

            mBluetoothService.sendUuidIntent(address);
        } else if (name.equals("Paired")) {
            if (propValues[1].equals("true")) {
                // If locally initiated pairing, we will
                // not go to BOND_BONDED state until we have received a
                // successful return value in onCreatePairedDeviceResult
                if (null == mBluetoothService.getPendingOutgoingBonding()) {
                    mBluetoothService.setBondState(address, BluetoothDevice.BOND_BONDED);
                }
            } else {
                mBluetoothService.setBondState(address, BluetoothDevice.BOND_NONE);
                mBluetoothService.setRemoteDeviceProperty(address, "Trusted", "false");
            }
        } else if (name.equals("Trusted")) {
            if (DBG)
                log("set trust state succeeded, value is: " + propValues[1]);
            mBluetoothService.setRemoteDeviceProperty(address, name, propValues[1]);
        }
    }

    /**
     * Called by native code on a PropertyChanged signal from
     * org.bluez.Input.
     *
     * @param path the object path for the changed input device
     * @param propValues a string array containing the key and one or more
     *  values.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.788 -0400", hash_original_method = "BE89D05483B96102E41C8C8949DB36D5", hash_generated_method = "B0BB02FF3FEB7B2727B34AB74C49FCE6")
    
private void onInputDevicePropertyChanged(String path, String[] propValues) {
        String address = mBluetoothService.getAddressFromObjectPath(path);
        if (address == null) {
            Log.e(TAG, "onInputDevicePropertyChanged: Address of the remote device is null");
            return;
        }
        log("Input Device : Name of Property is: " + propValues[0]);
        boolean state = false;
        if (propValues[1].equals("true")) {
            state = true;
        }
        mBluetoothService.handleInputDevicePropertyChange(address, state);
    }

    /**
     * Called by native code on a PropertyChanged signal from
     * org.bluez.Network.
     *
     * @param deviceObjectPath the object path for the changed PAN device
     * @param propValues a string array containing the key and one or more
     *  values.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.790 -0400", hash_original_method = "3F48DFDC97FCA92CC1A23D89E8613BB5", hash_generated_method = "12F5183865AA505F07C9EA83B6C2B185")
    
private void onPanDevicePropertyChanged(String deviceObjectPath, String[] propValues) {
        String name = propValues[0];
        String address = mBluetoothService.getAddressFromObjectPath(deviceObjectPath);
        if (address == null) {
            Log.e(TAG, "onPanDevicePropertyChanged: Address of the remote device in null");
            return;
        }
        if (DBG) {
            log("Pan Device property changed: " + address + "  property: "
                    + name + " value: "+ propValues[1]);
        }
        BluetoothDevice device = mAdapter.getRemoteDevice(address);
        if (name.equals("Connected")) {
            if (propValues[1].equals("false")) {
                mBluetoothService.handlePanDeviceStateChange(device,
                                          BluetoothPan.STATE_DISCONNECTED,
                                          BluetoothPan.LOCAL_PANU_ROLE);
            }
        } else if (name.equals("Interface")) {
            String iface = propValues[1];
            if (!iface.equals("")) {
                mBluetoothService.handlePanDeviceStateChange(device, iface,
                                              BluetoothPan.STATE_CONNECTED,
                                              BluetoothPan.LOCAL_PANU_ROLE);
            }
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.792 -0400", hash_original_method = "293226E9934F98E4FC2F62914E6342B0", hash_generated_method = "03C29666ACE9B3459416378C19609B83")
    
private String checkPairingRequestAndGetAddress(String objectPath, int nativeData) {
        String address = mBluetoothService.getAddressFromObjectPath(objectPath);
        if (address == null) {
            Log.e(TAG, "Unable to get device address in checkPairingRequestAndGetAddress, " +
                  "returning null");
            return null;
        }
        address = address.toUpperCase();
        mPasskeyAgentRequestData.put(address, new Integer(nativeData));

        if (mBluetoothService.getBluetoothState() == BluetoothAdapter.STATE_TURNING_OFF) {
            // shutdown path
            mBluetoothService.cancelPairingUserInput(address);
            return null;
        }
        // Set state to BONDING. For incoming connections it will be set here.
        // For outgoing connections, it gets set when we call createBond.
        // Also set it only when the state is not already Bonded, we can sometimes
        // get an authorization request from the remote end if it doesn't have the link key
        // while we still have it.
        if (mBluetoothService.getBondState(address) != BluetoothDevice.BOND_BONDED)
            mBluetoothService.setBondState(address, BluetoothDevice.BOND_BONDING);
        return address;
    }

    /**
     * Called by native code on a RequestPairingConsent method call to
     * org.bluez.Agent.
     *
     * @param objectPath the path of the device to request pairing consent for
     * @param nativeData a native pointer to the original D-Bus message
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.794 -0400", hash_original_method = "F9B91C6A43A2457E469B64CA7EECF8B6", hash_generated_method = "A326956E1EF7B621D8F57F47CD9B2A84")
    
private void onRequestPairingConsent(String objectPath, int nativeData) {
        String address = checkPairingRequestAndGetAddress(objectPath, nativeData);
        if (address == null) return;

        /* The link key will not be stored if the incoming request has MITM
         * protection switched on. Unfortunately, some devices have MITM
         * switched on even though their capabilities are NoInputNoOutput,
         * so we may get this request many times. Also if we respond immediately,
         * the other end is unable to handle it. Delay sending the message.
         */
        if (mBluetoothService.getBondState(address) == BluetoothDevice.BOND_BONDED) {
            Message message = mHandler.obtainMessage(EVENT_PAIRING_CONSENT_DELAYED_ACCEPT);
            message.obj = address;
            mHandler.sendMessageDelayed(message, 1500);
            return;
        }
        // Acquire wakelock during PIN code request to bring up LCD display
        mWakeLock.acquire();
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT,
                        BluetoothDevice.PAIRING_VARIANT_CONSENT);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
        // Release wakelock to allow the LCD to go off after the PIN popup notification.
        mWakeLock.release();
        return;
    }

    /**
     * Called by native code on a RequestConfirmation method call to
     * org.bluez.Agent.
     *
     * @param objectPath the path of the device to confirm the passkey for
     * @param passkey an integer containing the 6-digit passkey to confirm
     * @param nativeData a native pointer to the original D-Bus message
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.796 -0400", hash_original_method = "BAAA4823804BB5CB2EEDF6C02F45111E", hash_generated_method = "98583567604BD3CBED5704BEDCFDEBC7")
    
private void onRequestPasskeyConfirmation(String objectPath, int passkey, int nativeData) {
        String address = checkPairingRequestAndGetAddress(objectPath, nativeData);
        if (address == null) return;
        // Acquire wakelock during PIN code request to bring up LCD display
        mWakeLock.acquire();
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_KEY, passkey);
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT,
                BluetoothDevice.PAIRING_VARIANT_PASSKEY_CONFIRMATION);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
        // Release wakelock to allow the LCD to go off after the PIN popup notification.
        mWakeLock.release();
        return;
    }

    /**
     * Called by native code on a RequestPasskey method call to
     * org.bluez.Agent.
     *
     * @param objectPath the path of the device requesting a passkey
     * @param nativeData a native pointer to the original D-Bus message
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.798 -0400", hash_original_method = "6705494FBD0F88EB9D780508EB911135", hash_generated_method = "A0F7E1F62CE80902A6DBB69B361C771B")
    
private void onRequestPasskey(String objectPath, int nativeData) {
        String address = checkPairingRequestAndGetAddress(objectPath, nativeData);
        if (address == null) return;
        // Acquire wakelock during PIN code request to bring up LCD display
        mWakeLock.acquire();
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT,
                BluetoothDevice.PAIRING_VARIANT_PASSKEY);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
        // Release wakelock to allow the LCD to go off after the PIN popup notification.
        mWakeLock.release();
        return;
    }

    /**
     * Called by native code on a RequestPinCode method call to
     * org.bluez.Agent.
     *
     * @param objectPath the path of the device requesting a PIN code
     * @param nativeData a native pointer to the original D-Bus message
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.800 -0400", hash_original_method = "376D3C3326DC6A43D167BD03D83369CA", hash_generated_method = "51F45D1FE4BD43E553A1C8ADFD0246B5")
    
private void onRequestPinCode(String objectPath, int nativeData) {
        String address = checkPairingRequestAndGetAddress(objectPath, nativeData);
        if (address == null) return;

        String pendingOutgoingAddress =
                mBluetoothService.getPendingOutgoingBonding();
        BluetoothClass btClass = new BluetoothClass(mBluetoothService.getRemoteClass(address));
        int btDeviceClass = btClass.getDeviceClass();

        if (address.equals(pendingOutgoingAddress)) {
            // we initiated the bonding

            // Check if its a dock
            if (mBluetoothService.isBluetoothDock(address)) {
                String pin = mBluetoothService.getDockPin();
                mBluetoothService.setPin(address, BluetoothDevice.convertPinToBytes(pin));
                return;
            }

            // try 0000 once if the device looks dumb
            switch (btDeviceClass) {
            case BluetoothClass.Device.AUDIO_VIDEO_WEARABLE_HEADSET:
            case BluetoothClass.Device.AUDIO_VIDEO_HANDSFREE:
            case BluetoothClass.Device.AUDIO_VIDEO_HEADPHONES:
            case BluetoothClass.Device.AUDIO_VIDEO_PORTABLE_AUDIO:
            case BluetoothClass.Device.AUDIO_VIDEO_HIFI_AUDIO:
                if (mBluetoothService.attemptAutoPair(address)) return;
           }
        }

        if (btDeviceClass == BluetoothClass.Device.PERIPHERAL_KEYBOARD ||
            btDeviceClass == BluetoothClass.Device.PERIPHERAL_KEYBOARD_POINTING) {
            // Its a keyboard. Follow the HID spec recommendation of creating the
            // passkey and displaying it to the user. If the keyboard doesn't follow
            // the spec recommendation, check if the keyboard has a fixed PIN zero
            // and pair.
            if (mBluetoothService.isFixedPinZerosAutoPairKeyboard(address)) {
                mBluetoothService.setPin(address, BluetoothDevice.convertPinToBytes("0000"));
                return;
            }

            // Generate a variable PIN. This is not truly random but good enough.
            int pin = (int) Math.floor(Math.random() * 10000);
            sendDisplayPinIntent(address, pin);
            return;
        }
        // Acquire wakelock during PIN code request to bring up LCD display
        mWakeLock.acquire();
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT, BluetoothDevice.PAIRING_VARIANT_PIN);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
        // Release wakelock to allow the LCD to go off after the PIN popup notification.
        mWakeLock.release();
        return;
    }

    /**
     * Called by native code on a DisplayPasskey method call to
     * org.bluez.Agent.
     *
     * @param objectPath the path of the device to display the passkey for
     * @param passkey an integer containing the 6-digit passkey
     * @param nativeData a native pointer to the original D-Bus message
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.802 -0400", hash_original_method = "86BB9934E6C8CCDEBF0E4FA9EF01F2A6", hash_generated_method = "86ED2021B5FB276F0D5F19302A979606")
    
private void onDisplayPasskey(String objectPath, int passkey, int nativeData) {
        String address = checkPairingRequestAndGetAddress(objectPath, nativeData);
        if (address == null) return;

        // Acquire wakelock during PIN code request to bring up LCD display
        mWakeLock.acquire();
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_KEY, passkey);
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT,
                        BluetoothDevice.PAIRING_VARIANT_DISPLAY_PASSKEY);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
        //Release wakelock to allow the LCD to go off after the PIN popup notification.
        mWakeLock.release();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.803 -0400", hash_original_method = "6F026019012A28033F3A0C72379701E4", hash_generated_method = "3A67C9E270E383B0293AA6AC03C7749B")
    
private void sendDisplayPinIntent(String address, int pin) {
        // Acquire wakelock during PIN code request to bring up LCD display
        mWakeLock.acquire();
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_KEY, pin);
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT,
                        BluetoothDevice.PAIRING_VARIANT_DISPLAY_PIN);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
        //Release wakelock to allow the LCD to go off after the PIN popup notifcation.
        mWakeLock.release();
    }

    /**
     * Called by native code on a RequestOobData method call to
     * org.bluez.Agent.
     *
     * @param objectPath the path of the device requesting OOB data
     * @param nativeData a native pointer to the original D-Bus message
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.805 -0400", hash_original_method = "D95F95F909AD55031AB32141DC830DFD", hash_generated_method = "1CF55DCF2B73BDEC8755EB8FB09464AB")
    
private void onRequestOobData(String objectPath, int nativeData) {
        String address = checkPairingRequestAndGetAddress(objectPath, nativeData);
        if (address == null) return;

        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mAdapter.getRemoteDevice(address));
        intent.putExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT,
                BluetoothDevice.PAIRING_VARIANT_OOB_CONSENT);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);
    }

    /**
     * Called by native code on an Authorize method call to org.bluez.Agent.
     *
     * @param objectPath the path of the device requesting to be authorized
     * @param deviceUuid the UUID of the requesting device
     * @param nativeData reference for native data
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.808 -0400", hash_original_method = "17D37254811C4EF4B8866B9452E16190", hash_generated_method = "41344D21B7D684F953824CB8654BB9CA")
    
private void  onAgentAuthorize(String objectPath, String deviceUuid, int nativeData) {
        if (!mBluetoothService.isEnabled()) return;

        String address = mBluetoothService.getAddressFromObjectPath(objectPath);
        if (address == null) {
            Log.e(TAG, "Unable to get device address in onAuthAgentAuthorize");
            return;
        }

        boolean authorized = false;
        ParcelUuid uuid = ParcelUuid.fromString(deviceUuid);

        BluetoothDevice device = mAdapter.getRemoteDevice(address);
        mAuthorizationAgentRequestData.put(address, new Integer(nativeData));

        // Bluez sends the UUID of the local service being accessed, _not_ the
        // remote service
        if (mA2dp != null &&
            (BluetoothUuid.isAudioSource(uuid) || BluetoothUuid.isAvrcpTarget(uuid)
              || BluetoothUuid.isAdvAudioDist(uuid)) &&
              !isOtherSinkInNonDisconnectedState(address)) {
            authorized = mA2dp.getPriority(device) > BluetoothProfile.PRIORITY_OFF;
            if (authorized && !BluetoothUuid.isAvrcpTarget(uuid)) {
                Log.i(TAG, "First check pass for incoming A2DP / AVRCP connection from " + address);
                // Some headsets try to connect AVCTP before AVDTP - against the recommendation
                // If AVCTP connection fails, we get stuck in IncomingA2DP state in the state
                // machine.  We don't handle AVCTP signals currently. We only send
                // intents for AVDTP state changes. We need to handle both of them in
                // some cases. For now, just don't move to incoming state in this case.
                mBluetoothService.notifyIncomingA2dpConnection(address, false);
            } else {
                Log.i(TAG, "" + authorized +
                      "Incoming A2DP / AVRCP connection from " + address);
                mA2dp.allowIncomingConnect(device, authorized);
                mBluetoothService.notifyIncomingA2dpConnection(address, true);
            }
        } else if (BluetoothUuid.isInputDevice(uuid)) {
            // We can have more than 1 input device connected.
            authorized = mBluetoothService.getInputDevicePriority(device) >
                    BluetoothInputDevice.PRIORITY_OFF;
            if (authorized) {
                Log.i(TAG, "First check pass for incoming HID connection from " + address);
                // notify profile state change
                mBluetoothService.notifyIncomingHidConnection(address);
            } else {
                Log.i(TAG, "Rejecting incoming HID connection from " + address);
                mBluetoothService.allowIncomingProfileConnect(device, authorized);
            }
        } else if (BluetoothUuid.isBnep(uuid)) {
            // PAN doesn't go to the state machine, accept or reject from here
            authorized = mBluetoothService.allowIncomingTethering();
            mBluetoothService.allowIncomingProfileConnect(device, authorized);
        } else {
            Log.i(TAG, "Rejecting incoming " + deviceUuid + " connection from " + address);
            mBluetoothService.allowIncomingProfileConnect(device, authorized);
        }
        log("onAgentAuthorize(" + objectPath + ", " + deviceUuid + ") = " + authorized);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.809 -0400", hash_original_method = "DE6CDFC8960AA00EE8D87F72DE4AB59B", hash_generated_method = "FF25030491FC2BFA382BF534FD45CF42")
    
private boolean onAgentOutOfBandDataAvailable(String objectPath) {
        if (!mBluetoothService.isEnabled()) return false;

        String address = mBluetoothService.getAddressFromObjectPath(objectPath);
        if (address == null) return false;

        if (mBluetoothService.getDeviceOutOfBandData(
            mAdapter.getRemoteDevice(address)) != null) {
            return true;
        }
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.811 -0400", hash_original_method = "6DEAF4E850970951BE6FC6B3F5DD1D3C", hash_generated_method = "67FEB5BAAD5B4A61B2A161BEBE5209E4")
    
private boolean isOtherSinkInNonDisconnectedState(String address) {
        List<BluetoothDevice> devices =
            mA2dp.getDevicesMatchingConnectionStates(new int[] {BluetoothA2dp.STATE_CONNECTED,
                                                     BluetoothA2dp.STATE_CONNECTING,
                                                     BluetoothA2dp.STATE_DISCONNECTING});

        if (devices.size() == 0) return false;
        for (BluetoothDevice dev: devices) {
            if (!dev.getAddress().equals(address)) return true;
        }
        return false;
    }

    /**
     * Called by native code on a Cancel method call to org.bluez.Agent.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.812 -0400", hash_original_method = "F5106D0386D3020336DFAB8E81694EB1", hash_generated_method = "19EBF7A8C1F496ACA7BB8F658932423D")
    
private void onAgentCancel() {
        Intent intent = new Intent(BluetoothDevice.ACTION_PAIRING_CANCEL);
        mContext.sendBroadcast(intent, BLUETOOTH_ADMIN_PERM);

        mHandler.sendMessageDelayed(mHandler.obtainMessage(EVENT_AGENT_CANCEL),
                   1500);

        return;
    }

    /**
     * Called by native code for the async response to a DiscoverServices
     * method call to org.bluez.Adapter.
     *
     * @param deviceObjectPath the path for the specified device
     * @param result true for success; false on error
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.814 -0400", hash_original_method = "EC8E61A8079DE5D93B38041B01ED6536", hash_generated_method = "EEC6843FBE04C4A8CEB9ED69F88A2C92")
    
private void onDiscoverServicesResult(String deviceObjectPath, boolean result) {
        String address = mBluetoothService.getAddressFromObjectPath(deviceObjectPath);
        if (address == null) return;

        // We don't parse the xml here, instead just query Bluez for the properties.
        if (result) {
            mBluetoothService.updateRemoteDevicePropertiesCache(address);
        }
        mBluetoothService.sendUuidIntent(address);
        mBluetoothService.makeServiceChannelCallbacks(address);
    }

    /**
     * Called by native code for the async response to a CreateDevice
     * method call to org.bluez.Adapter.
     *
     * @param address the MAC address of the device to create
     * @param result {@link #CREATE_DEVICE_SUCCESS},
     *  {@link #CREATE_DEVICE_ALREADY_EXISTS} or {@link #CREATE_DEVICE_FAILED}}
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.816 -0400", hash_original_method = "A05F35CDD31E9B8D4D5DBF3B8BA2A28A", hash_generated_method = "22643CEC97564B2ABE2730A7BD5343B6")
    
private void onCreateDeviceResult(String address, int result) {
        if (DBG) log("Result of onCreateDeviceResult:" + result);

        switch (result) {
        case CREATE_DEVICE_ALREADY_EXISTS:
            String path = mBluetoothService.getObjectPathFromAddress(address);
            if (path != null) {
                mBluetoothService.discoverServicesNative(path, "");
                break;
            }
            Log.w(TAG, "Device exists, but we don't have the bluez path, failing");
            // fall-through
        case CREATE_DEVICE_FAILED:
            mBluetoothService.sendUuidIntent(address);
            mBluetoothService.makeServiceChannelCallbacks(address);
            break;
        case CREATE_DEVICE_SUCCESS:
            // nothing to do, UUID intent's will be sent via property changed
        }
    }

    /**
     * Called by native code for the async response to a Connect
     * method call to org.bluez.Input.
     *
     * @param path the path of the specified input device
     * @param result Result code of the operation.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.824 -0400", hash_original_method = "5D8D5E301F385A8103A96491A29BD204", hash_generated_method = "B192992FF9B2F7949786FAF7105E018F")
    
private void onInputDeviceConnectionResult(String path, int result) {
        // Success case gets handled by Property Change signal
        if (result != BluetoothInputDevice.INPUT_OPERATION_SUCCESS) {
            String address = mBluetoothService.getAddressFromObjectPath(path);
            if (address == null) return;

            boolean connected = false;
            BluetoothDevice device = mAdapter.getRemoteDevice(address);
            int state = mBluetoothService.getInputDeviceConnectionState(device);
            if (state == BluetoothInputDevice.STATE_CONNECTING) {
                if (result == BluetoothInputDevice.INPUT_CONNECT_FAILED_ALREADY_CONNECTED) {
                    connected = true;
                } else {
                    connected = false;
                }
            } else if (state == BluetoothInputDevice.STATE_DISCONNECTING) {
                if (result == BluetoothInputDevice.INPUT_DISCONNECT_FAILED_NOT_CONNECTED) {
                    connected = false;
                } else {
                    // There is no better way to handle this, this shouldn't happen
                    connected = true;
                }
            } else {
                Log.e(TAG, "Error onInputDeviceConnectionResult. State is:" + state);
            }
            mBluetoothService.handleInputDevicePropertyChange(address, connected);
        }
    }

    /**
     * Called by native code for the async response to a Connect
     * method call to org.bluez.Network.
     *
     * @param path the path of the specified PAN device
     * @param result Result code of the operation.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.827 -0400", hash_original_method = "A5AB6B3C30C973DC88BBE2862158661A", hash_generated_method = "7D1A618D51E7BC8B08FEFF0119D08667")
    
private void onPanDeviceConnectionResult(String path, int result) {
        log ("onPanDeviceConnectionResult " + path + " " + result);
        // Success case gets handled by Property Change signal
        if (result != BluetoothPan.PAN_OPERATION_SUCCESS) {
            String address = mBluetoothService.getAddressFromObjectPath(path);
            if (address == null) return;

            boolean connected = false;
            BluetoothDevice device = mAdapter.getRemoteDevice(address);
            int state = mBluetoothService.getPanDeviceConnectionState(device);
            if (state == BluetoothPan.STATE_CONNECTING) {
                if (result == BluetoothPan.PAN_CONNECT_FAILED_ALREADY_CONNECTED) {
                    connected = true;
                } else {
                    connected = false;
                }
            } else if (state == BluetoothPan.STATE_DISCONNECTING) {
                if (result == BluetoothPan.PAN_DISCONNECT_FAILED_NOT_CONNECTED) {
                    connected = false;
                } else {
                    // There is no better way to handle this, this shouldn't happen
                    connected = true;
                }
            } else {
                Log.e(TAG, "Error onPanDeviceConnectionResult. State is: "
                        + state + " result: "+ result);
            }
            int newState = connected? BluetoothPan.STATE_CONNECTED :
                BluetoothPan.STATE_DISCONNECTED;
            mBluetoothService.handlePanDeviceStateChange(device, newState,
                                                  BluetoothPan.LOCAL_PANU_ROLE);
        }
    }

    /**
     * Called by native code for the async response to a Connect
     * method call to org.bluez.Health
     *
     * @param chanCode The internal id of the channel
     * @param result Result code of the operation.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.828 -0400", hash_original_method = "9A3578365979051B1C9E49F53E923860", hash_generated_method = "D0561A4F6DC450EDCCB9B396E2CF9249")
    
private void onHealthDeviceConnectionResult(int chanCode, int result) {
        log ("onHealthDeviceConnectionResult " + chanCode + " " + result);
        // Success case gets handled by Property Change signal
        if (result != BluetoothHealth.HEALTH_OPERATION_SUCCESS) {
            mBluetoothService.onHealthDeviceChannelConnectionError(chanCode,
                                                 BluetoothHealth.STATE_CHANNEL_DISCONNECTED);
        }
    }

    /**
     * Called by native code on a DeviceDisconnected signal from
     * org.bluez.NetworkServer.
     *
     * @param address the MAC address of the disconnected device
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.831 -0400", hash_original_method = "2099D00FDFC3545893A7A9B87ABDF3A9", hash_generated_method = "E3FEECCCF863F0062650DD5C044110BF")
    
private void onNetworkDeviceDisconnected(String address) {
        BluetoothDevice device = mAdapter.getRemoteDevice(address);
        mBluetoothService.handlePanDeviceStateChange(device, BluetoothPan.STATE_DISCONNECTED,
                                                      BluetoothPan.LOCAL_NAP_ROLE);
    }

    /**
     * Called by native code on a DeviceConnected signal from
     * org.bluez.NetworkServer.
     *
     * @param address the MAC address of the connected device
     * @param iface interface of remote network
     * @param destUuid unused UUID parameter
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.833 -0400", hash_original_method = "1C4E29D3EA1D72FBB0F5102B7A952561", hash_generated_method = "CC1522D8F69FE6D3159800A202639F0C")
    
private void onNetworkDeviceConnected(String address, String iface, int destUuid) {
        BluetoothDevice device = mAdapter.getRemoteDevice(address);
        mBluetoothService.handlePanDeviceStateChange(device, iface, BluetoothPan.STATE_CONNECTED,
                                                      BluetoothPan.LOCAL_NAP_ROLE);
    }

    /**
     * Called by native code on a PropertyChanged signal from
     * org.bluez.HealthDevice.
     *
     * @param devicePath the object path of the remote device
     * @param propValues Properties (Name-Value) of the Health Device.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.834 -0400", hash_original_method = "FD8EF52F8E4F20B6F1322976C5E13876", hash_generated_method = "850FC00378C4F6D6507072006E646275")
    
private void onHealthDevicePropertyChanged(String devicePath, String[] propValues) {
        log("Health Device : Name of Property is: " + propValues[0] + " Value:" + propValues[1]);
        mBluetoothService.onHealthDevicePropertyChanged(devicePath, propValues[1]);
    }

    /**
     * Called by native code on a ChannelCreated/Deleted signal from
     * org.bluez.HealthDevice.
     *
     * @param devicePath the object path of the remote device
     * @param channelPath the path of the health channel.
     * @param exists Boolean to indicate if the channel was created or deleted.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:23.835 -0400", hash_original_method = "3524CCC20E74493FCF3DA12377ED25AE", hash_generated_method = "6E2BBF96430220DFA33B2461FBBECCB2")
    
private void onHealthDeviceChannelChanged(String devicePath, String channelPath,
            boolean exists) {
        log("Health Device : devicePath: " + devicePath + ":channelPath:" + channelPath +
                ":exists" + exists);
        mBluetoothService.onHealthDeviceChannelChanged(devicePath, channelPath, exists);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 14:22:08.326 -0400", hash_original_method = "96C94C385383C3A18A52276A4D2A2B32", hash_generated_method = "4CFDBFA182515EEF09ADB2455AD182E5")
    
    private void initializeNativeDataNative(){
    	//Formerly a native method
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 14:22:08.329 -0400", hash_original_method = "CCA549B8BD21D33747E82F2FABA549D3", hash_generated_method = "EDA71CE3300777F0AF4396B96B75C261")
    
    private void startEventLoopNative(){
    	//Formerly a native method
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 14:22:08.331 -0400", hash_original_method = "B73E6C3F88908329B8B1FBDA74259A5A", hash_generated_method = "222B91D9B917C95442C034E5C3A88995")
    
    private void stopEventLoopNative(){
    	//Formerly a native method
    }
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 14:24:56.778 -0400", hash_original_method = "98F372D174579E6EEAC27AA899E5B3A4", hash_generated_method = "7E694AF644AD5A78BB7FF2469A4B8E0E")
    
    private boolean isEventLoopRunningNative(){
    	//Formerly a native method
    	double taintDouble = 0;
    
    	return ((taintDouble) == 1);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-25 14:22:08.336 -0400", hash_original_method = "3B6CA7B24C36B662CB5B1AEC413DB28D", hash_generated_method = "0DAB225627D67322CE3F5E19CB1C0E87")
    
    private void cleanupNativeDataNative(){
    	//Formerly a native method
    }

}
