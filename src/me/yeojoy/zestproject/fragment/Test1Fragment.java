package me.yeojoy.zestproject.fragment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.yeojoy.zestproject.R;
import me.yeojoy.zestproject.ui.CustomToast;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test1Fragment extends Fragment {
    
    private static final String TAG = Test1Fragment.class.getSimpleName();
    
    private TextView mTvResult;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_1, container, false);
        
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);
        mTvResult.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                CustomToast.showToastCenter(getActivity(), "ㅎㅎㅎㅎ");
            }
        });
        return view;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
        Date date1 = Date.valueOf("2013-06-01");
        
        Date date2 = new Date(System.currentTimeMillis());

        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.DAY_OF_YEAR, (12 * 7 - 1));
        
        StringBuilder sb = new StringBuilder();
        sb.append(date1.toString()).append("\n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sb.append(sdf.format(cal.getTime())).append("\n\n");
        
        cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.MONTH, 3);
        
        sb.append(date1.toString()).append("\n");
        sb.append(sdf.format(cal.getTime()));
        
        mTvResult.setText(sb);
        mTvResult.append("\n\n");
        
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void[] params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                Log.d("yeojoy", "첫번째");
                mTvResult.append("\n첫 번째 꺼");
            }
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }.execute();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void[] params) {
                try {
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                Log.d("yeojoy", "두번째");
                mTvResult.append("\n 두 번째 꺼");
                
            }
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }.execute();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void[] params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                Log.d("yeojoy", "세번째");
                mTvResult.append("\n 세 번째 꺼");
            }
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }.execute();
        
    }
    
}
