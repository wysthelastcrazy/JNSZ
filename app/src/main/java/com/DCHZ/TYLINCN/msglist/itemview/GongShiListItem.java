package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PGongShiItemEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;

import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class GongShiListItem extends BaseItemView<PGongShiItemEntity> implements
		OnClickListener, OnCheckedChangeListener {

	private CheckBox cb;
	private TextView item_time;
	private TextView item_type;
	private TextView item_bumen;
	private TextView item_date;
	private TextView item_name;
	private PGongShiItemEntity mEntity;
	private IGongShiShenPiClickListener mListener;

	public GongShiListItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMsg(PGongShiItemEntity t) {
		// TODO Auto-generated method stub
		mEntity = t;
		cb.setChecked(isSelected);
		
		item_name.setText(t.YHName);
		item_type.setText(t.ProjectName);
		item_bumen.setText(t.GSRenWu);
		item_time.setText(t.GSTBZhengchang+"h");
		String date=t.GSDate;
		String[] dates=date.split(" ");
		item_date.setTag(dates[0]);
	}

	@Override
	public PGongShiItemEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}

	@Override
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View view = li.inflate(com.DCHZ.TYLINCN.R.layout.check_list_item, this,
				true);
		view.setOnClickListener(this);

		item_name = (TextView) view.findViewById(R.id.item_name);
		item_time = (TextView) view.findViewById(R.id.item_time);
		item_type = (TextView) view.findViewById(R.id.item_type);
		item_bumen = (TextView) view.findViewById(R.id.item_bumen);
		item_date = (TextView) view.findViewById(R.id.item_date);
		cb = (CheckBox) view.findViewById(R.id.item_cb);
		cb.setOnCheckedChangeListener(this);
	}

	public void setListener(IGongShiShenPiClickListener mListener) {
		this.mListener = mListener;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if(isSelected){
				mListener.cancelSelectListener(pos);
			}else{
				mListener.selectedListener(pos);
			}
			cb.setChecked(!isSelected);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if (isChecked) {
				isSelected = true;
				mListener.selectedListener(pos);
			} else {
				isSelected = false;
				mListener.cancelSelectListener(pos);
			}
		}
	}

}
