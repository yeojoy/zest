package me.yeojoy.zestproject.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        Date date = new Date();
        SimpleDateFormat f = null;
        StringBuilder sb = new StringBuilder();
        sb.append("Locale.KOREA\n");
        f = new SimpleDateFormat("E", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEEE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEEEE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEEEEE", Locale.KOREA);
        sb.append(f.format(date)).append("\n");
        sb.append("\n\nLocale.KOREAN\n");
        f = new SimpleDateFormat("E", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEEE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEEEE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        f = new SimpleDateFormat("EEEEEEEE", Locale.KOREAN);
        sb.append(f.format(date)).append("\n");
        
        mTvResult.setText(sb);
        
        mTvTime.setText(String.valueOf(mTvResult.getLineCount()));
    }
    
}
