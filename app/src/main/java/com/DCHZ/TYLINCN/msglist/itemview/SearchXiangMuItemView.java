package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PXiangMuSearchItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;

/**
 * Created by Administrator on 2016/10/6.
 */
public class SearchXiangMuItemView extends BaseItemView<PXiangMuSearchItemEntity>{
    private TextView txtName;
    public PXiangMuSearchItemEntity mEntity;
    public SearchXiangMuItemView(Context context) {
        super(context);
    }

    @Override
    public void setMsg(PXiangMuSearchItemEntity pXiangMuSearchItemEntity) {
        mEntity=pXiangMuSearchItemEntity;
        txtName.setText(mEntity.XMMingCheng);
    }

    @Override
    public PXiangMuSearchItemEntity getMsg() {
        return mEntity;
    }

    @Override
    public void onInflate() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.search_xiangmu_itemview, this, true);
        txtName= (TextView) this.findViewById(R.id.text_XMName);
    }
}
