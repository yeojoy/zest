package me.yeojoy.zestproject.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.yeojoy.zestproject.R;
import my.lib.time.TimeAgo;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class Test3Fragment extends Fragment implements OnClickListener, OnLongClickListener {
    
    private static final String TAG = Test3Fragment.class.getSimpleName();
    
    private TextView mTvResult, mTvTime;
    
    private WindowManager mWindowMgr;
    
    private long theTime;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_3, container, false);
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        mWindowMgr = getActivity().getWindowManager();
        
        view.findViewById(R.id.btn_adder).setOnClickListener(this);
        view.findViewById(R.id.btn_adder).setOnLongClickListener(this);
        
        
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
        
        setText();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        showDisplayInfo();
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
            SharedPreferences prefs = getActivity().getSharedPreferences("yeojoy", Context.MODE_PRIVATE);
            
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("time", new Date().getTime());
            editor.apply();
            return true;
        }
        return false;
    }
    
    private void setText() {
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(theTime));
        ssb.append("       ").append(TimeAgo.timeAgoString(new Date(theTime)));
        ssb.setSpan(new ForegroundColorSpan(Color.argb(255, 92, 193, 201)), 0, ssb.length(), 
                SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
        mTvTime.setText(ssb);
    }
    
    private void showDisplayInfo() {
        DisplayMetrics out = new DisplayMetrics();
        // getMetrics()와 getRealMetrics()는 둘이 같음...
        mWindowMgr.getDefaultDisplay().getMetrics(out);
//        mWindowMgr.getDefaultDisplay().getRealMetrics(real);
        
        StringBuilder sb = new StringBuilder();
        sb.append("getMetrics()에서 나온 값.\n");
        sb.append("density : ").append(out.density).append("\n");
        sb.append("densityDpi : ").append(out.densityDpi).append("\n");
        sb.append("heightPixels : ").append(out.heightPixels).append("\n");
        sb.append("scaledDensity : ").append(out.scaledDensity).append("\n");
        sb.append("widthPixels : ").append(out.widthPixels).append("\n");
        sb.append("xdpi : ").append(out.xdpi).append("\n");
        sb.append("ydpi : ").append(out.ydpi).append("\n\n\n");
        
        mTvResult.setText(sb);
        
    }
    
}
