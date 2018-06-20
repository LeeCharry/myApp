package com.example.jack.myapp.demo.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ���ݿ⽨�� time 20120426
 * 
 * @author chengyuan
 * 
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

	private static MyDatabaseHelper dbHelper = null;
	// private static MyDatabaseHelper dbFriendHelper = null;

	SharedPrefs sp;

	public MyDatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
		sp = new SharedPrefs(context);
	}
		
	// public static MyDatabaseHelper getInstance(Context context){
	// if(dbHelper == null){
	// dbHelper = new MyDatabaseHelper(context, Constants.dbName,
	// Constants.dbVer);
	// }
	// return dbHelper;
	// }
		
	// public static synchronized MyDatabaseHelper getFriendInstance(Context
	// friendContext, String databaseName, int friendDBver){
	// if(dbFriendHelper == null){
	// dbFriendHelper = new MyDatabaseHelper(friendContext, databaseName,
	// friendDBver);
	// }
	// return dbFriendHelper;
	// }
	//
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		System.out.println("onCreate()");

		// �û���Ϣ
		arg0.execSQL("create table if not exists user("
				+ "newId varchar(10) primary key, " + // ����
				"username varchar(10), " + // �û��� 0
				"password varchar(15)," + // ����1
				"user_type varchar(19) , " + // �û���ɫ2
				"loginmodel varchar(20)," + // ��½ģʽ3
				"identity varchar(10)," + // ��½ƾ֤4
				"city varchar(15)," + // ҵ������5
				"city_id varchar(19) , " + // ���д���6
				"prov varchar(20)," + // ҵ��ʡ7
				"prov_id varchar(10)," + // ʡ����8
				"lastpwdchangetime varchar(15)," + // ��������޸�ʱ��9
				"pwdovertime varchar(19) , " + // �������ʱ��10
				"isdevicelocked varchar(20)," + // �豸�Ƿ�����11

				"name varchar(10)," + // ��������12
				"phone varchar(15)," + // �ֻ�����13
				"canumber varchar(19), " + // CA����14
				"bankIdentification varchar(20)," + // ���б�ʾ15
				"area varchar(10)," + // ����16
				"company varchar(19)," + // ������˾17
				"institutionCode varchar(20)," + // ��������18
				"institutionName varchar(10)" + // ��������19

				")");
		// ҵ�������ṹscbscbprojects_apply
		arg0.execSQL("create table if not exists cfccc_apply("
				+ "id varchar(10) primary key, " + // 0

				"authNumber varchar(20), " + // ��Ȩ�� 1
				"urn varchar(20), " + // urn 2
				"orgCode varchar(10)," + // �л�����3
				"productId varchar(10), " + // ��Ʒ���4
				"staffId varchar(20), " + // Ա����5
				"serialNumber varchar(15)," + // ��ˮ��6
				"storeNumber varchar(10), " + // �̻����7
				"storeName varchar(30)," + // �̻�����8
				"barcode varchar(10)," + // ����9
				"signatureDate varchar(100), " + // �����ǩ������10
				"applyTime varchar(10)," + // ��������11
				"purchaseCode varchar(10), " + // ������;12
				"planNumber varchar(10)," + // ����ƻ�13
				"calculedTerm varchar(10)," + // ����14
				"desiredCreditLimit varchar(20), " + // �������15
				"claPrincipal varchar(15)," + // ������16
				"identitytype varchar(5)," + // ֤������17
				"identityNumber varchar(18)," + // ֤������18

				"groupLoanCode varchar(20), " + // �Ű쵥λ19
				"payrollAgencyUnit varchar(20)," + // �������ʵ�λ20
				"loanReductionInitial varchar(10), " + // CA�������(�ٷֱ�)21
				"specialCustCode varchar(10), " + // ����ͻ���22
				"nameCust varchar(5)," + // ������23
				"surnameCust varchar(10), " + // ������24
				"birthDateCust varchar(10)," + // ��������25
				"title varchar(2)," + // �Ա�26
				"maritalstatus varchar(5)," + // ����״��27
				"educationLevel varchar(10)," + // �����̶�28
				"houseStatus varchar(10)," + // סլ����29
				"firstHomeRentHousehold varchar(10)," + // ס��֧��30
				"phoneNumberZone varchar(10)," + // סլ�绰-zone 31
				"phoneNumberMain varchar(10)," + // סլ�绰-main 32
				"mobilePhone varchar(11)," + // �ֻ�33
				"yearSalary varchar(10)," + // ˰��������34
				"province varchar(10)," + // סլ��ַ/ʡ35
				"city varchar(10)," + // ��36
				"district varchar(20)," + // ��(��)37

				"address varchar(30), " + // ��ַ38
				"zipCode varchar(6), " + // �ʱ�39
				"email varchar(20)," + // �����ʼ�40
				"spIdentitytype varchar(8), " + // ��ż֤������41
				"spIdentityNumber varchar(18), " + // ��ż֤������42

				"spName varchar(5)," + // ��ż������43
				"spSurname varchar(10), " + // ��ż������44
				"spMobilePhone varchar(15)," + // ��ż�绰����45
				"spCompanyName varchar(20), " + // ��ż������λ46
				"spYearSalary varchar(10)," + // ��ż˰��������47
				"custCompanyIndustryMemo varchar(50), " + // ��ҵ��Ϣע��48
				"custJobCode varchar(15)," + // ְλ49
				"custCompanyName varchar(30), " + // ��λ����50
				"custCompanyType varchar(20)," + // ��λ����51
				"custCompanyTypeOther varchar(50), " + // ����(��ע��)52

				"custJobDuty varchar(20), " + // ְ��53
				"custJobDepartment varchar(20), " + // ��ְ����54
				"custJobYears varchar(5), " + // �ֵ�λ��������55
				"custJobProvince varchar(10), " + // ��λ��ַ/ʡ56
				"custJobCity varchar(10), " + // ��57
				"custJobDistrict varchar(15), " + // ��(��)58
				"custJobAddress varchar(30), " + // ��ַ59

				"custJobZipCode varchar(6), " + // �ʱ�60
				"custJobPhoneNumZone varchar(10), " + // ��λ�绰-zone61
				"custJobPhoneNumMain varchar(10), " + // ��λ�绰-main 62
				"custJobPhoneNumExtension varchar(10), " + // ��λ�绰-ext63
				"relCnLast varchar(5), " + // ������ϵ��,������64
				"relCnFirst varchar(10), " + // ������ϵ��,������65
				"relMobilePhone varchar(11), " + // �ֻ�66
				"relTelephoneZone varchar(10), " + // �绰-zone67

				"relTelephoneMain varchar(11), " + // �绰-main68
				"relCompanyName varchar(30), " + // ��λ����69
				"relRelationship varchar(11), " + // �������˹�ϵ70
				"relRelationshipOther varchar(20), " + // ��������������ϵ(��ע��)71
				"refCnLast varchar(5), " + // ������ϵ��,������72
				"refCnFirst varchar(10), " + // ������ϵ��,������73
				"refMobilePhone varchar(11), " + // �ֻ�74
				"refTelephoneZone varchar(10), " + // �绰-zone75
				"refTelephoneMain varchar(10), " + // �绰-main76
				"refCompanyName varchar(30), " + // ��λ����77
				"refRelationship varchar(10), " + // �������˹�ϵ78
				"refRelationshipOther varchar(20), " + // ��������������ϵ(��ע��)79
				"accountNumber varchar(20), " + // �Զ��ۿ��ǿ�����80
				"trustPayFlag varchar(10), " + // �Ƿ�����֧��81
				"trustAccountName varchar(10), " + // ����82

				"trustAccountNumber varchar(20), " + // ���˺�83
				"trustAccountBranchName varchar(15), " + // ��������84

				"isBonAcctFlag varchar(5), " + // �Ƿ������˺�85
				"commendBank varchar(10), " + // �Ƽ�����86
				"commendSubBank varchar(15), " + // �Ƽ�֧��87
				"commendPersonCode varchar(20), " + // ѡ���Ƽ��˹���88
				"cust1CustFutureField8 varchar(20), " + // ¼���Ƽ��˹���89
				"commendStaffTel varchar(15), " + // �Ƽ�����ϵ�绰90
				"inSource varchar(10), " + // ������Դ91
				"acceptBranch varchar(10), " + // �������92
				"acceptSubBranch varchar(10), " + // ����֧��93
				"enteringPerson varchar(20), " + // ѡ�������˹���94
				"cust2CustFutureField8 varchar(20), " + // ¼���Ƽ��˹���95
				"enteringMobile varchar(15), " + // ��������ϵ�绰96
				"acceptanceOpinions varchar(50), " + // �������97

				"purchaseType varchar(20), " + // ��Ʒ���98

				"cashPrice varchar(10), " + // �ֽ��99
				"initialShare varchar(10), " + // �׸���100
				"custTeacherRank varchar(10), " + // ְλ������ʦ���� 101

				"isUploadPhoto varchar(5), " + // �ͻ������ۺ�Ӱ102
				"hasIDCardCopy varchar(5), " + // ���֤��Ƭ103
				"hasLoanUse varchar(5), " + // ������;֤��104
				"hasBankPassbook varchar(5), " + // ����֤��105
				"hasOtherProofOfSalary varchar(5), " + // ����֤��106
				"hasIncomesUpdate  varchar(5), " + // ������������֤��107
				"hasOther varchar(5), " + // ����108
				"hasSpouseIDCardCopy varchar(5), " + // ��ż���֤��Ƭ109
				"hasSpouseBankPassbook varchar(5), " + // ��ż���д��ۻ򿨺�110
				"highQualityEnterprise varchar(5), " + // ������ҵ111

				"accFutureField33 varchar(5), " + // ����ר����112
				"applyGYDKType varchar(5), " + // ��������113
				"acceptUpOrDown varchar(5), " + // ��ר����δ����ͨ�����Ƿ�ͬ������빺��114
				"isFarmLoanFlag varchar(5), " + // ũ��115
				"isResident varchar(5), " + // �Ǿ���116
				"salaryDate varchar(5), " + // ��н��117
				"preferedComm varchar(5), " + // �˵��ʼĵ�ַ118
				"cust2FutureField4 varchar(5), " + // �˵�/��Ƭ���ͷ�ʽ119
				"provinceOther varchar(5), " + // �˵�/��Ƭ����������ַ/ʡ120
				"cityOther varchar(5), " + // �˵�/��Ƭ����������ַ/��121
				"districtOther varchar(5), " + // �˵�/��Ƭ����������ַ/��(��)122

				"addressOther varchar(5), " + // �˵�/��Ƭ����������ַ123
				"zipCodeOther varchar(5), " + // �˵�/��Ƭ�����ʱ�124
				"custClass varchar(5), " + // 125
				"hasIndSpousePbocAuth varchar(5), " + // 126

				"dynamicpassword varchar(10), " + // ��̬����127
				"flipperActivity varchar(10), " + // �ݸ�������Ĺ���ģ����
				"flipperLayoutnum varchar(2), " + // �ݸ��������Ӧ���ǵڼ��������ļ�
				"filename varchar(20), " + // �ļ�������
				"type varchar(20), " + // ҵ������
				"applyfile_states varchar(2)," + // ���ı�ʾ 1Ϊ�ݸ��䡢2Ϊ������
				"plonCustomerType varchar(6) ," + // �������� rp,�¿ͻ�
													// �ͻ����͵��ж�NEW_PT��������;
													// RP_PT��RP;
													// NEW_BT�������벹¼��OLD_BT���Ͽͻ���¼;
				"appAction varchar(5)," + "accessoryPhotoStage varchar(5)," + // ���븽������ı�־
				"commendname varchar(10)," + "goods varchar(20)" + ")");

		// ���
		arg0.execSQL("create table if not exists cfccc_customervisit("
				+ "id varchar(20) primary key, " + // ��Ȩ�� 0
				"authNumber varchar(20), " + // ��Ȩ�� 01
				"productCategory varchar(20), " + // ��Ʒ��� 1
				"visitType varchar(1), " + // �������, ��λ�ã�C;�ҷ�:H 2
				"custName varchar(15)," + // �ͻ�����3
				"asCom varchar(1200), " + // ���Ա��ע4
				"latitudeAndLongitude varchar(20), " + // ��γ��5
				"exemptHomeVisit varchar(7), " + // ��ҷ�ѡ��,��ҷ�:Y;��Ҫ�ҷ�:Nornull 6
				"homeVisitFinished varchar(3)," + // �ҷ�˳�����:OK;�ҷÿͻ������:TBS;�ͻ���������Υ�����KO
													// 7
				"homeVisit3Rd varchar(2), " + // û�е�������������:OK;��ס�����ϲ�,�����뿴Ӱ��:TBS;�е�������������:KO
												// 8
				"homeVisitLoanPurpose varchar(3)," + // ������;�����:OK;������;���ɵ��޷���ȫȷ��:TBS;������;���:KO
														// 9
				"homeVisitApplySelf varchar(3)," + // ȷ���׷���ǩ:OK;δ�׷���ǩ:TBS;�����������:KO
													// 10
				"homeVisitRisk varchar(2), " + // δ���ַ�������:OK;����,�����ע:TBS;���ֿͻ��Ĳ���������Ƿ�߶���ծ�ȸ߷�������:KO
												// 11
				// "homeVisitStatus varchar(1),"
				// +//����Ƿ�ͨ��,���δ�����ش��ɵ�:2;��÷��ֿͻ��жġ������ش��ɵ�:4 12

				"exemptCompVisit varchar(6), " + // �ⵥλ��ѡ��,�ⵥλ��:Y;��Ҫ��λ��:Nornull
													// 13
				"compVisitFinished varchar(3)," + // ��λ��˳�����:OK;��λ��δ˳�����:TBS;���ֿͻ��Ĳ���������Ƿ�߶���ծ�ȸ߷�������:KO
													// 14
				"compVisitIncome varchar(3)," + // �����ʵһ��:OK;�����ʵ��һ��:TBS;����֤�����:KO
												// 15
				"compVisitIncomeAmount varchar(10), " + // �����ʵ��һ�»������޷���ʵ�ʺ�ʵ����������:TBS
														// 16
				"compVisitJob varchar(3)," + // ������Ϣ(��ҵ��ְ���)һ��:OK;������Ϣ(��ҵ��ְ���)��һ�£�TBS;������Ϣ���:KO
												// 17
				"compVisit3Rd varchar(2)," + // �칫�绰��ʵ:OK;�칫�绰�޷���ʵ����ʵ:TBS 18
												// �汾���£���ʱ���
				"compVisitLoanPurpose varchar(3)," + // ��˾��������:OK;����,�����ע:TBS;��˾δ��������:KO
														// 19 �汾���£���ʱ���
				// "compVisitApplySelf varchar(3), " +//ȷ���׷���ǩ:OK;δ�׷���ǩ:TBS 20
				// �汾���£�����
				// "compVisitRisk varchar(2),"
				// +//δ���ֶĲ��������ȸ߷�������:OK;���ֶĲ��������ȸ߷�������:KO 21 �汾���£�����
				"positionDate varchar(14), " + // 22
				"memoVisit varchar(100)," + // �ύ���ܱ�ע

				"nameCust varchar(8)," + // ��
				"customervisitDate varchar(8)," + // �������
				"customervisitPhone varchar(11)," + // �ֻ�����
				"flipperActivity varchar(10), " + // �ݸ�������Ĺ���ģ����
				"flipperLayoutnum varchar(2), " + // �ݸ��������Ӧ���ǵڼ��������ļ�
				"filename varchar(20), " + // �ļ�������
				"bollen varchar(8)," + //
				"applyfile_states varchar(2)," + // ���ı�ʾ 1Ϊ�ݸ��䡢2Ϊ������
				"futureField6 varchar(10)"+     //2018-06-12   �Ƿ�ֱ��ͨ����ʶ
				")");

		// �������
		arg0.execSQL("create table if not exists send("
				+ "filename varchar(4) primary key, " + // �����ļ���0
				"beginnum varchar(40)," + // ������ʼ����1
				"totalnum varchar(19)" + // �ļ��ܶ���2
				")");
		// ************************************************************�����ļ����ݿ�****************************************//

		// �Ż���ϢFavorableMessage
		arg0.execSQL("create table if not exists FavorableMessage("
				+ "favorablecode varchar(2) , " + // �Ż���Ϣ���� 0
				"favorablecontent varchar(30)," + // �Ż���Ϣ��ʾ 1
				"updateTime varchar(14)," + // ����ʱ��2
				"org varchar(5)," + //
				"type varchar(5)" + ")");
		// ������;
		arg0.execSQL("create table if not exists PurchaseCode("
				+ "purchasecode varchar(3) primary key, " + // ������;���� 0
				"purchasecontent varchar(30)," + // ������;��ʾ1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// ��ҵ��Ϣ
		arg0.execSQL("create table if not exists CompanyMemo("
				+ "companymemocode varchar(2) primary key, " + // ��ҵ��Ϣ���� 0
				"companymemocontent varchar(30)," + // ��ҵ��Ϣ��ʾ1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// ְλ
		arg0.execSQL("create table if not exists JobMemo("
				+ "jobmemocode varchar(50) primary key, " + // ְλ���� 0
				"jobmemocontent varchar(30)," + // ְλ��ʾ1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// ְλ-��ʦ����    
		arg0.execSQL("create table if not exists JobTeacherLevel("
				+ "jobteachercode varchar(2) primary key, " + // ��ʦ������� 0
				"jobteachercontent varchar(30)," + // ��ʦ������ʾ1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// ��λ����
		arg0.execSQL("create table if not exists CompanyType("
				+ "companytypecode varchar(2) primary key, " + // ��λ���ʴ��� 0
				"companytypecontent varchar(30)," + // ��λ������ʾ1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// ְ��
		arg0.execSQL("create table if not exists JobLevel("
				+ "joblevelcode varchar(2) primary key, " + // ְ�� 0
				"joblevelcontent varchar(30)," + // ְ��1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// ����״��
		arg0.execSQL("create table if not exists MaritalStatus("
				+ "maritalstatuscode varchar(1) primary key, " + // ����״�� 0
				"maritalstatuscontent varchar(10)," + // ����״��1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// �����̶�
		arg0.execSQL("create table if not exists EducationLevel("
				+ "educationlevelcode varchar(1) primary key, " + // �����̶� 0
				"educationlevelcontent varchar(16)," + // �����̶�1
				"updateTime varchar(14)" + // ����ʱ��2
				")");
		// �ͻ�������
		arg0.execSQL("create table if not exists SpecialCost("
				+ "specialcostcode varchar(3) primary key, " + // �ͻ������� 0
				"specialcostcontent varchar(30)," + // �ͻ�������1
				"updateTime varchar(14)" + // ����ʱ��2
				")");

		// �Ƽ����������
		arg0.execSQL("create table if not exists Banck("
				+ "institutionCode varchar(4) primary key, " + // �������� 0
				"institutionName varchar(40)," + // ��������1
				"DB_ID varchar(19)," + // DB_ID2
				"updateTime varchar(14)," + // ����ʱ��4
				"area varchar(14)," + // ����
				"acceptable varchar(5)," + // �Ƿ����Ϊ������
				"reserver1 varchar(5)" + // Ԥ���ֶ�
				")");

		// �Ƽ�������֧��
		arg0.execSQL("create table if not exists SubBank("
				+ "branchCode varchar(4) primary key, " + // ֧�д��� 0
				"branchName varchar(40)," + // ֧������1
				"PAREND_DB_ID varchar(19)," + // PAREND_DB_IDͨ��֧���µ�PAREND_DB_ID�ֶ�ֵ���ڻ�����DB_ID�ֶ�ֵ,�����л�����֧�й�����2
				"updateTime varchar(14)," + // ����ʱ��3
				"area varchar(14)," + // ����
				"inputable varchar(5)," + // �Ƿ����¼���Ƽ���������
				"reserver1 varchar(5)" + // �Ƿ����Ϊ������
				")");
		// �Ƽ�����������
		arg0.execSQL("create table if not exists Sale("
				+ "saleCode varchar(10) primary key, " + // �Ƽ���/�����˴��� 0
				"saleName varchar(40)," + // �Ƽ���/����������1
				"areaNumbers varchar(20)," + // �������2
				"nodeType varchar(2)," + // ��������3
				"updateTime varchar(14)" + // ����ʱ��4
				")");
		// ************************************************************�����ļ����ݿ�****************************************//
		// Activity�
		arg0.execSQL("create table if not exists Activity("
				+ "Id varchar(10) primary key, " + // ID 0
				"activityId varchar(20)," + // �����1
				"activityName varchar(20)," + // �����1
				"company varchar(18)," + // ������˾2
				"cityCode varchar(3)," + // ���ڳ�������3
				"role varchar(20)," + // ��ɫ4
				"startTime varchar(10)," + // ��ʼ����5
				"endTime varchar(10)," + // ��������6
				"isTop varchar(1)," + // �Ƿ��ö�7
				"filePath varchar(64)," + // �ļ�·��8
				"filelength varchar(64)," + // �ļ�����10
				"updateTime varchar(14)," + // ����ʱ��9
				"isdownload varchar(14)" + // �Ƿ��Ѿ����� 0û������1�Ѿ�����
				")");
		// Contractֽ�ʺ�Լ���ò���
		arg0.execSQL("create table if not exists Contract("
				+ "isOpen varchar(2) primary key, " + // ֽ�ʺ�Լ���� 0
				"updateTime varchar(14)" + // ����ʱ��1
				")");
		// DraftTime�ݸ���ʱ��
		arg0.execSQL("create table if not exists DraftTime("
				+ "draftTime varchar(4) primary key, " + // �ݸ��䱣��ʱ�� 0
				"updateTime varchar(14)" + // ����ʱ��1
				")");
		// Merchant�̻�Ӫ��֧��
		arg0.execSQL("create table if not exists Merchant("
				+ "Id varchar(10) primary key, " + // ID 0
				"merchantId varchar(10), " + // ID1
				"merchantInfor varchar(20)," + // �̻�֧����Ϣ1
				"company varchar(18)," + // ������˾2
				"cityCode varchar(3)," + // ���ڳ�������3
				"role varchar(20)," + // ��ɫ4
				"startTime varchar(10)," + // ��ʼ����5
				"endTime varchar(10)," + // ��������6
				"isTop varchar(1)," + // �Ƿ��ö�7
				"filePath varchar(64)," + // �ļ�·��8
				"filelength varchar(64)," + // �ļ�����10
				"updateTime varchar(14)," + // ����ʱ��11
				"isdownload varchar(14)" + // �Ƿ��Ѿ����� 0û������1�Ѿ�����
				")");
		// Notice����
		arg0.execSQL("create table if not exists Notice("
				+ "Id varchar(10) primary key, " + // ID 0
				"noticeId varchar(20)," + // ��������1
				"noticeTitle varchar(20)," + // ��������1
				"noticeContent varchar(20)," + // ��������2
				"company varchar(18)," + // ��˾3
				"cityCode varchar(3)," + // ���ڳ�������4
				"role varchar(20)," + // ��ɫ5
				"startTime varchar(10)," + // ��ʼ����6
				"endTime varchar(10)," + // ��������7
				"isTop varchar(1)," + // �Ƿ��ö�8
				"updateTime varchar(14)" + // ����ʱ��9
				")");
		// Pic��������
		arg0.execSQL("create table if not exists Pic(" + "id varchar(1), " + // �׶�0
				"stage varchar(1), " + // �׶�0
				"file_name varchar(20), " + // �ļ������ƣ����ڴ����Ƭ1
				"big_info varchar(100), " + // ������Ϣ2
				"small_info varchar(100)," + // С����Ϣ3
				"updateTime varchar(14)" + // ����ʱ��4
				")");
		// PicSort�����������
		arg0.execSQL("create table if not exists PicSort("
				+ "bigName varchar(1), " + // �����������0
				"fileName varchar(20), " + // �ļ�������1
				"bigCode varchar(100), " + // ������2
				"updateTime varchar(14)" + // ����ʱ��3
				")");
		// Product��Ʒ
		arg0.execSQL("create table if not exists Product("
				+ "Id varchar(10) primary key, " + // ID 0
				"productId varchar(20)," + // ��Ʒ����1
				"productName varchar(20)," + // ��Ʒ����1
				"product varchar(20)," + // ��Ӧ��Ʒ2
				"company varchar(18)," + // ��˾3
				"cityCode varchar(3)," + // ���ڳ�������4
				"role varchar(20)," + // ��ɫ5
				"startTime varchar(10)," + // ��ʼ����6
				"endTime varchar(10)," + // ��������7
				"isTop varchar(1)," + // �Ƿ��ö�8
				"filePath varchar(64)," + // �ļ�·��9
				"filelength varchar(64)," + // �ļ�����10
				"updateTime varchar(14)," + // ����ʱ��11
				"isdownload varchar(14)" + // �Ƿ��Ѿ����� 0û������1�Ѿ�����
				")");
		// StandardTalk��׼����
		arg0.execSQL("create table if not exists StandardTalk("
				+ "Id varchar(10) primary key, " + // ID 0
				"standardId varchar(20)," + // ��׼��������1
				"standardNamer varchar(20)," + // ��׼��������1
				"company varchar(18)," + // ������˾2
				"cityCode varchar(3)," + // ���ڳ�������3
				"role varchar(20)," + // ��ɫ4
				"startTime varchar(10)," + // ��ʼ����5
				"endTime varchar(10)," + // ��������6
				"isTop varchar(1)," + // �Ƿ��ö�7
				"filePath varchar(64)," + // �ļ�·��8
				"filelength varchar(64)," + // �ļ�����10
				"updateTime varchar(14)," + // ����ʱ��9
				"isdownload varchar(14)" + // �Ƿ��Ѿ����� 0û������1�Ѿ�����
				")");
		// Training��ѵ
		arg0.execSQL("create table if not exists Training("
				+ "Id varchar(10) primary key, " + // ID 0
				"trainingId varchar(10), " + // ID 0
				"trainingNamer varchar(20)," + // �γ�����1
				"company varchar(18)," + // ������˾2
				"cityCode varchar(3)," + // ���ڳ�������3
				"role varchar(20)," + // ��ɫ4
				"startTime varchar(10)," + // ��ʼ����5
				"endTime varchar(10)," + // ��������6
				"seconds varchar(10)," + // �������7
				"isTop varchar(1)," + // �Ƿ��ö�8
				"filePath varchar(64)," + // �ļ�·��9
				"filelength varchar(64)," + // �ļ�����10
				"updateTime varchar(14)," + // ����ʱ��9
				"isdownload varchar(2)," + // �Ƿ��Ѿ����� 0û������1�Ѿ�����
				"isreply varchar(2)" + // �Ƿ��Ѿ��ظ� nullû�лظ� 1�Ѿ��ظ�
				")");

		// ʡ
		arg0.execSQL("create table if not exists Province("
				+ "province_id varchar(10) primary key," + // ʡ����
				"province varchar(20)," + // ʡ����
				"updateTime varchar(14)" + // ����ʱ��10
				")");
		// ��
		arg0.execSQL("create table if not exists City("
				+ "city_id varchar(20) primary key," + // �д���
				"city varchar(20)," + // ������
				"province_id varchar(20)," + // ʡ����
				"updateTime varchar(14)" + // ����ʱ��10
				")");
		// ��
		arg0.execSQL("create table if not exists District("
				+ "area_id varchar(20) primary key," + // ������
				"area varchar(20)," + // ������
				"city_id varchar(20)," + // �д���
				"updateTime varchar(14)" + // ����ʱ��10
				")");
		// ************************************************************�����ļ����ݿ�****************************************//
		// �Ű�/�������ʵ�λ
		arg0.execSQL("create table if not exists GroupCustomer("
				+ "GROUP_NO varchar(20) primary key," + // ��λ��� 0
				"GROUP_NAME varchar(120)," + // ��λ����1
				"GROUP_DISTRICT varchar(30)," + // ��λ���ڵ���2
				"ORG_CODE varchar(4)," + // �������3
				"CREATE_TIME varchar(14)," + // ����ʱ��4
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// PLAN���̻���Ӧ��ϵ��
		arg0.execSQL("create table if not exists PlanStore("
				+ "PLAN_ID varchar(9) primary key, " + // PLAN_ID 0
				"STORE_NUMBER varchar(9)," + // �̻���1
				"CREATE_TIME varchar(14)," + // ����ʱ��2
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// plan���̻�����Ʒ��Ӧ��ϵ��
		arg0.execSQL("create table if not exists PlanStoreGoods("
				+ "PLAN_ID varchar(9), " + // PLAN_ID 0
				"STORE_GOODS_ID varchar(10)," + // STORE_GOODS_ID1
				"CREATE_TIME varchar(14)," + // ����ʱ��2
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// ��Ʒ��Ϣ��
		arg0.execSQL("create table if not exists Goods("
				+ "GOODS_ID varchar(9) primary key, " + // ��ƷID 0
				"GOODS_CODE varchar(3)," + // ��Ʒ���1
				"GOODS_NAME varchar(50)," + // ��Ʒ����2
				"CATEGORY_ID varchar(4)," + // ���ID3
				"CREATE_TIME varchar(14)," + // ����ʱ��4
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// ��Ʒ�����Ϣ��
		arg0.execSQL("create table if not exists GoodsCategory("
				+ "CATEGORY_ID varchar(4) primary key, " + // ��Ʒ���ID0
				"CATEGORY_NAME varchar(60)," + // �������1
				"CATEGORY_CODE varchar(32)," + // �����2
				"CREATE_TIME varchar(14)," + // ����ʱ��3
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// �̻��û���Ӧ��ϵ��
		arg0.execSQL("create table if not exists UmsUserStores("
				+ "USER_ACCESS_ID varchar(25), " + // �û�ID0
				"STORE_NUMBER varchar(9)," + // �̻���1
				"CREATE_TIME varchar(14)," + // ����ʱ��2
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// �̻���Ϣ��
		arg0.execSQL("create table if not exists Stores("
				+ "STORE_NUMBER varchar(9) primary key, " + // �̻��� 0
				"STORE_NAME varchar(40)," + // �̻�����1
				"STORE_DISTRICT varchar(3)," + // �̻�����3
				"STORES_UNION varchar(2)," + // ��������2
				"CREATE_TIME varchar(14)," + // ����ʱ��4
				"STOPE_CHAIN varchar(14)," + "updateTime varchar(14)" + // ����ʱ��
				")");
		// �̻�����Ʒ��Ӧ��ϵ��
		arg0.execSQL("create table if not exists StoreGoods("
				+ "STORE_GOODS_ID varchar(10) primary key, " + // ����ID0
				"GOODS_ID varchar(9)," + // ��ƷID1
				"STORE_NUMBER varchar(9)," + // �̻���2
				"CREATE_TIME varchar(14)," + // ����ʱ��3
				"updateTime varchar(14)" + // ����ʱ��
				")");

		// ����ƻ���Ϣ��
		arg0.execSQL("create table if not exists Plan("
				+ "PLAN_ID varchar(9) primary key, " + // ����PLAN_ID0
				"PLAN_NUMBER varchar(5)," + // �ƻ����1
				"PLAN_NAME varchar(50)," + // �ƻ�����2
				"TERM_MIN varchar(5)," + // ������Сֵ3
				"TERM_MAX varchar(5)," + // �������ֵ4
				"CREATE_TIME varchar(14)," + // ����ʱ��5
				"custClass varchar(14)," + // ����ʱ��6 �ͻ�Ⱥ 20140410
				"updateTime varchar(14)" + // ����ʱ��

				")");

		// �ͻ�Ⱥ
		arg0.execSQL("create table if not exists CustClass("
				+ "KEY varchar(9), " + // ����PLAN_ID0
				"custClassValue varchar(10)," + // �ƻ����1
				"producType varchar(14)," + // ����ʱ��
				"updateTime varchar(14)" + ")");

		// ��Ʒ��Ϣ��
		arg0.execSQL("create table if not exists Products("
				+ "PRODUCT_ID varchar(9) primary key, " + // ����PRODUCT_ID0
				"LOGO varchar(3)," + // ��ƷLOGO1
				"PRODUCT_CODE varchar(4)," + // ��Ʒ���2
				"PRODUCT_NAME varchar(40)," + // ��Ʒ����3
				"PRODUCT_CATEGORY varchar(2)," + // ��Ʒ���4
				"CREATE_TIME varchar(14)," + // ����ʱ��5
				"updateTime varchar(14)" + // ����ʱ��
				")");
		// ��Ʒ��Ϣ��
		arg0.execSQL("create table if not exists ProductStores("
				+ "PRODUCT_ID varchar(9) , " + // ��ƷID 0
				"STORE_NUMBER varchar(9)," + // �̻����1
				"CREATE_TIME varchar(14)," + // ����ʱ��2
				"updateTime varchar(14)" + // ����ʱ��4
				")");
		// ************************************************************�����ļ����ݿ�****************************************//
		// ��������
		arg0.execSQL("create table if not exists accessory_photo("
				+ "stage varchar(1), " + // �׶�
				"file_name varchar(20), " + // �ļ������ƣ����ڴ����Ƭ
				"big_info varchar(100), " + // ������Ϣ
				"small_info varchar(100)" + // С����Ϣ
				")");

		// Ǳ�͹���
		arg0.execSQL("create table if not exists marketing_potential("
				+ "name varchar(20) primary key," + // �ͻ�����
				"phone varchar(20)," + // �ֻ�����
				"time varchar(20)" + // ¼��ʱ��			
				")");

		// ���Ź��������ֽ�ʺ�Լ�Ŀ��ر�
		arg0.execSQL("create table if not exists sms_switch("
				+ "contract_switch varchar(20)," + // ֽ�ʺ�Լ������ɨ��Ŀ���
				"contract__time varchar(20)" + // ¼��ʱ��
				")");

		// emm
		arg0.execSQL("create table if not exists emm(" + "id varchar(20),"
				+ "key varchar(20)," + //
				"uuid varchar(20)," + //
				"ISREGISTED varchar(20)" + ")");

		// ͨ�ù���
		arg0.execSQL("create table if not exists workOrder("
				+ "id varchar(20)," + // �����ı�ʶ
				"content varchar(20)," + // ����
				"expiration varchar(20)," + // ����ʱ��
				"number varchar(20)," + // ������id
				"acquisitionTime varchar(20) ," + // ��ȡʱ��
				"isfeedback  varchar(20)" + // �Ƿ��� 0Ϊδ������1Ϊ�ѷ���
				")");

		// �̻����ڵ�������
		arg0.execSQL("create table if not exists StoreDistrict("
				+ "areanum varchar(20)," + // �������
				"areaname varchar(20)," + // ��������
				"updateTime varchar(20)" + // ����ʱ��
				")");
		// �̻�����
		arg0.execSQL("create table if not exists StoreType("
				+ "storenum varchar(20)," + //
				"storename varchar(20)," + //
				"updateTime varchar(20)" + // ����ʱ��
				")");
		// ************************************************************�����ļ����ݿ�****************************************//

		arg0.execSQL("create table if not exists StoreM("
				+ "id varchar(10) primary key, " + // 0

				"storeName varchar(20)," + // �̻����� 1
				"storeType varchar(20)," + // �̻����� 2
				"address varchar(20)," + // �̻���ַ3
				"phone varchar(20)," + // �绰4
				"fax varchar(20)," + // ����5
				"zipCode varchar(20)," + // �ʱ�7
				"bizLicenseNo varchar(20)," + // Ӫҵִ�պ���8
				"establishDate varchar(20)," + // ��������6
				"industry varchar(20)," + // ������ҵ9
				"companyType varchar(20)," + // ��˾����10
				"employeeAmount varchar(20)," + // Ա������11
				"salesStaffAmount varchar(20)," + // ������Ա��12
				"storeAmount varchar(20)," + // �ŵ���13
				"storeArea varchar(20)," + // �ŵ������14
				"juridicalPersonName varchar(20)," + // ���˴�������15
				"juridicalPersonId varchar(20)," + // ���˴������֤����16
				"homeBranch varchar(20)," + // �����з�֧��17
				"accountNumber varchar(20)," + // �˺�18
				"isBonAcctFlag varchar(20)," + // �Ƿ������˺�19
				"costLastYear varchar(20)," + // ȥ��ɱ�20
				"profitLastYear varchar(20)," + // ȥ������21
				"costThisYear varchar(20)," + // ����ɱ�Ԥ��22
				"profitThisYear varchar(20)," + // ��������Ԥ��23
				"annualSalesRevenue varchar(20)," + // ���������루������24
				"annualSalesCount varchar(20)," + // ��������(����)25
				"avgMonth varchar(20)," + // �¾����۶�26
				"avgBankMonth varchar(20)," + // �¾����ڻ����������۶�27
				"avgMonthCount varchar(20)," + // �¾�����������28
				"avgMonthBankCount varchar(20)," + // �¾����ڻ�����������������29
				"areaCode varchar(20)," + // �̻����ڳ��еı��30
				"areaName varchar(20)," + // �̻����ڳ���31

				"storeManagerInfoName varchar(20)," + // ����32
				"storeManagerInfoIdCardNo varchar(20)," + // ���֤��33
				"storeManagerInfoTitle varchar(20)," + // ְ��34
				"seniority varchar(20)," + // ����35

				"storeCopoBankInfo_name varchar(20)," + // ��������36
				"storeCopoBankInfo_bankType varchar(20)," + // ������ʽ37
				"storeCopoBankInfo_products varchar(20)," + // ���ڲ�Ʒ38
				"storeCopoBankInfo_satisfaction varchar(20)," + // �����39

				"storeBizMgrInfo_mgrName varchar(20)," + // ҵ��չ��������40
				"storeBizMgrInfo_storeWebsite varchar(20)," + // �̻���վ��ַ41
				"storeBizMgrInfo_merchantView varchar(20)," + // �̻�������Ը�������Լ��������Ŵ�����Ŀ���42
				"storeBizMgrInfo_m1ApprovalDocsNum varchar(20)," + // ��һ���º�׼����������43
				"storeBizMgrInfo_m2ApprovalDocsNum varchar(20)," + // �ڶ����º�׼����������44
				"storeBizMgrInfo_m3ApprovalDocsNum varchar(20)," + // �������º�׼����������45
				"storeBizMgrInfo_m1ApprovalDocsAmount varchar(20)," + // ��һ���º�׼���46
				"storeBizMgrInfo_m2ApprovalDocsAmount varchar(20)," + // �ڶ����º�׼���47
				"storeBizMgrInfo_m3ApprovalDocsAmount varchar(20)," + // �������º�׼���48
				"storeBizMgrInfo_bizSope varchar(20)," + // �̻�ҵ��Χ49
				"storeBizMgrInfo_customerLocate varchar(20)," + // �̻��Ŀͻ���λ50
				"storeBizMgrInfo_gainCustomerChannel varchar(20)," + // �̻���ȡ�ͻ�����51
				"storeBizMgrInfo_managerSug varchar(20)," + // ҵ��չ����������̻���ϵά���ƻ�52

				"storeBizMgrInfo_dealRemark varchar(20)," + // �̻�����Э��53
				"storeBizMgrInfo_accountRemark varchar(20)," + // ���������˻�֤��54
				"storeBizMgrInfo_idCardRemark varchar(20)," + // ���˴������֤��ӡ��55
				"storeBizMgrInfo_managerRemark varchar(20)," + // ��Ҫ���������֤��ӡ�������ò�ѯ��Ȩ56
				"storeBizMgrInfo_bizLicenseRemark varchar(20)," + // ��˾Ӫҵִ�ա���֯��������֤��˰��Ǽ�֤��ӡ��57
				"storeBizMgrInfo_financialRemark varchar(20)," + // ���ڲ��񱨱�58
				"storeBizMgrInfo_loanCardRemark varchar(20)," + // ��˾������룬�Լ���ѯ��Ȩ��59
				"storeBizMgrInfo_other varchar(20)," + // �������̻�����ͻ����ͬ�����ȵȣ�60

				"storeCarsInfo_carModel varchar(20)," + // ���� 61
				"storeCarsInfo_carPrice varchar(20)," + // �۸�62
				"storeCarsInfo_salesStatus varchar(20)," + // ÿ���������63
				"storeCarsInfo_stockStatus varchar(20)," + // ������64
				"salesInfo varchar(20)," + // �����������65
				"operateInfo varchar(20)," + // ��Ӫ�������66

				"typeNum varchar(20), " + //
				"viewper varchar(20), " + //
				"viewper1 varchar(20), " + //
				"viewper2 varchar(20), " + //
				"viewper3 varchar(20), " + //
				"filename varchar(20), " + // �ļ�������
				"type varchar(20), " + // ҵ������
				"applyfile_states varchar(2)," + // ���ı�ʾ 1Ϊ�ݸ��䡢2Ϊ������
				"custName varchar(20), " + // ����
				"applyTime varchar(10)," + // ��������11
				"flipperActivity varchar(10), " + // �ݸ�������Ĺ���ģ����
				"flipperLayoutnum varchar(2) " + // �ݸ��������Ӧ���ǵڼ��������ļ�
				")");

		// Cellid����
		arg0.execSQL("create table if not exists CellId("
				+ "isOpen varchar(2) primary key, " + //
				// "isOpen varchar(2)," +//����ʱ��1
				"updateTime varchar(14)" + // ����ʱ��1
				")");

		// //StoreRecomm����
		arg0.execSQL("create table if not exists StoreRecomm("
				+ "calculate_month varchar(6), " + // �·�
				"merchant_id varchar(10)," + // �̻���
				"merchant_name varchar(6), " + // �̻�����
				"recomm_staff_id varchar(6), " + // ҵ��Ա���
				"recomm_staff_name varchar(6), " + // ҵ��Ա����
				"funded_amt_total varchar(6), " + // ��Ͷ�Ŷ�
				"funded_amt_year varchar(6), " + // ����Ͷ��
				"funded_amt_quarter varchar(6), " + // ����Ͷ��
				"funded_amt_month varchar(6), " + // ����Ͷ��
				"current_bal varchar(6), " + // ��ǰ���
				"quarter_beginning_bal varchar(6), " + // �������
				"quarter_end_bal varchar(6), " + // ��ĩ���
				"quarter_bal_average varchar(6), " + // �����վ�
				"NPL_rate varchar(6), " + // ����
				"funded_target varchar(6), " + // Ŀ��Ͷ��
				"last_update_date varchar(6) " + // ��������
				")");
		//
		// //Cellid����
		arg0.execSQL("create table if not exists ApplyRecomm("
				+ "calculate_month varchar(6), " + // �·�
				"recomm_branch varchar(10)," + // �Ƽ���
				"recomm_sub_branch varchar(6), " + // �Ƽ�����
				"recomm_staff_id varchar(6), " + // ҵ��Ա���
				"recomm_staff_name varchar(6), " + // ҵ��Ա����
				"funded_amt_total varchar(6), " + // ��Ͷ�Ŷ�
				"funded_amt_year varchar(6), " + // ����Ͷ��
				"funded_amt_quarter varchar(6), " + // ����Ͷ��
				"funded_amt_month varchar(6), " + // ����Ͷ��
				"current_bal varchar(6), " + // ��ǰ���
				"quarter_beginning_bal varchar(6), " + // �������
				"quarter_end_bal varchar(6), " + // ��ĩ���
				"quarter_bal_average varchar(6), " + // �����վ�
				"NPL_rate varchar(6), " + // ����
				"funded_target varchar(6), " + // Ŀ��Ͷ��
				"last_update_date varchar(6) " + // ��������
				")");

		arg0.execSQL("create table IF NOT EXISTS ElectronicData(" + // �������Ϲ����
				"_id varchar2(20) primary key," + // ��������id�����½���������ʱ���Ϊid
				"data_name varchar2(100)," + // ��������
				"customer_idtype varchar2(20)," + // ֤������
				"customer_idnum varchar2(100)," + // �ͻ�֤������
				"customer_name varchar2(30)," + // �ͻ�����
				"file_path varchar2(50)," + // ���ϱ���·���������ļ�������pad��sdcard·��
				"create_time varchar2(10)," + // ����ʱ��
				"expiration_time varchar2(10))");// ����ʱ��

		// ���޸����ݿ�Ĳ���

		// if (!isColumnExists(arg0, "StoreM", "typeNum")) {
		// arg0.execSQL("ALTER TABLE StoreM  ADD COLUMN typeNum varchar(1) ");
		//
		// }
		// if (!isColumnExists(arg0, "Stores", "STOPE_CHAIN")) {
		// arg0.execSQL("ALTER TABLE Stores  ADD COLUMN STOPE_CHAIN varchar(14) ");
		//
		// }

		if (!IsTableExists(arg0, "CellId")) {
			arg0.execSQL("create table if not exists CellId("
					+ "isOpen varchar(2) primary key, " + // ֽ�ʺ�Լ���� 0
					// "isOpen varchar(2)," +//����ʱ��1
					"updateTime varchar(14)" + // ����ʱ��1
					")");
		}

		// �˵����͵�ַ
		if (!IsTableExists(arg0, "BillAddress")) {
			arg0.execSQL("create table if not exists BillAddress("
					+ "KEY varchar(2), " + // �˵���������KEYֵ
					"VALUE varchar(2)," + // �˵�����������ʾValueֵ
					"updateTime varchar(14)" + // �ļ����ɵ�ʱ��
					")");
		}
		// �޿�֧������
		if (!IsTableExists(arg0, "PlanTerm")) {
			arg0.execSQL("create table if not exists PlanTerm("
					+ "KEY varchar(2), " + // ����KEYֵ
					"VALUE varchar(2)," + // ������ʾValueֵ
					"updateTime varchar(14)" + // �ļ����ɵ�ʱ��
					")");
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �޸����ݿ�
		// arg0.execSQL("drop table Banck");
		// arg0.execSQL("drop table SubBank");
		arg0.execSQL("drop table cfccc_customervisit");

		// ���
		arg0.execSQL("create table if not exists cfccc_customervisit("
				+ "id varchar(20) primary key, " + // ��Ȩ�� 0
				"authNumber varchar(20), " + // ��Ȩ�� 01
				"productCategory varchar(20), " + // ��Ʒ��� 1
				"visitType varchar(1), " + // �������, ��λ�ã�C;�ҷ�:H 2
				"custName varchar(15)," + // �ͻ�����3
				"asCom varchar(1000), " + // ���Ա��ע4
				"latitudeAndLongitude varchar(20), " + // ��γ��5
				"exemptHomeVisit varchar(7), " + // ��ҷ�ѡ��,��ҷ�:Y;��Ҫ�ҷ�:Nornull 6
				"homeVisitFinished varchar(3)," + // �ҷ�˳�����:OK;�ҷÿͻ������:TBS 7
				"homeVisit3Rd varchar(2), " + // û�е�������������:OK;�е�������������:KO 8
				"homeVisitLoanPurpose varchar(3)," + // ������;�����:OK;������;���:KO;������;���ɵ��޷���ȫȷ��:TBS
														// 9
				"homeVisitApplySelf varchar(3)," + // ȷ���׷���ǩ:OK;δ�׷���ǩ:TBS 10
				"homeVisitRisk varchar(2), " + // δ���ֶġ����ȸ߷�������:OK;���ֶĲ��������ȸ߷�������:KO
												// 11
				// "homeVisitStatus varchar(1),"
				// +//����Ƿ�ͨ��,���δ�����ش��ɵ�:2;��÷��ֿͻ��жġ������ش��ɵ�:4 12
					
				"exemptCompVisit varchar(6), " + // �ⵥλ��ѡ��,�ⵥλ��:Y;��Ҫ��λ��:Nornull
													// 13
				"compVisitFinished varchar(3)," + // ��λ��˳�����:OK;��λ�ÿͻ������:TBS 14
				"compVisitIncome varchar(3)," + // ���������ʵһ��:OK;���������ʵ��һ��:TBS 15
				"compVisitIncomeAmount varchar(10), " + // �׷�Աʵ�ʺ�ʵ������������16
				"compVisitJob varchar(3)," + // ������Ϣ(��ҵ��ְ���)һ��:OK;������Ϣ(��ҵ��ְ���)��һ�£�TBS
												// 17
				"compVisit3Rd varchar(2)," + // û�е�������������:OK;�е�������������:KO 18
				"compVisitLoanPurpose varchar(3)," + // ������;�����:OK;������;���:KO;������;���ɣ����޷���ȫȷ��:TBS
														// 19
				// "compVisitApplySelf varchar(3), " +//ȷ���׷���ǩ:OK;δ�׷���ǩ:TBS 20
				// "compVisitRisk varchar(2),"
				// +//δ���ֶĲ��������ȸ߷�������:OK;���ֶĲ��������ȸ߷�������:KO 21
				"positionDate varchar(14), " + // 22
				"memoVisit varchar(100)," + // �ύ���ܱ�ע

				"nameCust varchar(8)," + // ��
				"customervisitDate varchar(8)," + // �������
				"customervisitPhone varchar(11)," + // �ֻ�����
				"flipperActivity varchar(10), " + // �ݸ�������Ĺ���ģ����
				"flipperLayoutnum varchar(2), " + // �ݸ��������Ӧ���ǵڼ��������ļ�
				"filename varchar(20), " + // �ļ�������
				"bollen varchar(8)," + //
				"newaddress varchar(1000), " + // ���Ա�µ�ַ 2018.04.13
				"difficult varchar(1000), " + // ���Ա���ѹ�����ע2018.04.13
				"applyfile_states varchar(2) ," + // ���ı�ʾ 1Ϊ�ݸ��䡢2Ϊ������
				"futureField6 varchar(10)"+     //2018-06-12   �Ƿ�ֱ��ͨ����ʶ
				")");

		// �Ƽ����������
		/*
		 * arg0.execSQL("create table if not exists Banck(" +
		 * "institutionCode varchar(4) primary key, " +//�������� 0
		 * "institutionName varchar(40)," +//��������1 "DB_ID varchar(19),"
		 * +//DB_ID2 "updateTime varchar(14)," +//����ʱ��4 "area varchar(14),"
		 * +//���� "acceptable varchar(5)," +//�Ƿ����Ϊ������ "reserver1 varchar(5)"
		 * +//Ԥ���ֶ� ")");
		 * 
		 * //�Ƽ�������֧�� arg0.execSQL("create table if not exists SubBank(" +
		 * "branchCode varchar(4) primary key, " +//֧�д��� 0
		 * "branchName varchar(40)," +//֧������1 "PAREND_DB_ID varchar(19),"
		 * +//PAREND_DB_IDͨ��֧���µ�PAREND_DB_ID�ֶ�ֵ���ڻ�����DB_ID�ֶ�ֵ,�����л�����֧�й�����2
		 * "updateTime varchar(14)," +//����ʱ��3 "area varchar(14)," +//����
		 * "inputable varchar(5)," +//�Ƿ����¼���Ƽ��������� "reserver1 varchar(5)"
		 * +//�Ƿ����Ϊ������ ")");
		 */

		/*
		 * remove������ʱ�� ��������Ҫ��
		 */
		// sp.removeParameters("Banck");
		// sp.removeParameters("SubBank");

		arg0.execSQL("create table IF NOT EXISTS ElectronicData(" + // �������Ϲ����
				"_id varchar2(20) primary key," + // ��������id�����½���������ʱ���Ϊid
				"data_name varchar2(100)," + // ��������
				"customer_idtype varchar2(20)," + // ֤������
				"customer_idnum varchar2(100)," + // �ͻ�֤������
				"customer_name varchar2(30)," + // �ͻ�����
				"file_path varchar2(50)," + // ���ϱ���·���������ļ�������pad��sdcard·��
				"create_time varchar2(10)," + // ����ʱ��
				"expiration_time varchar2(10))");// ����ʱ��

		System.out.println("�޸����ݿ�onUpdate()");

	}

	public boolean isColumnExists(SQLiteDatabase db, String tabName, String col) {
		boolean result = true;
		Cursor cursor = null;
		try {
			cursor = db.rawQuery("select * from " + tabName, null);
			if (cursor.getColumnIndex(col) == -1) {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return result;
	}

	public boolean IsTableExists(SQLiteDatabase db, String tabName) {
		boolean result = false;
		if (tabName == null) {
			return false;
		}
		Cursor cursor = null;
		try {
			String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"
					+ tabName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return result;
	}

	public static int getDBVer() {
		return Constants.dbVer;
	}

	// ɾ�����ݿ�
	public boolean deleteDataBase(Context context) {
		return context.deleteDatabase(Constants.dbName);
	}

	public boolean deleteFriendDataBase(Context friendContext,
			String databaseName) {
		return friendContext.deleteDatabase(databaseName);
	}

}