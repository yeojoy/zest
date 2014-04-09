package me.yeojoy.zestproject.fragment;

import java.util.Date;
import java.util.List;

import me.yeojoy.zestproject.R;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test4Fragment extends Fragment implements OnClickListener, OnLongClickListener {
    
    private static final String TAG = Test4Fragment.class.getSimpleName();
    
    private TextView mTvResult, mTvTime;

    private SensorManager mSensorManager;
    
    private long theTime;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_4, container, false);
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        
        view.findViewById(R.id.btn_adder).setOnClickListener(this);
        view.findViewById(R.id.btn_adder).setOnLongClickListener(this);
        
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        
        return view;
        
    }
    
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = getActivity().getSharedPreferences("yeojoy", Context.MODE_PRIVATE); 
        theTime = prefs.getLong("time", 0);
        
        if (theTime == 0) {
            theTime = new Date().getTime();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("time", theTime);
            editor.apply();
        }
        
        PowerManager powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyWakelockTag");
        wakeLock.acquire();
        
        setText();
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        PowerManager powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyWakelockTag");
        wakeLock.release();
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_adder) {
            setText();
        }
    }
    
    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.btn_adder) {

            return true;
        }
        return false;
    }
    
    private void setText() {
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Sensor s : sensors) {
            sb.append(index).append(". Sensor name is ").append(s.getName()).append("\n\n");
            index++;
        }
        mTvResult.setText(sb);
        
        Sensor s = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (s != null) {
            mTvTime.setText(s.getName() + " STEP_DETECTOR가 살아있음.");
        } else {
            mTvTime.setText("STEP_DETECTOR가 죽음.");
        }
    }
    
}
