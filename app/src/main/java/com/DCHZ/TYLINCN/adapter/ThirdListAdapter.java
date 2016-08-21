package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.VThirdItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.ThirdListItemView;

public class ThirdListAdapter extends BaseListAdapter<VThirdItemEntity>{

	public ThirdListAdapter(ArrayList<VThirdItemEntity> mList) {
		super(mList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseItemView<VThirdItemEntity> getItemView(VThirdItemEntity t) {
		// TODO Auto-generated method stub
		ThirdListItemView itemVIew =new ThirdListItemView(Global.getContext());
		return itemVIew;
	}

}
