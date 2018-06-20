package com.example.jack.myapp.demo.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
	Context con;
	public SharedPrefs(Context context)
	{
		this.con=context;
	}
	/**
	 * 
	 * 
	 */
	public  void saveParameters(String spname,String spstr)
	{
		SharedPreferences sp=
		con.getSharedPreferences("cfcccparam", Activity.MODE_PRIVATE);
	
		sp.edit().putString(spname,spstr)		
		.commit();
	}	
	/*
	 * 
	 */
	public  String getParameters(String spname)
	{
		SharedPreferences sp=
		con.getSharedPreferences("cfcccparam", Activity.MODE_PRIVATE);
		String param=sp.getString(spname, null); 
		return param;
	}

	public  void removeParameters(String spname)
	{
		SharedPreferences sp=
		con.getSharedPreferences("cfcccparam", Activity.MODE_PRIVATE);
		sp.edit().remove(spname).commit();
//		String param=sp.getString("FavorableMessage", null); 
//		return param;
	}
}
