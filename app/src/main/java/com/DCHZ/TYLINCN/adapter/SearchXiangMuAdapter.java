package com.DCHZ.TYLINCN.adapter;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PXiangMuSearchItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.SearchXiangMuItemView;

import java.util.ArrayList;

/**
 * Created by yas on 2016/10/9.
 */
public class SearchXiangMuAdapter extends BaseListAdapter<PXiangMuSearchItemEntity>{
    public SearchXiangMuAdapter(ArrayList<PXiangMuSearchItemEntity> mList) {
        super(mList);
    }

    @Override
    public BaseItemView<PXiangMuSearchItemEntity> getItemView(PXiangMuSearchItemEntity pXiangMuSearchItemEntity) {
        SearchXiangMuItemView itemView=new SearchXiangMuItemView(Global.getContext());
        return itemView;
    }
}
