package com.DCHZ.TYLINCN.activity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.LinearLayout;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.BanLiYiJianAdapter;
import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.component.DetailShenPiYiJianTitleView;
import com.DCHZ.TYLINCN.component.HeaderDetailView;
import com.DCHZ.TYLINCN.component.JZADScoreTextView;
import com.DCHZ.TYLINCN.component.ListBottomView;
import com.DCHZ.TYLINCN.component.ListOpenWordView;
import com.DCHZ.TYLINCN.component.ListTopItemView;
import com.DCHZ.TYLINCN.entity.PBanLiYiJianEntity;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.entity.PDetailBXInfoEntity;
import com.DCHZ.TYLINCN.entity.PFuJianEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspBanLiYiJianEntity;
import com.DCHZ.TYLINCN.http.rsp.RspFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveReturnFlowBusinessEntity;
import com.DCHZ.TYLINCN.listener.IDaiBanClickListener;
import com.DCHZ.TYLINCN.listener.IHeaderClickListener;
import com.DCHZ.TYLINCN.listener.IWordOpenListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.util.FileUtil;
import com.DCHZ.TYLINCN.util.IntentUtils;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.ParseUtil;

public class FeiYongDetailActivity extends BaseNormalActivity{
	private List<Integer> mReqList = new ArrayList<Integer>();
	private int type;
	private PDaiBanEntity entity;
	private HeaderDetailView mHeader;
	private MsgPage mMsgPage;
	private final int FLAG_SET_LIST=0x100;
	private final int FLAG_SET_TOP=0x101;
	private final int FLAG_TJ=0x102;
	private final int FLAG_HT=0x103;
	private BanLiYiJianAdapter mAdapter;
	private final int GET_TYPE=100;
	private final int GET_JIESHOUREN=101;
	private ArrayList<VJieShouRenEntity> mList;
	private String TJtype;
	private RspFeiYongDetailEntity rsp;
	private ListBottomView mBottomView;
	private String mBLUserID;
	private String mBLUserName;
//	private List<String> mStrList=new ArrayList<String>();
	private String savePath=Environment.getExternalStorageDirectory().getAbsolutePath();
	private File file;
	private String filename;
	private static final int what=1; 
	private Handler mHandler=new Handler(){
  	  public void handleMessage(Message msg) {
  		hideLoadingDialog();
  		  switch (msg.what) {
		case what:
			String fileName=(String) msg.obj;
			String[] strs=fileName.split("\\.");
	  		String str=strs[strs.length-1];
	  		str=str.toLowerCase();
	  		if(!new File(savePath+"/"+fileName).exists()){
	  			showToast("文件下载失败！");
			}else {
				if("doc".equals(str)||"docx".equals(str)){
		  			Intent intent=FileUtil.getWordFileIntent(savePath+"/"+fileName);
		  			startActivity(intent);
		  		}else if("xls".equals(str)||"xlsx".equals(str)){
		  			Intent intent=FileUtil.getExcelFileIntent(savePath+"/"+fileName);
		  			startActivity(intent);
		  		}else if("pdf".equals(str)){
		  			Intent intent=FileUtil.getPdfFileIntent(savePath+"/"+fileName);
		  			startActivity(intent);
		  		}
			}
			break;
		default:
			showToast("文件下载失败！");
			break;
		}
  		
  	  };
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		initExtras();
		initLayout();
		registMsgRecevier(EventCommon.EVENT_FEIYONG_DETAIL);
		registMsgRecevier(EventCommon.EVENT_BANLI_YIJIAN);
		registMsgRecevier(EventCommon.EVENT_SAVE_FLOWBUSINESS);
		registMsgRecevier(EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS);
		int seq=ProtocalManager.getInstance().getFeiYongDetail(entity);
		mReqList.add(seq);
		showLoading();
	}
	
	private void initExtras(){
		Intent intent=getIntent();
		type=intent.getIntExtra(IntentUtils.KEY_TYPE, 0);
		entity=(PDaiBanEntity) intent.getSerializableExtra(IntentUtils.KEY_ENTITY);
	}
	private void initLayout() {
		// TODO Auto-generated method stub
		mList=new ArrayList<VJieShouRenEntity>();
//		showToast("type:"+type+"  entity:"+entity.LCID);
		mHeader=(HeaderDetailView) this.findViewById(R.id.header_detail);
		mHeader.setData(entity);
		mHeader.setClickListener(new IHeaderClickListener() {
			
			@Override
			public void backClick() {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		this.mMsgPage=(MsgPage) this.findViewById(R.id.mMsgPage_detail);
		mMsgPage.setEnablePullDown(false);
//		mMsgPage.setRefreshListener(mRefreshListener);
		if(this.type==Common.TYPE_DAIBAN){
			mBottomView=new ListBottomView(this);
			mMsgPage.addFooterView(mBottomView);
			mBottomView.setClickListener(new IDaiBanClickListener() {
				
				@Override
				public void typeClickListener() {
					// TODO Auto-generated method stub
					String noTag=rsp.mEntity.htInfo.get(0).nodeTag;
					IntentUtils.starTiJiaoActivity(FeiYongDetailActivity.this, GET_TYPE,noTag);
				}
				
				@Override
				public void nextClickListener() {
					// TODO Auto-generated method stub
					MyLog.debug(TAG, "[nextClickListener]  mList:"+mList.size());
					if("同意".equals(TJtype)||"退回".equals(TJtype)){
						IntentUtils.starJieShouRenActivity(FeiYongDetailActivity.this, mList, GET_JIESHOUREN);
					}else{
						showToast("请先选择提交方式！");
					}
				}
				
				@Override
				public void btnClickListener() {
					// TODO Auto-generated method stub
					String BLUserID="";
					String opinion=mBottomView.getOpinion();
					if("同意".equals(TJtype)){
						if(!TextUtils.isEmpty(mBLUserID)){
							BLUserID=mList.get(0).TJInfoEntity.nodeID+"$$"+mBLUserID;
							try {
								opinion = URLEncoder.encode(opinion, "GBK");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int seq=ProtocalManager.getInstance().SaveFlowBusiness(entity, BLUserID, opinion);
							mReqList.add(seq);
							showLoading();
						}else{
							showToast("请选择下一环节");
						}
					}else{
						if(!TextUtils.isEmpty(mBLUserID)){
							BLUserID=mList.get(0).HTInfoEntity.nodeID+"$$"+mBLUserID;
							try {
								opinion = URLEncoder.encode(opinion, "GBK");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int seq=ProtocalManager.getInstance().SaveReturnFlowBusiness(entity, BLUserID, opinion);
							mReqList.add(seq);
							showLoading();
						}else{
							showToast("请选择下一环节");
						}
					}
				}

				@Override
				public void noNextListener() {
					// TODO Auto-generated method stub
					showToast("请选择下一环节");
				}
			});
		}
		
		JZADScoreTextView img_banjie=(JZADScoreTextView) findViewById(R.id.img_banjie);
		if("已办结".equals(entity.JianKong)){
			img_banjie.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if(eventId==EventCommon.EVENT_FEIYONG_DETAIL){
			if(obj instanceof RspFeiYongDetailEntity){
				RspFeiYongDetailEntity rsp=(RspFeiYongDetailEntity) obj;
				Message msg=Message.obtain();
				msg.obj=rsp;
				msg.what=FLAG_SET_TOP;
				sendMsg(msg);
			}
		}
		 if(eventId==EventCommon.EVENT_BANLI_YIJIAN){
			if(obj instanceof RspBanLiYiJianEntity){
				RspBanLiYiJianEntity rsp=(RspBanLiYiJianEntity) obj;
				Message msg=Message.obtain();
				msg.obj=rsp;
				msg.what=FLAG_SET_LIST;
				sendMsg(msg);
			}
		}
		 if(eventId==EventCommon.EVENT_SAVE_FLOWBUSINESS){
			 if(obj instanceof RspSaveFlowBusinessEntity){
				 RspSaveFlowBusinessEntity rsp=(RspSaveFlowBusinessEntity) obj;
				 Message msg=Message.obtain();
					msg.obj=rsp;
					msg.what=FLAG_TJ;
					sendMsg(msg);
			 }
		 }
		 if(eventId==EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS){
			 if(obj instanceof RspSaveReturnFlowBusinessEntity){
				 RspSaveReturnFlowBusinessEntity rsp=(RspSaveReturnFlowBusinessEntity) obj;
				 Message msg=Message.obtain();
					msg.obj=rsp;
					msg.what=FLAG_HT;
					sendMsg(msg);
			 }
		 }
	}
	
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		int what=msg.what;
		switch (what) {
		case FLAG_SET_TOP:
			 rsp=(RspFeiYongDetailEntity)msg. obj;
			if(rsp!=null&&rsp.isSucc){
//				List<PShenPiInfoEntity> mList=rsp.mEntity.BXMXInfo;
				ArrayList<PDetailBXInfoEntity> bxInfo=rsp.mEntity.bxInfo;
				if(bxInfo!=null&&bxInfo.size()>0)
				mHeader.setItem(bxInfo.get(0).BXMXZhuDaoBuMen);
				View view=getHeaderView(bxInfo);
				mMsgPage.addHeaderView(view);
				int seq1=ProtocalManager.getInstance().getBanLiYiJian(entity.SLID);
				mReqList.add(seq1);
			}
			break;
		case FLAG_SET_LIST:
			hideLoadingDialog();
			RspBanLiYiJianEntity rsp1=(RspBanLiYiJianEntity) msg.obj;
			if(rsp1!=null&&rsp1.isSucc){
				ArrayList<PBanLiYiJianEntity> mList=rsp1.mEntity.banLiYiJian;
				MyLog.debug(TAG, "[handleMsg]  mList :"+mList.size());
				if(mAdapter==null){
					mAdapter=new BanLiYiJianAdapter(rsp1.mEntity.banLiYiJian);
					mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapter);
				}else{
					mAdapter.reSetList(rsp1.mEntity.banLiYiJian);
				}
			}
			mMsgPage.completeRefresh(rsp1.isSucc);
			break;
			case FLAG_TJ:
				hideLoadingDialog();
				RspSaveFlowBusinessEntity rsp=(RspSaveFlowBusinessEntity) msg.obj;
				if(rsp!=null&&rsp.isSucc){
					showToast("请求成功！");
					IntentUtils.startMainActivity(this);
					finish();
				}else{
					showToast("请求失败！");
				}
				break;
			case FLAG_HT:
				hideLoadingDialog();
				RspSaveReturnFlowBusinessEntity rsp2=(RspSaveReturnFlowBusinessEntity) msg.obj;
				if(rsp2!=null&&rsp2.isSucc){
					showToast("请求成功！");
					IntentUtils.startFirstActivity(this);
					finish();
				}else{
					showToast("请求失败！");
				}
				break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode!=Activity.RESULT_OK){
			return;
		}
		if(requestCode==GET_TYPE){
			TJtype=data.getStringExtra("type");
			mBottomView.setType(TJtype);
			if("同意".equals(TJtype)){
				mList=ParseUtil.getJieShouRen1(rsp.mEntity.tjInfo);
			}else{
				mList=ParseUtil.getJieShouRen(rsp.mEntity.htInfo);
			}
			MyLog.debug(TAG, "[onActivityResult]  mList:"+mList.size());
			mBLUserName="";
			mBLUserID="";
			mBottomView.setname(mBLUserName);
//			showToast(TJtype);
		}
		if(requestCode==GET_JIESHOUREN){
			mBLUserName=data.getStringExtra("name");
			mBLUserID=data.getStringExtra("id");
			mBottomView.setname(mBLUserName);
//			showToast(mBLUserID);
		}
	}
	
	private View getHeaderView(ArrayList<PDetailBXInfoEntity> bxInfo){
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		if(bxInfo!=null&&bxInfo.size()>0){
			ListTopItemView itemView = new ListTopItemView(this);
			ArrayList<String> keys = new ArrayList<>();
			keys.add("单据编号");
			keys.add("费用承担方");
			keys.add("备注");
			ArrayList<String> values = new ArrayList<>();
			values.add(bxInfo.get(0).LiuShuiHao);
			values.add(bxInfo.get(0).BXFeiYongChengDanFang);
			values.add(bxInfo.get(0).BXBeiZhu);
			itemView.setData(keys,values);
			layout.addView(itemView);
			for(int i=0;i<bxInfo.size();i++){
				ArrayList<String> mList=new ArrayList<String>();
				ListTopItemView topView=new ListTopItemView(this);
				mList.add(bxInfo.get(i).BXMXYiJiLeiXing);
				mList.add(bxInfo.get(i).BXMXErJiLeiXing);
				mList.add(bxInfo.get(i).BXMXFeiYongRiQi.split(" ")[0]);
				mList.add(bxInfo.get(i).BXMXXiangMuHao);
				mList.add(bxInfo.get(i).BXMXXiangMuMingCheng);
				mList.add(bxInfo.get(i).BXMXZhuDaoBuMen);
				mList.add(bxInfo.get(i).BXMXJinE);
				mList.add(bxInfo.get(i).BXMXMiaoShu);
				topView.setData(entity.LCID, mList);
//				if(i==bxInfo.size()-1){
//					topView.showBottom();
//				}
				layout.addView(topView);
			}
			ArrayList<PFuJianEntity> fujian=bxInfo.get(0).FuJian;
			if(fujian!=null&&fujian.size()>0){
				for(int i=0;i<fujian.size();i++){
					ListOpenWordView openView=new ListOpenWordView(this);
					openView.setData(fujian.get(i));
					openView.setOpenListener(mListener);
					layout.addView(openView);
				}
			}
			DetailShenPiYiJianTitleView view=new DetailShenPiYiJianTitleView(FeiYongDetailActivity.this);
			layout.addView(view);
	}
		return layout;
	}
private IWordOpenListener mListener=new IWordOpenListener() {
		
		@Override
		public void openListener(final String fileName,String path) {
			// TODO Auto-generated method stub
			filename=path;
			filename.replaceAll("\\\\", "/");
			// TODO Auto-generated method stub
			 try {  
		         file=new File(savePath,fileName);
		            if(!file.exists()){  
		            	file.getParentFile().mkdirs();
		            	file.createNewFile();
		            }
		        } catch (Exception e){  
		            e.printStackTrace();  
		        }  
			 showLoading("文件正在下载中，请稍后。。。");
			 new Thread() {
					public void run() {
						String str="";
						String name="";
						try {
							 name=URLEncoder.encode(FeiYongDetailActivity.this.filename, "utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String url="http://60.208.75.182:9080/file/"+name;
						MyLog.debug(TAG, "[IWordOpenListener]  url:"+url);
						boolean is=FileUtil.saveFileFromURL(url, file);
						Message message = new Message();
						if(is){
							message.what=what;
							message.obj = fileName;
						}
						mHandler.sendMessage(message);
					};
				}.start();
		}
	};
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(new File(savePath+"/"+filename).exists()){
			boolean is=FileUtil.deleteFile(savePath+"/"+filename);
		}
	};
}
