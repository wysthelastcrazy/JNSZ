package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PGongShiItemEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.GongShiListItem;

public class GongShiShenPiAdapter extends BaseListAdapter<PGongShiItemEntity>{
	private IGongShiShenPiClickListener mListener;
	public GongShiShenPiAdapter(ArrayList<PGongShiItemEntity> mList) {
		super(mList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseItemView<PGongShiItemEntity> getItemView(PGongShiItemEntity t) {
		// TODO Auto-generated method stub
		GongShiListItem itemView=new GongShiListItem(Global.getContext());
		itemView.setListener(mListener);
		return itemView;
	}
	public void setLisntener(IGongShiShenPiClickListener mListener){
		this.mListener=mListener;
	}
}
