package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PHeTongSearchItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;

/**
 * Created by yas on 2016/10/12.
 */
public class SearchHeTongItemView extends BaseItemView<PHeTongSearchItemEntity>{
    private TextView txtJinE;
    private TextView txtName;
    private TextView txtTime;
    private PHeTongSearchItemEntity mEntity;
    public SearchHeTongItemView(Context context) {
        super(context);
    }

    @Override
    public void setMsg(PHeTongSearchItemEntity pHeTongSearchItemEntity) {
        mEntity=pHeTongSearchItemEntity;
        txtJinE.setText(mEntity.HTJinE);
        txtName.setText(mEntity.HTMingCheng);
        txtTime.setText(mEntity.HTQianDingRiQi);
    }

    @Override
    public PHeTongSearchItemEntity getMsg() {
        return mEntity;
    }

    @Override
    public void onInflate() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.search_hetong_itemview,this,true);
        txtJinE= (TextView) this.findViewById(R.id.txtJinE);
        txtName= (TextView) this.findViewById(R.id.txtName);
        txtTime= (TextView) this.findViewById(R.id.txTime);
    }
}
