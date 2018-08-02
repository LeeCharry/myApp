package com.example.jack.myapp.demo.data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;


public class FileUtils {
	private String SDPATH;

	public String getSDPATH() {
		return SDPATH;
	}

	public FileUtils() {
		// 得到当前外部存储设备的目录
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}

	/*
	 * 在sd卡上创建文件
	 */
	public File createSDFILE(String fileName) {
		File file = new File(SDPATH + fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return file;
	}

	/*
	 * 在sd卡上创建目录
	 */
	public File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		if (!dir.exists()) {
			boolean b=dir.mkdirs();
			System.out.println(b);
		}
		return dir;
	}

	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	/*
	 * 将一个Inputstream里的数据写入sd卡中
	 */
	public File write2SDFromInput(String path, String fileName,
								  InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFILE(path + fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			int tmp;
			while ((tmp = (input.read(buffer))) != -1) {
				output.write(buffer, 0, tmp);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public boolean deleteSDFILE(String fileName) {
		boolean isDelete = false;
		if (isFileExist(fileName)) {
			File file = new File(SDPATH + fileName);
			isDelete = file.delete();
		}
		return isDelete;
	}

	public boolean deleteSDFILEPic(String fileName) {
		boolean isDelete = false;
		if (isFileExistPic(fileName)) {
			System.out.println("删除的文件是否存在，文件的名字是："+ fileName);
			File file = new File(fileName);
			isDelete = file.delete();
		}
		return isDelete;
	}

	public boolean isFileExistPic(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	// 删除文件夹下所有文件
	public void delFolder(String folderPath) {
		try {
			// 删除完里面所有内容
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			// 删除空文件夹
			myFilePath.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tmpList = file.list();
		File tmp = null;

		for (int i = 0; i < tmpList.length; i++) {
			if (path.endsWith(File.separator)) {
				tmp = new File(path + tmpList[i]);
			} else {
				tmp = new File(path + File.separator + tmpList[i]);
			}

			if (tmp.isFile()) {
				tmp.delete();
			}
			if (tmp.isDirectory()) {
				delAllFile(path + "/" + tmpList[i]);
				delFolder(path + "/" + tmpList[i]);
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 保存图片
	 * @param path
	 * @param fileName
	 * @param bitmap
	 */
	public void saveBitmap(String path, String fileName, Bitmap bitmap) {
		FileOutputStream fout = null;
		BufferedOutputStream bos = null;
		try {
			System.out.println("RP保存的文件夹名：" + path);
			System.out.println("RP保存的文件名：" + fileName);
			createSDDir(path);
			deleteSDFILE(path + fileName + ".jpg");
			File fPicture = createSDFILE(path + fileName + ".jpg");

			fout = new FileOutputStream(fPicture);
			bos = new BufferedOutputStream(fout);
			bitmap.compress(Bitmap.CompressFormat.JPEG, // 图片格式
					100, // 品质0-100
					bos // 使用的输出流
			);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (Exception e) {
				}
			}
			if (fout != null) {
				try {
					fout.flush();
					fout.close();
				} catch (Exception e) {
				}
			}
		}
		FileEncrypt fileencrypt=new FileEncrypt();
//		fileencrypt.encryptFile(Environment.getExternalStorageDirectory() + "/"+path + fileName + ".jpg");
	}

	/**
	 * 保存图片
	 * @param path
	 * @param fileName
	 * @param bitmap
	 */
	public void saveBitmap1(String path, String fileName, Bitmap bitmap) {
		FileOutputStream fout = null;
		BufferedOutputStream bos = null;
		try {
			System.out.println("RP保存的文件夹名：" + path);
			System.out.println("RP保存的文件名：" + fileName);
			createSDDir(path);
			deleteSDFILE(path + fileName + ".jpg");
			File fPicture = createSDFILE(path + fileName + ".jpg");

			fout = new FileOutputStream(fPicture);
			bos = new BufferedOutputStream(fout);
			bitmap.compress(Bitmap.CompressFormat.JPEG, // 图片格式
					100, // 品质0-100
					bos // 使用的输出流
			);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (Exception e) {
				}
			}
			if (fout != null) {
				try {
					fout.flush();
					fout.close();
				} catch (Exception e) {
				}
			}
		}
		FileEncrypt fileencrypt=new FileEncrypt();
		fileencrypt.encryptFile(Environment.getExternalStorageDirectory() + "/"+path + fileName + ".jpg");
	}

	public Bitmap resizeBitmap(Bitmap bitmap, int newWidth) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float temp = ((float) height) / ((float) width);
		int newHeight = (int) ((newWidth) * temp);
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// matrix.postRotate(45);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		bitmap.recycle();
		bitmap = null;
		return resizedBitmap;
	}
	/**
	 * 删除文件
	 * @param filepath 文件路径
	 */
	public void deletefile(String filepath) {
		File fizip = new File(filepath);
		if (fizip.exists()) {

			fizip.delete();
		Log.getInstance().writeLog(
					"删除目录" + filepath + "下文件成功");
		}
	}
	/**
	 * 删除目录
	 * @param dir 文件
	 */
	public void deleteDir(File dir) {
//		try {
//			Runtime.getRuntime().exec("rm -r "+dir);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return; // 检查参数
		for (File file : dir.listFiles()) {
			if (file.isFile()){
				file.delete(); // 删除所有文件
			} else if (file.isDirectory()){
				deleteDir(file); // 递规的方式删除文件夹
			}
		}
		dir.delete();// 删除目录本身
	}
	/**
	 * 删除目录
	 * @param dir 文件
	 */
	public void deleteDirpic(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory())
			return; // 检查参数
		for (File file : dir.listFiles()) {
			final File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
			file.renameTo(to);
			if (to.isFile()){
				to.delete(); // 删除所有文件
			}
			else if (to.isDirectory()){
				deleteDir(to); // 递规的方式删除文件夹
			}
		}

	}
}
