package com.DCHZ.TYLINCN.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;


public class MyInfoItemView extends RelativeLayout {
	private int mType;
	private TextView txtTitle;
	private View viewLine;
	
	public static final int TYPE_XIANMU = 1;	//项目查询
	public static final int TYPE_HETONG = 2;		//合同查询
	public static final int TYPE_SHOUKUAN = 3;	//收款查询
	public static final int TYPE_KEHU= 4;		//客户信息查询
	public static final int TYPE_YUANGONG = 5;	//员工信息查询
	public static final int TYPE_FAPAIO= 6;	//发票查询
	public static final int TYPE_XIANMU_BEIAN = 7;	//项目备案查询


	public MyInfoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public MyInfoItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public MyInfoItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init(){
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.myinfo_item_layout, this, true);
		
		this.txtTitle = (TextView) this.findViewById(R.id.txt_title);
		this.viewLine = this.findViewById(R.id.viewline);
	}

	public int getType(){
		return this.mType;
	}
	
	public void updateType(int mType){
		this.mType = mType;
		String str = "";
		Drawable d = null;
		switch(this.mType){
			case TYPE_XIANMU:
				str = "项目查询";
				break;
			case TYPE_HETONG:
				str = "合同查询";
				break;
			case TYPE_SHOUKUAN:
				str = "收款查询";
				break;
			case TYPE_KEHU:
				str = "客户信息查询";
				break;
			case TYPE_YUANGONG:
				str = "项目查询";
				break;
			case TYPE_FAPAIO:
				str = "项目查询";
				break;
			case TYPE_XIANMU_BEIAN:
				str="项目备案查询";
		}
		
		if(!TextUtils.isEmpty(str)){
			txtTitle.setText(str);
		}
	}

	public void setIsBottom(boolean isBottom){
		if(isBottom){
			LayoutParams llp = (LayoutParams) this.viewLine.getLayoutParams();
			llp.leftMargin = 0;
			this.viewLine.setLayoutParams(llp);
		}else{
			int marginLeft = (int) getResources().getDimension(R.dimen.common_margin_left);
			LayoutParams llp = (LayoutParams) this.viewLine.getLayoutParams();
			llp.leftMargin = marginLeft;
			this.viewLine.setLayoutParams(llp);
		}
	}
}
