
package me.yeojoy.zestproject.fragment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import me.yeojoy.zestproject.R;
import me.yeojoy.zestproject.service.WebParserIntentService;
import my.lib.MyLog;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test8Fragment extends Fragment implements OnClickListener, OnLongClickListener {

    private static final String TAG = Test8Fragment.class.getSimpleName();

    private TextView mTvResult;
    
    private AlarmManager alarmManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_8, container, false);
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);

        view.findViewById(R.id.btn_adder).setOnClickListener(this);
        view.findViewById(R.id.btn_adder).setOnLongClickListener(this);
        
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
    }
    
    @Override
    public void onStop() {
        super.onStop();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private PendingIntent pending;
    @Override
    public void onClick(View v) {
        MyLog.i(TAG, "onClick()");
        if (v.getId() == R.id.btn_adder) {
            Intent intent = new Intent(getActivity(), WebParserIntentService.class);
            pending = PendingIntent.getService(getActivity(), 0, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC, 
                    System.currentTimeMillis() + 1000, 1000 * 30, pending);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.btn_adder) {
            alarmManager.cancel(pending);
            return true;
        }
        return false;
    }

    private void setText(final Location location) {
        
        AsyncTask<Void, Void, Source> task = new AsyncTask<Void, Void, Source>() {
            @Override
            protected Source doInBackground(Void... params) {
                final String url = "http://cleanair.seoul.go.kr/air_city.htm?method=measure";
                
                try {
                    return new Source(new URL(url));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            
            @Override
            protected void onPostExecute(Source source) {
                super.onPostExecute(source);
                if (source == null) { 
                    MyLog.i(TAG, "result is null");
                    return;
                }
                
                List<Element> table = source.getAllElements(HTMLElementName.TR);
                
                for (Element e : table) {
                    MyLog.d(TAG, "String : " + e.getTextExtractor().toString());
                }
                
            }
        };
        
        task.execute();
    }
}
