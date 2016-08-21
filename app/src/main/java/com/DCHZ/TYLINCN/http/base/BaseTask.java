package com.DCHZ.TYLINCN.http.base;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.text.TextUtils;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.listener.ITaskListener;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.StrUtil;
import com.common.http.HttpBaseTask;
import com.common.http.HttpEngine;
import com.common.http.HttpURLConnHelper;
import com.common.http.HttpUtils;

/***
 * 公共请求基类
 * @date 2015/07/04
 * @param <ReqBaseEntity>
 */
public class BaseTask<T> extends HttpBaseTask implements ITaskListener{
	protected final String TAG = getClass().getSimpleName();
	
	public BaseTask(T t){
		super(t);
	}

	@Override
	public final void doTask() {
		// TODO Auto-generated method stub
		if(MyLog.isDebugable()){
			MyLog.debug(TAG,"[doTask]");
		}
		Object o = getReq();
		if(o instanceof ReqBaseEntity){
			ReqBaseEntity reqBaseEntity = (ReqBaseEntity) o;
			String url = reqBaseEntity.getReqUrl();
			//url转换为小写
//			if(!TextUtils.isEmpty(url)){
//				url = url.toLowerCase();
////				MyLog.l
//			}
			final int seqNo = reqBaseEntity.seqNo;
			//网络请求
			try {
				//传递参数方式1
//				StringEntity entity = null;
				//带有文件上传的表单提交
				MultipartEntity multiEntity = new MultipartEntity();
				//设置form表单数据
				Map<String,String> mMap = reqBaseEntity.getReqData();
				if(mMap == null){
					mMap = new HashMap<String, String>();
				}
				String params=null;
				if(mMap != null && mMap.size() > 0){
					 params="?";
					 for(Entry<String, String> entry:mMap.entrySet()){
						 String key = entry.getKey();
						 String val = entry.getValue();
						 params=params+key+"="+val+"&";
						 StringBody strBody = new StringBody(val+"",Charset.forName(HTTP.UTF_8));
						 multiEntity.addPart(key,strBody);
//						 MyLog.debug(TAG, "[doTask]  key:"+key+"  val"+val);
					 }
//					 entity = new UrlEncodedFormEntity(parameters);
					 params=params.substring(0, params.length()-1);
					 url=url+params;
				}
//				byte[] buffer = HttpUtils.postJson(url, multiEntity);
//				byte[] buffer = HttpUtils.postJsonV2(url,multiEntity,reqBaseEntity.useAllUrl);
				String refer = HttpEngine.getInstance().getRefer();
				String totleUrl = refer + url;
				byte[] buffer=HttpURLConnHelper.loadByteFromURL(totleUrl);
				MyLog.debug(TAG, "[totleUrl] url:"+totleUrl);
				if(buffer != null && buffer.length > 2){
					String str = new String(buffer,HTTP.UTF_8);
					MyLog.debug(TAG, "[doTask] befor str:"+str);
					str=StrUtil.getJSOAN(str);
//					MyLog.debug(TAG, "[doTask] after str:"+str);
					JSONObject jsonObj = new JSONObject(str);
					if(MyLog.isDebugable()){
						String tempStr = jsonObj.toString();
						if(MyLog.isDebugable()){
							MyLog.debug(TAG,"[doTask]" + " rsp str:" + tempStr);
						}
					}
					getResponse(jsonObj,true,Common.ERROR_CODE_SUCC,seqNo);
					//延迟一下，防止文件读写造成卡顿
					Thread.sleep(500);
					if(reqBaseEntity.needCache){
						saveCache(jsonObj.toString());
					}
				}else{
					getResponse(null, false,Common.ERROR_CODE_PROTOCAL,seqNo);
				}
			} catch (UnsupportedEncodingException ee) {
				MyLog.error(TAG, "", ee);
				getResponse(null, false, Common.ERROR_CODE_EXCEPTION,seqNo);
			} catch (Exception ee) {
				// TODO Auto-generated catch block
				MyLog.error(TAG, "", ee);
				getResponse(null, false, Common.ERROR_CODE_EXCEPTION,seqNo);
			}
		}
	}

	@Override
	public void recyle() {
		// TODO Auto-generated method stub
		MyLog.debug(TAG,"[recyle]");
	}

	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,int seqNo) {
		// TODO Auto-generated method stub
		MyLog.debug(TAG,"[getResponse]");
	}

	@Override
	public T getReq() {
		// TODO Auto-generated method stub
		return (T) this.t;
	}

	/***
	 * 加载cache数据
	 * @return
	 */
	protected JSONObject loadCache(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	/***
	 * 服务器返回数据持久化到数据库中
	 * @param data
	 * @return
	 */
	protected boolean saveCache(String data){
		return false;
	}
}
