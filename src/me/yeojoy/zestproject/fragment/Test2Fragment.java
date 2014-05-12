package me.yeojoy.zestproject.fragment;

import me.yeojoy.zestproject.R;
import me.yeojoy.zestproject.util.FileLogger;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Test2Fragment extends Fragment implements OnClickListener {
    
    private static final String TAG = Test2Fragment.class.getSimpleName();
    
    private static int mCounter = 100;
    
    private TextView mTvResult;
    private ImageView mIvImage;
    
    private Button mBtnAdder;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_2, container, false);
        
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);
        
        mIvImage = (ImageView) view.findViewById(R.id.iv_image);
        
        mBtnAdder = (Button) view.findViewById(R.id.btn_adder);
        mBtnAdder.setOnClickListener(this);
        
        return view;
        
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        FileLogger.startLogger(getActivity(), "");
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_adder) {
            mCounter = mCounter + 10;
            FileLogger.writeLogToFile(String.valueOf(mCounter));
            mTvResult.append(mCounter + "\n");
        }
    }
    
}
