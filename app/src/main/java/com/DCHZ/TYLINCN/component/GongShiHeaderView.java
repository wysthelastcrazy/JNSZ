package com.DCHZ.TYLINCN.component;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Global;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class GongShiHeaderView extends RelativeLayout implements OnClickListener{
	private TextView header_title;
	private TextView text_right;
	private BtnClickListener mListener;
	private String data;
	public GongShiHeaderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public GongShiHeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public GongShiHeaderView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) Global.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.gongshi_headerview,this,true);
		header_title=(TextView) this.findViewById(R.id.header_title);
		text_right=(TextView) this.findViewById(R.id.text_right);
		text_right.setOnClickListener(this);
	}
	public void setTile(String title){
		header_title.setText(title);
	}
	public void setRight(String right){
		text_right.setText(right);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==text_right){
			if(mListener!=null){
				mListener.btnRightClick();
			}
		}
	}
	
	public void setBtnClickListener(BtnClickListener mListener){
		this.mListener = mListener;
	}
	public static abstract class BtnClickListener{
		//右键的点击
		public void btnRightClick(){};
	}
}
