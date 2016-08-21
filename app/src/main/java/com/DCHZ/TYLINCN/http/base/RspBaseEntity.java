package com.DCHZ.TYLINCN.http.base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.DCHZ.TYLINCN.util.MyLog;

/***
 * 回复包的基类
 * @data 2015/06/27
 */
public abstract class RspBaseEntity {
	protected final String TAG = getClass().getSimpleName();
	
	/**是否返回正确**/
	public boolean isSucc;
	//请回序列号,客户端自定义使用
	public int seqNo;
	/**是否登录**/
	
	private RspBaseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public RspBaseEntity(JSONObject jsonObj,int seqNo){
		this.seqNo = seqNo;
		if(jsonObj != null){
			this.isSucc = true;
			MyLog.debug(TAG, "[RspBaseEntity]  jsonObj:"+jsonObj.toString());
			parseData(jsonObj);
		}
	}
	
	public abstract void parseData(JSONObject jsonObj);
}
