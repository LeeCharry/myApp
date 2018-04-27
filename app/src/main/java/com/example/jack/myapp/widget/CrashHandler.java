package com.example.jack.myapp.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * 日志处理工具类
 * https://blog.csdn.net/u012333411/article/details/49019415
 */
@SuppressLint("SimpleDateFormat")
public class CrashHandler implements UncaughtExceptionHandler {

    public static String TAG = "MyCrash";
    // ϵͳĬ�ϵ�UncaughtException������
    private UncaughtExceptionHandler mDefaultHandler;

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;

    // �����洢�豸��Ϣ���쳣��Ϣ
    private Map<String, String> infos = new HashMap<String, String>();

    // ���ڸ�ʽ������,��Ϊ��־�ļ�����һ����
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /** ��ֻ֤��һ��CrashHandlerʵ�� */
    private CrashHandler() {
    }

    /** ��ȡCrashHandlerʵ�� ,����ģʽ */
    public static CrashHandler getInstance() {
        return instance;
    }

    /**
     * ��ʼ��
     * 
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // ��ȡϵͳĬ�ϵ�UncaughtException������
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // ���ø�CrashHandlerΪ�����Ĭ�ϴ�����
        Thread.setDefaultUncaughtExceptionHandler(this);
        autoClear(5);
    }

    /**
     * ��UncaughtException����ʱ��ת��ú���������
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // ����û�û�д�������ϵͳĬ�ϵ��쳣������������
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            SystemClock.sleep(3000);
            // �˳�����
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.
     * 
     * @param ex
     * @return true:��������˸��쳣��Ϣ; ���򷵻�false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        try {
            // ʹ��Toast����ʾ�쳣��Ϣ
            new Thread() {

                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(mContext, "很抱歉，程序出现异常，即将重启.",
                            Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }.start();
            // �ռ��豸������Ϣ
            collectDeviceInfo(mContext);
            // ������־�ļ�
            saveCrashInfoFile(ex);
//            Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
//            mContext.startActivity(intent);
            SystemClock.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * �ռ��豸������Ϣ
     * 
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName + "";
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * ���������Ϣ���ļ���
     * @param ex
     * @return �����ļ�����,���ڽ��ļ����͵�������
     * @throws Exception
     */
    private String saveCrashInfoFile(Throwable ex) throws Exception {
        StringBuffer sb = new StringBuffer();
        try {
            SimpleDateFormat sDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String date = sDateFormat.format(new Date());
            sb.append("\r\n" + date + "\n");
            for (Map.Entry<String, String> entry : infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key + "=" + value + "\n");
            }

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.flush();
            printWriter.close();
            String result = writer.toString();
            sb.append(result);

            String fileName = writeFile(sb.toString());
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
            sb.append("an error occured while writing file...\r\n");
            writeFile(sb.toString());
        }
        return null;
    }

    private String writeFile(String sb) throws Exception {
        String time = formatter.format(new Date());
        String fileName = "crash-" + time + ".log";
        if (FileUtil.hasSdcard()) {
            String path = getGlobalpath();
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();
            FileOutputStream fos = new FileOutputStream(path + fileName, true);
            fos.write(sb.getBytes());
            fos.flush();
            fos.close();
        }
        return fileName;
    }

    public static String getGlobalpath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "crash" + File.separator;
    }
    
    public static void setTag(String tag) {
        TAG = tag;
    }
    
    /**
     * �ļ�ɾ��
     */
    public void autoClear(final int autoClearDay) {
    	FileUtil.delete(getGlobalpath(), new FilenameFilter() {

            @Override
            public boolean accept(File file, String filename) {
                String s = FileUtil.getFileNameWithoutExtension(filename);
                int day = autoClearDay < 0 ? autoClearDay : -1 * autoClearDay;
                String date = "crash-" + DateUtil.getOtherDay(day);
                return date.compareTo(s) >= 0;
            }
        });
    }

}
