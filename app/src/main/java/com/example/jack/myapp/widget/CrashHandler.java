package com.example.jack.myapp.widget;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

/**   
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.  
 *  
 *  需要在Application中注册，为了要在程序启动器就监控整个程序。 
 */  
public class CrashHandler implements UncaughtExceptionHandler{
	 //系统默认的UncaughtException处理类     
	private UncaughtExceptionHandler mDefaultHandler;
	 //CrashHandler实例    
	private static CrashHandler INSTANCE;
	//程序的Context对象   
	private Context mContext;
	
	/** 保证只有一个CrashHandler实例 */
	private CrashHandler(){}
	/** 获取CrashHandler实例 ,单例模式 */     
	public static CrashHandler getInstance(){
		if(INSTANCE == null){
			INSTANCE = new CrashHandler();
		}
		return INSTANCE;
	}
	
	/**   
     * 初始化   
     */  
	public void init(Context ctx){
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
	
	
	/**   
     * 当UncaughtException发生时会转入该函数来处理   
     */  
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		if(!handleException(ex) && mDefaultHandler!=null){
			//如果用户没有处理则让系统默认的异常处理器来处理   
			mDefaultHandler.uncaughtException(thread, ex);
		}
		else{
			try{
				Thread.sleep(2000);
			}catch(Exception e){
			}
			//退出程序   
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(10);
		}
	}
	
	/**   
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.   
     *    
     * @param ex   
     * @return true:如果处理了该异常信息;否则返回false.   
     */      
	private boolean handleException(Throwable ex){
		if(ex == null){
			return false;
		}
		new Thread() {

			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, "很抱歉，程序出现异常，即将退出.",
						Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}.start();

		//保存日志文件  
		saveCrashInfoToFile(ex, mContext);
		return true;
	}
	
	/**   
     * 保存错误信息到文件中   
     *    
     * @param ex   
     * @return  返回文件名称,便于将文件传送到服务器   
     */     
	public void saveCrashInfoToFile(Throwable ex, Context ctx){
		
		try{
			String result = null;
			
			Writer info = new StringWriter();
			PrintWriter printWrite = new PrintWriter(info);
			ex.printStackTrace(printWrite);
			
			Throwable cause = ex.getCause();
			while(cause != null){
				cause.printStackTrace(printWrite);
				cause = cause.getCause();
			}
			
			
			PackageManager pm = ctx.getPackageManager();
			
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
			
			if(pi != null){
				result = "程序异常 程序名: "+pi.applicationInfo.loadLabel(pm).toString()+",  版本号:　" + pi.versionName+", " + info.toString();
				Log.Instance().WriteLog(result);
			}
			
			printWrite.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
}
