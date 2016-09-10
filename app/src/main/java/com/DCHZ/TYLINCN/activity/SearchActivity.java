package com.DCHZ.TYLINCN.activity;

import android.os.Bundle;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;

/**
 * Created by yas on 2016/9/10.
 */
public class SearchActivity extends BaseNormalActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void handleReceiveMsg(int eventId, int seqNo, Object obj) {

    }
}
