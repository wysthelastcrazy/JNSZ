package com.DCHZ.TYLINCN.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
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
import com.DCHZ.TYLINCN.entity.PDetailGaiZhangEntity;
import com.DCHZ.TYLINCN.entity.PFuJianEntity;
import com.DCHZ.TYLINCN.entity.PGongZhangEntity;
import com.DCHZ.TYLINCN.entity.PGuDingZiChanEntity;
import com.DCHZ.TYLINCN.entity.PGuDingZiChanItemEntity;
import com.DCHZ.TYLINCN.entity.VJieShouRenEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspBanLiYiJianEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGaiZhangEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongZhangJieChuDetailEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGuDingZiChanDetailEntity;
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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class GuDingZiChangDetail extends BaseNormalActivity {
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
    private RspGuDingZiChanDetailEntity rsp;
    private ListBottomView mBottomView;

    private String mBLUserName;
    private String mBLUserID;
    private String savePath= Environment.getExternalStorageDirectory().getAbsolutePath();
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
                            Intent intent= FileUtil.getWordFileIntent(savePath + "/" + fileName);
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
    //	private List<String> mStrList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initExtras();
        initLayout();
        registMsgRecevier(EventCommon.EVENT_GUDINGZICHAN_DETAIL);
        registMsgRecevier(EventCommon.EVENT_BANLI_YIJIAN);
        registMsgRecevier(EventCommon.EVENT_SAVE_FLOWBUSINESS);
        registMsgRecevier(EventCommon.EVENT_SAVE_RETURN_FLOWBUSINESS);
//		String LCID=entity.LCID;
//		String YWID=entity.YWID;
        int seq=ProtocalManager.getInstance().geGuDingZiChanDetail(entity);
        mReqList.add(seq);
//		String SLID=entity.SLID;
//		int seq1=ProtocalManager.getInstance().getBanLiYiJian(SLID);
//		mReqList.add(seq1);
        showLoading();
    }

    private void initExtras(){
        Intent intent=getIntent();
        type=intent.getIntExtra(IntentUtils.KEY_TYPE, 0);
        entity=(PDaiBanEntity) intent.getSerializableExtra(IntentUtils.KEY_ENTITY);
    }
    private void initLayout() {
        // TODO Auto-generated method stub

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
//		mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
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
                    IntentUtils.starTiJiaoActivity(GuDingZiChangDetail.this, GET_TYPE,noTag);
                }

                @Override
                public void nextClickListener() {
                    // TODO Auto-generated method stub
                    MyLog.debug(TAG, "[nextClickListener]  mList:"+mList.size());
                    IntentUtils.starJieShouRenActivity(GuDingZiChangDetail.this, mList, GET_JIESHOUREN);
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
        if(eventId==EventCommon.EVENT_GUDINGZICHAN_DETAIL){
            if(obj instanceof RspGuDingZiChanDetailEntity){
                RspGuDingZiChanDetailEntity rsp=(RspGuDingZiChanDetailEntity) obj;
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
                rsp=(RspGuDingZiChanDetailEntity)msg. obj;
                if(rsp!=null&&rsp.isSucc){
//				List<PShenPiInfoEntity> mList=rsp.mEntity.BXMXInfo;
                    ArrayList<PGuDingZiChanEntity> bxInfo=rsp.mEntity.GuDingZiChanInfo;
                    if(bxInfo!=null&&bxInfo.size()>0)
                        mHeader.setValue(bxInfo.get(0).ShenQingRenName, bxInfo.get(0).ShenQingBuMenName, bxInfo.get(0).ShenQingRiQi.split(" ")[0], bxInfo.get(0).GouZhiBuMenName);
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
//		MyLog.debug(TAG, "[onActivityResult] resultCode:"+resultCode);
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
//			showToast(mBLUserName);
        }
    }
    private View getHeaderView(ArrayList<PGuDingZiChanEntity> bxInfo){
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        if(bxInfo!=null&&bxInfo.size()>0){
            ListTopItemView top=new ListTopItemView(this);
            ArrayList<String> keys=new ArrayList<String>();
            ArrayList<String> values=new ArrayList<String>();
            values.add(bxInfo.get(0).LeiXing);
            values.add(bxInfo.get(0).GouZhiYuanYin);
            keys.add("资产类型");
            keys.add("购置原因");
            top.setData(keys, values);
            layout.addView(top);
           ArrayList<PGuDingZiChanItemEntity>  mInfo=bxInfo.get(0).GuDingZiChanMXInfo;
            for(int i=0;i<mInfo.size();i++){
                ArrayList<String> mList=new ArrayList<String>();
                ListTopItemView topView=new ListTopItemView(this);
                mList.add(mInfo.get(i).ZiChanMingCheng);
                mList.add(mInfo.get(i).ZiChanGuiGe);
                mList.add(mInfo.get(i).ZiChanShuLiang);
                mList.add(mInfo.get(i).ZiChanJinE);
                mList.add(mInfo.get(i).ZiChanShuoMing);
                topView.setData(entity.LCID, mList);
//                if(i==mInfo.size()-1){
//                    topView.showBottom();
//                }
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
            DetailShenPiYiJianTitleView view=new DetailShenPiYiJianTitleView(GuDingZiChangDetail.this);
            layout.addView(view);
        }
        return layout;
    }

    private IWordOpenListener mListener=new IWordOpenListener() {

        @Override
        public void openListener(final String fileName,final String path) {
            // TODO Auto-generated method stub
            filename=fileName;
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
                    String filePath="";
                    try {
                        str=URLEncoder.encode("合同文件", "utf-8");
                        name=URLEncoder.encode(fileName, "utf-8");
                        filePath=path.replaceAll("\\\\", "/");
                        filePath=URLEncoder.encode(filePath, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String url="http://60.208.75.182:9080/file/"+filePath;
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
