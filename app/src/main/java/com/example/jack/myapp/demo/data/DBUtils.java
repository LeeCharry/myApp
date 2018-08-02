package com.example.jack.myapp.demo.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * 数据库操作类
 *
 * @author Administrator
 *
 */
public class DBUtils {
	private MyDatabaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	public Context con;
	DES des = new DES();

	// 新增share
	SharedPrefs sharedPrefs;

	public DBUtils(Context context) {
		this.con = context;
		sharedPrefs = new SharedPrefs(context);
	}

	public SQLiteDatabase getMyDataBase() {
		if (db == null) {
			dbHelper = new MyDatabaseHelper(con,Constants.dbName,
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
		dbHelper = new MyDatabaseHelper(con,Constants.dbName,Constants.dbVer);
		return dbHelper.deleteDataBase(con);
	}

	// 插入数据
	public void insert(String tablename, String[] clomname, String[] clomstr) {
		try {
			getMyDataBase();// 获得数据库对象
			ContentValues values = new ContentValues();
			// db.beginTransaction();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));

			}
			db.insert(tablename, null, values); // 插入数据
			// db.setTransactionSuccessful();
			// db.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog(
					"DBUTIL.insert异常:" + tablename + e.getMessage());
		} finally {
			closeDataBase();
		}

	}

	// 更新一行多个字段
	public long upload(String tablename, String[] clomname,

					   String[] clomstr, String id, String idstr) {
		long count = -1;
		try {
			getMyDataBase();// 获得数据库对象
			ContentValues values = new ContentValues();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}
			if (values.size() > 0) {
				count = db.update(tablename, values, id + "=?",
						new String[] { des.jiaMi(idstr) }); // 更新数据
			}

			// db.close();
			if (count == -1) {
				Log.getInstance().writeLog("DBUTIL.upload更新数据失敗");
			}

		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.upload异常:" + e.getMessage());
		} finally {
			closeDataBase();
		}
		return count;
	}

	/**
	 * 查询的RP老客户得到的数据更新数据库，没值的数据不可添加，有值的添加
	 *
	 * @param tablename
	 *            表名
	 * @param clomname
	 *            列数组
	 * @param clomstr
	 *            值数组
	 * @param id
	 *            查询的id
	 * @param idstr
	 *            查询的id值
	 * @return
	 */
	public List<String> uploadRP(String tablename, String[] clomname,
								 String[] clomstr, String id, String idstr) {
		List<String> strUpload = new ArrayList<String>();
		long count = -1;
		try {
			getMyDataBase();// 获得数据库对象
			ContentValues values = new ContentValues();
			for (int i = 0; i < clomname.length; i++) {
				if (!(clomstr[i]).equals("")) {
					values.put(clomname[i], des.jiaMi(clomstr[i]));
					strUpload.add(clomname[i]);
				}
			}
			System.out.println("更新的Id：" + idstr + "保存的值得个数是几个： "
					+ values.size());
			if (values.size() > 0) {
				count = db.update(tablename, values, id + "=?",
						new String[] { des.jiaMi(idstr) }); // 更新数据
			}

			// db.close();
			if (count == -1) {
				Log.getInstance().writeLog("DBUTIL.uploadRP -- 更新数据失敗");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().writeLog("DBUTIL.uploadRP异常:" + e.getMessage());
		} finally {
			closeDataBase();
		}
		return strUpload;
	}

	// 查询一行多个字段
	public String[] Applyquery(String tablename, String[] clunname,
							   String where, String wherea, String wherestr, String wherestra) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

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
					"DBUTIL.query异常:" + e.getMessage() + "   +  ( 表名为："
							+ tablename + ")");
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	// 查询一行多个字段
	public String[] query(String tablename, String[] clunname, String where,
						  String wherestr) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

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
					"DBUTIL.query异常:" + e.getMessage() + "   +  ( 表名为："
							+ tablename + ")");
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	// 查询日期版本号
	public String queryDate(String tablename, String colum) {
		String str = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(
				System.currentTimeMillis()));
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
			Log.getInstance().writeLog("DBUTIL.queryDate异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * 原生删除
	 *
	 * @param wherestr
	 */
	public void delete(String sql, String wherestr) {
		try {
			getMyDataBase();
			db.rawQuery(sql, new String[] { des.jiaMi(wherestr) });
			Log.getInstance().writeLog("删除数据库下" + des.jiaMi(wherestr) + "数据成功");
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.delete异常:" + e.getMessage());
		} finally {
			closeDataBase();
		}
	}

	// 刪除一行数据
	public void delete(String tablename, String where, String wherestr) {
		try {
			getMyDataBase();
			db.delete(tablename, where + "=?",
					new String[] { des.jiaMi(wherestr) });
			Log.getInstance().writeLog("删除数据库下" + des.jiaMi(wherestr) + "数据成功");
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.delete异常:" + e.getMessage());
		} finally {
			closeDataBase();
		}
	}

	// 刪除一行数据
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
			Log.getInstance().writeLog("删除数据库下" + des.jiaMi(wherestr) + "数据成功");
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.delete异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
	}

	// 删除所有数据
	public void deleteall(String tablename) {
		try {
			getMyDataBase();
			db.delete(tablename, null, null);
		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.deleteall异常:" + e.getMessage());
		} finally {
			closeDataBase();
		}
	}

	// 得到ID值num
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
			Log.getInstance().writeLog("DBUTIL.getApplyid异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return num;

	}

	// 得到ID值num
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
					"DBUTIL.getInvestigationid异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return num;

	}

	// 得到Strongde的ID值num
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
					"DBUTIL.getStoreApplyid异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return num;

	}

	// 得到外访做件id xinzheng
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

	// 查询多行多个字段
	public List<String[]> queryApply(String tablename, String[] clunname,
									 String where, String wherestr) {
		List<String[]> liststr = new ArrayList<String[]>();
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
			Log.getInstance().writeLog("DBUTIL.queryApply异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return liststr;
	}

	// 模糊查询多行多个字段
	public List<String[]> queryLikeApply(String sql, String[] clunname,
										 String wherestr) {
		List<String[]> liststr = new ArrayList<String[]>();
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
			Log.getInstance().writeLog("DBUTIL.queryApply异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return liststr;
	}

	// 查询每个箱的件个数
	public int numQuery(String tablename, String clunname, String clunstr) {
		int numstr = 0;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
			cur = db.query(tablename, null, clunname + "=?",
					new String[] { des.jiaMi(clunstr) }, null, null, null);
			numstr = cur.getCount();
		}

		catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.numQuery异常:" + e.getMessage());
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
					"DBUTIL.copyDBFileToSD异常:" + dbName + e.getMessage());
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
			Log.getInstance().writeLog(fileName + "中的版本号: " + ver);
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
	 *            查询字段名
	 * @param tablename
	 *            表
	 * @return
	 */
	public String[] klQuery(final Context con, String clunname, String tablename) {

		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

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
			// techown.shanghu.https.Log.Instance().WriteLog("团队：查询省klQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.klQuery异常:" + tablename + e.getMessage());
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
	 *            查询字段名
	 * @param tablename
	 *            表
	 * @return
	 */
	public String[] loanReductionInitialklQuery(final Context con,
												String clunname, String tablename, String selection,
												String selectionArgs) {

		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

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
			// techown.shanghu.https.Log.Instance().WriteLog("团队：查询省klQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.klQuery异常:" + tablename + e.getMessage());
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
	 * 根据对应值查询
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param where
	 *            查询条件
	 * @param wherestr
	 *            条件对象
	 * @param tablename
	 *            表
	 * @return
	 */
	public String[] Query(final Context con, String clunname, String where,
						  String wherestr, String tablename) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

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
			// techown.shanghu.https.Log.Instance().WriteLog("团队：查询市cityQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.Query异常:" + tablename + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * 查询优惠信息的code值
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param where
	 *            查询条件
	 * @param wherestr
	 *            条件对象
	 * @param tablename
	 *            表
	 * @return
	 */
	public String[] loanReductionInitialQuery(final Context con,
											  String clunname, String where, String wherestr, String where1,
											  String wherestr1, String tablename) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

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
			// techown.shanghu.https.Log.Instance().WriteLog("团队：查询市cityQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.Query异常:" + tablename + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * 无卡支付 Store
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param wherestrA
	 *            查询条件A
	 * @param wherestrB
	 *            查询条件B
	 * @param tablenameA
	 *            表A
	 * @param tablenameB
	 *            表B
	 * @return
	 */
	public String[] StoreWuKa(final Context con, String clunname,
							  String wherestrA, String wherestrB, String tablenameA,
							  String tablenameB) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
					"DBUTIL.Store异常:" + tablenameA + tablenameB
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
	 * 购易贷Store
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param wherestrA
	 *            查询条件A
	 * @param wherestrB
	 *            查询条件B
	 * @param tablenameA
	 *            表A
	 * @param tablenameB
	 *            表B
	 * @return
	 */
	public String[] Store(final Context con, String clunname, String wherestrA,
						  String wherestrB, String tablenameA, String tablenameB) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
			// System.out.println("查询商行的语句：" +
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
					"DBUTIL.Store异常:" + tablenameA + tablenameB
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
	 * 购易贷goods
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param wherestrA
	 *            查询条件A
	 * @param wherestrB
	 *            查询条件B
	 * @param wherestrC
	 *            查询条件C
	 * @param tablenameA
	 *            表A
	 * @param tablenameB
	 *            表B
	 * @return
	 */
	public String[] Goods(final Context con, String clunname, String wherestrA,
						  String wherestrB, String tablenameA, String tablenameB,
						  String wherestrC) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
					"DBUTIL.Goods异常:" + tablenameA + tablenameB
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
	 * 购易贷Plan
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param wherestrA
	 *            查询条件A
	 * @param wherestrB
	 *            查询条件B
	 * @param wherestrC
	 *            查询条件C
	 * @param tablenameA
	 *            表A
	 * @param tablenameB
	 *            表B
	 * @param tablenameC
	 *            表C
	 * @param num
	 *            查询条件
	 * @return
	 */
	public String[] Plan(final Context con, String clunname, String wherestrA,
						 String wherestrB, String wherestrC, String tablenameA,
						 String tablenameB, String tablenameC, String num) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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

			// System.out.println("还款方式的查询语句：" +
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
					"DBUTIL.Plan异常:" + tablenameA + tablenameB + tablenameC
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
	 * 购易贷Plan
	 *
	 * @param con
	 * @param clunname
	 *            查询字段名
	 * @param wherestrA
	 *            查询条件A
	 * @param wherestrB
	 *            查询条件B
	 * @param wherestrC
	 *            查询条件C
	 * @param tablenameA
	 *            表A
	 * @param tablenameB
	 *            表B
	 * @param tablenameC
	 *            表C
	 * @param num
	 *            查询条件
	 * @param wherestrD
	 *            查询条件D
	 * @return
	 */
	public String[] Plan(final Context con, String clunname, String wherestrA,
						 String wherestrB, String wherestrC, String tablenameA,
						 String tablenameB, String tablenameC, String num, String wherestrD,
						 String clas) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
			System.out.println("还款方式的查询语句：" + "select " + clunname + " from "
					+ tablenameA + "," + tablenameB + "," + tablenameC
					+ " where " + tablenameA + "." + wherestrA + "="
					+ tablenameB + "." + wherestrA + " and " + tablenameB + "."
					+ wherestrB + "= " + tablenameC + "." + wherestrB + " and "
					+ tablenameC + "." + wherestrC + "='" + num + "'" + " and "
					+ tablenameA + "." + wherestrD + "= '" + clas + "'");
			// System.out.println("还款方式的查询语句：" +
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
					"DBUTIL.Plan异常:" + tablenameA + tablenameB + tablenameC
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
	 * 查询数据库中的一个数据
	 *
	 * @param clunname
	 *            查询字段名
	 * @param wherestrA
	 *            查询条件A
	 * @param wherestrB
	 *            查询条件B
	 * @param wherestrC
	 *            查询条件C
	 * @param tablenameA
	 *            表A
	 * @param tablenameB
	 *            表B
	 * @param tablenameC
	 *            表C
	 * @param num
	 *            查询条件
	 * @return 查询的列值结果
	 */
	public String[] Plone_store(final Context con, String clunname,
								String wherestrA, String wherestrB, String wherestrC,
								String tablenameA, String tablenameB, String tablenameC, String num) {
		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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
					"DBUTIL.Plone_store异常:" + tablenameA + tablenameB
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
	 * 查询一个数组
	 *
	 * @param context
	 * @param tableName
	 *            表名
	 * @param strName
	 *            查询的字段数组
	 * @param where
	 *            查询条件
	 * @param whereValue
	 *            条件相应数组
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
					tableName + "--DBUTIL.queryStr异常:" + e.getMessage());
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
					tableName + "--DBUTIL.queryStr异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * 查询数据库中的一个数据
	 *
	 * @param context
	 * @param tableName
	 *            表名
	 * @param rowName
	 *            要查询的列名
	 * @param queryRow
	 *            查询的条件列
	 * @param queryRowValue
	 *            查询的条件列值
	 * @return 查询的列值结果
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
					tableName + "--DBUTIL.queryArrayValue异常:" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDataBase();
		}
		return strRowName;
	}

	/**
	 * 解析返回的数据存入数据库
	 *
	 * @param context
	 * @param requestData
	 *            要保存的数据
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
				// 将appInfoDto中的数据解析出来
				JSONObject jsonObjectAppInfoDto = jsonObject
						.optJSONObject("appInfoDto");

				if (jsonObjectAppInfoDto != null) {
					Iterator<?> iterator = jsonObjectAppInfoDto.keys();
					String strdata[] = selectApply(context,Constants.applyid,
							tableName);// 查出数据的的所有字段的名字
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

					String[] sameElement = getAllSameElement(strdata, strname);// 取相同的数据字段
					String[] strKey = new String[sameElement.length];// 放字段的数组
					String[] strValue = new String[sameElement.length];// 放值的数组
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
						tableName + "--DBUTIL.requestDataDispose异常:"
								+ e.getMessage());
			}

		}

	}

	/**
	 * 比较两个字符串是否有相同的元素
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
	 * 业务申请 查询数据库 中所有的字段名
	 *
	 * @param id
	 * @param table
	 *            表名
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
					table + "--DBUTIL.selectApply异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * 查询出工单的最大一个id值
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
					"--DBUTIL.selectWorkOrder异常:" + e.getMessage());
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
			getMyDataBase();// 获得数据库对象
			int i = 0;

			cur = db.rawQuery("select custClassValue from CustClass "
					+ " where producType= '" + value + "'", null);

			str = new String[cur.getCount()];
			Log.getInstance().writeLog(
					"QueryCustClassd的cur长度:" + cur.getCount());
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex("custClassValue")));
					i++;
				}

			}

		} catch (Exception e) {
			Log.getInstance().writeLog("DBUTIL.CustClass异常:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return str;
	}

	/**
	 * 查询一张表的全部数据
	 *
	 * @param clunname
	 * @param tablename
	 * @return
	 */
	public String[] QueryAllData(String clunname, String tablename, int sign) {

		String[] str = null;
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象

			List<String> list = new ArrayList<String>();
			// cur = db.query(tablename, new String[] { clunname }, null,
			// null, null, null, orderby);

			// 此处的排序没用，因为数据加密了无法进行排序操作
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
						if (lhs.equals("其他地址")) {
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
			// techown.shanghu.https.Log.Instance().WriteLog("团队：查询省klQuery:"+e.getMessage());
			Log.getInstance().writeLog(
					"DBUTIL.QueryAllData异常:" + tablename + e.getMessage());
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
			getMyDataBase();// 获得数据库对象
			List<String> listKey = new ArrayList<String>();
			List<String> listValue = new ArrayList<String>();
			// 此处的排序没用，因为数据加密了无法进行排序操作
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
					"DBUTIL.QueryAllData异常:" + tablename + e.getMessage());
			e.getMessage();
		} finally {
			if (cur != null) {
				cur.close();
			}
			closeDataBase();
		}
		return custMap;
	}

	// 查询整个表
	public Map<String, String> QueryTable(final Context con, String[] clunname,
										  String tablename, String where, String wherename) {
		Map<String, String> map = new HashMap<String, String>();
		Cursor cur = null;
		try {
			getMyDataBase();// 获得数据库对象
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