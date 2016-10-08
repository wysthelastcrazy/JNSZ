package com.DCHZ.TYLINCN.activity;

import android.os.Bundle;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;

/**
 * Created by Administrator on 2016/10/6.
 * 项目搜索列表页
 */
public class SearchXiangMuActivity extends BaseNormalActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_xiangmu);

    }

    @Override
    public void handleReceiveMsg(int eventId, int seqNo, Object obj) {

    }
}
