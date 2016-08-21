package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PJiDuHeTongItemEntity;
import com.DCHZ.TYLINCN.entity.PJiDuShouKuanItemEntity;
import com.DCHZ.TYLINCN.entity.VThirdItemEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;

public class ThirdListItemView extends BaseItemView<VThirdItemEntity> {
	private VThirdItemEntity mEntity;
	private TextView text_item1;
	private TextView text_item2;
	private TextView text_item3;
	private TextView text_item4;
	private RelativeLayout relat_Layout;
	public ThirdListItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMsg(VThirdItemEntity t) {
		// TODO Auto-generated method stub
		mEntity = t;
		if(t.type==VThirdItemEntity.TYPE_HeTong){
			text_item1.setText(t.mEntity.BMName);
	
			if ("0".equals(t.mEntity.NianDuHeTongYuSuan)||TextUtils.isEmpty(t.mEntity.NianDuHeTongYuSuan)) {
				text_item2.setText("-");
			} else {
				text_item2.setText(t.mEntity.NianDuHeTongYuSuan);
			}
			if ("0".equals(t.mEntity.NianDuHeTongShiJi)||TextUtils.isEmpty(t.mEntity.NianDuHeTongShiJi)) {
				text_item3.setText("-");
			} else {
				text_item3.setText(t.mEntity.NianDuHeTongShiJi);
			}
			if ("0%".equals(t.mEntity.WanChengBiLi)) {
				text_item4.setText("-");
			} else {
				text_item4.setText(t.mEntity.WanChengBiLi);
			}
			
			relat_Layout.setBackgroundResource(R.color.common_white);
			if ("总部小计".equals(t.mEntity.BMName)
					|| "子公司小计".equals(t.mEntity.BMName)
					|| "分公司小计".equals(t.mEntity.BMName)) {
				relat_Layout.setBackgroundResource(R.color.thirdList_bg);
			}
		}else{
			text_item1.setText(t.shouKuanEntity.BMName);
			
			if ("0".equals(t.shouKuanEntity.NianDuShouKuanYuSuan)||TextUtils.isEmpty(t.shouKuanEntity.NianDuShouKuanYuSuan)) {
				text_item2.setText("-");
			} else {
				text_item2.setText(t.shouKuanEntity.NianDuShouKuanYuSuan);
			}
			if ("0".equals(t.shouKuanEntity.NianDuShouKuanShiJi)||TextUtils.isEmpty(t.shouKuanEntity.NianDuShouKuanShiJi)) {
				text_item3.setText("-");
			} else {
				text_item3.setText(t.shouKuanEntity.NianDuShouKuanShiJi);
			}
			if ("0%".equals(t.shouKuanEntity.WanChengBiLi)) {
				text_item4.setText("-");
			} else {
				text_item4.setText(t.shouKuanEntity.WanChengBiLi);
			}
			
			relat_Layout.setBackgroundResource(R.color.common_white);
			if ("总部小计".equals(t.shouKuanEntity.BMName)
					|| "子公司小计".equals(t.shouKuanEntity.BMName)
					|| "分公司小计".equals(t.shouKuanEntity.BMName)) {
				relat_Layout.setBackgroundResource(R.color.thirdList_bg);
			}
		}
	}

	@Override
	public VThirdItemEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}

	@Override
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.third_listview_item, this, true);
		relat_Layout=(RelativeLayout) this.findViewById(R.id.relat_Layout);
		text_item1 = (TextView) this.findViewById(R.id.text_item1);
		text_item2 = (TextView) this.findViewById(R.id.text_item2);
		text_item3 = (TextView) this.findViewById(R.id.text_item3);
		text_item4 = (TextView) this.findViewById(R.id.text_item4);
	}

}
