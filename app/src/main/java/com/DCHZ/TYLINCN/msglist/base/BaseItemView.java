package com.DCHZ.TYLINCN.msglist.base;

import android.content.Context;
import android.widget.RelativeLayout;

/***
 * 所有的列表ItemView的基类
 */
public abstract class BaseItemView<T> extends RelativeLayout{
	protected final String TAG = getClass().getSimpleName();
	protected T t;
	protected int pos;
	protected boolean isSelected;
	
	public BaseItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		onInflate();
	}

	public abstract void setMsg(T t);
	public abstract T getMsg();
	public abstract void onInflate();
	
	public void setPos(int pos){
		this.pos = pos;
	}
	public void setIsSelected(boolean isSelected){
		this.isSelected=isSelected;
	}
}
