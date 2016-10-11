package com.DCHZ.TYLINCN.http;

import java.util.List;

import android.app.Activity;
import android.text.TextUtils;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;
import com.DCHZ.TYLINCN.http.req.ReqBaiLiYiJianEntity;
import com.DCHZ.TYLINCN.http.req.ReqChengBaoFeiDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqDaiBanListEntity;
import com.DCHZ.TYLINCN.http.req.ReqDiZhiYiHaoPinDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqFaPiaoDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqFaRenDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqFenBaoFangDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqGSShenPiEntity;
import com.DCHZ.TYLINCN.http.req.ReqGSTuiHuiEntity;
import com.DCHZ.TYLINCN.http.req.ReqGaiZhangDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqGongChengDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqGongShiShenPiEntity;
import com.DCHZ.TYLINCN.http.req.ReqGongZhangJieChuDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqGuDingZiChangDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqHeTongSearchEntity;
import com.DCHZ.TYLINCN.http.req.ReqIsHeGeEntity;
import com.DCHZ.TYLINCN.http.req.ReqJiDuHeTongEntity;
import com.DCHZ.TYLINCN.http.req.ReqJiDuShouKuanEntity;
import com.DCHZ.TYLINCN.http.req.ReqJieKuangDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqLoginEntity;
import com.DCHZ.TYLINCN.http.req.ReqPushEntity;
import com.DCHZ.TYLINCN.http.req.ReqQingJiaDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.req.ReqSaveReturnFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.req.ReqShenPiInfoEntity;
import com.DCHZ.TYLINCN.http.req.ReqTest;
import com.DCHZ.TYLINCN.http.req.ReqTouBiaoFeiYongDetailEntiy;
import com.DCHZ.TYLINCN.http.req.ReqUpDateEntity;
import com.DCHZ.TYLINCN.http.req.ReqXiangMuDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqXiangMuSearchEntity;
import com.DCHZ.TYLINCN.http.req.ReqYiBanListEntity;
import com.DCHZ.TYLINCN.http.req.ReqYingFuHTDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqYingShouHTDetailEntity;
import com.DCHZ.TYLINCN.http.req.ReqYueDuHeTongInfoEntity;
import com.DCHZ.TYLINCN.http.req.ReqYueDuShouKuanInfoEntity;
import com.DCHZ.TYLINCN.http.task.TaskBanLiYiJian;
import com.DCHZ.TYLINCN.http.task.TaskChengBaoFeiYongDetail;
import com.DCHZ.TYLINCN.http.task.TaskDaiBan;
import com.DCHZ.TYLINCN.http.task.TaskDiZhiYiHaoPinDetail;
import com.DCHZ.TYLINCN.http.task.TaskFaPiaoDetail;
import com.DCHZ.TYLINCN.http.task.TaskFaRenDetail;
import com.DCHZ.TYLINCN.http.task.TaskFeiYongDetail;
import com.DCHZ.TYLINCN.http.task.TaskFenBaoFangDetail;
import com.DCHZ.TYLINCN.http.task.TaskGSShenPi;
import com.DCHZ.TYLINCN.http.task.TaskGSTuiHui;
import com.DCHZ.TYLINCN.http.task.TaskGaiZhang;
import com.DCHZ.TYLINCN.http.task.TaskGongChengDetail;
import com.DCHZ.TYLINCN.http.task.TaskGongShiShenPi;
import com.DCHZ.TYLINCN.http.task.TaskGongZhangJIeChuDetail;
import com.DCHZ.TYLINCN.http.task.TaskGuDingZiChanDetail;
import com.DCHZ.TYLINCN.http.task.TaskHeTongSearch;
import com.DCHZ.TYLINCN.http.task.TaskIsHeGe;
import com.DCHZ.TYLINCN.http.task.TaskJiDuHeTong;
import com.DCHZ.TYLINCN.http.task.TaskJiDuShouKuan;
import com.DCHZ.TYLINCN.http.task.TaskJieKuangDetail;
import com.DCHZ.TYLINCN.http.task.TaskLogin;
import com.DCHZ.TYLINCN.http.task.TaskPushEntity;
import com.DCHZ.TYLINCN.http.task.TaskQingJiaDetail;
import com.DCHZ.TYLINCN.http.task.TaskSaveFlowBusiness;
import com.DCHZ.TYLINCN.http.task.TaskSaveReturnFlowBusiness;
import com.DCHZ.TYLINCN.http.task.TaskShenPiInfo;
import com.DCHZ.TYLINCN.http.task.TaskTest;
import com.DCHZ.TYLINCN.http.task.TaskTouBiaoFeiYongDetail;
import com.DCHZ.TYLINCN.http.task.TaskUpdate;
import com.DCHZ.TYLINCN.http.task.TaskXiangMuDetail;
import com.DCHZ.TYLINCN.http.task.TaskXiangMuSearch;
import com.DCHZ.TYLINCN.http.task.TaskYiBanList;
import com.DCHZ.TYLINCN.http.task.TaskYingFuHTDetail;
import com.DCHZ.TYLINCN.http.task.TaskYingShouHTDetail;
import com.DCHZ.TYLINCN.http.task.TaskYueDuHeTongInfo;
import com.DCHZ.TYLINCN.http.task.TaskYueDuShouKuanInfo;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;
import com.common.http.HttpEngine;

/***
 * 协议管理类
 * 协议组装使用
 * @date 2015/06/17
 * @author wys
 */
public class ProtocalManager {
	private static ProtocalManager instance;
	
	private ProtocalManager() {
		// TODO Auto-generated constructor stub
	}

	public synchronized static final ProtocalManager getInstance(){
		if(instance == null){
			instance = new ProtocalManager();
		}
		return instance;
	}
	private int addTask(BaseTask task){
		int seq = Common.getSeqNo();
		ReqBaseEntity reqBaseEntity = (ReqBaseEntity) task.getReq();
		reqBaseEntity.seqNo = seq;
		HttpEngine.getInstance().addTask(task);
		return seq;
	}
	public int getTest(){
		ReqTest req=new ReqTest();
		TaskTest task=new TaskTest(req);
		return addTask(task);
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public int getLogin(String userName,String pwd){
		ReqLoginEntity req=new ReqLoginEntity();
		req.UserName=userName;
		req.UserPwd=pwd;
		TaskLogin task=new TaskLogin(req);
		return addTask(task);
	}
	
	/**
	 * 获取代办事项
	 * @param YHID	用户id
	 * @return
	 */
	public int getDaiBanList(String YHID,int pageIndex){
		ReqDaiBanListEntity req =new ReqDaiBanListEntity();
		req.YHID=YHID;
		req.pageSize=MConfiger.PAGE_SIZE;
		req.pageIndex=pageIndex;
		TaskDaiBan task=new TaskDaiBan(req);
		return addTask(task);
	}
	/**
	 * 获取已办事项
	 * @param YHID
	 * @param pageIndex
	 * @return
	 */
	public int getYiBanList(String YHID,int pageIndex){
		ReqYiBanListEntity req=new ReqYiBanListEntity();
		req.YHID=YHID;
		req.pageSize=MConfiger.PAGE_SIZE;
		req.pageIndex=pageIndex;
		TaskYiBanList task=new TaskYiBanList(req);
		return addTask(task);
	}
	/**
	 * 获取审批信息
	 * @param LCID
	 * @param YWID
	 * @return
	 */
	public int getTouBiaoDetail(PDaiBanEntity entity){
		ReqShenPiInfoEntity req=new ReqShenPiInfoEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskShenPiInfo task=new TaskShenPiInfo(req);
		return addTask(task);
	}
	/**
	 * 获取办理意见
	 * @param SLID
	 * @return
	 */
	public int getBanLiYiJian(String SLID){
		ReqBaiLiYiJianEntity req=new ReqBaiLiYiJianEntity();
		req.SLID=SLID;
		TaskBanLiYiJian task=new TaskBanLiYiJian(req);
		return addTask(task);
	}
	/**
	 * 工时审批info获取
	 * @param YHID
	 * @param pageIndex
	 * @return
	 */
	public int getGongShiShenPiInfo(String YHID,int pageIndex){
		ReqGongShiShenPiEntity req=new ReqGongShiShenPiEntity();
		req.SPYHID=YHID;
		req.pageIndex=pageIndex;
		req.pageSize=MConfiger.PAGE_SIZE;
		TaskGongShiShenPi task=new TaskGongShiShenPi(req);
		return addTask(task);
	}
	/**
	 * 年度合同信息获取
	 * @param year
	 * @return
	 */
	public int getJiDuHeTongInfo(String year){
		ReqJiDuHeTongEntity req=new ReqJiDuHeTongEntity();
		req.NianValue=year;
		TaskJiDuHeTong task=new TaskJiDuHeTong(req);
		return addTask(task);
	}
	/**
	 * 年度收款信息获取
	 * @param year
	 * @return
	 */
	public int getJiDuShouKuanInfo(String year){
		ReqJiDuShouKuanEntity req=new ReqJiDuShouKuanEntity();
		req.NianValue=year;
		TaskJiDuShouKuan task=new TaskJiDuShouKuan(req);
		return addTask(task);
	}
	/**
	 * 工时审批
	 * @param GSIDs
	 * @param YHID
	 * @return
	 */
	public int GSShenPi(String GSIDs,String YHID){
		ReqGSShenPiEntity req=new ReqGSShenPiEntity();
		req.GSIDs=GSIDs;
		req.SPYHID=YHID;
		TaskGSShenPi task=new TaskGSShenPi(req);
		return addTask(task);
	}
	/**
	 * 工时退回
	 * @param GSIDs
	 * @return
	 */
	public int GSTuiHui(String GSIDs){
		ReqGSTuiHuiEntity req=new ReqGSTuiHuiEntity();
		req.GSIDs=GSIDs;
		TaskGSTuiHui task=new TaskGSTuiHui(req);
		return addTask(task);
	}
	/**
	 * 费用报销单详情
	 * @param entity
	 * @return
	 */
	public int getFeiYongDetail(PDaiBanEntity entity){
		ReqFeiYongDetailEntity req=new ReqFeiYongDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskFeiYongDetail task=new TaskFeiYongDetail(req);
		return addTask(task);
	}
	/**
	 * 借款审批单详情
	 * @param entity
	 * @return
	 */
	public int getJieKuangDetail(PDaiBanEntity entity){
		ReqJieKuangDetailEntity req=new ReqJieKuangDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskJieKuangDetail task=new TaskJieKuangDetail(req);
		return addTask(task);
	}
	
	/**
	 * 请假审批单详情
	 * @param entity
	 * @return
	 */
	public int getQingJiaDetail(PDaiBanEntity entity){
		ReqQingJiaDetailEntity req=new ReqQingJiaDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskQingJiaDetail task=new TaskQingJiaDetail(req);
		return addTask(task);
	}
	/**
	 * 发票审批单详情
	 * @param entity
	 * @return
	 */
	public int getFaPiaoDetail(PDaiBanEntity entity){
		ReqFaPiaoDetailEntity req=new ReqFaPiaoDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskFaPiaoDetail task=new TaskFaPiaoDetail(req);
		return addTask(task);
	}
	
	/**
	 * 应付合同单详情
	 * @param entity
	 * @return
	 */
	public int getYingFuHTDetail(PDaiBanEntity entity){
		ReqYingFuHTDetailEntity req=new ReqYingFuHTDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskYingFuHTDetail task=new TaskYingFuHTDetail(req);
		return addTask(task);
	}
	
	/**
	 * 应收合同单详情
	 * @param entity
	 * @return
	 */
	public int getYingShouHTDetail(PDaiBanEntity entity){
		ReqYingShouHTDetailEntity req=new ReqYingShouHTDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskYingShouHTDetail task=new TaskYingShouHTDetail(req);
		return addTask(task);
	}
	/**
	 * 分包方合同单详情
	 * @param entity
	 * @return
	 */
	public int getFenBaoFangTDetail(PDaiBanEntity entity){
		ReqFenBaoFangDetailEntity req=new ReqFenBaoFangDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskFenBaoFangDetail task=new TaskFenBaoFangDetail(req);
		return addTask(task);
	}
	
	/**
	 * 盖章申请详情
	 * @param entity
	 * @return
	 */
	public int getGaiZhangDetail(PDaiBanEntity entity){
		ReqGaiZhangDetailEntity req=new ReqGaiZhangDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskGaiZhang task=new TaskGaiZhang(req);
		return addTask(task);
	}
	/**
	 * 法人授权详情
	 * @param entity
	 * @return
	 */
	public int getFaRenDetail(PDaiBanEntity entity){
		ReqFaRenDetailEntity req=new ReqFaRenDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskFaRenDetail task=new TaskFaRenDetail(req);
		return addTask(task);
	}
	/**
	 * 项目下达详情
	 * @param entity
	 * @return
	 */
	public int getXiangMuDetail(PDaiBanEntity entity){
		ReqXiangMuDetailEntity req=new ReqXiangMuDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskXiangMuDetail task=new TaskXiangMuDetail(req);
		return addTask(task);
	}
	/**
	 * 工程拨款详情
	 * @param entity
	 * @return
	 */
	public int getGongChengDetail(PDaiBanEntity entity){
		ReqGongChengDetailEntity req=new ReqGongChengDetailEntity();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskGongChengDetail task=new TaskGongChengDetail(req);
		return addTask(task);
	}

	/**
	 * 公章借出申请
	 * @return
	 */
	public int getGongZhangJieChuDetail(PDaiBanEntity entity){
		ReqGongZhangJieChuDetailEntiy req=new ReqGongZhangJieChuDetailEntiy();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskGongZhangJIeChuDetail task=new TaskGongZhangJIeChuDetail(req);
		return addTask(task);
	}
	/**
	 * 固定资产申请
	 * @return
	 */
	public int geGuDingZiChanDetail(PDaiBanEntity entity){
		ReqGuDingZiChangDetailEntiy req=new ReqGuDingZiChangDetailEntiy();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskGuDingZiChanDetail task=new TaskGuDingZiChanDetail(req);
		return addTask(task);
	}
	/**
	 * 低值易耗品申请
	 * @return
	 */
	public int geDiZhiYiHaoPinDetail(PDaiBanEntity entity){
		ReqDiZhiYiHaoPinDetailEntiy req=new ReqDiZhiYiHaoPinDetailEntiy();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskDiZhiYiHaoPinDetail task=new TaskDiZhiYiHaoPinDetail(req);
		return addTask(task);
	}
	/**
	 * 投标费用
	 * @return
	 */
	public int getTouBiaoFeiYongDetail(PDaiBanEntity entity){
		ReqTouBiaoFeiYongDetailEntiy req=new ReqTouBiaoFeiYongDetailEntiy();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskTouBiaoFeiYongDetail task=new TaskTouBiaoFeiYongDetail(req);
		return addTask(task);
	}
	/**
	 * 承包费用费用
	 * @return
	 */
	public int getTChengBaoeiYongDetail(PDaiBanEntity entity){
		ReqChengBaoFeiDetailEntiy req=new ReqChengBaoFeiDetailEntiy();
		req.LCID=entity.LCID;
		req.SLID=entity.SLID;
		req.YWID=entity.YWID;
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.JDID=strs3[0];
		TaskChengBaoFeiYongDetail task=new TaskChengBaoFeiYongDetail(req);
		return addTask(task);
	}
	public int SaveFlowBusiness(PDaiBanEntity entity,String BLUserID,String opinion){
		ReqSaveFlowBusinessEntity req=new ReqSaveFlowBusinessEntity();
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		req.BLUserID=BLUserID;
		req.opinion=opinion;
		
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.FlowJDID=strs3[0];
		req.FlowSLID=entity.SLID;
		req.YWID=entity.YWID;
		req.FlowVerID=entity.LCID;
		String[] arr=BLUrl.split(";");
		String[] arr1=arr[3].split("=");
		String[] arr2=arr1[1].split("@");
		req.FLowBLID=arr2[0];
		TaskSaveFlowBusiness task=new TaskSaveFlowBusiness(req);
		return addTask(task);
	}
	
	public int SaveReturnFlowBusiness(PDaiBanEntity entity,String BLUserID,String opinion){
		ReqSaveReturnFlowBusinessEntity req=new ReqSaveReturnFlowBusinessEntity();
		req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		req.BLUserID=BLUserID;
		req.opinion=opinion;
		
		String BLUrl=entity.BLUrl;
		String[] strs=BLUrl.split("\\?");
		req.UrlParam=strs[1].replaceAll("\\&", "\\$");
		String[] strs1=BLUrl.split(";");
		String[] strs2=strs1[1].split("=");
		String[] strs3=strs2[1].split("@");
		req.FlowJDID=strs3[0];
		req.FlowSLID=entity.SLID;
		req.YWID=entity.YWID;
		req.FlowVerID=entity.LCID;
		String[] arr=BLUrl.split(";");
		String[] arr1=arr[3].split("=");
		String[] arr2=arr1[1].split("@");
		req.FLowBLID=arr2[0];
		TaskSaveReturnFlowBusiness task=new TaskSaveReturnFlowBusiness(req);
		return addTask(task);
	}
	
	public int getIsHeGe(String YWID,String isHeGe){
		ReqIsHeGeEntity req=new ReqIsHeGeEntity();
		req.YWID=YWID;
		req.GFPingShenJieLun=isHeGe;
		TaskIsHeGe task=new TaskIsHeGe(req);
		return addTask(task);
	}
	public int Update(){
		ReqUpDateEntity req=new ReqUpDateEntity();
		TaskUpdate task=new TaskUpdate(req);
		return addTask(task);
	}
	public int reqPush(String strYHID,String strClientID){
		ReqPushEntity req=new ReqPushEntity();
		req.strYHID=strYHID;
		req.strClientID=strClientID;
		req.strDeviceToken="";
		TaskPushEntity task=new TaskPushEntity(req);
		return addTask(task);
	}

	/**
	 * 获取月度合同信息
	 * @param NianValue
	 * @return
	 */
	public int getYueDuHeTongInfo(String NianValue){
		ReqYueDuHeTongInfoEntity req=new ReqYueDuHeTongInfoEntity();
		req.NianValue=NianValue;
		TaskYueDuHeTongInfo task=new TaskYueDuHeTongInfo(req);
		return addTask(task);
	}
	/**
	 * 获取月度收款信息
	 * @param NianValue
	 * @return
	 */
	public int getYueDuSHouKuanInfo(String NianValue){
		ReqYueDuShouKuanInfoEntity req=new ReqYueDuShouKuanInfoEntity();
		req.NianValue=NianValue;
		TaskYueDuShouKuanInfo task=new TaskYueDuShouKuanInfo(req);
		return addTask(task);
	}

	/**
	 * 项目搜索
	 * @param page
	 * @param strWhere
	 * @return
	 */
	public int reqXiangMuSearchList(int page,String strWhere ){
		ReqXiangMuSearchEntity req=new ReqXiangMuSearchEntity();
		req.strYHID=SharePreLoginUtil.loadLoginInfo().YHID;
		req.pageIndex=page;
		req.strWhere=strWhere;
		TaskXiangMuSearch task=new TaskXiangMuSearch(req);
		return addTask(task);
	}

	/**
	 * 合同搜索
	 * @param page
	 * @param strWhere
	 * @return
	 */
	public int reqHeTongSearchList(int page,String strWhere){
		ReqHeTongSearchEntity req=new ReqHeTongSearchEntity();
		req.strYHID=SharePreLoginUtil.loadLoginInfo().YHID;
		req.pageIndex=page;
		req.strWhere=strWhere;
		TaskHeTongSearch task=new TaskHeTongSearch(req);
		return addTask(task);
	}
}
