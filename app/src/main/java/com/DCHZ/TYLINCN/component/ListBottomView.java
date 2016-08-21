package com.DCHZ.TYLINCN.component;


import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.listener.IDaiBanClickListener;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class ListBottomView extends RelativeLayout implements OnClickListener{
	private EditText edit_opinion;
	private LinearLayout layout_type;
	private LinearLayout layout_next;
	private Button btn_do;
	private IDaiBanClickListener mListener;
	private TextView text_type;
	private TextView text_next;
	public ListBottomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListBottomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_bottom_item, this, true);
		edit_opinion=(EditText) this.findViewById(R.id.edit_opinion);
		layout_type=(LinearLayout) this.findViewById(R.id.layout_type);
		layout_type.setOnClickListener(this);
		layout_next=(LinearLayout) this.findViewById(R.id.layout_next);
		layout_next.setOnClickListener(this);
		btn_do=(Button) this.findViewById(R.id.btn_do);
		btn_do.setOnClickListener(this);
		
		text_type=(TextView) this.findViewById(R.id.text_type);
		text_next=(TextView) this.findViewById(R.id.text_next);
	}
	
	public void setType(String type){
		text_type.setText(type);
	}
	public void setname(String name){
		text_next.setText(name);
	}
	public String getOpinion(){
		return edit_opinion.getText().toString();
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if(view==layout_type){
				mListener.typeClickListener();
			}else if(view==layout_next){
				mListener.nextClickListener();
			}else if(view==btn_do){
				if(TextUtils.isEmpty(text_next.getText().toString())||"下一环节".endsWith(text_next.getText().toString())){
					mListener.noNextListener();
				}else{
					mListener.btnClickListener();
				}
			}
		}
	}
	
	public void setClickListener(IDaiBanClickListener mListener){
		this.mListener=mListener;
	}
}
