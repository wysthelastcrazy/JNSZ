package com.DCHZ.TYLINCN.msglist.itemview;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PBanLiYiJianEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.util.MyLog;
import com.common.util.DateUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

public class BanLiItemView extends BaseItemView<PBanLiYiJianEntity> {
	private TextView text_item_name;
	private TextView text_item_content;
	private TextView text_item_time;
	private TextView text_item_isOk;
	private PBanLiYiJianEntity mEntity;
	public BanLiItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setMsg(PBanLiYiJianEntity entity) {
		// TODO Auto-generated method stub
		mEntity=entity;
//		set name
		String name=entity.JDName;
		String[] str=name.split(":");
		text_item_name.setText(str[1]);
		text_item_content.setText(str[0]);
		
		//set content
		String content=entity.BLOpinion;
		
		//set time
		String time=entity.BLAcceptDate;
		time=DateUtil.DateToStr(DateUtil.StrToDate(time));
		text_item_time.setText(time);
		
		//set isOk
		text_item_isOk.setText(content);
	}
	@Override
	public PBanLiYiJianEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}
	@Override
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_item, this, true);
		this.text_item_name=(TextView) this.findViewById(R.id.text_item_name);
		this.text_item_content=(TextView) this.findViewById(R.id.text_item_content);
		this.text_item_time=(TextView) this.findViewById(R.id.text_item_time);
		this.text_item_isOk=(TextView) this.findViewById(R.id.text_item_isOk);
	}
}
