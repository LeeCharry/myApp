package com.example.jack.myapp.demo.data;

import java.util.Map;


public class Constants {
	//	public static final String urlstr = "http://193.1.232.32:80/njbank/mobile/";//生产`
//	public static final String urlstr = "http://193.1.232.32:82/njbank/mobile/";//预生产`
//	public static final String urlstr = "http://192.168.1.203:8080/njbank/mobile/";
//	public static final String urlstr = "http://116.231.84.108:8080/njbank/mobile/";// 公司测试
//	public static final String urlstr="http://193.1.232.32:83/njbank/mobile/";//南京银行测试  3g卡连测试环境地址
//	public static final String urlstr="http://192.168.1.194:8080/njbank/mobile/";
// 	public static final String urlstr="http://193.1.232.32:81/njbank/mobile/";//
	//chenji 新加pad外访 测试环境地址
	//public static final String urlstr="http://159.191.1.44:8081/njbank/mobile/";
	//public static final String urlstr="http://192.168.0.54:8081/njbank/mobile/";
	//齐鲁测试环境
	public static final String urlstr ="http://159.191.1.44:8080/njbank/mobile/";


	public static final String THEME_PACKAGE_NAME="theme_package_name" ;//sharedprefs中保存包名的键名
	public static final String DEFAULT_THEME_PACKAGE = "com.techown.cfccc"; //项目包名
	public static final String THEME_ACTION = "com.app.cfcccshin.Theme" ;//需要替换的主题

	public static boolean isDecrypt=false;//时候加密控制z
	public static String password="2805143";//文件加密密码
	public static String applyid;//业务申请id
	public static String authNumber;//外访作件id
	public static String Number;//外访任务授权号
	public static String storeid;//商户id

	public static boolean whetherEntrusted = false; // 是否受托支付
	public static  String PIC_PATH ="";
	/**
	 * 1 拍照
	 * 2 重拍
	 * 3 电子资料库选择
	 */
	public static int mPhotoRequest = 0;

	//yzh
	public static String[] STOPE_CHAIN=null;
	public static String[] storeNum=null;	  // 商户编号
	public static String[] plan=null; // 诚易贷plan

	//	public static String[] custClass=null; // plan客户群
//	public static	Map<String, String>custMap = null;
	public static String[] storeName=new String[3]; //
	public static String[] sfclCardAddStoreName=null; //
	public static String[]sfclCardAddSTOPE_CHAIN=null;

	public static String KEY=null;//密钥
	public static String ISREGISTED=null;
	public static String UUID=null;//设备号
	public static String futureField04=null;
	public static String maritalstatus= "";//婚姻状况
	public static String customerType=null;//预审提交的类型
	public static String loginmodel="0";//是否首次登入
	public static String bank = ""; // 推荐机构默认项

	public static String pagecount = "0" ;// 中页数
	public static Integer accessoryPhotoStage = 0 ;//附件拍摄的标志到了那个位置

	public static boolean homeInvestigation  = false;
	public static boolean companyInvestigation = false ;


	public static String homeorcompany = "" ; // 判断是家访还是外访

	//机构号 南京银行301 长沙银行309  根据需要修改 chenji  2017/03/20
//	public static String orgCode= "070" ; //员工登陆保留的  机构号  外访机构号
	public static String orgCode= "301" ; //员工登陆保留的  机构号  外访机构号
	public static String city = "" ;// 员工登陆保留的区号

	public static boolean isOpenRequired= true ;// 判断照片是否拍完
	public static boolean isOpenRequired1= false ;

	public static String userIdStr = "" ;// 员工号

	public static String name="";//员工名称
	public static String phone="";//员工手机号码

	public static String dbName = "cfccc.db"; // 数据库名字
	public static int dbVer =  14; //数据库版本号 //生产14

	public static String picBiPai = "请确认必拍项是否必拍" ;


	public static boolean submitSucceed = false  ;// 提交成功


	public static String auther;

	public static boolean isNext;//员工培训时图片是否能下一张


	public static boolean jiaose=true;//角色控制

}
