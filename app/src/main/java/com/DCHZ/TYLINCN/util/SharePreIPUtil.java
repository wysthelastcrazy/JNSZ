package com.DCHZ.TYLINCN.util;

import com.DCHZ.TYLINCN.commen.Global;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;


/***
 * LoginInfo信息
 * @date 2015/07/13
 */
public class SharePreIPUtil {
	private final String TAG = "SharePreIPUtil";
	private static final String ip="ip";
	private final static String FILE_NAME = "IPInfo_FILE";
	
	public static void saveIpInfo(String ip){
		if(!TextUtils.isEmpty(ip)){
			SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString(ip, ip);
			editor.commit();
		}
	}
	
	public static String loadIpInfo(){
		SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
		String mip = sp.getString(ip, "");
		return mip;
		
	}
}
