package me.yeojoy.zestproject;

import com.squareup.otto.Bus;

import me.yeojoy.zestproject.fragment.LeftFragment;
import me.yeojoy.zestproject.fragment.RightFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class SecondActivity extends FragmentActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();
    
    private FragmentManager mFragmentManager;
    
    private Fragment mLeftFrag, mRightFrag;
    
    public static Bus mBus = new Bus();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        setContentView(R.layout.activity_second);
        
        mFragmentManager = getSupportFragmentManager();
        
        mLeftFrag = (LeftFragment) mFragmentManager.findFragmentById(R.id.frag_left);
        mRightFrag = (RightFragment) mFragmentManager.findFragmentById(R.id.frag_right);
        
    }
}
