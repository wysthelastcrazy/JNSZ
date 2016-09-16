package com.DCHZ.TYLINCN.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.BanLiYiJianAdapter;
import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.component.HeaderDetailView;
import com.DCHZ.TYLINCN.component.JZADScoreTextView;
import com.DCHZ.TYLINCN.component.ListBottomView;
import com.DCHZ.TYLINCN.component.ListTopItemView;
import com.DCHZ.TYLINCN.entity.PBanLiYiJianEntity;
import com.DCHZ.TYLINCN.entity.PChengBaoEntity;
import com.DCHZ.TYLINCN.entity.PChengBaoFeiYongItemEntity;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.entity.PTouBiaoFeiYongEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspBanLiYiJianEntity;
import com.DCHZ.TYLINCN.http.rsp.RspChengBaoFeiYongDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSaveReturnFlowBusinessEntity;
import com.DCHZ.TYLINCN.http.rsp.RspTouBiaoFeiYongDetailEntity;
import com.DCHZ.TYLINCN.listener.IDaiBanClickListener;
import com.DCHZ.TYLINCN.listener.IHeaderClickListener;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.util.IntentUtils;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.ParseUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class ChengBaoFeiDetailActivity extends BaseNormalActivity {
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
    private RspChengBaoFeiYongDetailEntity rsp;
    private ListBottomView mBottomView;
    private String mBLUserID;
    private String mBLUserName;
    //	private List<String> mStrList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initExtras();
        initLayout();
        registMsgRecevier(EventCommon.EVENT_CHENGBAOFEIYONG);
        registMsgRecevier(EventCommon.EVENT_BANLI_YIJIAN);
        registMsgRecevier(EventCommon.EVENT_SAVE_FLOWBUSINESS);
        registMsgRecevier(EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS);
        int seq= ProtocalManager.getInstance().getTChengBaoeiYongDetail(entity);
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
//		mHeader.setData(entity);
        mHeader.initKey(entity.LCID);
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
        if(this.type== Common.TYPE_DAIBAN){
            mBottomView=new ListBottomView(this);
            mMsgPage.addFooterView(mBottomView);
            mBottomView.setClickListener(new IDaiBanClickListener() {

                @Override
                public void typeClickListener() {
                    // TODO Auto-generated method stub
                    String noTag=rsp.mEntity.htInfo.get(0).nodeTag;
                    IntentUtils.starTiJiaoActivity(ChengBaoFeiDetailActivity.this, GET_TYPE,noTag);
                }

                @Override
                public void nextClickListener() {
                    // TODO Auto-generated method stub
                    MyLog.debug(TAG, "[nextClickListener]  mList:" + mList.size());
                    if("同意".equals(TJtype)||"退回".equals(TJtype)){
                        IntentUtils.starJieShouRenActivity(ChengBaoFeiDetailActivity.this, mList, GET_JIESHOUREN);
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
        if(eventId==EventCommon.EVENT_CHENGBAOFEIYONG){
            if(obj instanceof RspChengBaoFeiYongDetailEntity){
                RspChengBaoFeiYongDetailEntity rsp=(RspChengBaoFeiYongDetailEntity) obj;
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
                rsp=(RspChengBaoFeiYongDetailEntity)msg. obj;
                if(rsp!=null&&rsp.isSucc){
//				List<PShenPiInfoEntity> mList=rsp.mEntity.BXMXInfo;
                    ArrayList<PChengBaoEntity> bxInfo=rsp.mEntity.ChengBaoFeiTiQuInfo;
                    if(bxInfo!=null&&bxInfo.size()>0)
//				mHeader.setItem(bxInfo.get(0).BXMXZhuDaoBuMen);
                        mHeader.setValue(bxInfo.get(0).uname, bxInfo.get(0).BMName, bxInfo.get(0).QKTianBiaoRiQi.split(" ")[0], "");
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
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==GET_TYPE){
            TJtype=data.getStringExtra("type");
            mBottomView.setType(TJtype);
            if("同意".equals(TJtype)){
                mList= ParseUtil.getJieShouRen1(rsp.mEntity.tjInfo);
            }else{
                mList=ParseUtil.getJieShouRen(rsp.mEntity.htInfo);
            }
            MyLog.debug(TAG, "[onActivityResult]  mList:"+mList.size());
//			showToast(TJtype);
        }
        if(requestCode==GET_JIESHOUREN){
            mBLUserName=data.getStringExtra("name");
            mBLUserID=data.getStringExtra("id");
            mBottomView.setname(mBLUserName);
//			showToast(mBLUserID);
        }
    }

    private View getHeaderView(ArrayList<PChengBaoEntity> bxInfo){
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        if(bxInfo!=null&&bxInfo.size()>0){
            ArrayList<PChengBaoFeiYongItemEntity> itemInfo=bxInfo.get(0).ChengBaoFeiTiQuMXInfo;
            for(int i=0;i<itemInfo.size();i++){
                ArrayList<String> mList=new ArrayList<String>();
                ListTopItemView topView=new ListTopItemView(this);
                mList.add(itemInfo.get(i).XMBianHao);
                mList.add(itemInfo.get(i).XMMingCheng);
                mList.add(itemInfo.get(i).ShouRuJinE);
                mList.add(itemInfo.get(i).DiJianJinE);
                mList.add(itemInfo.get(i).BenCiShouRu);
                mList.add(itemInfo.get(i).CWPingZhengHao);
                mList.add(itemInfo.get(i).TiQuBiLi);
                mList.add(itemInfo.get(i).BenCiJingJiao);
                mList.add(itemInfo.get(i).BenCiYingTi);
                mList.add(itemInfo.get(i).QianYanJingFei);
                mList.add(itemInfo.get(i).GuDingZiChan);
                mList.add(itemInfo.get(i).ShenTuFei);
                mList.add(itemInfo.get(i).PeiXunFei);
                mList.add(itemInfo.get(i).ShuiFei);
                mList.add(itemInfo.get(i).BenCiShiTi);
                topView.setData(entity.LCID, mList);
                layout.addView(topView);
            }
            ListTopItemView top=new ListTopItemView(this);
            ArrayList<String> keys=new ArrayList<String>();
            ArrayList<String> values=new ArrayList<String>();
            values.add(bxInfo.get(0).LeiJiShouRu);
            values.add(bxInfo.get(0).LeiJiJinShangJiao);
            values.add(bxInfo.get(0).ZhiBaoJin);
            values.add(bxInfo.get(0).LeiJiShenTuFei);
            values.add(bxInfo.get(0).GuDingZiChan);
            values.add(bxInfo.get(0).DanTuFei);
            values.add(bxInfo.get(0).QianYanJingFei);
            values.add(bxInfo.get(0).LeiJiYingTi);
            values.add(bxInfo.get(0).ShiJiTiQuE);
            values.add(bxInfo.get(0).BeiZhu);
            keys.add("累计收入");
            keys.add("累计净上交");
            keys.add("质保金");
            keys.add("累计审图费");
            keys.add("固定资产");
            keys.add("打图费");
            keys.add("前研经费");
            keys.add("累计应提承包费");
            keys.add("实际提取额");
            keys.add("备注");
            top.setData(keys,values);
            layout.addView(top);

        }
        return layout;
    }
}
