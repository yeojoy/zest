package me.yeojoy.zestproject.fragment;

import me.yeojoy.zestproject.R;
import me.yeojoy.zestproject.SecondActivity;
import me.yeojoy.zestproject.entity.EventEntity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class LeftFragment extends Fragment implements OnClickListener, OnLongClickListener {
    
    private static final String TAG = LeftFragment.class.getSimpleName();
    
    private TextView mTvResult;
    private EditText mEtMessage;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test, container, false);
        mTvResult = (TextView) view.findViewById(R.id.tv_result);
        
        mEtMessage = (EditText) view.findViewById(R.id.et_message);
        
        view.findViewById(R.id.btn_refresh).setOnClickListener(this);
        view.findViewById(R.id.btn_send).setOnClickListener(this);
        view.findViewById(R.id.btn_refresh).setOnLongClickListener(this);
        view.findViewById(R.id.btn_send).setOnLongClickListener(this);
        
        SecondActivity.mBus.register(this);
        
        return view;
        
    }
    
    @Override
    public void onStart() {
        super.onStart();
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
        switch (v.getId()) {
            case R.id.btn_send:
                String text = mEtMessage.getText().toString();
                if (TextUtils.isEmpty(text)) return;
                
                EventEntity entity = new EventEntity();
                entity.message = text;
                SecondActivity.mBus.post(entity);
                setText(text);
                mEtMessage.setText("");
                break;
            
            case R.id.btn_refresh:
                mTvResult.setText("");
                break;
        }
    }
    
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                
                return true;
                
            case R.id.btn_refresh:
                
                return true;
                
        }
        return false;
    }
    
    private void setText(String message) {
        StringBuilder sb = new StringBuilder(mTvResult.getText());
        sb.append("\n");
        sb.append(message);
        mTvResult.setText(sb);
    }
    
}
