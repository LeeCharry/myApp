package com.example.jack.myapp.demo.data;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class DES {
	byte rawKeyData[];
	byte[] encryptedData;
	public Key key;
//	// ��Կ
//	public void Key() {
//		try {
//			SecureRandom sr = new SecureRandom();
//			// Ϊ����ѡ���DES�㷨����һ��KeyGenerator����
//			KeyGenerator kg = KeyGenerator.getInstance("DES");
//			kg.init(sr);
//			// �����ܳ�
//			SecretKey key = kg.generateKey();
//			// ��ȡ�ܳ�����
//			rawKeyData = key.getEncoded();
//			// System.out.println("�ܳ�===>" + rawKeyData);
//		} catch (Exception e) {
//
//		}
//	}

	public String jiaMi(String string) {
		String tempStr=null;
		if(Constants.isDecrypt)
		{
			// 2.1 >>> ���ü��ܷ���
			try {
				if (string == null) {
					return null;
				} else {
					encryptedData = encrypt(rawKeyData, string);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempStr = byteArr2HexStr(encryptedData);	
		}
		else
		{
			tempStr=string;	
		}
		return tempStr;
	}

	public String jieMI(String string) {
		String tempStr=null;
		if(Constants.isDecrypt){
		try {
			
			if (string == null) {
				return null;
			} else {
				tempStr = decrypt(rawKeyData, hexStr2ByteArr(string));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else
		{
			tempStr=string;	
		}
		return tempStr;
	}

	public String decrypt(byte rawKeyData[], byte[] encryptedData)
			throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException {
		// // DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();
		// // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
		// DESKeySpec dks = new DESKeySpec(rawKeyData);
		// // ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����һ��SecretKey����
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// SecretKey key = keyFactory.generateSecret(dks);
		// // Cipher����ʵ����ɽ��ܲ���
		try {
			
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom("techown".getBytes()));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cipher cipher = Cipher.getInstance("DES");
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		// ��ʽִ�н��ܲ���
		byte decryptedData[] = cipher.doFinal(encryptedData);
		// System.out.println("���ܺ�===>" + new String(decryptedData));
		return new String(decryptedData);
	}

	public byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);// ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {// �Ѹ���ת��Ϊ����
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {// С��0F������Ҫ��ǰ�油0
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public byte[] encrypt(byte rawKeyData[], String str)throws InvalidKeyException, NoSuchAlgorithmException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchPaddingException, InvalidKeySpecException {
		// // DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();
		// // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
		// DESKeySpec dks = new DESKeySpec(rawKeyData);
		// // ����һ���ܳ׹�����Ȼ��������DESKeySpecת����һ��SecretKey����
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// SecretKey key = keyFactory.generateSecret(dks);
		// // Cipher����ʵ����ɼ��ܲ���
		try {
			
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom("techown".getBytes()));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			
		}
		Cipher cipher = Cipher.getInstance("DES");
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// ���ڣ���ȡ���ݲ�����
		byte data[] = str.getBytes();
		// ��ʽִ�м��ܲ���
		byte[] encryptedData = cipher.doFinal(data);

		// System.out.println("���ܺ�===>" + encryptedData);
		return encryptedData;
	}
}
