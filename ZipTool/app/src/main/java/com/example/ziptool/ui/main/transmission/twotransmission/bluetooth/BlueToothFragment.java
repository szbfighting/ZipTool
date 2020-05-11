package com.example.ziptool.ui.main.transmission.twotransmission.bluetooth;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ziptool.R;
import com.example.ziptool.util.BlueToothUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlueToothFragment extends Fragment {



    private static final String TAG = "BlueToothFragment";
    public BlueToothFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_blue_tooth, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Map<String,String> myInfo = BlueToothUtil.getLocalBTInfo();
        boolean enabled = Boolean.valueOf(myInfo.get("enabled"));
        View view = getView();
        Switch aSwitch = view.findViewById(R.id.device_status_switch);
        aSwitch.setChecked(enabled);
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                BlueToothUtil.bluetoothAdapter.enable();
            }else{
                BlueToothUtil.bluetoothAdapter.disable();
            }
        });

        String name = myInfo.get("name");
        TextView textView = view.findViewById(R.id.my_devicename_tv);
        textView.setText(name);

        List<BluetoothDevice> list = BlueToothUtil.getBondedDevices();
        List<String> names = new ArrayList<>();
        for (BluetoothDevice device : list) {
            names.add(device.getName());
        }

        ListView lv = view.findViewById(R.id.bonded_devide_lv);
        lv.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,names));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+TAG);
    }
}
