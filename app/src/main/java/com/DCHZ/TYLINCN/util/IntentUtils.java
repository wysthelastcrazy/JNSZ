package com.DCHZ.TYLINCN.util;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.activity.FaPiaoDetailActivity;
import com.DCHZ.TYLINCN.activity.FaRenDetailActivity;
import com.DCHZ.TYLINCN.activity.FeiYongDetailActivity;
import com.DCHZ.TYLINCN.activity.FenBaoFangDetailActivity;
import com.DCHZ.TYLINCN.activity.FirstActivity;
import com.DCHZ.TYLINCN.activity.GaiZhangDetailActivity;
import com.DCHZ.TYLINCN.activity.GongChengDetailActivity;
import com.DCHZ.TYLINCN.activity.HelpActivity;
import com.DCHZ.TYLINCN.activity.JieKuangDetailActivity;
import com.DCHZ.TYLINCN.activity.JieShouRenActivity;
import com.DCHZ.TYLINCN.activity.LoginActivity;
import com.DCHZ.TYLINCN.activity.MainActivity;
import com.DCHZ.TYLINCN.activity.QingJiaDetailActivity;
import com.DCHZ.TYLINCN.activity.TiJiaoTypeActivity;
import com.DCHZ.TYLINCN.activity.TouBiaoDetailActivity;
import com.DCHZ.TYLINCN.activity.XiangMuDetailActivity;
import com.DCHZ.TYLINCN.activity.XiuGaiIPActivity;
import com.DCHZ.TYLINCN.activity.YingFuHTDetailActivity;
import com.DCHZ.TYLINCN.activity.YingShouHTDetailActivity;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentUtils {
	public static final String KEY_ENTITY="mEntity";
	public static final String KEY_TYPE="type";
	/***
	 * 情动详情页
	 * @param mContext
	 * @param mEntity
	 */
	public static void startDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,TouBiaoDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动首页
	 * @param mContext
	 */
	public static void startMainActivity(Context mContext){
		Intent intent=new Intent(mContext,MainActivity.class);
		mContext.startActivity(intent);
	}
	/**
	 * 启动费用报销单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startFeiYongDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,FeiYongDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动借款审批单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startJieKuangDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,JieKuangDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动请假单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startQingJiagDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,QingJiaDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动发票开具单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startFaPiaoDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,FaPiaoDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	
	/**
	 * 启动应付合同单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startYingFuHTDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,YingFuHTDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动应收合同单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startYingShouHTDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,YingShouHTDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动分包方合同单详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startFenBaoFangDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,FenBaoFangDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 盖章申请详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startGaiZhangDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,GaiZhangDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 法人授权详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startFaRenDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,FaRenDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 项目下达详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startXiangMuDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,XiangMuDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 工程拨款详情
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void startGongChengDetailActivity(Context mContext,PDaiBanEntity mEntity,int type){
		Intent intent=new Intent(mContext,GongChengDetailActivity.class);
		intent.putExtra(KEY_ENTITY, mEntity);
		intent.putExtra(KEY_TYPE, type);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	/**
	 * 启动提交方式
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void starTiJiaoActivity(Activity mContext,int requestCode,String noTag){
		Intent intent=new Intent(mContext,TiJiaoTypeActivity.class);
		intent.putExtra("noTag", noTag);
		mContext.startActivityForResult(intent, requestCode);
	}
	/**
	 * 启动接收人
	 * @param mContext
	 * @param mEntity
	 * @param type
	 */
	public static void starJieShouRenActivity(Activity mContext,ArrayList<VJieShouRenEntity> entity,int requestCode){
		Intent intent=new Intent(mContext,JieShouRenActivity.class);
		intent.putExtra(KEY_ENTITY, entity);
		mContext.startActivityForResult(intent, requestCode);
	}
	public static void startLonginActivity(Activity mContext){
		Intent intent=new Intent(mContext,LoginActivity.class);
		mContext.startActivity(intent);
	}
	public static void startXiuGaiIpActivity(Activity mContext){
		Intent intent=new Intent(mContext,XiuGaiIPActivity.class);
		mContext.startActivity(intent);
	}
	public static void startHelpActivity(Activity mContext){
		Intent intent=new Intent(mContext,HelpActivity.class);
		mContext.startActivity(intent);
	}
	public static void startFirstActivity(Activity mContext){
		Intent intent=new Intent(mContext,FirstActivity.class);
		mContext.startActivity(intent);
	}
}
