package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PBanLiYiJianEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.BanLiItemView;
import com.DCHZ.TYLINCN.util.MyLog;

public class BanLiYiJianAdapter extends BaseListAdapter<PBanLiYiJianEntity>{

	public BanLiYiJianAdapter(ArrayList<PBanLiYiJianEntity> mList) {
		super(mList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseItemView<PBanLiYiJianEntity> getItemView(PBanLiYiJianEntity t) {
		// TODO Auto-generated method stub
		BanLiItemView itemVIew =new BanLiItemView(Global.getContext());
		return itemVIew;
	}

}
