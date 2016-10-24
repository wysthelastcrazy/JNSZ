package com.DCHZ.TYLINCN.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.component.ListTopItemView;
import com.DCHZ.TYLINCN.entity.PXiangMuSearchItemEntity;
import com.DCHZ.TYLINCN.util.IntentUtils;

import java.util.ArrayList;

/**
 * Created by yas on 2016/10/11.
 */
public class SearchXiangMuDetailActivity extends BaseNormalActivity implements View.OnClickListener {
    private TextView txtBack;
    private ListTopItemView mTopView;
    private PXiangMuSearchItemEntity mEntity;
    private ArrayList<String> keyList=new ArrayList<String>();
    private ArrayList<String> valList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_xiangmu_detail);
        initExtra();
        initLayout();
    }

    private void initExtra() {
        mEntity= (PXiangMuSearchItemEntity) getIntent().getSerializableExtra(IntentUtils.KEY_ENTITY);
    }

    private void initLayout() {
        txtBack= (TextView) this.findViewById(R.id.text_back);
        txtBack.setOnClickListener(this);
        mTopView= (ListTopItemView) this.findViewById(R.id.topView);
        //项目名称，项目编号，客户名称，主导部门，项目经理，立项日期，配合部门
        keyList.add("项目名称");
        keyList.add("项目编号");
        keyList.add("客户名称");
        keyList.add("承担部门");
        keyList.add("项目负责人");
        keyList.add("立项日期");
        keyList.add("配合部门");
        if (mEntity!=null){
            valList.add(mEntity.XMMingCheng);
            valList.add(mEntity.XMBianHao);
            valList.add(mEntity.KHMingCheng);
            valList.add(mEntity.ZhuDaoBuMen);
            valList.add(mEntity.XMJingLi);
            valList.add(mEntity.XMLiXiangRiQi.split(" ")[0]);
            valList.add(mEntity.PeiHeBuMen);
        }
        mTopView.setData(keyList,valList);
    }

    @Override
    public void handleReceiveMsg(int eventId, int seqNo, Object obj) {

    }

    @Override
    public void onClick(View view) {
        if (view==txtBack){
            finish();
        }
    }
}
