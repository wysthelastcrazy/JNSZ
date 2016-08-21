package com.DCHZ.TYLINCN.commen;

/***
 * 事件相关，定义事件相关
 * @date 2015/07/05
 */
public class EventCommon {

	//=============================数据回调通知ID
	public static final int EVENT_FIRST=200;
	//登录
	public static final int EVENT_LOGIN=EVENT_FIRST+1;
	//获取代办事项
	public static final int EVENT_DAIBAN=EVENT_LOGIN+1;
	//已办事项
	public static final int EVENT_YIBAN= EVENT_DAIBAN+1;
	//代办数量
	public static final int EVENT_DAIBAN_COUNT=EVENT_YIBAN+1;
	//审批信息
	public static final int EVENT_SHENPI_INFO=EVENT_DAIBAN_COUNT+1;
	//办理意见
	public static final int EVENT_BANLI_YIJIAN=EVENT_SHENPI_INFO+1;
	//工时审批info拉取
	public static final int EVENT_GONGSHI_SHENPI=EVENT_BANLI_YIJIAN+1;
	
	//季度合同信息
	public static final int EVENT_JIDU_HETONG=EVENT_GONGSHI_SHENPI+1;
	//季度收款
	public static final int EVENT_JIDU_SHOUKUAN=EVENT_JIDU_HETONG+1;
	//工时审批
	public static final int EVEN_GSSHENPI=EVENT_JIDU_SHOUKUAN+1;
	//工时退回
	public static final int EVENT_GSTUIHUI=EVEN_GSSHENPI+1;
	//费用报销单详情
	public static final int EVENT_FEIYONG_DETAIL=EVENT_GSTUIHUI+1;
	//借款审批单详情
	public static final int EVENT_JIEKUANG_DETAIL=EVENT_FEIYONG_DETAIL+1;
	//请假申请
	public static final int EVENT_QINGJIA_DETAIL=EVENT_JIEKUANG_DETAIL+1;
	//发票
	public static final int EVEN_FAPIAO_DETAIL=EVENT_QINGJIA_DETAIL+1;
	//应付合同
	public static final int EVENT_YINGFU_HT=EVEN_FAPIAO_DETAIL+1;
	//应收合同
	public static final int EVENT_YINGSHOU_HT=EVENT_YINGFU_HT+1;
	//分包方
	public static final int EVENT_FENBAOFANG=EVENT_YINGSHOU_HT+1;
	//请求提交
	public static final int EVENT_SAVE_FLOWBUSINESS=EVENT_FENBAOFANG+1;
	//退回
	public static final int EVENT_SAVE_RETURN_FLOWBUSINESS=EVENT_SAVE_FLOWBUSINESS+1;
	public static final int EVENT_IS_HEGE=EVENT_SAVE_RETURN_FLOWBUSINESS+1;
	public static final int EVENT_UPDATE=EVENT_IS_HEGE+1;
	
	//盖章申请
	public static final int EVENT_GAIZHANG=EVENT_UPDATE+1;
	//法人授权
	public static final int EVENT_FAREN=EVENT_GAIZHANG+1;
	//项目下达
	public static final int EVENT_XIANGMU=EVENT_FAREN+1;
	//工程拨款申请
	public static final int EVENT_GONGCHENG=EVENT_XIANGMU+1;
	//推送
	public static final int EVENT_PUSH=EVENT_GONGCHENG+1;
	public static final int EVENT_ID_DATA_END = 600;
	
	//=============================UI点击时间通知
	public static final int EVENT_ID_UI_BTN_CLICK = EVENT_ID_DATA_END + 1;
	//分类更多icon点击事件
	public static final int EVENT_ICON_MORE_CLICK = EVENT_ID_UI_BTN_CLICK + 1;
	
	public static final int EVENT_ID_UI_END = 700;
	//=============================网络相关错误的通知
	public static final int EVENT_ID_ERROR = EVENT_ID_UI_END + 1;
}
