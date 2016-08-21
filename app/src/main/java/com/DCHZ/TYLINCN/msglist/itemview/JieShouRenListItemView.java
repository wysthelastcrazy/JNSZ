package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.component.ListOpenWordView;
import com.DCHZ.TYLINCN.entity.VJieShouRenListEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.util.MyLog;

public class JieShouRenListItemView extends BaseItemView<VJieShouRenListEntity> implements OnCheckedChangeListener{

	private VJieShouRenListEntity mEntity;
	private TextView text_name;
	private CheckBox cb;
	private IGongShiShenPiClickListener mListener;
	public JieShouRenListItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMsg(VJieShouRenListEntity t) {
		// TODO Auto-generated method stub
		mEntity=t;
		text_name.setText(t.name);
		cb.setChecked(isSelected);
		MyLog.debug(TAG, "[setMsg]  pos:"+pos+"   isSelected:"+isSelected);
	}

	@Override
	public VJieShouRenListEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}

	@Override
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	   View view=li.inflate(com.DCHZ.TYLINCN.R.layout.jieshouren_list_item, this,
				true);
	   view.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(mListener!=null){
				isSelected=!isSelected;
				cb.setChecked(isSelected);
//				if(isSelected){
//					mListener.selectedListener(pos);
//				}else{
//					mListener.cancelSelectListener(pos);
//				}
			}
		}
	});
	   text_name=(TextView) view.findViewById(R.id.text_name);
	   cb = (CheckBox) view.findViewById(R.id.item_cb);
	   cb.setOnCheckedChangeListener(this);
	}
	
	public void setListener(IGongShiShenPiClickListener mListener) {
		this.mListener = mListener;
	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if (isChecked) {
				isSelected = true;
				mListener.selectedListener(pos);
//				MyLog.debug(TAG, "[onCheckedChanged][selectedListener]  pos:"+pos);
//				cb.setChecked(isSelected);
			} else {
				isSelected = false;
				mListener.cancelSelectListener(pos);
//				cb.setChecked(isSelected);
//				MyLog.debug(TAG, "[onCheckedChanged][cancelSelectListener]  pos:"+pos);
			}
		}
	}

}
