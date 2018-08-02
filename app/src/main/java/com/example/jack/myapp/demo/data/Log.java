package com.example.jack.myapp.demo.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;

public class Log {
	private static Log _lg;
	/** 日志保存路径 */
	private static final String LOG_SAVE_PATH = Environment.getExternalStorageDirectory()+"/Log/"; // sd卡上的日志文件目录
	/** 日志开关 */
	private static final boolean LOG_SWITCH = true;

	// single
	public static Log getInstance() {
		if (_lg == null) {
			_lg = new Log();
		}
		return _lg;
	}

	/** 插入日志 */
	public void writeLog(String msg) {

		if (msg == null)
			return;
		System.out.println(msg);
		if (LOG_SWITCH) {
			File file = checkLogFileIsExist();
			if (file == null)
				return;
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file, true);
//				SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
				String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
				fos.write((date + "	" + msg)
						.getBytes("gbk"));
				fos.write("\r\n".getBytes("gbk"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				file = null;
			}
		}
	}

	/**
	 * 打印异常堆栈信息
	 *
	 * @param e
	 * @return
	 */
	public void WriteStackTrace(Throwable e) {
		if (e != null) {
			// StringWriter sw = new StringWriter();
			// PrintWriter pw = new PrintWriter(sw);
			// e.printStackTrace(pw);
			// return sw.toString();
			writeLog(e.getStackTrace().toString());
		}
		// return "";
	}

	/** 检查日志文件是否存在 */
	private File checkLogFileIsExist() {
		// if(!MemorySpaceManager.isSDExist()){ //sd 卡是否存在
		// return null;
		// }
		File file = new File(LOG_SAVE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
//		file = new File(LOG_SAVE_PATH +Constants.userIdStr+ dateStr + ".txt");
		file = new File(LOG_SAVE_PATH + dateStr +"_"+Constants.userIdStr+ ".txt");
		if (!isLogExist(file)) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sdf = null;
		return file;
	}

	/**
	 * 检查当天日志文件是否存在
	 *
	 * @param file
	 * @return
	 */
	private boolean isLogExist(File file) {
		boolean ret = false;
		try {
			File tempFile = new File(LOG_SAVE_PATH);
			File[] files = tempFile.listFiles();
			if (files == null)
				return ret;
			for (int i = 0; i < files.length; i++) {
				String name = files[i].getName().trim();
				if (name != null && name.equalsIgnoreCase(file.getName())) {
					ret = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}