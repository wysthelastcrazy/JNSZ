package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;

import com.DCHZ.TYLINCN.entity.PXiangMuSearchItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;

/**
 * Created by Administrator on 2016/10/6.
 */
public class SearchXiangMuItemView extends BaseItemView<PXiangMuSearchItemEntity>{
    public PXiangMuSearchItemEntity mEntity;
    public SearchXiangMuItemView(Context context) {
        super(context);
    }

    @Override
    public void setMsg(PXiangMuSearchItemEntity pXiangMuSearchItemEntity) {

    }

    @Override
    public PXiangMuSearchItemEntity getMsg() {
        return mEntity;
    }

    @Override
    public void onInflate() {

    }
}
