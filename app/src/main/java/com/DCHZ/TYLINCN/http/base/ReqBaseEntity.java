package com.DCHZ.TYLINCN.http.base;
import java.util.Map;

/***
 * 请求包的基类
 * @date 2015/06/27
 */
public abstract class ReqBaseEntity {
	//请求链接为全链接
	public boolean useAllUrl = false;
	//是否需要cache系统
	public boolean needCache = false;
	
	/**请求序列号**/
	public int seqNo = -1;

	/**获取请求地址**/
	public abstract String getReqUrl();

	/**获取请求数据Data
	 * key value形式保存
	 * **/
	public abstract Map<String,String> getReqData();
}
