
package me.yeojoy.zestproject.fragment;

import java.util.Calendar;

import me.yeojoy.zestproject.R;
import my.lib.MyLog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test7Fragment extends Fragment implements OnClickListener, OnLongClickListener {

    private static final String TAG = Test7Fragment.class.getSimpleName();

    private TextView mTvResult, mTvTime;

    private long theTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.frag_test_7, container, false);
        mTvResult = (TextView) view.findViewById(R.id.tv_test1_result);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);

        view.findViewById(R.id.btn_adder).setOnClickListener(this);
        view.findViewById(R.id.btn_adder).setOnLongClickListener(this);
        
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(batter, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        setText();
    }
    
    BroadcastReceiver batter = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            MyLog.d(TAG, "Batter Level : " + level);
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(batter);
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
            fireNotification();
            return true;
        }
        return false;
    }

    private void fireNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity())
        .setSmallIcon(R.drawable.sample_profile_pic)
        .setContentTitle("Event tracker")
        .setContentText("Events received");
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Event tracker details:");
        
        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
    
            inboxStyle.addLine(events[i]);
        }
        
        // Moves the big view style object into the notification object.
        mBuilder.setStyle(inboxStyle);
        // Issue the notification here.
        
        NotificationManager manager = (NotificationManager) 
                getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
    }

    private void setText() {
        Long year = 1000 * 60 * 60 * 24 * 365l;

        StringBuilder sb = new StringBuilder();
        sb.append("year : " + year).append("\n");
        sb.append("cal year : " + Calendar.YEAR).append("\n");

        sb.append("MAX VALUE : ").append(Long.MAX_VALUE).append("\n");
        sb.append("MAX VALUE : ").append(Integer.MAX_VALUE).append("\n");

        mTvResult.setText(sb);

        String num = "01034871111";
        String[] numberParts = new String[3];
        if (!num.contains("-")) {
            numberParts[0] = num.substring(0, 3);
            numberParts[1] = num.substring(3, 7);
            numberParts[2] = num.substring(7, 11);
        }
        mTvResult.append("\n" + numberParts[0] + "\n");
        mTvResult.append(numberParts[1] + "\n");
        mTvResult.append(numberParts[2] + "\n");
    }

}
