package me.yeojoy.zestproject.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

public class FileLogger {
    
    private static final String TAG = FileLogger.class.getSimpleName();
    
    private static final int MAX_FILE_SIZE = 100;
    private static final String PREFIX_FILE = "zest_";
    private static final String SUBFIX_FILE = ".txt";
    private static final String PATH_LOG = "/aLog/";
    private static String mTimeForFileName;
    private static String mTime;
    private static String mFileName;
    private static String mFilePath;
    
    private static SharedPreferences mPrefs;
    
    public static void startLogger(Context context, String cronExp) {
        Log.i(TAG, "startLogger()");
        mPrefs = context.getSharedPreferences("file_logger", Context.MODE_PRIVATE);
        
        if (makeLoggerFile(context)) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n=================================================\n");
            sb.append("  Every ").append(cronExp).append(" minute(s), update step data.").append("\n");
            sb.append("=================================================\n");

            writeLogToFile(sb.toString());
        }
    }
    
    private static boolean makeLoggerFile(Context context) {
        Log.i(TAG, "makeLoggerFile()");
        
        mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + PATH_LOG;
        
        File dir = new File(mFilePath);
        
        if (!dir.exists()) {
            Log.i(TAG, "makeLoggerFile(), path doesn't exist. mkdir");
            dir.mkdir();
        }

        mTimeForFileName = mPrefs.getString("log_date", "");
        
        if (TextUtils.isEmpty(mTimeForFileName)) {
            mTimeForFileName = new SimpleDateFormat("MMdd_HHmm").format(new Date());
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString("log_date", mTimeForFileName);
            editor.apply();
        }

        mTime = mTimeForFileName;
        mFileName = PREFIX_FILE + mTime + SUBFIX_FILE;
        
        File logFile = new File(mFilePath + mFileName);
        Log.i(TAG, "makeLoggerFile(), File Path is " + logFile.getAbsolutePath());
        
        if (logFile.exists()) {
            Log.i(TAG, "makeLoggerFile(), File exists.");
            // 파일 크기 비교 후 새로 생성
            if (logFile.length() > (MAX_FILE_SIZE * 1024)) {
                Log.i(TAG, "makeLoggerFile(), over Max size");
                makeLoggerFile(context);
                
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("log_date", null);
                editor.apply();
            }
        } else {
            // 파일 생성
            try {
                logFile.createNewFile();
                Log.i(TAG, "makeLoggerFile(), create a new file.");
            } catch (IOException e) {
                e.printStackTrace();
                
                return false;
            }
        }
        
        return true;
    }
    
    public static void writeLogToFile(String message) {
        if (message == null) return;
        
        Log.i(TAG, "writeLogToFile()");
        
        File logFile = new File(mFilePath + mFileName);
        
        Log.i(TAG, "writeLogToFile(), File is " + logFile.getAbsolutePath());
        FileInputStream fis = null;
        StringBuilder preDataBuilder = new StringBuilder();
        String preData = null;
        try {
            if (logFile.exists() && logFile.length() > 0) {
                Log.i(TAG, "writeLogToFile(), Log file exists.");
                fis = new FileInputStream(logFile);
                int avail = fis.available();
                byte[] data = new byte[avail];
                
                while (fis.read(data) != -1) {
                    preDataBuilder.append(new String(data));
                }
                
                preData = preDataBuilder.toString();
                if (!TextUtils.isEmpty(preData))
                    Log.d(TAG, "Message : " + preData);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {}
        }
        
        FileOutputStream fos = null;
                
        StringBuilder msgBuilder = new StringBuilder();
        
        try {
            fos = new FileOutputStream(logFile);
            
            // 먼저 데이터를 쓴 다음 새로운 데이터를 쓴다.
            if (!TextUtils.isEmpty(preData))
                fos.write(preData.getBytes());
            
            msgBuilder.append(getTimeNow(new Date())).append(" : ").append(message).append("\n\n");
            fos.write(msgBuilder.toString().getBytes());
            Log.i(TAG, "writeLogToFile(), write that successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void writeLogToFile(Date date) {
        Log.i(TAG, "writeLogToFile()");
        
        writeLogToFile(getTimeNow(date));
    }
    
    private static String getTimeNow(Date now) {
        Log.i(TAG, "getTimeNow()");
        if (now == null)
            now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return formatter.format(now);
    }
}
