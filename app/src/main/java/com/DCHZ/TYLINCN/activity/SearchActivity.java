package com.DCHZ.TYLINCN.activity;

import android.os.Bundle;
import android.view.View;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.component.MyInfoItemView;
import com.DCHZ.TYLINCN.util.IntentUtils;

/**
 * Created by yas on 2016/9/10.
 */
public class SearchActivity extends BaseNormalActivity implements View.OnClickListener {
    private MyInfoItemView itemView01;
    private MyInfoItemView itemView02;
    private MyInfoItemView itemView03;
    private MyInfoItemView itemView04;
    private MyInfoItemView itemView05;
    private MyInfoItemView itemView06;
    private MyInfoItemView itemView07;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initLayout();
    }

    private void initLayout() {
        itemView01= (MyInfoItemView) findViewById(R.id.item01);
        itemView01.updateType(MyInfoItemView.TYPE_XIANMU);
        itemView01.setOnClickListener(this);
        itemView02= (MyInfoItemView) findViewById(R.id.item02);
        itemView02.updateType(MyInfoItemView.TYPE_HETONG);
        itemView02.setOnClickListener(this);
        itemView03= (MyInfoItemView) findViewById(R.id.item03);
        itemView03.updateType(MyInfoItemView.TYPE_SHOUKUAN);
        itemView03.setOnClickListener(this);
        itemView04= (MyInfoItemView) findViewById(R.id.item04);
        itemView04.updateType(MyInfoItemView.TYPE_KEHU);
        itemView04.setOnClickListener(this);
        itemView05= (MyInfoItemView) findViewById(R.id.item05);
        itemView05.updateType(MyInfoItemView.TYPE_YUANGONG);
        itemView05.setOnClickListener(this);
        itemView06= (MyInfoItemView) findViewById(R.id.item06);
        itemView06.updateType(MyInfoItemView.TYPE_FAPAIO);
        itemView06.setOnClickListener(this);
        itemView07= (MyInfoItemView) findViewById(R.id.item07);
        itemView07.updateType(MyInfoItemView.TYPE_XIANMU_BEIAN);
        itemView07.setOnClickListener(this);
    }

    @Override
    public void handleReceiveMsg(int eventId, int seqNo, Object obj) {

    }

    @Override
    public void onClick(View v) {
        if (v==itemView01){
            IntentUtils.startSearchXiangMuActivity(SearchActivity.this);
        }else if (v==itemView02){
            showToast("item02");
        }else if (v==itemView03){
            showToast("item03");
        }else if (v==itemView04){
            showToast("item04");
        }else if (v==itemView05){
            showToast("item05");
        }else if (v==itemView06){
            showToast("item06");
        }else if (v==itemView07){
            showToast("item07");
        }
    }
}
