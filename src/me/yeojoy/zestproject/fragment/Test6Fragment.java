package me.yeojoy.zestproject.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.yeojoy.zestproject.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test6Fragment extends Fragment implements OnClickListener, OnLongClickListener {
    
    private static final String TAG = Test6Fragment.class.getSimpleName();
    
    private TextView mTvResult, mTvTime;
    
    private long theTime;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_6, container, false);
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
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.set(2014, 3, 21, 18, 0, 0);
        sb.append("Cal : ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(1398070800000l));
        sb.append("\nTime : ").append(c.getTimeInMillis());
        mTvResult.setText(sb);
    }
    
}
