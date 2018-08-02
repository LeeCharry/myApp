package com.example.jack.myapp.demo.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class FileEncrypt {
	/**
	 * 加密文件
	 * @param filepath 文件路径
	 */
	public void encryptFile(String filepath)  {
		if(Constants.isOpenRequired1){
			File dir=new File(filepath);
			//如果传入的是文件路径则加密文件
			if (dir == null || !dir.exists() || !dir.isDirectory())
			{
				encrypt(dir.getPath());
				return;
			}
			//如果传入的是目录路径则递规的方式加密目录
			for (File file : dir.listFiles()) {
				if (file.isFile())
				{
					encrypt(file.getPath());
				}
				else if (file.isDirectory())
				{
					encryptFile(file.getPath());
				}

			}
		}
	}
	/**
	 * 加密方法
	 * @param filepath 文件路径
	 */
	public void encrypt(String filepath)
	{
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(filepath, "rw");
			int value = -1;
			int i=0;
			while((value = raf.read()) != -1){
				//如果是txt文件则全部文件异或加密
				if(filepath.endsWith(".txt"))
				{
					long pointer = raf.getFilePointer();
					raf.seek(pointer - 1);
					raf.write(value ^ Constants.password.hashCode());

				}
				//如果是其他文件则只异或机密文件的前2个字符
//                	 else
//                	 {
//                		 if(i<2)
//                 	 	{
//                 	 		  long pointer = raf.getFilePointer();
//                               raf.seek(pointer - 1);
//                               raf.write(value ^ Constants.password.hashCode());
//                               i++;
//                 	 	}
//                 	 	else{
//                 	 		break;
//                 	 	} 
//                	 }

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				raf.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
