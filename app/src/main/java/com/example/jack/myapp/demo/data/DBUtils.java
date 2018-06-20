package com.example.jack.myapp.demo.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.techown.cfccc.util.FileUtils;
import com.techown.cfccc.util.Log;

/**
 * ���ݿ������
 * 
 * @author Administrator
 * 
 */
public class DBUtils {
	private MyDatabaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	public Context con;
	DES des = new DES();

	// ����share
	SharedPrefs sharedPrefs;

	public DBUtils(Context context) {
		this.con = context;
		sharedPrefs = new SharedPrefs(context);
	}

	public SQLiteDatabase getMyDataBase() {
		if (db == null) {
			dbHelper = new MyDatabaseHelper(con, Constants.dbName,
					Constants.dbVer);
			db = dbHelper.getWritableDatabase();
		}
		return db;
	}

	public void closeDataBase() {
		if (db != null) {
			db.close();
			db = null;
		}
	}

	public boolean deleteDatabase() {
		dbHelper = new MyDatabaseHelper(con, Constants.dbName, Constants.dbVer);
		return dbHelper.deleteDataBase(con);
	}

	// ��������
	public void insert(String tablename, String[] clomname, String[] clomstr) {
		try {
			getMyDataBase();// ������ݿ����
			ContentValues values = new ContentValues();
			// db.beginTransaction();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));

			}
			db.insert(tablename, null, values); // ��������
			// db.setTransactionSuccessful();
			// db.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"DBUTIL.insert�쳣:" + tablename + e.getMessage());
		} finally {
			closeDataBase();
		}

	}

	// ����һ�ж���ֶ�
	public long upload(String tablename, String[] clomname,

	String[] clomstr, String id, String idstr) {
		long count = -1;
		try {
			getMyDataBase();// ������ݿ����
			ContentValues values = new ContentValues();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}
			if (values.size() > 0) {
				count = db.update(tablename, values, id + "=?",
						new String[] { des.jiaMi(idstr) }); // ��������
			}

			// db.close();
			if (count == -1) {
				Log.getInstance().writeLog("DBUTIL.upload��������ʧ��");
			}

		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.upload�쳣:" + e.getMessage());
		} finally {
			closeDataBase();
		}
		return count;
	}

	/**
	 * ��ѯ��RP�Ͽͻ��õ������ݸ������ݿ⣬ûֵ�����ݲ�����ӣ���ֵ�����
	 * 
	 * @param tablename
	 *            ����
	 * @param clomname
	 *            ������
	 * @param clomstr
	 *            ֵ����
	 * @param id
	 *            ��ѯ��id
	 * @param idstr
	 *            ��ѯ��idֵ
	 * @return
	 */
	public List<String> uploadRP(String tablename, String[] clomname,
			String[] clomstr, String id, String idstr) {
		List<String> strUpload = new ArrayList<String>();
		long count = -1;
		try {
			getMyDataBase();// ������ݿ����
			ContentValues values = new ContentValues();
			for (int i = 0; i < clomname.length; i++) {
				if (!(clomstr[i]).equals("")) {
					values.put(clomname[i], des.jiaMi(clomstr[i]));
					strUpload.add(clomname[i]);
				}
			}
			System.out.println("���µ�Id��" + idstr + "�����ֵ�ø����Ǽ����� "
					+ values.size());
			if (values.size() > 0) {
				count = db.update(tablename, values, id + "=?",
						new String[] { des.jiaMi(idstr) }); // ��������
			}

			// db.close();
			if (count == -1) {
				Log.getInstance().writeLog("DBUTIL.uploadRP -- ��������ʧ��");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog("DBUTIL.uploadRP�쳣:" + e.getMessage());
		} finally {
			closeDataBase();
		}
		return strUpload;
	}

	// ��ѯһ�ж���ֶ�
	public String[] Applyquery(String tablename, String[] clunname,
			String where, String wherea, String wherestr, String wherestra) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			cur = db.rawQuery("select " + clunname[0] + "," + clunname[1]
					+ " from " + tablename + " where " + tablename + "."
					+ where + "=" + "'" + wherestr + "'" + " and " + tablename
					+ "." + wherea + "=" + "'" + wherestra + "'", null);

			if (cur.getCount() != 0) {
				str = new String[clunname.length];
				while (cur.moveToNext()) {
					for (int i = 0; i < clunname.length; i++) {
						if (cur.getString(i) != null) {
							str[i] = des.jieMI(cur.getString(i));
						} else {
							str[i] = "";
						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"DBUTIL.query�쳣:" + e.getMessage() + "   +  ( ����Ϊ��"
							+ tablename + ")");
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	// ��ѯһ�ж���ֶ�
	public String[] query(String tablename, String[] clunname, String where,
			String wherestr) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			cur = db.query(tablename, clunname, where + "=?",
					new String[] { des.jiaMi(wherestr) }, null, null, null);
			if (cur.getCount() != 0) {
				str = new String[clunname.length];
				while (cur.moveToNext()) {
					for (int i = 0; i < clunname.length; i++) {
						if (cur.getString(i) != null) {
							str[i] = des.jieMI(cur.getString(i));
						} else {
							str[i] = "";
						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"DBUTIL.query�쳣:" + e.getMessage() + "   +  ( ����Ϊ��"
							+ tablename + ")");
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	// ��ѯ���ڰ汾��
	public String queryDate(String tablename, String colum) {
		String str = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(
				System.currentTimeMillis()));
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			cur = db.query(tablename, new String[] { colum }, null, null, null,
					null, null);
			if (cur.getCount() != 0) {

				while (cur.moveToNext()) {

					if (cur.getString(cur.getColumnIndex(colum)) != null) {
						str = des
								.jieMI(cur.getString(cur.getColumnIndex(colum)));
					}
					break;
				}
			}

		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.queryDate�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ԭ��ɾ��
	 * 
	 * @param tablename
	 * @param where
	 * @param wherestr
	 */
	public void delete(String sql, String wherestr) {
		try {
			getMyDataBase();
			db.rawQuery(sql, new String[] { des.jiaMi(wherestr) });
			Log.getInstance().writeLog("ɾ�����ݿ���" + des.jiaMi(wherestr) + "���ݳɹ�");
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.delete�쳣:" + e.getMessage());
		} finally {
			closeDataBase();
		}
	}

	// �h��һ������
	public void delete(String tablename, String where, String wherestr) {
		try {
			getMyDataBase();
			db.delete(tablename, where + "=?",
					new String[] { des.jiaMi(wherestr) });
			Log.getInstance().writeLog("ɾ�����ݿ���" + des.jiaMi(wherestr) + "���ݳɹ�");
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.delete�쳣:" + e.getMessage());
		} finally {
			closeDataBase();
		}
	}

	// �h��һ������
	public void deleteInvestigation(String tablename, String where,
			String wherestr, String wherestra, String wherea) {

		Cursor cur = null;
		try {
			getMyDataBase();// "delete from cfccc_customervisit where 10790017 =
							// authNumber and 10790017=C
			cur = db.rawQuery("delete from " + tablename + " where " + where
					+ " = " + "'" + wherestr + "'" + " and " + wherea + "="
					+ "'" + wherestra + "'", null);
			// db.delete(tablename, where + "=?",
			// new String[] { des.jiaMi(wherestr) });
			Log.getInstance().writeLog("ɾ�����ݿ���" + des.jiaMi(wherestr) + "���ݳɹ�");
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.delete�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
	}

	// ɾ����������
	public void deleteall(String tablename) {
		try {
			getMyDataBase();
			db.delete(tablename, null, null);
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.deleteall�쳣:" + e.getMessage());
		} finally {
			closeDataBase();
		}
	}

	// �õ�IDֵnum
	public String getApplyid() {// library routine called out of sequence
		String num = null;
		Cursor cur = null;
		try {
			getMyDataBase();
			cur = db.query("cfccc_apply", new String[] { "id" }, null, null,
					null, null, null);
			if (cur.getCount() == 0) {
				num = "1";
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						num = String.valueOf(Integer.parseInt(des.jieMI(cur
								.getString(0))) + 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog("DBUTIL.getApplyid�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return num;

	}

	// �õ�IDֵnum
	public String getInvestigationid() {// library routine called out of
										// sequence
		String num = null;
		Cursor cur = null;
		try {
			getMyDataBase();
			cur = db.query("cfccc_customervisit", new String[] { "id" }, null,
					null, null, null, null);
			if (cur.getCount() == 0) {
				num = "1";
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						num = String.valueOf(Integer.parseInt(des.jieMI(cur
								.getString(0))) + 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"DBUTIL.getInvestigationid�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return num;

	}

	// �õ��������id xinzheng
	public String getInvestigationidFromSp() {// library routine called out of
		// sequence
		String visitId = sharedPrefs.getParameters("visitid");
		if (visitId == null || "".equals(visitId)) {
			visitId = "1";
			sharedPrefs.saveParameters("visitid", visitId);
		} else {
			visitId = String.valueOf(Integer.parseInt(visitId) + 1);
			sharedPrefs.saveParameters("visitid", visitId);
		}
		return visitId;
	}

	// �õ�Strongde��IDֵnum
	public String getStoreApplyid() {// library routine called out of sequence
		String num = null;
		Cursor cur = null;
		try {
			getMyDataBase();
			cur = db.query("StoreM", new String[] { "id" }, null, null, null,
					null, null);
			if (cur.getCount() == 0) {
				num = "1";
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						num = String.valueOf(Integer.parseInt(des.jieMI(cur
								.getString(0))) + 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"DBUTIL.getStoreApplyid�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return num;

	}

	// ��ѯ���ж���ֶ�
	public List<String[]> queryApply(String tablename, String[] clunname,
			String where, String wherestr) {
		List<String[]> liststr = new ArrayList<String[]>();
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			if (where == null || wherestr == null) {
				cur = db.query(tablename, clunname, null, null, null, null,
						null);
			} else {
				cur = db.query(tablename, clunname, where + "=?",
						new String[] { des.jiaMi(wherestr) }, null, null, null);
			}

			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					String str[] = new String[clunname.length];
					for (int i = 0; i < clunname.length; i++) {
						if (cur.getString(cur.getColumnIndex(clunname[i])) == null) {
							str[i] = "";
						} else {
							str[i] = des.jieMI(cur.getString(cur
									.getColumnIndex(clunname[i])));
						}
					}
					liststr.add(str);
				}
			}

		}

		catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.queryApply�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return liststr;
	}

	// ��ѯ��������
	public List<String[]> querySendBox(String tablename, String[] clunname,
			String where, String wherestr, DBUtils dbutils) {
		DecimalFormat df = new DecimalFormat("0.00");// ��ʽ��С��
		List<String[]> liststr = new ArrayList<String[]>();
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			if (where == null || wherestr == null) {
				cur = db.query(tablename, clunname, null, null, null, null,
						null);
			} else {
				cur = db.query(tablename, clunname, where + "=?",
						new String[] { des.jiaMi(wherestr) }, null, null, null);
			}

			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					String str[] = new String[clunname.length + 1];
					String[] sendnum = new String[2];
					for (int i = 0; i < clunname.length + 1; i++) {
						if (i == clunname.length) {
							str[clunname.length] = "20";
							sendnum = dbutils.query("send", new String[] {
									"beginnum", "totalnum" }, "filename", des
									.jieMI(cur.getString(cur
											.getColumnIndex(clunname[0]))));
							if (sendnum[1].equals("0")) {
								str[clunname.length] = "0";
							} else {
								if (((float) Integer.parseInt(sendnum[0]) / Integer
										.parseInt(sendnum[1])) < 0.1) {
									str[clunname.length] = df
											.format((float) Integer
													.parseInt(sendnum[0])
													/ Integer
															.parseInt(sendnum[1]))
											.substring(3);// ���ص���String����
								} else {
									if (((float) Integer.parseInt(sendnum[0]) / Integer
											.parseInt(sendnum[1])) == 1) {
										str[clunname.length] = "100";
									} else {
										str[clunname.length] = df
												.format((float) Integer
														.parseInt(sendnum[0])
														/ Integer
																.parseInt(sendnum[1]))
												.substring(2);
									}
								}

							}

							System.out.println("====>" + sendnum.toString());
						} else {
							if (cur.getString(cur.getColumnIndex(clunname[i])) == null) {
								str[i] = "";
							} else {
								str[i] = des.jieMI(cur.getString(cur
										.getColumnIndex(clunname[i])));
							}
						}
					}
					liststr.add(str);
				}
			}

		}

		catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.queryApply�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return liststr;
	}

	// ģ����ѯ���ж���ֶ�
	public List<String[]> queryLikeApply(String sql, String[] clunname,
			String wherestr) {
		List<String[]> liststr = new ArrayList<String[]>();
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			cur = db.rawQuery(sql,
					new String[] { des.jiaMi(wherestr), des.jiaMi(wherestr) });

			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					String str[] = new String[clunname.length];
					for (int i = 0; i < clunname.length; i++) {
						if (cur.getString(cur.getColumnIndex(clunname[i])) == null) {
							str[i] = "";
						} else {
							str[i] = des.jieMI(cur.getString(cur
									.getColumnIndex(clunname[i])));
						}
					}
					liststr.add(str);
				}
			}

		}

		catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.queryApply�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return liststr;
	}

	// ��ѯÿ����ļ�����
	public int numQuery(String tablename, String clunname, String clunstr) {
		int numstr = 0;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			cur = db.query(tablename, null, clunname + "=?",
					new String[] { des.jiaMi(clunstr) }, null, null, null);
			numstr = cur.getCount();
		}

		catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.numQuery�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return numstr;
	}

	public boolean isDatabaseExists(Context context, String dbName) {

		File dbFile = context.getDatabasePath(dbName);
		if (dbFile.exists()) {
			return true;
		}
		return false;
	}

	public void copyDBFileToSD(Context context, String dbName) {

		FileInputStream fin = null;
		try {
			File dbFile = context.getDatabasePath(dbName);
			fin = new FileInputStream(dbFile);

			FileUtils fu = new FileUtils();
			fu.write2SDFromInput("product/", dbName, fin);

		} catch (FileNotFoundException e) {
			Log.getInstance().writeLog(
					"DBUTIL.copyDBFileToSD�쳣:" + dbName + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void getDBFileFromSD(String packageName, String dbName) {

		Log.getInstance().writeLog("getDBFileFromSD: " + dbName);
		FileOutputStream fop = null;
		FileInputStream fip = null;
		try {
			fip = new FileInputStream("/mnt/sdcard/product/" + dbName);

			String path = "/data/data/" + packageName + "/databases/";

			File dbFile = new File(path + dbName);
			if (dbFile.exists()) {
				dbFile.delete();
			}
			dbFile.createNewFile();

			fop = new FileOutputStream(dbFile);
			byte buffer[] = new byte[4 * 1024];
			int tmp;
			while ((tmp = (fip.read(buffer))) != -1) {
				fop.write(buffer, 0, tmp);
			}
			fop.flush();

		} catch (Exception e) {
			Log.getInstance().writeLog(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (fip != null)
					fip.close();
				if (fop != null)
					fop.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getDBVer(String fileName) {
		int ver = 1;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("/mnt/sdcard/TJB/ver/" + fileName);
			byte[] buf = new byte[1024];
			int tmp = fis.read(buf);
			String verStr = new String(buf, 0, tmp);
			ver = Integer.parseInt(verStr);
			Log.getInstance().writeLog(fileName + "�еİ汾��: " + ver);
		} catch (Exception e) {
			Log.getInstance().writeLog(e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
			}
		}
		return ver;
	}

	// add yuzhenghui
	/**
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param tablename
	 *            ��
	 * @return
	 */
	public String[] klQuery(final Context con, String clunname, String tablename) {

		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			Set<String> set = new HashSet<String>();
			cur = db.query(tablename, new String[] { clunname }, null, null,
					null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					set.add(des.jieMI(cur.getString(cur
							.getColumnIndex(clunname))));
				}
			}
			str = new String[set.size()];
			str = set.toArray(str);

		} catch (Exception e) {
			// techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯʡklQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.klQuery�쳣:" + tablename + e.getMessage());
			e.getMessage();
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	// add yuzhenghui
	/**
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param tablename
	 *            ��
	 * @return
	 */
	public String[] loanReductionInitialklQuery(final Context con,
			String clunname, String tablename, String selection,
			String selectionArgs) {

		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			Set<String> set = new HashSet<String>();
			cur = db.query(tablename, new String[] { clunname }, selection,
					new String[] { selectionArgs }, null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					set.add(des.jieMI(cur.getString(cur
							.getColumnIndex(clunname))));
				}
			}
			str = new String[set.size()];
			str = set.toArray(str);

		} catch (Exception e) {
			// techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯʡklQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.klQuery�쳣:" + tablename + e.getMessage());
			e.getMessage();
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	public String[] getCity11(Context con, String province) {

		String strcity[] = null;
		String[] str = Query(con, "city_id", "city", province, "City");
		if (str != null && str.length > 0) {
			strcity = Query(con, "area", "city_id", str[0], "District");
		}

		return strcity;
	}

	public String[] getCity(Context con, String province) {

		String strcity[] = null;
		String[] str = Query(con, "province_id", "province", province,
				"Province");
		if (str != null && str.length > 0) {
			strcity = Query(con, "city", "province_id", str[0], "City");
		}

		return strcity;
	}

	/**
	 * ���ݶ�Ӧֵ��ѯ
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param where
	 *            ��ѯ����
	 * @param wherestr
	 *            ��������
	 * @param tablename
	 *            ��
	 * @return
	 */
	public String[] Query(final Context con, String clunname, String where,
			String wherestr, String tablename) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			int i = 0;
			cur = db.query(tablename, new String[] { clunname }, where + "=?",
					new String[] { des.jiaMi(wherestr) }, null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			// techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯ��cityQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.Query�쳣:" + tablename + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ��ѯ�Ż���Ϣ��codeֵ
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param where
	 *            ��ѯ����
	 * @param wherestr
	 *            ��������
	 * @param tablename
	 *            ��
	 * @return
	 */
	public String[] loanReductionInitialQuery(final Context con,
			String clunname, String where, String wherestr, String where1,
			String wherestr1, String tablename) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			int i = 0;
			// cur = db.query(tablename, new String[] { clunname }, where +
			// "=?",
			// new String[] { des.jiaMi(wherestr) }, null, null, null);
			cur = db.rawQuery("select " + clunname + " from " + tablename
					+ " where " + where + " = " + "'" + wherestr + "'"
					+ " and " + where1 + " = " + "'" + wherestr1 + "'", null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			// techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯ��cityQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.Query�쳣:" + tablename + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * �޿�֧�� Store
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param wherestrA
	 *            ��ѯ����A
	 * @param wherestrB
	 *            ��ѯ����B
	 * @param tablenameA
	 *            ��A
	 * @param tablenameB
	 *            ��B
	 * @return
	 */
	public String[] StoreWuKa(final Context con, String clunname,
			String wherestrA, String wherestrB, String tablenameA,
			String tablenameB) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;
			// cur =
			// db.rawQuery("select "+clunname+" from "+tablenameA+" where "+wherestr+" in (select "+wherestr+" from "+tablenameB+" where "+wherestrC+" in (select "+wherestrC+" from "+tablenameC+"))",
			// null);
			// select STORE_NAME from PRODUCT_ID,Stores,ProductStores where
			// PRODUCT_ID.STORE_NUMBER=Stores.PRODUCT_ID and
			// ProductStores.PRODUCT_ID='0002';
			// select storename from store,productstore where
			// store.number=productstore.storenumber and
			// productstore.productid='0002'
			cur = db.rawQuery("select " + clunname + " from " + tablenameA
					+ "," + tablenameB + " where " + tablenameA + "."
					+ wherestrA + "=" + tablenameB + "." + wherestrA + " and "
					+ tablenameB + "." + wherestrB + " in ('0026','0027') and "
					+ tablenameA
					+ ".STOPE_CHAIN  in ('A','B') group by  Stores.STORE_NAME",
					null);
			// cur =
			// db.rawQuery("select "+clunname+" from "+tablenameA+","+tablenameB+" where "+tablenameA+"."+wherestrA+"="+tablenameB+"."+wherestrA+"  and "+tablenameA+".STOPE_CHAIN  in ('A','B')",
			// null);
			//
			// System.out.println("select "+clunname+" from "+tablenameA+","+tablenameB+" where "+tablenameA+"."+wherestrA+"="+tablenameB+"."+wherestrA+" and "+tablenameB+"."+wherestrB+"='0002' and "+tablenameA+".STOPE_CHAIN  in ('A','B')"
			// );

			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.Store�쳣:" + tablenameA + tablenameB
							+ e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ���״�Store
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param wherestrA
	 *            ��ѯ����A
	 * @param wherestrB
	 *            ��ѯ����B
	 * @param tablenameA
	 *            ��A
	 * @param tablenameB
	 *            ��B
	 * @return
	 */
	public String[] Store(final Context con, String clunname, String wherestrA,
			String wherestrB, String tablenameA, String tablenameB) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;
			// cur =
			// db.rawQuery("select "+clunname+" from "+tablenameA+" where "+wherestr+" in (select "+wherestr+" from "+tablenameB+" where "+wherestrC+" in (select "+wherestrC+" from "+tablenameC+"))",
			// null);
			// select STORE_NAME from PRODUCT_ID,Stores,ProductStores where
			// PRODUCT_ID.STORE_NUMBER=Stores.PRODUCT_ID and
			// ProductStores.PRODUCT_ID='0002';
			// select storename from store,productstore where
			// store.number=productstore.storenumber and
			// productstore.productid='0002'
			cur = db.rawQuery("select " + clunname + " from " + tablenameA
					+ "," + tablenameB + " where " + tablenameA + "."
					+ wherestrA + "=" + tablenameB + "." + wherestrA + " and "
					+ tablenameB + "." + wherestrB + "='0002'", null);
			// System.out.println("��ѯ���е���䣺" +
			// "select "+clunname+" from "+tablenameA+","+tablenameB+" where "+tablenameA+"."+wherestrA+"="+tablenameB+"."+wherestrA+" and "+tablenameB+"."+wherestrB+"='0002'"
			// );

			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.Store�쳣:" + tablenameA + tablenameB
							+ e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ���״�goods
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param wherestrA
	 *            ��ѯ����A
	 * @param wherestrB
	 *            ��ѯ����B
	 * @param wherestrC
	 *            ��ѯ����C
	 * @param tablenameA
	 *            ��A
	 * @param tablenameB
	 *            ��B
	 * @return
	 */
	public String[] Goods(final Context con, String clunname, String wherestrA,
			String wherestrB, String tablenameA, String tablenameB,
			String wherestrC) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;
			// cur =
			// db.rawQuery("select "+clunname+" from "+tablenameA+" where "+wherestr+" in (select "+wherestr+" from "+tablenameB+" where "+wherestrC+" in (select "+wherestrC+" from "+tablenameC+"))",
			// null);
			// select goodname from storegoods,301204001 where
			// storegoods.goodid=301204001.goodid and 301204001.stornum=goods
			// select GOODS_NAME from Goods,StoreGoods where
			// Goods.GOODS_ID=StoreGoods.GOODS_ID and
			// StoreGoods.STORE_NUMBER=3012G9001
			cur = db.rawQuery("select " + clunname + " from " + tablenameA
					+ "," + tablenameB + " where " + tablenameA + "."
					+ wherestrA + "=" + tablenameB + "." + wherestrA + " and "
					+ tablenameB + "." + wherestrB + "= '" + wherestrC + "'",
					null);

			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.Goods�쳣:" + tablenameA + tablenameB
							+ e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ���״�Plan
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param wherestrA
	 *            ��ѯ����A
	 * @param wherestrB
	 *            ��ѯ����B
	 * @param wherestrC
	 *            ��ѯ����C
	 * @param tablenameA
	 *            ��A
	 * @param tablenameB
	 *            ��B
	 * @param tablenameC
	 *            ��C
	 * @param num
	 *            ��ѯ����
	 * @return
	 */
	public String[] Plan(final Context con, String clunname, String wherestrA,
			String wherestrB, String wherestrC, String tablenameA,
			String tablenameB, String tablenameC, String num) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;
			// cur =
			// db.rawQuery("select "+clunname+" from "+tablenameA+" where "+wherestr+" in (select "+wherestr+" from "+tablenameB+" where "+wherestrC+" in (select "+wherestrC+" from "+tablenameC+"))",
			// null);
			// select PLAN_NAME from Plan,PlanStoreGoods,StoreGoods where
			// Plan.PLAN_ID=PlanStoreGoods.PLAN_ID and
			// PlanStoreGoods.STORE_GOODS_ID= StoreGoods.STORE_GOODS_ID and
			// PlanStoreGoods.GOODS_ID='0000015451'

			cur = db.rawQuery("select " + clunname + " from " + tablenameA
					+ "," + tablenameB + "," + tablenameC + " where "
					+ tablenameA + "." + wherestrA + "=" + tablenameB + "."
					+ wherestrA + " and " + tablenameB + "." + wherestrB + "= "
					+ tablenameC + "." + wherestrB + " and " + tablenameC + "."
					+ wherestrC + "='" + num + "'", null);

			// System.out.println("���ʽ�Ĳ�ѯ��䣺" +
			// "select "+clunname+" from "+tablenameA+","+tablenameB+","+tablenameC+" where "+tablenameA+"."+wherestrA+"="+tablenameB+"."+wherestrA+" and "+tablenameB+"."+wherestrB+"= "+tablenameC+"."+wherestrB+" and "+tablenameC+"."+wherestrC+"='"+num+"'");

			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.Plan�쳣:" + tablenameA + tablenameB + tablenameC
							+ e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ���״�Plan
	 * 
	 * @param con
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param wherestrA
	 *            ��ѯ����A
	 * @param wherestrB
	 *            ��ѯ����B
	 * @param wherestrC
	 *            ��ѯ����C
	 * @param tablenameA
	 *            ��A
	 * @param tablenameB
	 *            ��B
	 * @param tablenameC
	 *            ��C
	 * @param num
	 *            ��ѯ����
	 * @param wherestrD
	 *            ��ѯ����D
	 * @return
	 */
	public String[] Plan(final Context con, String clunname, String wherestrA,
			String wherestrB, String wherestrC, String tablenameA,
			String tablenameB, String tablenameC, String num, String wherestrD,
			String clas) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;
			// cur =
			// db.rawQuery("select "+clunname+" from "+tablenameA+" where "+wherestr+" in (select "+wherestr+" from "+tablenameB+" where "+wherestrC+" in (select "+wherestrC+" from "+tablenameC+"))",
			// null);
			// select PLAN_NAME from Plan,PlanStoreGoods,StoreGoods where
			// Plan.PLAN_ID=PlanStoreGoods.PLAN_ID and
			// PlanStoreGoods.STORE_GOODS_ID= StoreGoods.STORE_GOODS_ID and
			// PlanStoreGoods.GOODS_ID='0000015451'

			cur = db.rawQuery("select " + clunname + " from " + tablenameA
					+ "," + tablenameB + "," + tablenameC + " where "
					+ tablenameA + "." + wherestrA + "=" + tablenameB + "."
					+ wherestrA + " and " + tablenameB + "." + wherestrB + "= "
					+ tablenameC + "." + wherestrB + " and " + tablenameC + "."
					+ wherestrC + "='" + num + "'" + " and " + tablenameA + "."
					+ wherestrD + "= '" + clas + "'", null);
			System.out.println("���ʽ�Ĳ�ѯ��䣺" + "select " + clunname + " from "
					+ tablenameA + "," + tablenameB + "," + tablenameC
					+ " where " + tablenameA + "." + wherestrA + "="
					+ tablenameB + "." + wherestrA + " and " + tablenameB + "."
					+ wherestrB + "= " + tablenameC + "." + wherestrB + " and "
					+ tablenameC + "." + wherestrC + "='" + num + "'" + " and "
					+ tablenameA + "." + wherestrD + "= '" + clas + "'");
			// System.out.println("���ʽ�Ĳ�ѯ��䣺" +
			// "select "+clunname+" from "+tablenameA+","+tablenameB+","+tablenameC+" where "+tablenameA+"."+wherestrA+"="+tablenameB+"."+wherestrA+" and "+tablenameB+"."+wherestrB+"= "+tablenameC+"."+wherestrB+" and "+tablenameC+"."+wherestrC+"='"+num+"'");

			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.Plan�쳣:" + tablenameA + tablenameB + tablenameC
							+ e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ��ѯ���ݿ��е�һ������
	 * 
	 * @param context
	 * @param clunname
	 *            ��ѯ�ֶ���
	 * @param wherestrA
	 *            ��ѯ����A
	 * @param wherestrB
	 *            ��ѯ����B
	 * @param wherestrC
	 *            ��ѯ����C
	 * @param tablenameA
	 *            ��A
	 * @param tablenameB
	 *            ��B
	 * @param tablenameC
	 *            ��C
	 * @param num
	 *            ��ѯ����
	 * @return ��ѯ����ֵ���
	 */
	public String[] Plone_store(final Context con, String clunname,
			String wherestrA, String wherestrB, String wherestrC,
			String tablenameA, String tablenameB, String tablenameC, String num) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;
			// select STORE_NAME from Stores,ProductStores where
			// Stores.STORE_NUMBER=ProductStores.STORE_NUMBER and
			// ProductStores.PRODUCT_ID= Products.PRODUCT_ID and
			// Products.PRODUCT_CATEGORY ='03' 30141
			cur = db.rawQuery("select " + clunname + " from " + tablenameA
					+ "," + tablenameB + " ," + tablenameC + " where "
					+ tablenameA + "." + wherestrA + " like '30141%' and "
					+ tablenameA + "." + wherestrA + "=" + tablenameB + "."
					+ wherestrA + " and " + tablenameB + "." + wherestrB + "= "
					+ tablenameC + "." + wherestrB + " and " + tablenameC + "."
					+ wherestrC + " ='" + num + "'", null);

			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.Plone_store�쳣:" + tablenameA + tablenameB
							+ tablenameC + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ��ѯһ������
	 * 
	 * @param context
	 * @param tableName
	 *            ����
	 * @param strName
	 *            ��ѯ���ֶ�����
	 * @param where
	 *            ��ѯ����
	 * @param whereValue
	 *            ������Ӧ����
	 * @return
	 */
	public String[][] queryStr(Context context, String tableName,
			String[] strName, String where, String whereValue) {
		String[][] str = new String[strName.length][];
		Cursor cur = null;
		getMyDataBase();
		try {

			if (whereValue == null) {
				cur = db.query(tableName, strName, null, null, null, null, null);
			} else {
				cur = db.query(tableName, strName, where,
						new String[] { des.jiaMi(whereValue) }, null, null,
						null);
			}
			if (cur.getCount() != 0) {
				str = new String[strName.length][cur.getCount()];
				int i = 0;
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					for (int j = 0; j < strName.length; j++) {
						str[j][i] = des.jieMI(cur.getString(cur
								.getColumnIndex(strName[j])));
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					tableName + "--DBUTIL.queryStr�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	public String[][] queryNotice(String tableName, String[] strName,
			String where, String whereValue, String orderby) {
		String[][] str = new String[strName.length][];
		Cursor cur = null;
		getMyDataBase();
		try {

			if (whereValue == null) {
				cur = db.query(tableName, strName, null, null, null, null,
						orderby);
			} else {
				cur = db.query(tableName, strName, where + "=?",
						new String[] { des.jiaMi(whereValue) }, null, null,
						orderby);
			}
			if (cur.getCount() != 0) {
				str = new String[strName.length][cur.getCount()];
				int i = 0;
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					for (int j = 0; j < strName.length; j++) {
						str[j][i] = des.jieMI(cur.getString(cur
								.getColumnIndex(strName[j])));
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					tableName + "--DBUTIL.queryStr�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ��ѯ���ݿ��е�һ������
	 * 
	 * @param context
	 * @param tableName
	 *            ����
	 * @param rowName
	 *            Ҫ��ѯ������
	 * @param queryRow
	 *            ��ѯ��������
	 * @param queryRowValue
	 *            ��ѯ��������ֵ
	 * @return ��ѯ����ֵ���
	 */
	public String queryArrayValue(Context context, String tableName,
			String[] rowName, String queryRow, String queryRowValue) {
		Cursor cursor = null;
		getMyDataBase();
		String strRowName = "";
		try {
			cursor = db
					.query(tableName, rowName, queryRow + "=?",
							new String[] { des.jiaMi(queryRowValue) }, null,
							null, null);
			if (cursor.getCount() != 0) {
				while (cursor.moveToNext()) {
					strRowName = des.jieMI(cursor.getString(cursor
							.getColumnIndex(rowName[0])));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					tableName + "--DBUTIL.queryArrayValue�쳣:" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDataBase();
		}
		return strRowName;
	}

	/**
	 * �������ص����ݴ������ݿ�
	 * 
	 * @param context
	 * @param requestData
	 *            Ҫ���������
	 * @param tableName
	 */
	public void requestDataDispose(Context context, String requestData,
			String tableName) {

		String[] strOtherKey = { "strErrorCode", "strErrorMessage",
				"strSuccess", "strCustomerType" };

		if (requestData != null && !requestData.equals("")) {

			try {
				JSONObject jsonObject = new JSONObject(requestData);

				strOtherKey[0] = jsonObject.optString("errorCode");
				strOtherKey[1] = jsonObject.optString("errorMessage");
				strOtherKey[2] = jsonObject.optString("success");
				strOtherKey[3] = jsonObject.optString("customerType");
				jsonObject.optJSONObject("appInfoDto");
				// ��appInfoDto�е����ݽ�������
				JSONObject jsonObjectAppInfoDto = jsonObject
						.optJSONObject("appInfoDto");

				if (jsonObjectAppInfoDto != null) {
					Iterator<?> iterator = jsonObjectAppInfoDto.keys();
					String strdata[] = selectApply(context, Constants.applyid,
							tableName);// ������ݵĵ������ֶε�����
					String[] strname = new String[jsonObjectAppInfoDto.length()];

					int ii = 0;
					while (iterator.hasNext()) {

						String name = (String) iterator.next();

						if (name.equals("cust1_custFutureField8")) {
							name = "cust1custFutureField8";
						} else if (name.equals("cust2_CustFutureField8")) {
							name = "cust2custFutureField8";
						}

						strname[ii] = name;
						ii++;
					}

					String[] sameElement = getAllSameElement(strdata, strname);// ȡ��ͬ�������ֶ�
					String[] strKey = new String[sameElement.length];// ���ֶε�����
					String[] strValue = new String[sameElement.length];// ��ֵ������
					strKey = sameElement;
					for (int i = 0; i < sameElement.length; i++) {
						if (!jsonObjectAppInfoDto.getString(sameElement[i])
								.equals("null")) {
							strValue[i] = jsonObjectAppInfoDto
									.getString(sameElement[i]);
						} else {
							strValue[i] = "";
						}
					}
					System.out.println("jinglail b ");
					if (!strOtherKey.equals("0")) {
						uploadRP(tableName, strKey, strValue, "id",
								Constants.applyid);
					}

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.getInstance().writeLog(
						tableName + "--DBUTIL.requestDataDispose�쳣:"
								+ e.getMessage());
			}

		}

	}

	/**
	 * �Ƚ������ַ����Ƿ�����ͬ��Ԫ��
	 * 
	 * @param strArr1
	 * @param strArr2
	 * @return
	 */
	public String[] getAllSameElement(String[] strArr1, String[] strArr2) {
		if (strArr1 == null || strArr2 == null) {
			return null;
		}
		Arrays.sort(strArr1);
		Arrays.sort(strArr2);

		List<String> list = new ArrayList<String>();

		int k = 0;
		int j = 0;
		while (k < strArr1.length && j < strArr2.length) {
			if (strArr1[k].compareTo(strArr2[j]) == 0) {
				if (strArr1[k].equals(strArr2[j])) {
					list.add(strArr1[k]);
					k++;
					j++;
				}
				continue;
			} else if (strArr1[k].compareTo(strArr2[j]) < 0) {
				k++;
			} else {
				j++;
			}
		}

		String[] strss = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			strss[i] = list.get(i);
		}
		return strss;
	}

	/**
	 * ҵ������ ��ѯ���ݿ� �����е��ֶ���
	 * 
	 * @param id
	 * @param table
	 *            ����
	 * @return
	 */
	public String[] selectApply(Context context, String id, String table) {
		// SQLiteDatabase db = null;
		Cursor cur = null;
		// DBUtils dbUtils = new DBUtils(context);
		String[] str = null;
		DES des = new DES();
		try {
			getMyDataBase();
			cur = db.query(table, null, "id =?",
					new String[] { des.jiaMi(id) }, null, null, null);

			str = cur.getColumnNames();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.getInstance().writeLog(
					table + "--DBUTIL.selectApply�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ��ѯ�����������һ��idֵ
	 * 
	 * @return
	 */
	public String selectWorkOrder() {
		String str = "1";
		Cursor cur = null;

		try {
			getMyDataBase();

			cur = db.query("workOrder", new String[] { "id" }, null, null,
					null, null, null);

			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str = des.jieMI(cur.getString(cur.getColumnIndex("id")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"--DBUTIL.selectWorkOrder�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}

		return str;
	}

	public String[] QueryCustClass(String value) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			int i = 0;

			cur = db.rawQuery("select custClassValue from CustClass "
					+ " where producType= '" + value + "'", null);

			str = new String[cur.getCount()];
			Log.getInstance().writeLog(
					"QueryCustClassd��cur����:" + cur.getCount());
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex("custClassValue")));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.CustClass�쳣:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * ��ѯһ�ű��ȫ������
	 * 
	 * @param clunname
	 * @param tablename
	 * @return
	 */
	public String[] QueryAllData(String clunname, String tablename, int sign) {

		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����

			List<String> list = new ArrayList<String>();
			// cur = db.query(tablename, new String[] { clunname }, null,
			// null, null, null, orderby);

			// �˴�������û�ã���Ϊ���ݼ������޷������������
			cur = db.rawQuery("SELECT " + clunname + " FROM " + tablename
					+ " Order By " + clunname, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					list.add(des.jieMI(cur.getString(cur
							.getColumnIndex(clunname))));
				}
			}

			if (sign == 1) {
				Collections.sort(list, new Comparator<String>() {
					@Override
					public int compare(String lhs, String rhs) {
						return Integer.valueOf(lhs) - Integer.valueOf(rhs);
					}
				});
			}

			if (sign == 3 && tablename.equals("BillAddress")) {
				Collections.sort(list, new Comparator<String>() {
					@Override
					public int compare(String lhs, String rhs) {
						if (lhs.equals("������ַ")) {
							return 1;
						} else {
							return -1;
						}
					}
				});
			}
			str = new String[list.size()];
			str = list.toArray(str);
			if (sign == 2) {
				Arrays.sort(str, String.CASE_INSENSITIVE_ORDER);
				Log.getInstance().writeLog("Constants.custClass:" + str);
			}

		} catch (Exception e) {
			// techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯʡklQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.QueryAllData�쳣:" + tablename + e.getMessage());
			e.getMessage();
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	public Map<String, String> QueryAllData(String tablename, String key,
			String value) {

		Map<String, String> custMap = new HashMap<String, String>();
		;
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			List<String> listKey = new ArrayList<String>();
			List<String> listValue = new ArrayList<String>();
			// �˴�������û�ã���Ϊ���ݼ������޷������������
			cur = db.rawQuery("SELECT * FROM " + tablename, null);
			if (cur.getCount() != 0) {

				while (cur.moveToNext()) {
					listKey.add(des.jieMI(cur.getString(cur
							.getColumnIndex(value))));

					listValue.add(cur.getString(cur.getColumnIndex(key)));
				}
			}
			String[] k = new String[listKey.size()];
			String[] v = new String[listValue.size()];
			k = listKey.toArray(k);
			v = listValue.toArray(v);
			for (int i = 0; i < v.length; i++) {
				custMap.put(k[i], v[i]);
			}
			if (custMap != null)
				System.out.println("" + custMap.toString());
		} catch (Exception e) {
			Log.getInstance().writeLog(
					"DBUTIL.QueryAllData�쳣:" + tablename + e.getMessage());
			e.getMessage();
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return custMap;
	}

	// ��ѯ������
	public Map<String, String> QueryTable(final Context con, String[] clunname,
			String tablename, String where, String wherename) {
		Map<String, String> map = new HashMap<String, String>();
		Cursor cur = null;
		try {
			getMyDataBase();// ������ݿ����
			// cur=db.query(tablename,clunname,where,wherename,null,null,null);
			cur = db.query(tablename, clunname, where + "=?",
					new String[] { wherename }, null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					map.put(cur.getString(cur.getColumnIndex(clunname[0])),
							cur.getString(cur.getColumnIndex(clunname[1])));
				}
			}
		} catch (Exception e) {
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}

		return map;
	}
}