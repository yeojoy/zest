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

public class Test5Fragment extends Fragment implements OnClickListener, OnLongClickListener {
    
    private static final String TAG = Test5Fragment.class.getSimpleName();
    
    private TextView mTvResult, mTvTime;
    
    private long theTime;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_4, container, false);
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        
        view.findViewById(R.id.btn_adder).setOnClickListener(this);
        view.findViewById(R.id.btn_adder).setOnLongClickListener(this);
        
        
        return view;
        
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        setText();
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
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
        mTvResult.setText("a\na\na\na\n");
        mTvTime.setText(String.valueOf(mTvResult.getLineCount()));
    }
    
}
