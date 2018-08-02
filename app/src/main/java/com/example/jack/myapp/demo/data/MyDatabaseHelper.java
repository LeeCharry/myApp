package com.example.jack.myapp.demo.data;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jack.myapp.demo.data.Constants;
import com.example.jack.myapp.demo.data.SharedPrefs;

/**
 * 数据库建表
 * time 20120426
 * @author chengyuan
 *
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

	private static MyDatabaseHelper dbHelper = null;
//	private static MyDatabaseHelper dbFriendHelper = null;

	SharedPrefs sp;

	public MyDatabaseHelper(Context context, String name, int version){
		super(context, name, null, version);
		sp=new SharedPrefs(context);
	}

//	public static  MyDatabaseHelper getInstance(Context context){
//		if(dbHelper == null){
//			dbHelper = new MyDatabaseHelper(context, Constants.dbName, Constants.dbVer);
//		}
//		return dbHelper;
//	}

	//	public static synchronized MyDatabaseHelper getFriendInstance(Context friendContext, String databaseName, int friendDBver){
//		if(dbFriendHelper == null){
//			dbFriendHelper = new MyDatabaseHelper(friendContext, databaseName, friendDBver);
//		}
//		return dbFriendHelper;
//	}
//
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		System.out.println("onCreate()");

		//用户信息
		arg0.execSQL("create table if not exists user(" +
				"newId varchar(10) primary key, " +//主键
				"username varchar(10), " +//用户名 0
				"password varchar(15)," +//密码1
				"user_type varchar(19) , " +//用户角色2
				"loginmodel varchar(20)," +//登陆模式3
				"identity varchar(10)," +//登陆凭证4
				"city varchar(15)," +//业代城市5
				"city_id varchar(19) , " +//城市代码6
				"prov varchar(20)," +//业代省7
				"prov_id varchar(10)," +//省代码8
				"lastpwdchangetime varchar(15)," +//最后密码修改时间9
				"pwdovertime varchar(19) , " +//密码过期时间10
				"isdevicelocked varchar(20)," +//设备是否锁定11

				"name varchar(10)," +//中文姓名12
				"phone varchar(15)," +//手机号码13
				"canumber varchar(19), " +//CA工号14
				"bankIdentification varchar(20)," +//银行标示15
				"area varchar(10)," +//地区16
				"company varchar(19)," +//所属公司17
				"institutionCode varchar(20)," +//机构代码18
				"institutionName varchar(10)" +//机构名称19

				")");
		//业务申请表结构scbscbprojects_apply
		arg0.execSQL("create table if not exists cfccc_apply(" +
				"id varchar(10) primary key, " +//0

				"authNumber varchar(20), " +//授权号 1
				"urn varchar(20), " +//urn 2
				"orgCode varchar(10)," +//行机构号3
				"productId varchar(10), " +//产品编号4
				"staffId varchar(20), " +//员工号5
				"serialNumber varchar(15)," +//流水号6
				"storeNumber varchar(10), " +//商户编号7
				"storeName varchar(30)," +//商户名称8
				"barcode varchar(10)," +//条码9
				"signatureDate varchar(100), " +//申请表签字日期10
				"applyTime varchar(10)," +//申请日期11
				"purchaseCode varchar(10), " +//贷款用途12
				"planNumber varchar(10)," +//还款计划13
				"calculedTerm varchar(10)," +//期数14
				"desiredCreditLimit varchar(20), " +//期望额度15
				"claPrincipal varchar(15)," +//贷款金额16
				"identitytype varchar(5)," +//证件类型17
				"identityNumber varchar(18)," +//证件号码18

				"groupLoanCode varchar(20), " +//团办单位19
				"payrollAgencyUnit varchar(20)," +//代发工资单位20
				"loanReductionInitial varchar(10), " +//CA建议打折(百分比)21
				"specialCustCode varchar(10), " +//特殊客户码22
				"nameCust varchar(5)," +//中文姓23
				"surnameCust varchar(10), " +//中文名24
				"birthDateCust varchar(10)," +//出生日期25
				"title varchar(2)," +//性别26
				"maritalstatus varchar(5)," +//婚姻状况27
				"educationLevel varchar(10)," +//教育程度28
				"houseStatus varchar(10),"+//住宅性质29
				"firstHomeRentHousehold varchar(10)," +//住房支出30
				"phoneNumberZone varchar(10)," +//住宅电话-zone 31
				"phoneNumberMain varchar(10)," +//住宅电话-main 32
				"mobilePhone varchar(11)," +//手机33
				"yearSalary varchar(10)," +//税后年收入34
				"province varchar(10)," +//住宅地址/省35
				"city varchar(10)," +//市36
				"district varchar(20)," +//区(县)37


				"address varchar(30), " +//地址38
				"zipCode varchar(6), " +//邮编39
				"email varchar(20)," +// 电子邮件40
				"spIdentitytype varchar(8), " +//配偶证件类型41
				"spIdentityNumber varchar(18), " +//配偶证件号码42

				"spName varchar(5)," +//配偶中文姓43
				"spSurname varchar(10), " +//配偶中文名44
				"spMobilePhone varchar(15)," +//配偶电话号码45
				"spCompanyName varchar(20), " +//配偶工作单位46
				"spYearSalary varchar(10)," +//配偶税后年收入47
				"custCompanyIndustryMemo varchar(50), " +//行业信息注明48
				"custJobCode varchar(15)," +//职位49
				"custCompanyName varchar(30), " +//单位名称50
				"custCompanyType varchar(20)," +//单位性质51
				"custCompanyTypeOther varchar(50), " +//其他(请注明)52

				"custJobDuty varchar(20), " +//职务53
				"custJobDepartment varchar(20), " +//任职部门54
				"custJobYears varchar(5), " +//现单位工作年限55
				"custJobProvince varchar(10), " +//单位地址/省56
				"custJobCity varchar(10), " +//市57
				"custJobDistrict varchar(15), " +//区(县)58
				"custJobAddress varchar(30), " +//地址59

				"custJobZipCode varchar(6), " +//邮编60
				"custJobPhoneNumZone varchar(10), " +//单位电话-zone61
				"custJobPhoneNumMain varchar(10), " +//单位电话-main 62
				"custJobPhoneNumExtension varchar(10), " +//单位电话-ext63
				"relCnLast varchar(5), " +//亲属联系人,中文姓64
				"relCnFirst varchar(10), " +//亲属联系人,中文名65
				"relMobilePhone varchar(11), " +//手机66
				"relTelephoneZone varchar(10), " +//电话-zone67

				"relTelephoneMain varchar(11), " +//电话-main68
				"relCompanyName varchar(30), " +//单位名称69
				"relRelationship varchar(11), " +//与申请人关系70
				"relRelationshipOther varchar(20), " +//与申请人其他关系(请注明)71
				"refCnLast varchar(5), " +//其他联系人,中文姓72
				"refCnFirst varchar(10), " +//其他联系人,中文名73
				"refMobilePhone varchar(11), " +//手机74
				"refTelephoneZone varchar(10), " +//电话-zone75
				"refTelephoneMain varchar(10), " +//电话-main76
				"refCompanyName varchar(30), " +//单位名称77
				"refRelationship varchar(10), " +//与申请人关系78
				"refRelationshipOther varchar(20), " +//与申请人其他关系(请注明)79
				"accountNumber varchar(20), " +//自动扣款借记卡卡号80
				"trustPayFlag varchar(10), " +//是否受托支付81
				"trustAccountName varchar(10), " +//户名82

				"trustAccountNumber varchar(20), " +//存款户账号83
				"trustAccountBranchName varchar(15), " +//开户银行84

				"isBonAcctFlag varchar(5), " +//是否我行账号85
				"commendBank varchar(10), " +//推荐机构86
				"commendSubBank varchar(15), " +//推荐支行87
				"commendPersonCode varchar(20), " +//选择推荐人工号88
				"cust1CustFutureField8 varchar(20), " +//录入推荐人工号89
				"commendStaffTel varchar(15), " +//推荐人联系电话90
				"inSource varchar(10), " +//进件来源91
				"acceptBranch varchar(10), " +//受理机构92
				"acceptSubBranch varchar(10), " +//受理支行93
				"enteringPerson varchar(20), " +//选择受理人工号94
				"cust2CustFutureField8 varchar(20), " +//录入推荐人工号95
				"enteringMobile varchar(15), " +//受理人联系电话96
				"acceptanceOpinions varchar(50), " +//受理意见97

				"purchaseType varchar(20), " +//商品类别98

				"cashPrice varchar(10), " +//现金价99
				"initialShare varchar(10), " +//首付款100
				"custTeacherRank varchar(10), " +// 职位——教师级别 101

				"isUploadPhoto varchar(5), " +//客户和销售合影102
				"hasIDCardCopy varchar(5), " +//身份证照片103
				"hasLoanUse varchar(5), " +//贷款用途证明104
				"hasBankPassbook varchar(5), " +//收入证明105
				"hasOtherProofOfSalary varchar(5), " +//财力证明106
				"hasIncomesUpdate  varchar(5), " +//更新收入或财力证明107
				"hasOther varchar(5), " +//其他108
				"hasSpouseIDCardCopy varchar(5), " +//配偶身份证照片109
				"hasSpouseBankPassbook varchar(5), " +//配偶银行存折或卡号110
				"highQualityEnterprise varchar(5), " +//优质企业111

				"accFutureField33 varchar(5), " +//期望专享额度112
				"applyGYDKType varchar(5), " +//申请种类113
				"acceptUpOrDown varchar(5), " +//如专享额度未审批通过，是否同意仅申请购易114
				"isFarmLoanFlag varchar(5), " +//农户115
				"isResident varchar(5), " +//非居民116
				"salaryDate varchar(5), " +//发薪日117
				"preferedComm varchar(5), " +//账单邮寄地址118
				"cust2FutureField4 varchar(5), " +//账单/卡片寄送方式119
				"provinceOther varchar(5), " +//账单/卡片寄送其他地址/省120
				"cityOther varchar(5), " +//账单/卡片寄送其他地址/市121
				"districtOther varchar(5), " +//账单/卡片寄送其他地址/区(县)122

				"addressOther varchar(5), " +//账单/卡片寄送其他地址123
				"zipCodeOther varchar(5), " +//账单/卡片寄送邮编124
				"custClass varchar(5), " +//125
				"hasIndSpousePbocAuth varchar(5), " +//126


				"dynamicpassword varchar(10), " +//动态密码127
				"flipperActivity varchar(10), " +//草稿箱出来的功能模块名
				"flipperLayoutnum varchar(2), " +//草稿箱出来对应的是第几个布局文件
				"filename varchar(20), " +//文件夹名字
				"type varchar(20), " +//业务类型
				"applyfile_states varchar(2)," +//件的标示 1为草稿箱、2为发件箱
				"plonCustomerType varchar(6) ," +// 发件类型 rp,新客户 客户类型的判断NEW_PT：新申请; RP_PT：RP; NEW_BT：新申请补录；OLD_BT：老客户补录;
				"appAction varchar(5)," +
				"accessoryPhotoStage varchar(5)," +  //  进入附件拍摄的标志
				"commendname varchar(10)," +
				"goods varchar(20)"+
				")");

		//外访
		arg0.execSQL("create table if not exists cfccc_customervisit(" +
				"id varchar(20) primary key, " +//授权号 0
				"authNumber varchar(20), " +//授权号 01
				"productCategory varchar(20), " +//产品编号 1
				"visitType varchar(1), " +//外访类型, 单位访：C;家访:H 2
				"custName varchar(15)," +//客户姓名3
				"asCom varchar(100), " +//外访员备注4
				"latitudeAndLongitude varchar(20), " +//经纬度5
				"exemptHomeVisit varchar(7), " +//免家访选项,免家访:Y;需要家访:Nornull 6
				"homeVisitFinished varchar(3)," +//家访顺利完成:OK;家访客户不配合:TBS;客户经理严重违规情况KO 7
				"homeVisit3Rd varchar(2), " +//没有第三方介入嫌疑:OK;居住环境较差,具体请看影像:TBS;有第三方介入嫌疑:KO 8
				"homeVisitLoanPurpose varchar(3)," +//贷款用途无虚假:OK;贷款用途可疑但无法完全确认:TBS;贷款用途虚假:KO 9
				"homeVisitApplySelf varchar(3)," +//确认亲访亲签:OK;未亲访亲签:TBS;进件材料虚假:KO 10
				"homeVisitRisk varchar(2), " +//未发现风险特征:OK;其他,详见备注:TBS;发现客户赌博、吸毒、欠高额外债等高风险特征:KO 11
//						"homeVisitStatus varchar(1)," +//外访是否通过,外访未发现重大疑点:2;外访发现客户有赌、毒等重大疑点:4 12

				"exemptCompVisit varchar(6), " +//免单位访选项,免单位访:Y;需要单位访:Nornull 13
				"compVisitFinished varchar(3)," +//单位访顺利完成:OK;单位访未顺利完成:TBS;发现客户赌博、吸毒、欠高额外债等高风险特征:KO 14
				"compVisitIncome varchar(3)," +//收入核实一致:OK;收入核实不一致:TBS;收入证明虚假:KO 15
				"compVisitIncomeAmount varchar(10), " +//收入核实不一致或收入无法核实际核实到的月入金额:TBS 16
				"compVisitJob varchar(3)," +//工作信息(行业、职务等)一致:OK;工作信息(行业、职务等)不一致：TBS;工作信息虚假:KO 17
				"compVisit3Rd varchar(2)," +//办公电话真实:OK;办公电话无法核实或不真实:TBS 18 版本更新：临时变更
				"compVisitLoanPurpose varchar(3)," +//公司正常运作:OK;其他,详见备注:TBS;公司未正常运作:KO 19	 版本更新：临时变更
				//"compVisitApplySelf varchar(3), " +//确认亲访亲签:OK;未亲访亲签:TBS 20 版本更新：废弃
				//"compVisitRisk varchar(2)," +//未发现赌博、吸毒等高风险特征:OK;发现赌博、吸毒等高风险特征:KO 21 版本更新：废弃
				"positionDate varchar(14), "+//22
				"memoVisit varchar(100)," + 	 //提交主管备注

				"nameCust varchar(8)," +//性
				"customervisitDate varchar(8)," +//外访日期
				"customervisitPhone varchar(11)," +//手机号码
				"flipperActivity varchar(10), " +//草稿箱出来的功能模块名
				"flipperLayoutnum varchar(2), " +//草稿箱出来对应的是第几个布局文件
				"filename varchar(20), " +//文件夹名字
				"bollen varchar(8)," +//
				"applyfile_states varchar(2) " +//件的标示 1为草稿箱、2为发件箱


				")");

		//发件箱表
		arg0.execSQL("create table if not exists send(" +
				"filename varchar(4) primary key, " +//发件文件名0
				"beginnum varchar(40)," +//发送起始段数1
				"totalnum varchar(19)" +//文件总段数2
				")");
//************************************************************批量文件数据库****************************************//

		//优惠信息FavorableMessage
		arg0.execSQL("create table if not exists FavorableMessage(" +
				"favorablecode varchar(2) , " +//优惠信息代码 0
				"favorablecontent varchar(30)," +//优惠信息显示 1
				"updateTime varchar(14)," +//创建时间2
				"org varchar(5),"+//
				"type varchar(5)"+
				")");
		//贷款用途
		arg0.execSQL("create table if not exists PurchaseCode(" +
				"purchasecode varchar(3) primary key, " +//贷款用途代码 0
				"purchasecontent varchar(30)," +//贷款用途显示1
				"updateTime varchar(14)" +//创建时间2
				")");
		//行业信息
		arg0.execSQL("create table if not exists CompanyMemo(" +
				"companymemocode varchar(2) primary key, " +//行业信息代码 0
				"companymemocontent varchar(30)," +//行业信息显示1
				"updateTime varchar(14)" +//创建时间2
				")");
		//职位
		arg0.execSQL("create table if not exists JobMemo(" +
				"jobmemocode varchar(2) primary key, " +//职位代码 0
				"jobmemocontent varchar(30)," +//职位显示1
				"updateTime varchar(14)" +//创建时间2
				")");
		//职位-教师级别
		arg0.execSQL("create table if not exists JobTeacherLevel(" +
				"jobteachercode varchar(2) primary key, " +//教师级别代码 0
				"jobteachercontent varchar(30)," +//教师级别显示1
				"updateTime varchar(14)" +//创建时间2
				")");
		//单位性质
		arg0.execSQL("create table if not exists CompanyType(" +
				"companytypecode varchar(2) primary key, " +//单位性质代码 0
				"companytypecontent varchar(30)," +//单位性质显示1
				"updateTime varchar(14)" +//创建时间2
				")");
		//职务
		arg0.execSQL("create table if not exists JobLevel(" +
				"joblevelcode varchar(2) primary key, " +//职务 0
				"joblevelcontent varchar(30)," +//职务1
				"updateTime varchar(14)" +//创建时间2
				")");
		//婚姻状况
		arg0.execSQL("create table if not exists MaritalStatus(" +
				"maritalstatuscode varchar(1) primary key, " +//婚姻状况 0
				"maritalstatuscontent varchar(10)," +//婚姻状况1
				"updateTime varchar(14)" +//创建时间2
				")");
		//教育程度
		arg0.execSQL("create table if not exists EducationLevel(" +
				"educationlevelcode varchar(1) primary key, " +//教育程度 0
				"educationlevelcontent varchar(16)," +//教育程度1
				"updateTime varchar(14)" +//创建时间2
				")");
		//客户特殊码
		arg0.execSQL("create table if not exists SpecialCost(" +
				"specialcostcode varchar(3) primary key, " +//客户特殊码 0
				"specialcostcontent varchar(30)," +//客户特殊码1
				"updateTime varchar(14)" +//创建时间2
				")");

		//推荐与受理机构
		arg0.execSQL("create table if not exists Banck(" +
				"institutionCode varchar(4) primary key, " +//机构代码 0
				"institutionName varchar(40)," +//机构名称1
				"DB_ID varchar(19)," +//DB_ID2
				"updateTime varchar(14)," +//创建时间4
				"area varchar(14)," +//地区
				"acceptable varchar(5)," +//是否加载为受理行
				"reserver1 varchar(5)" +//预留字段
				")");

		//推荐与受理支行
		arg0.execSQL("create table if not exists SubBank(" +
				"branchCode varchar(4) primary key, " +//支行代码 0
				"branchName varchar(40)," +//支行名称1
				"PAREND_DB_ID varchar(19)," +//PAREND_DB_ID通过支行下的PAREND_DB_ID字段值等于机构下DB_ID字段值,来进行机构与支行关联。2
				"updateTime varchar(14)," +//创建时间3
				"area varchar(14)," +//地区
				"inputable varchar(5)," +//是否可以录入推荐或受理人
				"reserver1 varchar(5)" +//是否加载为受理行
				")");
		//推荐人与受理人
		arg0.execSQL("create table if not exists Sale(" +
				"saleCode varchar(10) primary key, " +//推荐人/受理人代码 0
				"saleName varchar(40)," +//推荐人/受理人名称1
				"areaNumbers varchar(20)," +//地区编号2
				"nodeType varchar(2)," +//网点类型3
				"updateTime varchar(14)" +//创建时间4
				")");
//************************************************************批量文件数据库****************************************//
		//Activity活动
		arg0.execSQL("create table if not exists Activity(" +
				"Id varchar(10) primary key, " +//ID 0
				"activityId varchar(20)," +//活动名称1
				"activityName varchar(20)," +//活动名称1
				"company varchar(18)," +//所属公司2
				"cityCode varchar(3)," +//所在城市区号3
				"role varchar(20)," +//角色4
				"startTime varchar(10)," +//开始日期5
				"endTime varchar(10)," +//结束日期6
				"isTop varchar(1)," +//是否置顶7
				"filePath varchar(64)," +//文件路径8
				"filelength varchar(64)," +//文件长度10
				"updateTime varchar(14)," +//更新时间9
				"isdownload varchar(14)" +//是否已经下载  0没有下载1已经下载
				")");
		//Contract纸质合约启用参数
		arg0.execSQL("create table if not exists Contract(" +
				"isOpen varchar(2) primary key, " +//纸质合约启用 0
				"updateTime varchar(14)" +//更新时间1
				")");
		//DraftTime草稿箱时长
		arg0.execSQL("create table if not exists DraftTime(" +
				"draftTime varchar(4) primary key, " +//草稿箱保存时长 0
				"updateTime varchar(14)" +//更新时间1
				")");
		//Merchant商户营销支持
		arg0.execSQL("create table if not exists Merchant(" +
				"Id varchar(10) primary key, " +//ID 0
				"merchantId varchar(10), " +//ID1
				"merchantInfor varchar(20)," +//商户支持信息1
				"company varchar(18)," +//所属公司2
				"cityCode varchar(3)," +//所在城市区号3
				"role varchar(20)," +//角色4
				"startTime varchar(10)," +//开始日期5
				"endTime varchar(10)," +//结束日期6
				"isTop varchar(1)," +//是否置顶7
				"filePath varchar(64)," +//文件路径8
				"filelength varchar(64)," +//文件长度10
				"updateTime varchar(14)," +//更新时间11
				"isdownload varchar(14)" +//是否已经下载  0没有下载1已经下载
				")");
		//Notice公告
		arg0.execSQL("create table if not exists Notice(" +
				"Id varchar(10) primary key, " +//ID 0
				"noticeId varchar(20)," +//公告主题1
				"noticeTitle varchar(20)," +//公告主题1
				"noticeContent varchar(20)," +//公告内容2
				"company varchar(18)," +//公司3
				"cityCode varchar(3)," +//所在城市区号4
				"role varchar(20)," +//角色5
				"startTime varchar(10)," +//开始日期6
				"endTime varchar(10)," +//结束日期7
				"isTop varchar(1)," +//是否置顶8
				"updateTime varchar(14)" +//更新时间9
				")");
		//Pic附件拍摄
		arg0.execSQL("create table if not exists Pic(" +
				"id varchar(1), " +//阶段0
				"stage varchar(1), " +//阶段0
				"file_name varchar(20), " +//文件夹名称，用于存放照片1
				"big_info varchar(100), " +//大类信息2
				"small_info varchar(100)," +//小类信息3
				"updateTime varchar(14)" +//更新时间4
				")");
		//PicSort附件大类参数
		arg0.execSQL("create table if not exists PicSort(" +
				"bigName varchar(1), " +//拍摄大类名称0
				"fileName varchar(20), " +//文件夹命名1
				"bigCode varchar(100), " +//大类编号2
				"updateTime varchar(14)" +//更新时间3
				")");
		//Product产品
		arg0.execSQL("create table if not exists Product(" +
				"Id varchar(10) primary key, " +//ID 0
				"productId varchar(20)," +//产品名称1
				"productName varchar(20)," +//产品名称1
				"product varchar(20)," +//对应产品2
				"company varchar(18)," +//公司3
				"cityCode varchar(3)," +//所在城市区号4
				"role varchar(20)," +//角色5
				"startTime varchar(10)," +//开始日期6
				"endTime varchar(10)," +//结束日期7
				"isTop varchar(1)," +//是否置顶8
				"filePath varchar(64)," +//文件路径9
				"filelength varchar(64)," +//文件长度10
				"updateTime varchar(14)," +//更新时间11
				"isdownload varchar(14)" +//是否已经下载  0没有下载1已经下载
				")");
		//StandardTalk标准话术
		arg0.execSQL("create table if not exists StandardTalk(" +
				"Id varchar(10) primary key, " +//ID 0
				"standardId varchar(20)," +//标准话术名称1
				"standardNamer varchar(20)," +//标准话术名称1
				"company varchar(18)," +//所属公司2
				"cityCode varchar(3)," +//所在城市区号3
				"role varchar(20)," +//角色4
				"startTime varchar(10)," +//开始日期5
				"endTime varchar(10)," +//结束日期6
				"isTop varchar(1)," +//是否置顶7
				"filePath varchar(64)," +//文件路径8
				"filelength varchar(64)," +//文件长度10
				"updateTime varchar(14)," +//更新时间9
				"isdownload varchar(14)" +//是否已经下载  0没有下载1已经下载
				")");
		//Training培训
		arg0.execSQL("create table if not exists Training(" +
				"Id varchar(10) primary key, " +//ID 0
				"trainingId varchar(10), " +//ID 0
				"trainingNamer varchar(20)," +//课程名称1
				"company varchar(18)," +//所属公司2
				"cityCode varchar(3)," +//所在城市区号3
				"role varchar(20)," +//角色4
				"startTime varchar(10)," +//开始日期5
				"endTime varchar(10)," +//结束日期6
				"seconds varchar(10)," +//读秒控制7
				"isTop varchar(1)," +//是否置顶8
				"filePath varchar(64)," +//文件路径9
				"filelength varchar(64)," +//文件长度10
				"updateTime varchar(14)," +//更新时间9
				"isdownload varchar(2)," +//是否已经下载  0没有下载1已经下载
				"isreply varchar(2)" +//是否已经回复  null没有回复 1已经回复
				")");

		//省
		arg0.execSQL("create table if not exists Province(" +
				"province_id varchar(10) primary key," +//省代码
				"province varchar(20)," +//省名字
				"updateTime varchar(14)" +//更新时间10
				")");
		//市
		arg0.execSQL("create table if not exists City(" +
				"city_id varchar(20) primary key," +//市代码
				"city varchar(20)," +//市名字
				"province_id varchar(20)," +//省代码
				"updateTime varchar(14)" +//更新时间10
				")");
		//区
		arg0.execSQL("create table if not exists District(" +
				"area_id varchar(20) primary key," +//区代码
				"area varchar(20)," +//区名字
				"city_id varchar(20)," +//市代码
				"updateTime varchar(14)" +//更新时间10
				")");
//************************************************************批量文件数据库****************************************//
		//团办/代发工资单位
		arg0.execSQL("create table if not exists GroupCustomer(" +
				"GROUP_NO varchar(20) primary key," +// 单位编号 0
				"GROUP_NAME varchar(120)," +//单位名称1
				"GROUP_DISTRICT varchar(30)," +//单位所在地区2
				"ORG_CODE varchar(4)," +//机构编号3
				"CREATE_TIME varchar(14)," +//创建时间4
				"updateTime varchar(14)" +//更新时间
				")");
		//PLAN和商户对应关系表
		arg0.execSQL("create table if not exists PlanStore(" +
				"PLAN_ID varchar(9) primary key, " +//PLAN_ID 0
				"STORE_NUMBER varchar(9)," +//商户号1
				"CREATE_TIME varchar(14)," +//创建时间2
				"updateTime varchar(14)" +//更新时间
				")");
		//plan、商户及商品对应关系表
		arg0.execSQL("create table if not exists PlanStoreGoods(" +
				"PLAN_ID varchar(9), " +//PLAN_ID 0
				"STORE_GOODS_ID varchar(10)," +//STORE_GOODS_ID1
				"CREATE_TIME varchar(14)," +//创建时间2
				"updateTime varchar(14)" +//更新时间
				")");
		//商品信息表
		arg0.execSQL("create table if not exists Goods(" +
				"GOODS_ID varchar(9) primary key, " +// 商品ID 0
				"GOODS_CODE varchar(3)," +//商品编号1
				"GOODS_NAME varchar(50)," +//商品名称2
				"CATEGORY_ID varchar(4)," +//类别ID3
				"CREATE_TIME varchar(14)," +//创建时间4
				"updateTime varchar(14)" +//更新时间
				")");
		//商品类别信息表
		arg0.execSQL("create table if not exists GoodsCategory(" +
				"CATEGORY_ID varchar(4) primary key, " +//商品类别ID0
				"CATEGORY_NAME varchar(60)," +//类别名称1
				"CATEGORY_CODE varchar(32)," +//类别编号2
				"CREATE_TIME varchar(14)," +//创建时间3
				"updateTime varchar(14)" +//更新时间
				")");
		//商户用户对应关系表
		arg0.execSQL("create table if not exists UmsUserStores(" +
				"USER_ACCESS_ID varchar(25), " +//用户ID0
				"STORE_NUMBER varchar(9)," +//商户号1
				"CREATE_TIME varchar(14)," +//创建时间2
				"updateTime varchar(14)" +//更新时间
				")");
		//商户信息表
		arg0.execSQL("create table if not exists Stores(" +
				"STORE_NUMBER varchar(9) primary key, " +//商户号 0
				"STORE_NAME varchar(40)," +//商户名称1
				"STORE_DISTRICT varchar(3)," +//商户地区3
				"STORES_UNION varchar(2)," +//网点类型2
				"CREATE_TIME varchar(14)," +//创建时间4
				"STOPE_CHAIN varchar(14)," +
				"updateTime varchar(14)" +//更新时间
				")");
		//商户、商品对应关系表
		arg0.execSQL("create table if not exists StoreGoods(" +
				"STORE_GOODS_ID varchar(10) primary key, " +//主键ID0
				"GOODS_ID varchar(9)," +//商品ID1
				"STORE_NUMBER varchar(9)," +//商户号2
				"CREATE_TIME varchar(14)," +//创建时间3
				"updateTime varchar(14)" +//更新时间
				")");


		//还款计划信息表
		arg0.execSQL("create table if not exists Plan(" +
				"PLAN_ID varchar(9) primary key, " +//主键PLAN_ID0
				"PLAN_NUMBER varchar(5)," +//计划编号1
				"PLAN_NAME varchar(50)," +//计划名称2
				"TERM_MIN varchar(5)," +//期数最小值3
				"TERM_MAX varchar(5)," +//期数最大值4
				"CREATE_TIME varchar(14)," +//创建时间5
				"custClass varchar(14),"+//创建时间6 客户群 20140410
				"updateTime varchar(14)" +//更新时间

				")");

		//客户群
		arg0.execSQL("create table if not exists CustClass(" +
				"KEY varchar(9), " +//主键PLAN_ID0
				"custClassValue varchar(10)," +//计划编号1
				"producType varchar(14)," +//更新时间
				"updateTime varchar(14)" +
				")");

		//产品信息表
		arg0.execSQL("create table if not exists Products(" +
				"PRODUCT_ID varchar(9) primary key, " +//主键PRODUCT_ID0
				"LOGO varchar(3)," +//产品LOGO1
				"PRODUCT_CODE varchar(4)," +//产品编号2
				"PRODUCT_NAME varchar(40)," +//产品名称3
				"PRODUCT_CATEGORY varchar(2)," +//产品类别4
				"CREATE_TIME varchar(14)," +//创建时间5
				"updateTime varchar(14)" +//更新时间
				")");
		//产品信息表
		arg0.execSQL("create table if not exists ProductStores(" +
				"PRODUCT_ID varchar(9) , " +//产品ID 0
				"STORE_NUMBER varchar(9)," +//商户编号1
				"CREATE_TIME varchar(14)," +//创建时间2
				"updateTime varchar(14)" +//更新时间4
				")");
		//************************************************************批量文件数据库****************************************//
		//附件拍摄
		arg0.execSQL("create table if not exists accessory_photo(" +
				"stage varchar(1), " +//阶段
				"file_name varchar(20), " +//文件夹名称，用于存放照片
				"big_info varchar(100), " +//大类信息
				"small_info varchar(100)" +//小类信息
				")");



		//潜客管理
		arg0.execSQL("create table if not exists marketing_potential(" +
				"name varchar(20) primary key," +//客户姓名
				"phone varchar(20)," +//手机号码
				"time varchar(20)" +//录入时间
				")");

		//短信过后，条码和纸质合约的开关表
		arg0.execSQL("create table if not exists sms_switch(" +
				"contract_switch varchar(20)," +//纸质合约和条码扫描的开关
				"contract__time varchar(20)" +//录入时间
				")");

		//emm
		arg0.execSQL("create table if not exists emm(" +
				"id varchar(20)," +
				"key varchar(20)," +//
				"uuid varchar(20)," +//
				"ISREGISTED varchar(20)" +
				")");


		//通用工单
		arg0.execSQL("create table if not exists workOrder(" +
				"id varchar(20)," +// 工单的标识
				"content varchar(20)," +//内容
				"expiration varchar(20)," +// 过期时间
				"number varchar(20)," +// 工单的id
				"acquisitionTime varchar(20) ," + // 获取时间
				"isfeedback  varchar(20)" +// 是否反馈 0为未反馈，1为已反馈
				")");

		//商户所在地区城市
		arg0.execSQL("create table if not exists StoreDistrict(" +
				"areanum varchar(20)," +// 地区编号
				"areaname varchar(20)," +//地区名称
				"updateTime varchar(20)" +//更新时间
				")");
		//商户类型
		arg0.execSQL("create table if not exists StoreType(" +
				"storenum varchar(20)," +//
				"storename varchar(20)," +//
				"updateTime varchar(20)" +//更新时间
				")");
		//************************************************************批量文件数据库****************************************//


		arg0.execSQL("create table if not exists StoreM(" +
				"id varchar(10) primary key, " +//0

				"storeName varchar(20)," +	//商户名称 1
				"storeType varchar(20)," +	//商户类型 2
				"address varchar(20)," +	//商户地址3
				"phone varchar(20)," +	//电话4
				"fax varchar(20)," +	//传真5
				"zipCode varchar(20)," +	//邮编7
				"bizLicenseNo varchar(20)," +	//营业执照号码8
				"establishDate varchar(20)," +	//成立日期6
				"industry varchar(20)," +	//所属行业9
				"companyType varchar(20)," +	//公司类型10
				"employeeAmount varchar(20)," +	//员工人数11
				"salesStaffAmount varchar(20)," +	//销售人员数12
				"storeAmount varchar(20)," +	//门店数13
				"storeArea varchar(20)," +	//门店总面积14
				"juridicalPersonName varchar(20)," +	//法人代表姓名15
				"juridicalPersonId varchar(20)," +	//法人代表身份证号码16
				"homeBranch varchar(20)," +	//开户行分支行17
				"accountNumber varchar(20)," +	//账号18
				"isBonAcctFlag varchar(20)," +	//是否我行账号19
				"costLastYear varchar(20)," +	//去年成本20
				"profitLastYear varchar(20)," +	//去年利润21
				"costThisYear varchar(20)," +	//本年成本预估22
				"profitThisYear varchar(20)," +	//本年利润预估23
				"annualSalesRevenue varchar(20)," +	//年销售收入（汽车）24
				"annualSalesCount varchar(20)," +	//年销售量(汽车)25
				"avgMonth varchar(20)," +	//月均销售额26
				"avgBankMonth varchar(20)," +	//月均金融机构贷款销售额27
				"avgMonthCount varchar(20)," +	//月均销售总量数28
				"avgMonthBankCount varchar(20)," +	//月均金融机构贷款销售总量数29
				"areaCode varchar(20)," +	//商户所在城市的编号30
				"areaName varchar(20)," +	//商户所在城市31


				"storeManagerInfoName varchar(20)," +	//姓名32
				"storeManagerInfoIdCardNo varchar(20)," +	//身份证号33
				"storeManagerInfoTitle varchar(20)," +	//职称34
				"seniority varchar(20)," +	//年资35


				"storeCopoBankInfo_name varchar(20)," +	//银行名称36
				"storeCopoBankInfo_bankType varchar(20)," +	//合作方式37
				"storeCopoBankInfo_products varchar(20)," +	//金融产品38
				"storeCopoBankInfo_satisfaction varchar(20)," +	//满意度39

				"storeBizMgrInfo_mgrName varchar(20)," +	//业务发展经理姓名40
				"storeBizMgrInfo_storeWebsite varchar(20)," +	//商户网站地址41
				"storeBizMgrInfo_merchantView varchar(20)," +	//商户合作意愿和期望以及对我行信贷服务的看法42
				"storeBizMgrInfo_m1ApprovalDocsNum varchar(20)," +	//第一个月核准件（个数）43
				"storeBizMgrInfo_m2ApprovalDocsNum varchar(20)," +	//第二个月核准件（个数）44
				"storeBizMgrInfo_m3ApprovalDocsNum varchar(20)," +	//第三个月核准件（个数）45
				"storeBizMgrInfo_m1ApprovalDocsAmount varchar(20)," +	//第一个月核准金额46
				"storeBizMgrInfo_m2ApprovalDocsAmount varchar(20)," +	//第二个月核准金额47
				"storeBizMgrInfo_m3ApprovalDocsAmount varchar(20)," +	//第三个月核准金额48
				"storeBizMgrInfo_bizSope varchar(20)," +	//商户业务范围49
				"storeBizMgrInfo_customerLocate varchar(20)," +	//商户的客户定位50
				"storeBizMgrInfo_gainCustomerChannel varchar(20)," +	//商户获取客户渠道51
				"storeBizMgrInfo_managerSug varchar(20)," +	//业务发展经历意见和商户关系维护计划52

				"storeBizMgrInfo_dealRemark varchar(20)," +	//商户合作协议53
				"storeBizMgrInfo_accountRemark varchar(20)," +	//拨款银行账户证明54
				"storeBizMgrInfo_idCardRemark varchar(20)," +	//法人代表身份证复印件55
				"storeBizMgrInfo_managerRemark varchar(20)," +	//主要经理人身份证复印件和信用查询授权56
				"storeBizMgrInfo_bizLicenseRemark varchar(20)," +	//公司营业执照、组织机构代码证和税务登记证复印件57
				"storeBizMgrInfo_financialRemark varchar(20)," +	//近期财务报表58
				"storeBizMgrInfo_loanCardRemark varchar(20)," +	//公司贷款卡编码，以及查询授权书59
				"storeBizMgrInfo_other varchar(20)," +	//其他（商户与其客户额合同样本等等）60

				"storeCarsInfo_carModel varchar(20)," +	//车型 61
				"storeCarsInfo_carPrice varchar(20)," +	//价格62
				"storeCarsInfo_salesStatus varchar(20)," +	//每月销售情况63
				"storeCarsInfo_stockStatus varchar(20)," +	//库存情况64
				"salesInfo varchar(20)," +	//销售情况介绍65
				"operateInfo varchar(20)," + //经营情况介绍66


				"typeNum varchar(20), " +//
				"viewper varchar(20), " +//
				"viewper1 varchar(20), " +//
				"viewper2 varchar(20), " +//
				"viewper3 varchar(20), " +//
				"filename varchar(20), " +//文件夹名字
				"type varchar(20), " +//业务类型
				"applyfile_states varchar(2)," +//件的标示 1为草稿箱、2为发件箱
				"custName varchar(20), " +//名字
				"applyTime varchar(10)," +//申请日期11
				"flipperActivity varchar(10), " +//草稿箱出来的功能模块名
				"flipperLayoutnum varchar(2) " +//草稿箱出来对应的是第几个布局文件
				")");


		//Cellid开关
		arg0.execSQL("create table if not exists CellId(" +
				"isOpen varchar(2) primary key, " +//
				//"isOpen varchar(2)," +//更新时间1
				"updateTime varchar(14)" +//更新时间1
				")");



//				//StoreRecomm开关
		arg0.execSQL("create table if not exists StoreRecomm(" +
				"calculate_month varchar(6), " +//月份
				"merchant_id varchar(10)," +//商户号
				"merchant_name varchar(6), " +//商户名称
				"recomm_staff_id varchar(6), " +//业务员编号
				"recomm_staff_name varchar(6), " +//业务员姓名
				"funded_amt_total varchar(6), " +//总投放额
				"funded_amt_year varchar(6), " +//当年投放
				"funded_amt_quarter varchar(6), " +//当季投放
				"funded_amt_month varchar(6), " +//当月投放
				"current_bal varchar(6), " +//当前余额
				"quarter_beginning_bal varchar(6), " +//季初余额
				"quarter_end_bal varchar(6), " +//季末余额
				"quarter_bal_average varchar(6), " +//季度日均
				"NPL_rate varchar(6), " +//不良
				"funded_target varchar(6), " +//目标投放
				"last_update_date varchar(6) " +//更新日期
				")");
//
//				//Cellid开关
		arg0.execSQL("create table if not exists ApplyRecomm(" +
				"calculate_month varchar(6), " +//月份
				"recomm_branch varchar(10)," +//推荐行
				"recomm_sub_branch varchar(6), " +//推荐网点
				"recomm_staff_id varchar(6), " +//业务员编号
				"recomm_staff_name varchar(6), " +//业务员姓名
				"funded_amt_total varchar(6), " +//总投放额
				"funded_amt_year varchar(6), " +//当年投放
				"funded_amt_quarter varchar(6), " +//当季投放
				"funded_amt_month varchar(6), " +//当月投放
				"current_bal varchar(6), " +//当前余额
				"quarter_beginning_bal varchar(6), " +//季初余额
				"quarter_end_bal varchar(6), " +//季末余额
				"quarter_bal_average varchar(6), " +//季度日均
				"NPL_rate varchar(6), " +//不良
				"funded_target varchar(6), " +//目标投放
				"last_update_date varchar(6) " +//更新日期
				")");

		arg0.execSQL("create table IF NOT EXISTS ElectronicData(" + // 电子资料管理表
				"_id varchar2(20) primary key," + // 电子资料id，以新建电子资料时间戳为id
				"data_name varchar2(100)," + // 资料名称
				"customer_idtype varchar2(20)," + //证件类型
				"customer_idnum varchar2(100)," + // 客户证件号码
				"customer_name varchar2(30)," + // 客户姓名
				"file_path varchar2(50)," + // 资料保存路径，资料文件保存在pad的sdcard路径
				"create_time varchar2(10)," + //过期时间
				"expiration_time varchar2(10))");// 创建时间

		// 做修改数据库的操作

//				if (!isColumnExists(arg0, "StoreM", "typeNum")) {
//					arg0.execSQL("ALTER TABLE StoreM  ADD COLUMN typeNum varchar(1) ");
//
//				}
//				if (!isColumnExists(arg0, "Stores", "STOPE_CHAIN")) {
//					arg0.execSQL("ALTER TABLE Stores  ADD COLUMN STOPE_CHAIN varchar(14) ");
//
//				}

		if (!IsTableExists(arg0, "CellId")) {
			arg0.execSQL("create table if not exists CellId(" +
					"isOpen varchar(2) primary key, " +//纸质合约启用 0
//							"isOpen varchar(2)," +//更新时间1
					"updateTime varchar(14)" +//更新时间1
					")");
		}

		// 账单寄送地址
		if (!IsTableExists(arg0, "BillAddress")) {
			arg0.execSQL("create table if not exists BillAddress(" +
					"KEY varchar(2), " +//账单寄送类型KEY值
					"VALUE varchar(2)," +//账单寄送类型显示Value值
					"updateTime varchar(14)" +//文件生成的时间
					")");
		}
		// 无卡支付期数
		if (!IsTableExists(arg0, "PlanTerm")) {
			arg0.execSQL("create table if not exists PlanTerm(" +
					"KEY varchar(2), " +//类型KEY值
					"VALUE varchar(2)," +//类型显示Value值
					"updateTime varchar(14)" +//文件生成的时间
					")");
		}


	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 修改数据库
		//arg0.execSQL("drop table Banck");
		//arg0.execSQL("drop table SubBank");
		arg0.execSQL("drop table cfccc_customervisit");

		//外访
		arg0.execSQL("create table if not exists cfccc_customervisit(" +
				"id varchar(20) primary key, " +//授权号 0
				"authNumber varchar(20), " +//授权号 01
				"productCategory varchar(20), " +//产品编号 1
				"visitType varchar(1), " +//外访类型, 单位访：C;家访:H 2
				"custName varchar(15)," +//客户姓名3
				"asCom varchar(100), " +//外访员备注4
				"latitudeAndLongitude varchar(20), " +//经纬度5
				"exemptHomeVisit varchar(7), " +//免家访选项,免家访:Y;需要家访:Nornull 6
				"homeVisitFinished varchar(3)," +//家访顺利完成:OK;家访客户不配合:TBS 7
				"homeVisit3Rd varchar(2), " +//没有第三方介入嫌疑:OK;有第三方介入嫌疑:KO 8
				"homeVisitLoanPurpose varchar(3)," +//贷款用途无虚假:OK;贷款用途虚假:KO;贷款用途可疑但无法完全确认:TBS 9
				"homeVisitApplySelf varchar(3)," +//确认亲访亲签:OK;未亲访亲签:TBS 10
				"homeVisitRisk varchar(2), " +//未发现赌、毒等高风险特征:OK;发现赌博、吸毒等高风险特征:KO 11
//				"homeVisitStatus varchar(1)," +//外访是否通过,外访未发现重大疑点:2;外访发现客户有赌、毒等重大疑点:4 12

				"exemptCompVisit varchar(6), " +//免单位访选项,免单位访:Y;需要单位访:Nornull 13
				"compVisitFinished varchar(3)," +//单位访顺利完成:OK;单位访客户不配合:TBS 14
				"compVisitIncome varchar(3)," +//工作收入核实一致:OK;工作收入核实不一致:TBS 15
				"compVisitIncomeAmount varchar(10), " +//亲访员实际核实到的月收入金额16
				"compVisitJob varchar(3)," +//工作信息(行业、职务等)一致:OK;工作信息(行业、职务等)不一致：TBS 17
				"compVisit3Rd varchar(2)," +//没有第三方介入嫌疑:OK;有第三方介入嫌疑:KO 18
				"compVisitLoanPurpose varchar(3)," +//贷款用途无虚假:OK;贷款用途虚假:KO;贷款用途可疑，但无法完全确认:TBS 19
				//"compVisitApplySelf varchar(3), " +//确认亲访亲签:OK;未亲访亲签:TBS 20
				//"compVisitRisk varchar(2)," +//未发现赌博、吸毒等高风险特征:OK;发现赌博、吸毒等高风险特征:KO 21
				"positionDate varchar(14), "+//22
				"memoVisit varchar(100)," + 	 //提交主管备注

				"nameCust varchar(8)," +//性
				"customervisitDate varchar(8)," +//外访日期
				"customervisitPhone varchar(11)," +//手机号码
				"flipperActivity varchar(10), " +//草稿箱出来的功能模块名
				"flipperLayoutnum varchar(2), " +//草稿箱出来对应的是第几个布局文件
				"filename varchar(20), " +//文件夹名字
				"bollen varchar(8)," +//
				"applyfile_states varchar(2) " +//件的标示 1为草稿箱、2为发件箱
				")");

		//推荐与受理机构
		/*arg0.execSQL("create table if not exists Banck(" +
				"institutionCode varchar(4) primary key, " +//机构代码 0
				"institutionName varchar(40)," +//机构名称1
				"DB_ID varchar(19)," +//DB_ID2
				"updateTime varchar(14)," +//创建时间4
				"area varchar(14)," +//地区
				"acceptable varchar(5)," +//是否加载为受理行
				"reserver1 varchar(5)" +//预留字段
				")");

		//推荐与受理支行
		arg0.execSQL("create table if not exists SubBank(" +
				"branchCode varchar(4) primary key, " +//支行代码 0
				"branchName varchar(40)," +//支行名称1
				"PAREND_DB_ID varchar(19)," +//PAREND_DB_ID通过支行下的PAREND_DB_ID字段值等于机构下DB_ID字段值,来进行机构与支行关联。2
				"updateTime varchar(14)," +//创建时间3
				"area varchar(14)," +//地区
				"inputable varchar(5)," +//是否可以录入推荐或受理人
				"reserver1 varchar(5)" +//是否加载为受理行
				")");*/



		/*
		 * remove掉更新时间
		 * 参数表需要加
		 */
		//sp.removeParameters("Banck");
		//sp.removeParameters("SubBank");


		arg0.execSQL("create table IF NOT EXISTS ElectronicData(" + // 电子资料管理表
				"_id varchar2(20) primary key," + // 电子资料id，以新建电子资料时间戳为id
				"data_name varchar2(100)," + // 资料名称
				"customer_idtype varchar2(20)," + //证件类型
				"customer_idnum varchar2(100)," + // 客户证件号码
				"customer_name varchar2(30)," + // 客户姓名
				"file_path varchar2(50)," + // 资料保存路径，资料文件保存在pad的sdcard路径
				"create_time varchar2(10)," + //过期时间
				"expiration_time varchar2(10))");// 创建时间

		System.out.println("修改数据库onUpdate()");


	}



	public boolean isColumnExists(SQLiteDatabase db, String tabName, String col){
		boolean result = true;
		Cursor cursor = null;
		try{
			cursor = db.rawQuery("select * from "+tabName, null);
			if(cursor.getColumnIndex(col) == -1){
				result = false;
			}
		}
		catch(Exception e){
			result = false;
		}
		finally{
			if(cursor!=null)
				cursor.close();
		}
		return result;
	}

	public boolean IsTableExists(SQLiteDatabase db, String tabName){
		boolean result = false;
		if(tabName == null){
			return false;
		}
		Cursor cursor = null;
		try {
			String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tabName.trim()+"' ";
			cursor = db.rawQuery(sql, null);
			if(cursor.moveToNext()){
				int count = cursor.getInt(0);
				if(count>0){
					result = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			if(cursor != null)
				cursor.close();
		}
		return result;
	}


	public static int getDBVer(){
		return Constants.dbVer;
	}

	//删除数据库
	public boolean deleteDataBase(Context context){
		return context.deleteDatabase(Constants.dbName);
	}

	public boolean deleteFriendDataBase(Context friendContext, String databaseName){
		return friendContext.deleteDatabase(databaseName);
	}

}