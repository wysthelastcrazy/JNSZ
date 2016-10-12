package com.DCHZ.TYLINCN.adapter;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PHeTongSearchItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.SearchHeTongItemView;

import java.util.ArrayList;

/**
 * Created by yas on 2016/10/12.
 */
public class SearchHeTongAdapter extends BaseListAdapter<PHeTongSearchItemEntity>{
    public SearchHeTongAdapter(ArrayList<PHeTongSearchItemEntity> mList) {
        super(mList);
    }

    @Override
    public BaseItemView<PHeTongSearchItemEntity> getItemView(PHeTongSearchItemEntity pHeTongSearchItemEntity) {
        SearchHeTongItemView itemView=new SearchHeTongItemView(Global.getContext());
        return itemView;
    }
}
