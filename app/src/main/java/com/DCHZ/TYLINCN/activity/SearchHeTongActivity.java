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
import com.DCHZ.TYLINCN.adapter.SearchHeTongAdapter;
import com.DCHZ.TYLINCN.adapter.SearchXiangMuAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.HeaderSearchView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.entity.PHeTongSearchItemEntity;
import com.DCHZ.TYLINCN.entity.PXiangMuSearchItemEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspHeTongSearchEntity;
import com.DCHZ.TYLINCN.http.rsp.RspXiangMuSearchEntity;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.IntentUtils;

import java.util.ArrayList;

/**
 * Created by yas on 2016/10/12.
 */
public class SearchHeTongActivity extends BaseNormalActivity implements View.OnClickListener {
    private TextView txtBack;
    private MsgPage mMsgPage;
    private HeaderSearchView mSearchView;
    private ArrayList<Integer> mReqList=new ArrayList<Integer>();
    private int page=1;
    private static final int FLAG_SHOW=0x100;
    private boolean hasNext=true;
    private boolean isRefresh;
    private SearchHeTongAdapter mAdapter;
    private String strWhere="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hetong);
        registMsgRecevier(EventCommon.EVENT_HETONG_SEARCH);
        int seq=ProtocalManager.getInstance().reqHeTongSearchList(page, strWhere);
        mReqList.add(seq);
        initLayout();
    }

    private void initLayout() {
        txtBack= (TextView) this.findViewById(R.id.text_back);
        txtBack.setOnClickListener(this);
        mMsgPage= (MsgPage) this.findViewById(R.id.mMsgPage);
        mMsgPage.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (mAdapter!=null&&mAdapter.getCount()>pos){
                    PHeTongSearchItemEntity entity= (PHeTongSearchItemEntity) mAdapter.getItem(pos);
                    IntentUtils.startSearchHeTongDetailActivity(SearchHeTongActivity.this,entity);
                }
            }
        });
        this.mMsgPage.setRefreshListener(mRefreshListener);
        this.mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);

        mSearchView= (HeaderSearchView) this.findViewById(R.id.header_search);
        mSearchView.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                strWhere=editable.toString();
                int seq=ProtocalManager.getInstance().reqHeTongSearchList(refreshPage(), strWhere);
                mReqList.add(seq);
            }
        });
    }
    // mMsgpage的监听事件，包括下拉刷新和点击加载更多
    private IRefreshListener mRefreshListener = new IRefreshListener() {
        @Override
        public void reachListViewBottom() {
            if (hasNext){
                int seq= ProtocalManager.getInstance().reqHeTongSearchList(nextPage(), strWhere);
                mReqList.add(seq);
            }
        }
        @Override
        public void onRefresh(NLPullRefreshView view) {
            isRefresh=true;
            int seq=ProtocalManager.getInstance().reqHeTongSearchList(refreshPage(), strWhere);
            mReqList.add(seq);
        }
    };
    @Override
    public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
        if (eventId==EventCommon.EVENT_HETONG_SEARCH){
            if (mReqList.contains(seqNo)){
                if (obj instanceof RspHeTongSearchEntity){
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
                RspHeTongSearchEntity rsp= (RspHeTongSearchEntity) msg.obj;
                if (isRefresh){
                    isRefresh=false;
                    mMsgPage.completeRefresh(rsp.isSucc);
                }
                if (rsp.isSucc){
                    if (mAdapter==null){
                        mAdapter=new SearchHeTongAdapter(rsp.mEntity.HTInfo);
                        mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
                        mMsgPage.setListAdapter(mAdapter);
                    }else{
                        if (page==1){
                            mAdapter.reSetList(rsp.mEntity.HTInfo);
                        }else{
                            mAdapter.appendList(rsp.mEntity.HTInfo);
                        }
                    }
                    if (rsp.mEntity.HTInfo.size()< MConfiger.PAGE_SIZE){
                        hasNext=false;
                    }
                }else{
                    String str = "网络异常！";
                    showToast(str);
                }
                break;
        }
    }
    private int nextPage() {
        return page+1;
    }

    private int refreshPage() {
        page=1;
        return page;
    }

    @Override
    public void onClick(View view) {
        if (view==txtBack){
            finish();
        }
    }
}
