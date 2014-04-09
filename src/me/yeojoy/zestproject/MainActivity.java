package me.yeojoy.zestproject;

import me.yeojoy.zestproject.fragment.Test1Fragment;
import me.yeojoy.zestproject.fragment.Test2Fragment;
import me.yeojoy.zestproject.fragment.Test3Fragment;
import me.yeojoy.zestproject.fragment.Test4Fragment;
import me.yeojoy.zestproject.fragment.Test5Fragment;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    
    public static final int TEST_1_FRAGMENT = 10001;
    public static final int TEST_2_FRAGMENT = 10002;
    public static final int TEST_3_FRAGMENT = 10003;
    public static final int TEST_4_FRAGMENT = 10004;
    public static final int TEST_5_FRAGMENT = 10005;
    
    private FragmentManager mFragmentManager;
    
    private int mCurrentFragmentIndex;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
        mFragmentManager = getFragmentManager();
        
        showNewFragment(TEST_5_FRAGMENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu()");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.id.frag_1:
                showAnotherFragment(TEST_1_FRAGMENT);
                break;
                
            case R.id.frag_2:
                showAnotherFragment(TEST_2_FRAGMENT);
                
                break;
                
            case R.id.frag_3:
                showAnotherFragment(TEST_3_FRAGMENT);
                
                break;
                
            case R.id.frag_4:
                showAnotherFragment(TEST_4_FRAGMENT);
                
                break;
            case R.id.frag_5:
                showAnotherFragment(TEST_5_FRAGMENT);
                
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private Fragment getFragment(int index) {
        Log.d(TAG, "getFragment()");
        mCurrentFragmentIndex = index;
        
        Fragment frag = null;
        
        switch (index) {
            
            case TEST_1_FRAGMENT:
                frag = new Test1Fragment();
                break;
                
            case TEST_2_FRAGMENT:
                frag = new Test2Fragment();
                break;
                
            case TEST_3_FRAGMENT:
                frag = new Test3Fragment();
                break;
                
            case TEST_4_FRAGMENT:
                frag = new Test4Fragment();
                break;
                
            case TEST_5_FRAGMENT:
                frag = new Test5Fragment();
                break;
                
                
        }
        
        if (frag != null) return frag;
        
        return null;
    }
    
    
    
    private void showNewFragment(int index) {
        Log.d(TAG, "showNewFragment()");
        if (index != mCurrentFragmentIndex)
            mFragmentManager.beginTransaction()
                    .add(R.id.rl_container, getFragment(index)).commit();
    } 
    
    private void showAnotherFragment(int index) {
        Log.d(TAG, "showAnotherFragment()");
        if (index != mCurrentFragmentIndex)
            mFragmentManager.beginTransaction()
                    .replace(R.id.rl_container, getFragment(index)).commit();
    }
    
    private void showAnotherFragmentAndAddToBackStack(int index) {
        Log.d(TAG, "showAnotherFragmentAndAddToBackStack()");
        
        if (index != mCurrentFragmentIndex) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.rl_container, getFragment(index));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    
}
