package com.example.ziptool.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlueToothUtil {

    public static BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public static Map<String,String> getLocalBTInfo(){
        String name = bluetoothAdapter.getName();
        String address = bluetoothAdapter.getAddress();
        boolean enabled = bluetoothAdapter.isEnabled();
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("address",address);
        map.put("enabled",String.valueOf(enabled));
        return map;
    }


    public static List<BluetoothDevice> getBondedDevices(){
        Set<BluetoothDevice> deviceSet = bluetoothAdapter.getBondedDevices();
        BluetoothDevice[] deviceArray = deviceSet.toArray(new BluetoothDevice[deviceSet.size()]);
        List<BluetoothDevice> deviceList = Arrays.asList(deviceArray);
        return deviceList;
    }
}
