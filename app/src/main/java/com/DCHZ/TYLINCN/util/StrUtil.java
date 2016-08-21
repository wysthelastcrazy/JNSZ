package com.DCHZ.TYLINCN.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class StrUtil {

	public static String getString(ArrayList<String> mList){
		String str="";
		if(mList!=null&&mList.size()>0){
			for(int i=0;i<mList.size();i++){
				str=str+mList.get(i)+",";
			}
		}
		if(str.length()>1)
		str=str.substring(0, str.length()-1);
		return str;
	} 
	public static String getJSOAN(String str){
		String[] strs1=str.split(">");
		String[] strs=strs1[2].split("<");
		return strs[0];
	}
	/** 
     * 手机号验证 
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
    
}
