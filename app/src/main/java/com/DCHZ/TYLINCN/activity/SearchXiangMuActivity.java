package com.DCHZ.TYLINCN.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.SearchXiangMuAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.HeaderSearchView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.entity.PXiangMuSearchItemEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspXiangMuSearchEntity;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.IntentUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/6.
 * 项目搜索列表页
 */
public class SearchXiangMuActivity extends BaseNormalActivity implements View.OnClickListener {
    private TextView txtBack;
    private MsgPage mMsgPage;
    private SearchXiangMuAdapter mAdapter;
    private ArrayList<Integer> mReqList=new ArrayList<Integer>();
    private int page=1;
    private static final int FLAG_SHOW=0x100;
    private boolean hasNext=true;
    private boolean isRefresh;
    private HeaderSearchView mSearchView;
    private String strWhere="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_xiangmu);
        initLayout();
        registMsgRecevier(EventCommon.EVENT_XIANGMU_SEARCH);
        int seq=ProtocalManager.getInstance().reqXiangMuSearchList(page,strWhere);
        mReqList.add(seq);
        showLoading();
    }

    private void initLayout() {
        txtBack= (TextView) this.findViewById(R.id.text_back);
        txtBack.setOnClickListener(this);
        mMsgPage= (MsgPage) this.findViewById(R.id.mMsgPage);
        mMsgPage.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (mAdapter != null && mAdapter.getCount() > pos) {
                    PXiangMuSearchItemEntity entity = (PXiangMuSearchItemEntity) mAdapter.getItem(pos);
                    IntentUtils.startSearchXiangMuDetailActivity(SearchXiangMuActivity.this, entity);
                }
            }
        });
        this.mMsgPage.setRefreshListener(mRefreshListener);
        mSearchView= (HeaderSearchView) this.findViewById(R.id.header_search);
        mSearchView.setHint("项目名称/项目负责人/承担部门/客户名称");
        mSearchView.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                strWhere = editable.toString();
                int seq = ProtocalManager.getInstance().reqXiangMuSearchList(refreshPage(), strWhere);
                mReqList.add(seq);
            }
        });
    }

    // mMsgpage的监听事件，包括下拉刷新和点击加载更多
    private IRefreshListener mRefreshListener = new IRefreshListener() {
        @Override
        public void reachListViewBottom() {
            if (hasNext){
                int seq=ProtocalManager.getInstance().reqXiangMuSearchList(nextPage(),strWhere);
                mReqList.add(seq);
            }
        }
        @Override
        public void onRefresh(NLPullRefreshView view) {
            isRefresh=true;
            mAdapter=null;
            int seq=ProtocalManager.getInstance().reqXiangMuSearchList(refreshPage(),strWhere);
            mReqList.add(seq);
        }
    };
    @Override
    public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
        if (eventId==EventCommon.EVENT_XIANGMU_SEARCH){
            if (mReqList.contains(seqNo)){
                if (obj instanceof RspXiangMuSearchEntity){
                    Message msg = Message.obtain();
                    msg.what = FLAG_SHOW;
                    msg.obj = obj;
                    sendMsg(msg);
                }
            }
        }
    }

    @Override
    protected void handleMsg(Message msg) {
        int what=msg.what;
        switch (what){
            case FLAG_SHOW:
                hideLoadingDialog();
                RspXiangMuSearchEntity rsp= (RspXiangMuSearchEntity) msg.obj;
                if (isRefresh){
                    isRefresh=false;
                    mMsgPage.completeRefresh(rsp.isSucc);
                }
                if (rsp.isSucc){
                    if (mAdapter==null){
                        mAdapter=new SearchXiangMuAdapter(rsp.mEntity.XMInfo);
                        mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
                        mMsgPage.setListAdapter(mAdapter);
                    }else{
                        if (page==1){
                            mAdapter.reSetList(rsp.mEntity.XMInfo);
                        }else{
                            mAdapter.appendList(rsp.mEntity.XMInfo);
                        }
                    }
                    if (rsp.mEntity.XMInfo.size()< MConfiger.PAGE_SIZE){
                        hasNext=false;
                    }
                }else{
                    String str = "网络异常！";
                    showToast(str);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view==txtBack){
            finish();
        }
    }
    private int nextPage() {
        page=page+1;
        return page;
    }

    private int refreshPage() {
        page=1;
        return page;
    }
}
