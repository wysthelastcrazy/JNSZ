package com.DCHZ.TYLINCN.component;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.entity.PFuJianEntity;
import com.DCHZ.TYLINCN.listener.IWordOpenListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class ListOpenWordView extends RelativeLayout implements OnClickListener{
	private TextView text_desp;
	private TextView text_open;
	private IWordOpenListener mListener;
	private PFuJianEntity entity;
	public ListOpenWordView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListOpenWordView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListOpenWordView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_open_word, this, true);
		text_desp=(TextView) this.findViewById(R.id.text_desp);
		text_open=(TextView) this.findViewById(R.id.text_open);
		text_open.setOnClickListener(this);
	}
	
	public void setData(PFuJianEntity entity){
		this.entity=entity;
		text_desp.setText(entity.FJName);
	}
	public void setOpenListener(IWordOpenListener mListener){
		this.mListener=mListener;
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if(view==text_open){
				mListener.openListener(entity.FJName,entity.FJPath);
			}
		}
	}

}
