package me.yeojoy.zestproject;

import me.yeojoy.zestproject.fragment.Test1Fragment;
import me.yeojoy.zestproject.fragment.Test2Fragment;
import me.yeojoy.zestproject.fragment.Test3Fragment;
import me.yeojoy.zestproject.fragment.Test4Fragment;
import me.yeojoy.zestproject.fragment.Test5Fragment;
import me.yeojoy.zestproject.fragment.Test6Fragment;
import me.yeojoy.zestproject.fragment.Test7Fragment;
import me.yeojoy.zestproject.fragment.Test8Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    
    private FragmentManager mFragmentManager;
    
    private ViewPager mFragmentPager;
    
    private int mCurrentFragmentIndex;
    
    private int[] mFragmentIds;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mFragmentIds = getResources().getIntArray(R.array.fragment_ids);
        
//        showNewFragment(mFragmentIds[mFragmentIds.length - 1]);
        
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(mFragmentManager);
        mFragmentPager = (ViewPager) findViewById(R.id.vp_pager);
        mFragmentPager.setAdapter(fragmentPagerAdapter);
        
        mFragmentPager.setCurrentItem(mFragmentIds[mFragmentIds.length - 1]);
        
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
            case R.id.action_second:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
                
            case R.id.frag_1:
                showAnotherFragment(mFragmentIds[0]);
                break;
                
            case R.id.frag_2:
                showAnotherFragment(mFragmentIds[1]);
                
                break;
                
            case R.id.frag_3:
                showAnotherFragment(mFragmentIds[2]);
                
                break;
                
            case R.id.frag_4:
                showAnotherFragment(mFragmentIds[3]);
                
                break;
            case R.id.frag_5:
                showAnotherFragment(mFragmentIds[4]);
                
                break;
            case R.id.frag_6:
                showAnotherFragment(mFragmentIds[5]);
                
                break;
            case R.id.frag_7:
                showAnotherFragment(mFragmentIds[6]);
                
                break;
            case R.id.frag_8:
                showAnotherFragment(mFragmentIds[7]);
                
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private Fragment getFragment(int index) {
        Log.d(TAG, "getFragment()");
        mCurrentFragmentIndex = index;
        
        Fragment frag = null;
        
        switch (index) {
            
            case 0:
                frag = new Test1Fragment();
                break;
                
            case 1:
                frag = new Test2Fragment();
                break;
                
            case 2:
                frag = new Test3Fragment();
                break;
                
            case 3:
                frag = new Test4Fragment();
                break;
                
            case 4:
                frag = new Test5Fragment();
                break;
                
            case 5:
                frag = new Test6Fragment();
                break;
                
            case 6:
                frag = new Test7Fragment();
                break;
                
            case 7:
                frag = new Test8Fragment();
                break;
                
                
        }
        
        if (frag != null) return frag;
        
        return null;
    }
    
    
    
    private void showNewFragment(int index) {
        Log.d(TAG, "showNewFragment()");
        if (index != mCurrentFragmentIndex)
            mFragmentManager.beginTransaction()
                    .add(R.id.vp_pager, getFragment(index)).commit();
    } 
    
    private void showAnotherFragment(int index) {
        Log.d(TAG, "showAnotherFragment()");
        if (index != mCurrentFragmentIndex)
            mFragmentManager.beginTransaction()
                    .replace(R.id.vp_pager, getFragment(index)).commit();
    }
    
//    private void showAnotherFragmentAndAddToBackStack(int index) {
//        Log.d(TAG, "showAnotherFragmentAndAddToBackStack()");
//        
//        if (index != mCurrentFragmentIndex) {
//            FragmentTransaction transaction = mFragmentManager.beginTransaction();
//            transaction.replace(R.id.vp_pager, getFragment(index));
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
//    }
    
 // Since this is an object collection, use a FragmentStatePagerAdapter,
 // and NOT a FragmentPagerAdapter.
    public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
        public FragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
    
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = getFragment(i);
            return fragment;
        }
    
        @Override
        public int getCount() {
            return mFragmentIds.length;
        }
           
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
                case 0:
                    title = getResources().getString(R.string.frag_1);
                    break;
                case 1:
                    title = getResources().getString(R.string.frag_2);
                    break;
                case 2:
                    title = getResources().getString(R.string.frag_3);
                    break;
                case 3:
                    title = getResources().getString(R.string.frag_4);
                    break;
                case 4:
                    title = getResources().getString(R.string.frag_5);
                    break;
                case 5:
                    title = getResources().getString(R.string.frag_6);
                    break;
                case 6:
                    title = getResources().getString(R.string.frag_7);
                    break;
                case 7:
                    title = getResources().getString(R.string.frag_8);
                    break;
                 
            }
            return title;
        }
    }
}
