package me.yeojoy.zestproject.ui;

import me.yeojoy.zestproject.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {
    public static void showToastCenter(Context context, String msg) {
        Toast toast = new Toast(context);
        
        LayoutInflater inflater = LayoutInflater.from(context);
        View toastView = inflater.inflate(R.layout.custom_toast, null);
        TextView tvMgs = (TextView) toastView.findViewById(R.id.tv_toast_msg);
        
        tvMgs.setText(msg);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
