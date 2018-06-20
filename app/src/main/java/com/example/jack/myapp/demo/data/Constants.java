package com.example.jack.myapp.demo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.string;

public class Constants {
	
	// public static final String urlstr =
	// "http://193.1.232.32:80/njbank/mobile/";//����`
//	 public static final String urlstr =
//	 "http://193.1.232.32:82/njbank/mobile/";//Ԥ����`
	// public static final String urlstr =
	// "http://192.168.1.203:8080/njbank/mobile/";
	// public static final String urlstr =
	// "http://116.231.84.108:8080/njbank/mobile/";// ��˾����
	// public static final String
	// urlstr="http://193.1.232.32:83/njbank/mobile/";//�Ͼ����в��� 3g�������Ի�����ַ
	// public static final String
	// urlstr="http://192.168.1.194:8080/njbank/mobile/";
	// public static final String
	// urlstr="http://193.1.232.32:81/njbank/mobile/";//
									
//	public static String urlstr = "http://159.191.1.48:8080/njbank/mobile/";//Ԥ����
	public static String urlstr = "http://159.191.1.44:8080/njbank/mobile/";//UAT4
			
	public static final String THEME_PACKAGE_NAME = "theme_package_name";// sharedprefs�б�������ļ���
	public static final String DEFAULT_THEME_PACKAGE = "com.techown.cfccc"; // ��Ŀ����
	public static final String THEME_ACTION = "com.app.cfcccshin.Theme";// ��Ҫ�滻������
																						
	public static boolean isDecrypt = false;// ʱ����ܿ���z
	public static String password = "2805143";// �ļ���������		
	public static String applyid;// ҵ������id		
	public static String authNumber;// �������id					
	public static String Number;// ���������Ȩ��
	public static String storeid;// �̻�id

	public static boolean whetherEntrusted = false; // �Ƿ�����֧��
	public static String PIC_PATH = "";
	/**
	 * 1 ���� 2 ���� 3 �������Ͽ�ѡ��
	 */
	public static int mPhotoRequest = 0;
		
	// yzh
	public static String[] STOPE_CHAIN = null;
	public static String[] storeNum = null; // �̻����
	public static String[] plan = null; // ���״�plan

	// public static String[] custClass=null; // plan�ͻ�Ⱥ
	// public static Map<String, String>custMap = null;
	public static String[] storeName = new String[3]; //
	public static String[] sfclCardAddStoreName = null; //
	public static String[] sfclCardAddSTOPE_CHAIN = null;
		
	public static String KEY = null;// ��Կ
	public static String ISREGISTED = null;
	public static String UUID = null;// �豸��
	public static String futureField04 = null;
	public static String maritalstatus = "";// ����״��
	public static String customerType = null;// Ԥ���ύ������
	public static String loginmodel = "0";// �Ƿ��״ε���
	public static String bank = ""; // �Ƽ�����Ĭ����

	public static String pagecount = "0";// ��ҳ��
	public static Integer accessoryPhotoStage = 0;// ��������ı�־�����Ǹ�λ��

	public static boolean homeInvestigation = false;
	public static boolean companyInvestigation = false;

	public static String homeorcompany = ""; // �ж��Ǽҷû������

	
	// public static String orgCode= "070" ; //Ա����½������ ������ ��û�����
	public static String orgCode = "301"; // Ա����½������ ������ ��û�����
	public static String city = "";// Ա����½����������

	public static boolean isOpenRequired = true;// �ж���Ƭ�Ƿ�����
	public static boolean isOpenRequired1 = false;

	public static String userIdStr = "";// Ա����

	public static String name = "";// Ա������
	public static String phone = "";// Ա���ֻ�����

	public static String dbName = "cfccc.db"; // ���ݿ�����
	public static int dbVer = 16; // ���ݿ�汾�� 

	public static String picBiPai = "��ȷ�ϱ������Ƿ����";

	public static boolean submitSucceed = false;// �ύ�ɹ�

	public static String auther;

	public static boolean isNext;// Ա����ѵʱͼƬ�Ƿ�����һ��

	public static boolean jiaose = true;// ��ɫ����

//	// uat4,��� ip��port
	public static String ip = "159.191.1.44";// ip ��ַ
	public static String port = "8080";// �˿ں� 
	//Ԥ����
//	public static String ip = "180.166.238.14";// ip ��ַ
//	public static String port = "7788";// �˿ں� 
	public static String picIp = "http://159.191.1.165:3670/docsrv/public/photo.do?urn=";// ͼƬ����ӿ�
																						// uat4
	
	public static String smallPicIp = "http://159.191.1.165:3670/docsrv/public/wfphoto.do?urn=";// ͼƬ����ӿ�
	public static String appVersion = "nj:1.0.4.4"; // �����������Ʒ�
	public static String testUser = "0100701001"; // ������Ա
	public static Boolean isUat = true; // �����������Ʒ�
	public static Boolean isFake = false;// �Ƿ��Ǽ����ݣ�ȥ��˾����Ū�������
	public static Boolean isNjUat4 = false;// �Ƿ����Ͼ��Ǳ������������uat4
			
	// ��Ȩ��������
	public static String authorName;
	public static String userCity; // �û�����
	public static String userOrganaziton; // �û�������
	public static String visitorName;  //���Ա����

	public static HashMap<String, String> cityHashMap = new HashMap<String, String>(); // ���Ա���к�
	public static HashMap<String, String> organazitonHashMap = new HashMap<String, String>(); // ���Ա������
	public static HashMap<String, String> custemerTypeMap = new HashMap<String, String>(); // �ͻ����
				
				
	public static HashMap<String, String> visitorAdministorMap = new HashMap<String, String>(); // ���Ա����Աmap
	public static HashMap<String, HashMap<String, String>> visitor = new HashMap<String, HashMap<String, String>>(); // �ͻ����
	public static HashMap<String, String> nanjinvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> wuxivisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> suzhouvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> natongvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> taizhouvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> yangzhouvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> shanghaivisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> hangzhouvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> changzhouvisitor = new HashMap<String, String>(); // �ͻ����
	public static HashMap<String, String> bejingvisitor = new HashMap<String, String>(); // �ͻ����
	public static String signtype="P";  //ǩ���ӿ�����   P:һСʱ�ϴ�     K:����
	public static boolean gpsToast=false;//�Ƿ񵯳���ʾ 
	public static boolean isStart=true;//�Ƿ����߳�
	public static boolean isupdate=false;//�Ƿ��ϴ�λ����Ϣ
	public static ArrayList<Visitor> visitorList = new ArrayList<Visitor>();
					
	public static ArrayList<Visitor> tempVisitorList = new ArrayList<Visitor>();
							
	static {
		// ��ʼ�����з�����
		organazitonHashMap.put("0106", "CFCCC�Ͼ�����");
		organazitonHashMap.put("A001", "CFCCC����������");
		organazitonHashMap.put("B001", "CFCCC���ݷ�����");
		organazitonHashMap.put("C001", "CFCCC��ͨ������");
		organazitonHashMap.put("D001", "CFCCC̩�ݷ�����");
		organazitonHashMap.put("E001", "CFCCC���ݷ�����");
		organazitonHashMap.put("F001", "CFCCC�Ϻ�������");
		organazitonHashMap.put("G001", "CFCCC���ݷ�����");
		organazitonHashMap.put("7101", "�������۴�������");
		organazitonHashMap.put("7201", "��ɽ¹�Ǵ�������");
		organazitonHashMap.put("H001", "CFCCC���ݷ�����");
		organazitonHashMap.put("I001", "CFCCC����������");

		
		
		// ��������
		cityHashMap.put("511", "�򽭵���");
		cityHashMap.put("515", "�γǵ���");
		cityHashMap.put("10", "��������");
		cityHashMap.put("512", "���ݵ���");
		cityHashMap.put("21", "�Ϻ�����");
		cityHashMap.put("25", "�Ͼ�����");
		cityHashMap.put("571", "���ݵ���");
		cityHashMap.put("514", "���ݵ���");
		cityHashMap.put("523", "̩�ݵ���");
		cityHashMap.put("513", "��ͨ����");
		cityHashMap.put("519", "���ݵ���");
		cityHashMap.put("510", "��������");
		cityHashMap.put("516", "���ݵ���");

		// �ͻ����
		custemerTypeMap.put("10", "һ��ͻ�");
		custemerTypeMap.put("20", "����ͻ�");
		custemerTypeMap.put("30", "����ͻ�");
		custemerTypeMap.put("40", "����ͻ�");
		custemerTypeMap.put("05", "��һ��ͻ�");

		
		visitorAdministorMap.put("0100701001", "���ķ�"); 
		visitorAdministorMap.put("0100701005", "������");
		visitorAdministorMap.put("0900701007", "����");
		visitorAdministorMap.put("0400701001", "½С��");
		visitorAdministorMap.put("0500701004", "����");
		
		// �Ͼ����Ա
		nanjinvisitor.put("0100701001", "���ķ�");
		nanjinvisitor.put("0100701005", "������");
		nanjinvisitor.put("0100701013", "����");
		nanjinvisitor.put("0100701010", "������");
		nanjinvisitor.put("0100701009", "����");
		nanjinvisitor.put("0100701011", "���л�");
		nanjinvisitor.put("0100701014", "����");
		nanjinvisitor.put("0100701006", "������");
		nanjinvisitor.put("0100701012", "�ı�");
		nanjinvisitor.put("0300701004", "����");

		// �������Ա
		wuxivisitor.put("0400701001", "½С��");
		wuxivisitor.put("0400701002", "���");
		wuxivisitor.put("0400701003", "�����");
		wuxivisitor.put("0400701004", "���Შ");
		wuxivisitor.put("0400701004", "���Შ");
		wuxivisitor.put("0400701005", "������");

		// �������Ա
		suzhouvisitor.put("0900701006", "������");
		suzhouvisitor.put("0900701007", "����");
		suzhouvisitor.put("0900701008", "��׿��");
		suzhouvisitor.put("0900701009", "����");
		suzhouvisitor.put("7201701001", "����");
		suzhouvisitor.put("0900701010", "�˹���");
		suzhouvisitor.put("0900701011", "����");
		suzhouvisitor.put("7201701002", "½ѩ��");
		suzhouvisitor.put("0900701013", "����Ƽ");
		suzhouvisitor.put("0900701012", "л־��");
		suzhouvisitor.put("0900701001", "�����");
		suzhouvisitor.put("7201701004", "���ǣ�");
		suzhouvisitor.put("7201701005", "�¹���");
		suzhouvisitor.put("0900701014", "�¼�ӯ");

		// ��ͨ���Ա
		natongvisitor.put("0600701002", "������");
		natongvisitor.put("0600701001", "��ϼ");
		natongvisitor.put("0600701004", "�ų���");

		// ̩�����Ա
		taizhouvisitor.put("0200701003", "ʩ��÷");
		taizhouvisitor.put("0200701002", "����");
		taizhouvisitor.put("0200701001", "�Ǻ�ϲ");
		taizhouvisitor.put("0200701004", "Ҧ��");

		// �������Ա
		yangzhouvisitor.put("0800701002", "�³�");
		yangzhouvisitor.put("0800701001", "Ф��");
		yangzhouvisitor.put("0800701003", "������");

		// �Ϻ����Ա
		shanghaivisitor.put("0300701020", "����");
		shanghaivisitor.put("0300701018", "����");
		shanghaivisitor.put("0300701009", "����");
		shanghaivisitor.put("0300701011", "��˧");
		shanghaivisitor.put("0300701021", "������");
		shanghaivisitor.put("0300701022", "������");
		shanghaivisitor.put("0300701003", "����");
		shanghaivisitor.put("0300701010", "������");
		shanghaivisitor.put("0300701016", "����");
		shanghaivisitor.put("0300701012", "��쿱�");
		shanghaivisitor.put("0300701013", "����");
		shanghaivisitor.put("0300701014", "������");
		shanghaivisitor.put("0300701015", "л�Ʒ�");
		shanghaivisitor.put("0300701019", "��Ц��");
		shanghaivisitor.put("0300701017", "��ǿ");
		shanghaivisitor.put("0300701024", "�辧");
		shanghaivisitor.put("0300701023", "������");

		// �������Ա
		hangzhouvisitor.put("0700701003", "����");
		hangzhouvisitor.put("0700701002", "��ΰ");
		hangzhouvisitor.put("0700701001", "�����");
		hangzhouvisitor.put("0700701004", "�麼��");

		// �������Ա
		changzhouvisitor.put("1001701002", "����");
		changzhouvisitor.put("1001701001", "������");
		changzhouvisitor.put("1001701003", "����");

		// �������Ա
		bejingvisitor.put("0500701001", "�Ų�");
		bejingvisitor.put("0500701002", "����");
		bejingvisitor.put("0500701003", "���");
		bejingvisitor.put("0500701004", "����");
		bejingvisitor.put("0500701005", "���");
		bejingvisitor.put("0500701006", "������");
		bejingvisitor.put("0500701007", "������");
		bejingvisitor.put("0500701011", "�߽�");
		bejingvisitor.put("0500701012", "����");
		bejingvisitor.put("0500701013", "����");
		bejingvisitor.put("0500701008", "����");
		bejingvisitor.put("0500701010", "��Ң");
		bejingvisitor.put("0500701009", "����");
		bejingvisitor.put("0500701014", "����");

		// ��ʼ�����Ա
		visitor.put("25", nanjinvisitor);
		visitor.put("510", wuxivisitor); 
		visitor.put("512", suzhouvisitor);
		visitor.put("513", natongvisitor);
		visitor.put("523", taizhouvisitor);
		visitor.put("514", yangzhouvisitor);
		visitor.put("21", shanghaivisitor);
		visitor.put("571", hangzhouvisitor);
		visitor.put("519", changzhouvisitor);
		visitor.put("10", bejingvisitor); 

		for (Map.Entry<String, HashMap<String, String>> entry : visitor
				.entrySet()) {

			for (Map.Entry<String, String> entrys : entry.getValue().entrySet()) {
				visitorList.add(new Visitor(entrys.getKey().toString(), entrys
						.getValue().toString()));
			}

		}

		if (!isUat) { // �������������ִ�����д���
			Constants.urlstr = "http://193.1.232.32:80/njbank/mobile/";// ����`
//			Constants.urlstr = "http://193.1.232.32:8880/njbank/mobile/";// Ԥ����`
			Constants.ip = "180.166.238.14";
			Constants.port = "7788";
			Constants.picIp = "http://158.1.232.100:8083/docsrv/public/photo.do?urn=";
			Constants.smallPicIp = "http://158.1.232.100:8083/docsrv/public/wfphoto.do?urn=";// ͼƬ����ӿ�
			Constants.appVersion = "nj:1.0.4.4";
		}

		if (isNjUat4) {
			Constants.urlstr = "http://159.156.1.175:30010/njbank/mobile/";// �Ͼ�cfccc5¥�������uat4����
		}

	}

}
